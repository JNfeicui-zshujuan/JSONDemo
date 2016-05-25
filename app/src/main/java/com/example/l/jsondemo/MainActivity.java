package com.example.l.jsondemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int MESSAGE = 0;
    Button mBtnC;
    TextView mTvShow;
    TextView mTvShow1;
    TextView mTvShowClass;
    String fJsonStr;
    private static final String TAG = "MainActivity";
    private static final int LOAD = 1;
    private List<Object> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvShow = (TextView) findViewById(R.id.result);
        mTvShow1 = (TextView) findViewById(R.id.tv_list);
        mTvShowClass = (TextView) findViewById(R.id.tv_class);
    }

    public void onInter() throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        final String HTTP = "http://192.168.1.147:8080/index2.jsp";
        Uri builtUri = Uri.parse(HTTP).buildUpon().build();
        Log.d(TAG, "uri的地址::: " + builtUri);
        URL url = new URL(builtUri.toString());
        Log.d(TAG, "url: " + url);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
//        urlConnection.connect();

        //输入流获取请求的内容
        InputStream inputStream = urlConnection.getInputStream();
        StringBuffer buffer = new StringBuffer();
        if (inputStream == null) {
            // Nothing to do.
            return;
        }
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
            // But it does make debugging a *lot* easier if you print out the completed
            // buffer for debugging.
            buffer.append(line + "\n");
        }
        if (buffer.length() == 0) {
            // Stream was empty.  No point in parsing.
            return;
        }
        fJsonStr = buffer.toString();
        Log.d(TAG, "forecastJsonStr: " + fJsonStr);
    }

    public void myclick(View view) throws Exception {
        new Thread() {
            @Override
            public void run() {
                try {
                    onInter();
                    handler.sendEmptyMessage(LOAD);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD:
                    try {
                        param(fJsonStr);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    public void param(String json) throws JSONException {
        list = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(fJsonStr);
            JSONArray jsonArray = jsonObject.getJSONArray("students");
            String classname = jsonObject.getString("classname");
            Log.d(TAG, "班级   " + classname);
            mTvShowClass.setText(classname);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                final String name = jsonObject1.getString("name");
                int age = jsonObject1.getInt("age");
                Log.d(TAG, "名字 " + name + "   年龄  " + age);
                list.add(name);
                list.add(age);
                Log.d(TAG, "param: " + list);
            }
            mTvShow.setText(list.get(0).toString() + "    " + list.get(1).toString());
            mTvShow1.setText(list.get(2).toString() + "   " + list.get(3).toString());
        } catch (JSONException e) {
            throw e;
        }
    }
}