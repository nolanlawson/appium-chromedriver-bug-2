Appium test case
==========

Simple WebView-backed Android app to demo Appium bugs.

Steps to reproduce
---------

Make sure an Android device is connected via USB. Then do:

```bash
git clone https://github.com/nolanlawson/appium-chromedriver-bug-2.git
cd appium-chromedriver-bug-2
./gradlew assembleDebug
cd appium
npm install
npm test
```
