"use strict";

var wd = require("wd");
var chai = require("chai");
var chaiAsPromised = require("chai-as-promised");

chai.use(chaiAsPromised);
chai.should();
chaiAsPromised.transferPromiseness = wd.transferPromiseness;

var desired = {
	"appium-version": "1.0",
	platformName: "Android",
	deviceName: "foobarbaz",
	app: require('path').resolve("../app/build/outputs/apk/app-debug.apk"),
	"app-package": "webview.com.example.nlawson.webview",
	"app-activity": "webview.com.example.nlawson.webview.MyActivity"
};

var browser = wd.promiseChainRemote("0.0.0.0", 4723);

module.exports = {
  browser: browser,
  desired: desired
};
