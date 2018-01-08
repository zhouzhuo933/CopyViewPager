package com.zhouzhuo.customview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_one,btn_two,btn_three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_one = (Button) findViewById(R.id.btn_one);
        btn_one.setOnClickListener(this);
        btn_two = (Button) findViewById(R.id.btn_two);
        btn_two.setOnClickListener(this);
        btn_three = (Button) findViewById(R.id.btn_three);
        btn_three.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_one:
                Intent intent = new Intent(MainActivity.this,TestActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_two:
                intent = new Intent(MainActivity.this,DemoActivity_1.class);
                startActivity(intent);
                break;
            case R.id.btn_three:
                intent = new Intent(MainActivity.this,DemoActivity_2.class);
                startActivity(intent);
                break;
        }

    }
}
