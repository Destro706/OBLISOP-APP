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

public class Termine extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View termineLayout = inflater.inflate(R.layout.termine, container, false);

        if (!isNetworkAvialable(getActivity())) {
            Toast.makeText(getActivity(), "Inhalte konnten nicht geladen werden\nLeider keine Internetverbindung", Toast.LENGTH_SHORT).show();
        } else {
            WebView termineOblisop = (WebView) termineLayout.findViewById(R.id.webTermine);
            termineOblisop.getSettings().setJavaScriptEnabled(false);
            termineOblisop.loadUrl("http://www.oblisop.de/Termine4.php");

            String username = "username = " + LoginActivity.getUsername1();
            CookieManager.getInstance().setCookie("http://www.oblisop.de/Termine4.php", username);
            termineOblisop.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }
            });
        }
        return termineLayout;

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


