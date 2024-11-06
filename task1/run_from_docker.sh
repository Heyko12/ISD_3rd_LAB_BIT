#!/bin/bash
cd ISD_3rd_LAB_BIT/task1/Consumer/
nohup java -jar Consumer.jar &
sleep 1
cd ../Producer/
nohup java -jar Producer.jar &
sleep 20
cd ../Consumer/
cat db.txt