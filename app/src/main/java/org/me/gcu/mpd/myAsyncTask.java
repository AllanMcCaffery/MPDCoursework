package org.me.gcu.mpd;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static android.content.ContentValues.TAG;


public class myAsyncTask extends AsyncTask<String, Void, String[]> {

    private GetXMLData mainActivity;

    interface GetXMLData {
        void getXMLData(String[] url);
    }


    public myAsyncTask(GetXMLData mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    //Runs in a separate thread. Gets XML file from URL.
    protected String[] doInBackground(String... params) {

        String xmlIncidents;
        String xmlCurrentRoadworks;
        String xmlPlannedRoadworks;

        xmlIncidents = getData(params[0]);
        xmlCurrentRoadworks = getData(params[1]);
        xmlPlannedRoadworks = getData(params[2]);

        String xmlData[] = {xmlIncidents, xmlCurrentRoadworks, xmlPlannedRoadworks};

        return xmlData;
    }


    @Override
    //Runs on main UI thread
    protected void onPostExecute(String[] s) {

        if(null != mainActivity) {
            mainActivity.getXMLData(s);
        }

    }

    private String getData(String url) {

        String result = "";

        try {
            String inputLine = "";
            URL aUrl = new URL(url);
            URLConnection yc = aUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            while ((inputLine = in.readLine()) != null) {
                result = result + inputLine;
                //Log.e("MyTag", inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            Log.e(TAG, "getData: URL error " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "IO Exception " + e.getMessage());
        }
        return result;
    }
}

