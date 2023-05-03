package com.example.mcdonnao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;

import java.util.ArrayList;

public class order_detail_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_page);
        //機器人
        RobotCallback robotCallback=new RobotCallback();
        RobotAPI robotAPI=new RobotAPI(getApplicationContext(),robotCallback);
        //解決陣列清空的問題，所以另外放到全域變數
        GlobalVariable gv = (GlobalVariable)getApplicationContext();

        //取得要放套餐名字跟價錢的textview ID
        TextView view_which_set=findViewById(R.id.textView7);
        TextView view_price_set=findViewById(R.id.textView15);
        //把Extras的值接進來
        String which_set=getIntent().getExtras().getString("which_set",null);
        Integer price_set=getIntent().getExtras().getInt("price_set",0);
        //設定文字
        view_which_set.setText(which_set);
        view_price_set.setText("$"+String.valueOf(price_set));

        //取得checkbox按鈕id
        CheckBox chk_drink=findViewById(R.id.checkBox);
        CheckBox chk_french_fries=findViewById(R.id.checkBox2);
        CheckBox chk_cheese=findViewById(R.id.checkBox3);
        //哪個選項按鈕按下就疊加價錢並更新label
//        if (chk_drink.isChecked()){
//            price_set+=5;
//            view_price_set.setText("$"+String.valueOf(price_set));
//        }
//        else if(chk_french_fries.isChecked()){
//            price_set+=10;
//            view_price_set.setText("$"+String.valueOf(price_set));
//        }
//        else if(chk_cheese.isChecked()) {
//            price_set += 3;
//            view_price_set.setText("$" + String.valueOf(price_set));
//        }

        //取得取消按鈕id
        Button btn_no = findViewById(R.id.button6);
        //取得確定按鈕id
        Button btn_yes=findViewById(R.id.button9);
        Intent intent = new Intent(this,order_main_page.class);

        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("不要"+which_set+"?");
                robotAPI.robot.speak("取消"+which_set);
                //startActivity(intent);
                //可停止可不停止
                finish();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果點確定按鈕就新增到購物車裡
                //Extra類似key-value傳到order_main_page
                gv.addFood(which_set);
                gv.addPrice(price_set);
//                intent.putExtra("ok_set",which_set);
                System.out.println(which_set+"放入購物車");
                robotAPI.robot.speak(which_set+"放入購物車");
                System.out.println("現在總計: $"+gv.getPrice());
                robotAPI.robot.speak("現在總計"+gv.getPrice());
                startActivity(intent);
                //可停止可不停止
                finish();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("關閉detail_page");
    }
}