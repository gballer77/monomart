#!/usr/bin/env bash

SERVER_PID=$(lsof -t -i:8080)

if [ -n "$SERVER_PID" ]
then
  kill -9 "$SERVER_PID"
  echo "Stopping server..."
else
  echo "No server running. Proceeding..."
fi
