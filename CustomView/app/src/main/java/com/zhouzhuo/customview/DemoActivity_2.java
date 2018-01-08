package com.zhouzhuo.customview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouzhuo.customview.ui.HorizontalScrollViewEx2;
import com.zhouzhuo.customview.ui.ListViewEx;
import com.zhouzhuo.customview.utils.MyUtils;

import java.util.ArrayList;

/**
 * Created by zhouzhuo on 2018/1/5.
 */

public class DemoActivity_2 extends Activity {
    private HorizontalScrollViewEx2 horizontalScrollViewEx2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_2);
        initView();
    }

    private void initView() {
        horizontalScrollViewEx2 = findViewById(R.id.container);
        LayoutInflater inflate = getLayoutInflater();
        int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i=0;i<3;i++){
            ViewGroup layout = (ViewGroup) inflate.inflate(R.layout.content_layout2,
                    horizontalScrollViewEx2,false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.title);
            textView.setText("page  "+(i+1));
            layout.setBackgroundColor(Color.rgb(255/(i+1),255/(i+1),0));
            createList(layout);
            horizontalScrollViewEx2.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListViewEx listView = layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<>();
        for (int i=0;i<50;i++){
            datas.add("name "+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,R.layout.content_list_item,R.id.name,datas
        );
        listView.setAdapter(adapter);
        listView.setHorizontalScrollViewEx2(horizontalScrollViewEx2);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DemoActivity_2.this,"click item:"+position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("zhouzhuo","DemoActivity_2 dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("zhouzhuo","DemoActivity_2 onTouchEvent");
        return super.onTouchEvent(event);
    }
}
