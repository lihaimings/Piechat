package com.example.ljli.piechatview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  MyPieChat myPieChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPieChat=(MyPieChat)findViewById(R.id.mPieView);
        ArrayList<PieData> list=new ArrayList<PieData>();
        PieData data=new PieData("苹果",1);
        PieData data2=new PieData("香蕉",1);
        PieData data3=new PieData("雪梨",1);
        PieData data4=new PieData("核桃",1);
        PieData data5=new PieData("核桃",5);
        PieData data6=new PieData("核桃",5);
        list.add(data);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        list.add(data6);
        list.add(data6);
        myPieChat.setData(list);
    }
}
