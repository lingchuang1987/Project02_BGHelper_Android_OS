package lingchuang.myprojwork_01_introbbg;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by Ray.C on 2015/9/9.
 */
public class Activity_PTT extends Activity {
    WebView webView;
    String uri="https://www.ptt.cc/bbs/BoardGame/index.html";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.websurf_pttbg);
        webView=(WebView)findViewById(R.id.webView2);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog = ProgressDialog.show(Activity_PTT.this, "正在前往PTT 桌遊版", "Loading...", true);
                Thread thread = new Thread();
                try {
                    thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(Activity_PTT.this, e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });
        webView.loadUrl(uri);


    }
}
