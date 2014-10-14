'use strict';

var tests = [
  './test-webview',
  //'./test-basic'
];

var promise = require('bluebird').resolve();

tests.forEach(function (testName) {
  promise = promise.then(function () {
    return test(testName);
  });
});
promise.done();

function test(testName) {
  console.log('\nStarting test: ' + testName + "\n");
  var setup = require('./setup');
  var browser = setup.browser;
  var desired = setup.desired;
  
  var driver = browser.init(desired).setImplicitWaitTimeout(3000);
  
  return driver.then(function () {
    return browser;
  }).then(function (browser) {
    return require(testName)(browser, driver);
  }).fin(function () {
    console.log('\nDone with test: ' + testName + "\n");
  	return browser.quit();
  });
}




