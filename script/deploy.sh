#!/bin/sh

set -e

sudo ./gradlew clean build bootJar -Djasypt.encryptor.password=${BETTERDAY_JASYPT_PASSWORD}

sudo scp -i ${BETTERDAY_PEM_PATH} ./api/external-api/build/libs/external-api.jar ${BETTERDAY_EC2_USERNAME}@${BETTERDAY_EC2_HOST}:/home/ubuntu
