package webview.com.example.nlawson.webview;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;


public class MyActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my);

    WebView webView = (WebView) findViewById(R.id.web_view);
    webView.getSettings().setJavaScriptEnabled(true);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      WebView.setWebContentsDebuggingEnabled(true); // for appium
    }

    String html = "<html>" +
        "<body>" +
        "<h1>Yo, this is like the simplest web page ever</h1>" +
        "<pre style='width: 100%' id='display'></pre>" +
        "<textarea style='width: 100%; height: 300px;' class='my-text-area' placeholder='Enter some text here'></textarea>" +
        "<script>" +
        "document.addEventListener('DOMContentLoaded', function () {" +
        "var count = 0; " +
        "setInterval(function () {" +
        "document.getElementById('display').innerHTML = 'Running for ' + (++count) + ' seconds';" +
        "}, 1000);" +
        "});" +
        "</script>" +
        "</body>" +
        "</html>";

    webView.loadDataWithBaseURL("http://fake.com", html, "text/html", "utf-8", null);
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.my, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
