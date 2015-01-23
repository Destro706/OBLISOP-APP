package de.oblisop.destro706;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Register extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (!isNetworkAvialable(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Leider keine Internetverbindung\nBitte später nochmal versuchen", Toast.LENGTH_SHORT).show();
            Register.this.finish();
        } else {
            WebView registerOblisop = (WebView) findViewById(R.id.registerWebView);
            registerOblisop.getSettings().setJavaScriptEnabled(false);
            registerOblisop.getSettings().setBuiltInZoomControls(true);
            registerOblisop.getSettings().setDisplayZoomControls(false);
            registerOblisop.loadUrl("http://www.oblisop.de/register5.php");

            registerOblisop.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url.contains("/login.php")) {
                        Register.this.finish();
                    } else
                        view.loadUrl(url);
                    return true;
                }
            });
        }

    }
    private static boolean isNetworkAvialable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if (netInfos != null)
                if (netInfos.isConnected())
                    return true;
        }
        return false;
    }

}
