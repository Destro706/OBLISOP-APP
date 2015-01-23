package de.oblisop.destro706;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Liste extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View listeLayout = inflater.inflate(R.layout.liste, container, false);
        if (!isNetworkAvialable(getActivity())) {
            Toast.makeText(getActivity(), "Inhalte konnten nicht geladen werden\nLeider keine Internetverbindung", Toast.LENGTH_SHORT).show();
        } else {
            WebView listeOblisop = (WebView) listeLayout.findViewById(R.id.webListe);
            listeOblisop.getSettings().setJavaScriptEnabled(false);
            listeOblisop.getSettings().setBuiltInZoomControls(true);
            listeOblisop.getSettings().setDisplayZoomControls(false);
            listeOblisop.loadUrl("http://www.oblisop.de/Liste3.php");
            String username = "username = " + LoginActivity.getUsername1();
            CookieManager.getInstance().setCookie("http://www.oblisop.de/Liste3.php", username);

            listeOblisop.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            listeOblisop.getSettings().setLoadWithOverviewMode(true);
            listeOblisop.getSettings().setUseWideViewPort(true);
        }
        return listeLayout;

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

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }





}
