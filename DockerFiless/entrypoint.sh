#!/bin/bash

java -jar /var/code/assignment-1.0-SNAPSHOT.jar server  /var/code/dev.yml &

if [ $? -eq 0 ]
then
  echo "No system error while making container running"
  sleep infinity
fi