package com.example.l.jsondemo.Engine;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by zhengshujuan on 2016/5/27.
 */
public class ProgressBarAsyncTask extends AsyncTask<Integer,Integer,String >{

    public static final String TAG="PrograssBarA*****";
    private TextView textView;
    private ProgressBar proBar;
    //构造方法赋值
    public ProgressBarAsyncTask(TextView mTV, ProgressBar mPB) {
        super();
        this.textView=mTV;
        this.proBar=mPB;

    }

//    public void execute(int i) {
//        doInBackground();
//
//
//    }
//通过调用publishProgress方法触发onProgressUpdate对UI进行操作.
    @Override
    protected String doInBackground(Integer... params) {
        NetOperator netOperator=new NetOperator();
        int i=0;
        for ( i=10;i<100;i+=10){
        netOperator.operator();
        publishProgress(i);
            Log.d(TAG, "doInBackground: "+i);
    }
    return i+params[0].intValue()+" ";

}
    //用于网络操作的内部类
    private class NetOperator {
        private void operator(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "operator: 网络操作");
        }

    }
    @Override
    protected void onPreExecute() {

        textView.setText("开始执行异步线程");
        Log.d(TAG, "onPreExecute: 开始执行");
    }
/*
* String参数对应AsyncTask中的第三个参数,也就是接收doInBackground的返回值
* 该方法在doInBackground方法执行结束之后运行,运行在UI线程中可以对UI控件进行设置
* */
    @Override
    protected void onPostExecute(String result) {
        textView.setText("异步操作执行结束"+result);
        Log.d(TAG, "onPostExecute: 结束执行");
    }
//该方法在UI线程中执行.可对UI控件进行操作.
    @Override
    protected void onProgressUpdate(Integer... values) {

        int vlaue=values[0];
        proBar.setProgress(vlaue);
        Log.d(TAG, "onProgressUpdate: 触发"+vlaue);
    }
}
