package com.example.mcdonnao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //機器人
        RobotCallback robotCallback=new RobotCallback();
        RobotAPI robotAPI=new RobotAPI(getApplicationContext(),robotCallback);

        //以id獲取元件
        Button start_btn=findViewById(R.id.button);
        //按下start跳轉到orderpage
        //Intent把畫面A(this)跟B(orderpage)接起來
        Intent intent=new Intent(this,order_main_page.class);
        //設定事件監聽者
        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("開始點餐");
                robotAPI.robot.speak("開始點餐");
                startActivity(intent);
            }
        });
    }
}