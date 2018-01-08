package com.zhouzhuo.customview;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity implements View.OnClickListener,View.OnLongClickListener{

    private static final String TAG = "TestActivity";

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 1030;
    private static final int DELAYED_TIME = 33;

    private Button mButton1;
    private View mButton2;
    private int mCount = 0;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_SCROLL_TO:
                    mCount++;
                    if(mCount<=FRAME_COUNT){
                        float fraction = mCount/(float)FRAME_COUNT;
                        int scrollX = (int)fraction*10;
                        Log.d("zhouzhuo","ScrollX:"+scrollX);
                        mButton1.scrollTo(-mCount,0);
                        mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO,DELAYED_TIME);
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initView();
    }

    private void initView() {
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setOnClickListener(this);
        mButton2 = findViewById(R.id.button2);
        mButton2.setOnLongClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            Log.d("zhouzhuo","button1.left="+mButton1.getLeft());
            Log.d("zhouzhuo","button1.x="+mButton1.getX());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                Toast.makeText(TestActivity.this,"Button1 click",Toast.LENGTH_SHORT).show();
                mHandler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAYED_TIME);
                break;
        }

    }

    @Override
    public boolean onLongClick(View v) {
        Toast.makeText(TestActivity.this,"long click",Toast.LENGTH_SHORT).show();
        return true;
    }
}
