package com.zhouzhuo.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouzhuo.customview.ui.HorizontalScrollViewEx;
import com.zhouzhuo.customview.utils.MyUtils;

import java.util.ArrayList;

public class DemoActivity_1 extends AppCompatActivity {
    private final String TAG = "DemoActivity_1";
    private HorizontalScrollViewEx scrollViewEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_1);
        initView();
    }

    private void initView() {
        scrollViewEx = (HorizontalScrollViewEx) findViewById(R.id.container);
        LayoutInflater layoutInflute = getLayoutInflater();
        int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i=0;i<3;i++){
            ViewGroup layout = (ViewGroup) layoutInflute.inflate(R.layout.content_layout,scrollViewEx,false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = layout.findViewById(R.id.tv_title);
            textView.setText("page"+(i+1));
            layout.setBackgroundColor(Color.rgb(255/(i+1),255/(i+1),0));
            createList(layout);
            scrollViewEx.addView(layout);
        }
    }

    private void createList(ViewGroup layout) {
        ListView listView = layout.findViewById(R.id.list);
        ArrayList<String> data = new ArrayList<>();
        for (int i=0;i<50;i++){
            data.add("name "+i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,R.layout.content_list_item,R.id.name,data
        );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DemoActivity_1.this,"click item:"+position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
