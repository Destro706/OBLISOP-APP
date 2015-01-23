package de.oblisop.destro706;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Galerie extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View galerieLayout = inflater.inflate(R.layout.galerie, container, false);
        if (!isNetworkAvialable(getActivity())) {
            Toast.makeText(getActivity(), "Inhalte konnten nicht geladen werden\nLeider keine Internetverbindung", Toast.LENGTH_SHORT).show();
        } else {
            WebView galerieOblisop = (WebView) galerieLayout.findViewById(R.id.webGalerie);
            galerieOblisop.getSettings().setJavaScriptEnabled(true);
            galerieOblisop.getSettings().setBuiltInZoomControls(false);
            galerieOblisop.getSettings().setDisplayZoomControls(false);
            galerieOblisop.loadUrl("http://www.oblisop.de/Galerie3.php");

            galerieOblisop.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
            galerieOblisop.getSettings().setLoadWithOverviewMode(true);
            galerieOblisop.getSettings().setUseWideViewPort(true);
        }
        return galerieLayout;

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

