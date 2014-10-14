#!/bin/bash

./node_modules/.bin/appium &
export APPIUM_PID=$!

echo "Waiting for Appium to settle..."
sleep 5
echo "Appium has settled."

npm run test-appium

EXIT_STATUS=$?
if [[ ! -z $APPIUM_PID ]]; then
  echo "Shutting down Appium"
  kill $APPIUM_PID
fi

echo "Exiting with status: $EXIT_STATUS"
exit $EXIT_STATUS