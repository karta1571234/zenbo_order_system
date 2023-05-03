package com.example.mcdonnao;

import android.app.Application;

public class GlobalVariable extends Application {
    private String food = "";
    private int price = 0, count=0;

    public void doPay(){
        this.food = "";
        this.price= 0;
        this.count=0;
    }
    //修改 變數値
    public void addFood(String food){
        this.food += food + "\n";
    }
    //取得 變數值
    public String getFood() {
        return food;
    }

    public void addPrice(int price) {
        this.price += price;
        this.count+=1;
    }
    public int getPrice(){
        return price;
    }

    public int getCount() {
        return count;
    }
}
