import { SQSClient, SendMessageCommand } from "@aws-sdk/client-sqs";
import Replicate from "replicate";

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
      const recordBody = JSON.parse(record.body);
      const message = JSON.parse(recordBody.Message);
      
      const prompt = message.prompt;
      const diaryUid = message.diaryUid;

      // 알람 음악 prediction 생성
      // 문서 주소 : https://replicate.com/meta/musicgen/api/learn-more

      const input = {
        top_k: 250,
        top_p: 0,
        prompt: prompt,
        duration: 15,
        temperature: 1,
        continuation: false,
        model_version: "melody-large",
        output_format: "mp3",
        continuation_start: 0,
        multi_band_diffusion: false,
        normalization_strategy: "peak",
        classifier_free_guidance: 3
      };

      const prediction = await replicate.predictions.create(
        {
          version: "671ac645ce5e552cc63a54a2bbff63fcf798043055d2dac5fc9e36a837eedcfb",
          input
        }
      );
                  
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
