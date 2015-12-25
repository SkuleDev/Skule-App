package com.skuledev.skuleapp.skule_app.Resources;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AsyncParseJson extends AsyncTask<String, String, String> {

    int length = 0;

    final String TAG = "AsyncTaskParseJson.java";

    static String[] uri = new String[30];
    static String[] imageURL = new String[50];

    // set your json string url here
    String Url = "https://api.edamam.com/search?q=&app_id=nope&app_key=notgivingyou";

    // JSONArray
    JSONArray dataJsonArr = null;

    @Override
    protected void onPreExecute() {
        //Do stuff here before execution of the asynchronous code below
    }

    @Override
    protected String doInBackground(String... arg0) {

        try {

            // instantiate our json parser
            JsonParser jParser = new JsonParser();

            // get json string from url
            JSONObject json = jParser.getJSONFromUrl(Url);

            // get the array of name hits (hits should be changed depending on the json)
            dataJsonArr = json.getJSONArray("hits");
            Log.d("length: ", "length: " + dataJsonArr.length());

            length = dataJsonArr.length();

            // loop through all elements
            for (int i = 0; i < dataJsonArr.length(); i++) {

                JSONObject c = dataJsonArr.getJSONObject(i);

                uri[i] = c.getJSONObject("recipe").getString("label");
                imageURL[i] = c.getJSONObject("recipe").getString("image");

                // show the values in our logcat
                Log.d("uri", uri[i]);
                Log.d("Image URL", imageURL[i]);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String strFromDoInBg){
        //This is a UI thread, make changes to ui here like loading elements
        // to the listview or anything to be done after the json has been parsed.
    }
}