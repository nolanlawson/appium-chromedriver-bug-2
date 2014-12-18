'use strict';

var packageName = 'webview.com.example.nlawson.webview';

function test(browser, driver) {
  return browser
    .sleep(5000)
    .elementById('android:id/button1')
    .click()
    .sleep(10000)
    .elementById(packageName + ':id/web_view').should.eventually.exist
    .sleep(5000)
    .elementById(packageName + ':id/web_view')
    .click()
    .sleep(5000)
    .contexts()
    .then(function (ctxs) {
      console.log(ctxs);
      var newContext = ctxs.filter(function (ctx) {
         return /WEBVIEW.*nlawson/.test(ctx);
      })[0];
      return driver.context(newContext);
    })
    .elementByCssSelector('.my-textarea')
    .sendKeys('yo I am totally entering some text')
    .sleep(5000)
    .contexts()
    .then(function (ctxs) {
      return ctxs.filter(function (ctx) {
        return ctx.indexOf('NATIVE') !== -1;
      })[0];
    })
    ;
}

module.exports = function (browser, driver) {
  return test(browser, driver);
};
