package de.oblisop.destro706;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Willkommen extends Fragment {

    private WebView playerOfTheMonth;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View wilkommenLayout = inflater.inflate(R.layout.willkommen, container, false);
        if(!isNetworkAvialable(getActivity())) {
            Toast.makeText(getActivity(), "Inhalte konnten nicht geladen werden\nLeider keine Internetverbindung", Toast.LENGTH_SHORT).show();
        } else {
            playerOfTheMonth = (WebView) wilkommenLayout.findViewById(R.id.webViewPlayerOfTheMonth);
            playerOfTheMonth.getSettings().setJavaScriptEnabled(false);
            new LoadData().execute();
//            playerOfTheMonth.setInitialScale(400);
        }
        return wilkommenLayout;

    }

    private static boolean isNetworkAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    private class  LoadData extends AsyncTask<Void,Void,Void>
    {
        String primeDiv="artikel";
        String html;
        Document doc = null;

        @Override
        protected Void doInBackground(Void... params) {

            try {

                doc = Jsoup.connect("http://www.oblisop.de/playerOfTheMonth.html").timeout(100000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Elements alldivs=doc.select("div");
            ArrayList<String> list=new ArrayList<>();

            for(org.jsoup.nodes.Element e: alldivs)
            {
                if(!e.id().equals(""))
                    list.add(e.id());
            }
            //removing all <div> without "div2"
            for(int i=0;i<list.size();i++)
            {
                if(!list.get(i).equals(primeDiv))
                    doc.select("div[id="+list.get(i)+"]").remove();
            }

            html=alldivs.outerHtml();

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            playerOfTheMonth.loadDataWithBaseURL(null,doc.html(),
                    "text/html", "utf-8", null);


        }

    }

}
