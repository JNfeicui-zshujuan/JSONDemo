package com.example.l.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.l.jsondemo.Engine.ProgressBarAsyncTask;

public class AsyncTaskActivity extends AppCompatActivity {
    public static final String TAG = "AsyncTask*********";
    private TextView mTV;
    private ProgressBar mPB;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        mTV= (TextView) findViewById(R.id.tv_01);
        mPB= (ProgressBar) findViewById(R.id.pb_01);
        mBtn= (Button) findViewById(R.id.btn_01);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressBarAsyncTask asyncTask=new ProgressBarAsyncTask(mTV,mPB);
                asyncTask.execute(1000);
                Log.d(TAG, "你点击了我");

            }
        });
    }
}
