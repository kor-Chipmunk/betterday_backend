import { SQSClient, SendMessageCommand } from "@aws-sdk/client-sqs";
import Replicate from "replicate";
import axios from 'axios';

const replicate = new Replicate({
  auth: process.env.REPLICATE_API_TOKEN,
});

const sqsClient = new SQSClient({ region: "ap-northeast-2" });

const BACKEND_URL = process.env.BACKEND_URL;
const CHECK_QUEUE_URL = process.env.CHECK_QUEUE_URL;

export const handler = async (event) => {

	console.log(event);

    try {
		for (const record of event.Records) {
            const messageBody = JSON.parse(record.body);
            
            const prediction = messageBody.prediction;
            const diaryUid = messageBody.diaryUid;
           
			const latest = await replicate.predictions.get(prediction.id);

			console.log(`Latest prediction: ${JSON.stringify(latest)}`);

			if (latest.status === 'starting' || latest.status === 'processing') {			
				// 체크 스케쥴링 큐에 등록
				const requestBody = {
					prediction,
					diaryUid,
				};
				const params = {
					QueueUrl: `${CHECK_QUEUE_URL}`,
					MessageBody: JSON.stringify(requestBody),
				};

				const data = await sqsClient.send(new SendMessageCommand(params));
				console.log("Success, message sent. MessageID:", data.MessageId);
			}
			else if (latest.status === 'succeeded') {
				// 결과물 음원 주소
				const alarmUrl = latest.output;

				// 백엔드 서버에 알람 URL 등록 콜백
				const response = await axios.post(`${BACKEND_URL}/api/v1/alarms`, {
					diaryUid,
					link: alarmUrl
				});
            
				console.log(`Status: ${response.status}`);
				console.log(`Response data: ${JSON.stringify(response.data)}`);
			}
			else if (latest.status === 'failed') {				
				throw new Error(latest.error);
			}
			else if (latest.status === 'canceled') {
				throw new Error('Prediction was canceled');
			}
			else {
				throw new Error('Unknown status');
			}
        }
        return {
            statusCode: 200,
            body: 'Messages processed successfully'
        };
    } catch (error) {
        console.error('Error processing messages', error);
        return {
            statusCode: 500,
            body: 'Error processing messages'
        };
    }
};
