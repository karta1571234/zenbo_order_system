package com.example.mcdonnao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;

import java.util.ArrayList;

public class order_main_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main_page);
        //機器人
        RobotCallback robotCallback=new RobotCallback();
        RobotAPI robotAPI=new RobotAPI(getApplicationContext(),robotCallback);
        //解決陣列清空的問題，所以另外放到全域變數
        GlobalVariable gv = (GlobalVariable)getApplicationContext();

        //宣告ArrayList用來放新增的餐點,可以隨意更改它的大小
//        ArrayList<String> food_item=new ArrayList<>();
        //把Extra的值接進來
//        String ok_set=getIntent().getStringExtra("ok_set");
//        System.out.println("test"+ok_set);

        String food_item=gv.getFood();
        int price_total=gv.getPrice();
        int count=gv.getCount();

        //取得各個漢堡的button id
        //取得各個漢堡價錢的textview id
        Button btn = findViewById(R.id.button2);
        TextView tv=findViewById(R.id.textView3);
        Button btn1 = findViewById(R.id.button3);
        TextView tv1=findViewById(R.id.textView10);
        Button btn2 = findViewById(R.id.button4);
        TextView tv2=findViewById(R.id.textView11);
        //取得結帳的button id
        Button btn3 = findViewById(R.id.button5);

        //設定按下各個漢堡跳轉到orderdetail畫面
        Intent intent = new Intent(this, order_detail_page.class);
        //設定按下結帳跳轉到pay畫面
        Intent intent1 = new Intent(this,pay_page.class);
        //設定事件監聽者
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //宣告一個string放哪個套餐
                String which_set=btn.getText().toString(); //大麥克套餐
                //宣告一個int放套餐多少錢
                Integer price_set=Integer.valueOf(tv.getText().toString());
                //Extra類似key-value傳出去，不能寫2個所以要傳多值必須搭配bundle，如下
//                intent.putExtra("which_set",which_set);
//                intent.putExtra("price_set",price_set);
                //Extas可傳多值，並搭配Bundle
                Bundle bundle=new Bundle();
                bundle.putString("which_set",which_set);
                bundle.putInt("price_set",price_set);
                intent.putExtras(bundle);
                System.out.println("選取的套餐是:"+which_set);
                robotAPI.robot.speak("選取的套餐是"+which_set);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String which_set=btn1.getText().toString();
                Integer price_set=Integer.valueOf(tv1.getText().toString());
//                intent.putExtra("which_set",which_set);
//                intent.putExtra("price_set",price_set);
                Bundle bundle=new Bundle();
                bundle.putString("which_set",which_set);
                bundle.putInt("price_set",price_set);
                intent.putExtras(bundle);
                System.out.println("選取的套餐是:"+which_set);
                robotAPI.robot.speak("選取的套餐是"+which_set);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String which_set=btn2.getText().toString();
                Integer price_set=Integer.valueOf(tv2.getText().toString());
//                intent.putExtra("which_set",which_set);
//                intent.putExtra("price_set",price_set);
                Bundle bundle=new Bundle();
                bundle.putString("which_set",which_set);
                bundle.putInt("price_set",price_set);
                intent.putExtras(bundle);
                System.out.println("選取的套餐是:"+which_set);
                robotAPI.robot.speak("選取的套餐是"+which_set);
                startActivity(intent);
            }
        });
        //結帳按鈕
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("結帳button");
                robotAPI.robot.speak("去結帳");
                Bundle bundle=new Bundle();
                bundle.putString("food_item",food_item);
                bundle.putInt("price_total",price_total);
                bundle.putInt("count",count);
                intent1.putExtras(bundle);
                startActivity(intent1);
                gv.doPay();
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("點餐中...");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("order_main_page恢復了");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("完成回到Main");
    }
}