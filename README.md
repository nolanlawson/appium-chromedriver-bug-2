Appium test case
==========

Simple WebView-backed Android app to demo Appium bugs.

Steps to reproduce
---------

Make sure an Android device is connected via USB. Then do:

```bash
git clone git@github.com:nolanlawson/appium-android-demo.git
cd appium-android-demo
./gradlew assembleDebug
cd appium
npm install
npm test
```