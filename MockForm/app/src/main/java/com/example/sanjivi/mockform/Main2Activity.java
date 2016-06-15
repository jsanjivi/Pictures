package com.example.sanjivi.mockform;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;

import java.sql.Date;
import java.util.Calendar;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView txt=(TextView)findViewById(R.id.textView4);
        TextView txt1=(TextView)findViewById(R.id.textView5);
        Bundle extras=getIntent().getExtras();
        String name;
        Calendar currentTime=Calendar.getInstance();
        int hour=currentTime.get(Calendar.HOUR_OF_DAY);
        int min=currentTime.get(Calendar.MINUTE);
        int sec=currentTime.get(Calendar.SECOND);
        if(extras!=null){
         name=extras.getString("name1");
         txt.setText("Thank you "+name+" for your response.");
         txt1.setText("Timestamp:"+hour+":"+min+":"+sec);
    }
    }
    public void onClick(View v)
    {finish();
    }
}
