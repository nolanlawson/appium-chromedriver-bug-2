'use strict';

var packageName = 'webview.com.example.nlawson.webview';

function test(browser, driver) {
  return browser
    .sleep(5000)
    .elementByXPath("//android.view.View[1]/android.widget.FrameLayout[2]" +
      "/android.widget.RelativeLayout[1]/android.view.View[1]/android.view.View[3]" + 
      "/android.widget.EditText[1]").sendKeys("entering some text")
    .sleep(10000)
    .elementByXPath('//android.webkit.WebView')
    /*
    .click()
    .sleep(1000)
    .contexts()
    .then(function (ctxs) {
      console.log(ctxs);
      var newContext = ctxs.filter(function (ctx) {
         return /WEBVIEW.*com.example.nlawson/.test(ctx);
      })[0];
      return driver.context(newContext);
    })
    .sleep(1000)
    .elementByCss('.my-text-area')
    .click()
    .sendKeys('This is the text I have successfully typed')
    .sleep(1000)
    .contexts()
    .then(function (ctxs) {
      return ctxs.filter(function (ctx) {
        return ctx.indexOf('NATIVE') !== -1;
      })[0];
    }).sleep(1000)
    */
    ;
}

module.exports = function (browser, driver) {
  return test(browser, driver);
};