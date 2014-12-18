package webview.com.example.nlawson.webview;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class WebViewActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);

    final WebView webView = (WebView) findViewById(R.id.web_view);
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setDatabaseEnabled(true);
    settings.setAllowFileAccess(true);
    settings.setDomStorageEnabled(true);
    settings.setDefaultTextEncodingName("utf-8");
    settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
    settings.setAllowUniversalAccessFromFileURLs(true);

    //Pipe javascript console messages through to logcat
    webView.setWebChromeClient(new WebChromeClient() {
      @Override
      public boolean onConsoleMessage(ConsoleMessage cm) {
        Log.d("Console", cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
        return true;
      }
    });

    webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      WebView.setWebContentsDebuggingEnabled(true); // for appium
    }

    new AsyncTask<Void, Void, Void>(){

      @Override
      protected Void doInBackground(Void... voids) {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return null;
      }

      @Override
      protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        webView.loadUrl("file:///android_asset/layoutengine/index.html");
      }
    }.execute((Void)null);
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

  public class MyJavaScriptInterface {

    @JavascriptInterface
    public String doSomething() {
      return "yo";
    }

    @JavascriptInterface
    public String doSomethingElse() {
      return "hey";
    }

    @JavascriptInterface
    public void sendRequest(String str) {
      Log.d("yo", str);
    }

  }
}
