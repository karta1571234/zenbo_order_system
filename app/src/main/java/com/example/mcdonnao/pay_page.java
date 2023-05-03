package com.example.mcdonnao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;

import java.util.ArrayList;

public class pay_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_page);
        //機器人
        RobotCallback robotCallback=new RobotCallback();
        RobotAPI robotAPI=new RobotAPI(getApplicationContext(),robotCallback);
        //解決陣列清空的問題，所以另外放到全域變數
        GlobalVariable gv = (GlobalVariable)getApplicationContext();

        //把Extras的值接進來
        String pay_set=getIntent().getExtras().getString("food_item",null);
        Integer pay_price=getIntent().getExtras().getInt("price_total",0);
        Integer count=getIntent().getExtras().getInt("count",0);
        //用ID取得你點了啥跟總共多少的textview及總共幾份套餐
        TextView view_list=findViewById(R.id.textView8);
        view_list.setText(pay_set);
        TextView view_total=findViewById(R.id.textView9);
        view_total.setText("$"+String.valueOf(pay_price));
        TextView view_count=findViewById(R.id.textView13);
        view_count.setText(String.valueOf(count)+"份套餐s");

        Intent intent = new Intent(this,MainActivity.class);
        Button btn_ok=findViewById(R.id.button8);

        System.out.println("總共:"+count+"份套餐");
        robotAPI.robot.speak("總共"+count+"份套餐");
        System.out.println("你點了:"+pay_set);
        robotAPI.robot.speak("你點了"+pay_set);
        System.out.println("一共是:"+pay_price);
        robotAPI.robot.speak("一共是"+pay_price);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("完成結帳button");
                robotAPI.robot.speak("完成結帳，謝謝光臨~");
                startActivity(intent);
                finish();
            }
        });
    }
}