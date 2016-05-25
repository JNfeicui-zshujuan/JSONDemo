package com.example.l.jsondemo;

import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.provider.Settings;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by l on 2016/5/23.
 */
public class QueryTask extends AsyncTask<String,Void,String>{
    Context context;
    TextView tv_result;


    public QueryTask(Context context, TextView tv_result) {
        super();
        this.context=context;
        this.tv_result=tv_result;
    }

    @Override
    protected String doInBackground(String... params) {
        String city=params[0];
        
        //List<NameValuePair> headList=new ArrayList<NameValuePair>();




        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        if (result!=null){
            try {
                JSONObject jsonObject=new JSONObject(result);
                int resultCode=jsonObject.getInt("resultcode");
                if (resultCode==200){
                    JSONArray resultJsonArray=jsonObject.getJSONArray(result);
                    JSONObject resultJsonObject=resultJsonArray.getJSONObject(0);
               String output=context.getString(R.string.city) + ": " + resultJsonObject.getString("city") + "\n"
                       + context.getString(R.string.PM2_5) + ": " + resultJsonObject.getString("PM2.5") + "\n"
                       + context.getString(R.string.AQI) + ": " + resultJsonObject.getString("AQI") + "\n"
                       + context.getString(R.string.quality) + ": " + resultJsonObject.getString("quality") + "\n"
                       + context.getString(R.string.PM10) + ": " + resultJsonObject.getString("PM10") + "\n"
                       + context.getString(R.string.CO) + ": " + resultJsonObject.getString("CO") + "\n"
                       + context.getString(R.string.NO2) + ": " + resultJsonObject.getString("NO2") + "\n"
                       + context.getString(R.string.O3) + ": " + resultJsonObject.getString("O3") + "\n"
                       + context.getString(R.string.SO2) + ": " + resultJsonObject.getString("SO2") + "\n"
                       + context.getString(R.string.time) + ": " + resultJsonObject.getString("time") + "\n";
                    tv_result.setText(output);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
