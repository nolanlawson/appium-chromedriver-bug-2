package webview.com.example.nlawson.webview;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_web_view, container, false);


    final WebView webView = (WebView) rootView.findViewById(R.id.web_view);
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
          Thread.sleep(1000);
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

    return rootView;

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
