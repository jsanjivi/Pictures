package com.example.sanjivi.pictures;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.os.Handler;
import android.os.Message;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] tracks={"Footballers","Cricketers"};
    int i,flag=0;
    TextView timerTextView;
    long startTime = 0;
    long startTime1 = 0;
    long startTime2 = 0;
    long remainingTime=0;
    //runs without a timer by reposting this handler at the end of the runnable
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);


            timerTextView.setText(String.format("%02d", seconds));

            timerHandler.postDelayed(this, 1000);
        }
    };
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            if(i==1)
            imageView.setImageResource(R.drawable.zlatan);
            else if(i==2)
            imageView.setImageResource(R.drawable.rooney);
            else if(i==3)
            imageView.setImageResource(R.drawable.neymar);
            else if(i==4)
            imageView.setImageResource(R.drawable.ronaldo);
        }
    };
    Handler handler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            if(i==1)
                imageView.setImageResource(R.drawable.kohli);
            else if(i==2)
                imageView.setImageResource(R.drawable.starc);
            else if(i==3)
                imageView.setImageResource(R.drawable.abd);
            else if(i==4)
                imageView.setImageResource(R.drawable.warner);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.zlatan);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tracks);
        Spinner spinner=(Spinner)findViewById(R.id.spinner); //spinner element
        spinner.setAdapter(adapter); //attaching adapter to spinner
        spinner.setOnItemSelectedListener(this);
        timerTextView = (TextView) findViewById(R.id.textView);
        timerTextView.setText("00");
    }
    int j;
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString(); //on selecting a spinner item
        j=position;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if(j==0)
            imageView.setImageResource(R.drawable.zlatan);
        else if(j==1)
            imageView.setImageResource(R.drawable.kohli);
        timerHandler.removeCallbacks(timerRunnable);
        startTime1 = System.currentTimeMillis();
    }
    public void onNothingSelected(AdapterView<?> arg0)
    {// TODO Auto-generated method stub
    }
    Object mPauseLock=new Object();
    Boolean mPaused=false;
    public void onClick(View v) {
        if (v.getId() == R.id.slideshow)
        {startTime = System.currentTimeMillis();
            timerHandler.postDelayed(timerRunnable, 0);
            remainingTime=0;
            Runnable r1 = new Runnable() {
                @Override
                public void run() {
                 synchronized (mPauseLock){
                     i=1;
                     mPaused=false;

                   }
                    while (i <= 4) {
                         synchronized (mPauseLock)
                                {if(flag==1)
                                {  try {
                                        Thread.sleep(remainingTime);
                                    } catch (InterruptedException e) {
                                        Thread.currentThread().interrupt();
                                    }
                                    flag=0;
                                }
                            }
                        if(j==0)
                        handler.sendEmptyMessage(0);
                        else if(j==1)
                        handler1.sendEmptyMessage(0);
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        i++;
                        synchronized (mPauseLock) {//synchronized is used when two or more threads access the same variables

                            while (mPaused) {
                                try {
                                    mPauseLock.wait();
                                } catch (InterruptedException e) {
                                }
                            }
                        }
                    }
                }
            };
            Thread delay = new Thread(r1);
            delay.start();



    }
        else if(v.getId()==R.id.play){
            startTime2=System.currentTimeMillis();
            startTime=startTime+startTime2-startTime1;
            timerHandler.postDelayed(timerRunnable, 0);
            onResumeThread();
        }
        else if(v.getId()==R.id.stop){
            timerHandler.removeCallbacks(timerRunnable);
            startTime1 = System.currentTimeMillis();
            onPauseThread();
                    }
}

    public void onPauseThread(){
    synchronized (mPauseLock){
        mPaused=true;
        remainingTime=3000-startTime1%3000;
        flag=1;
    }
    }
    public void onResumeThread(){
        synchronized (mPauseLock){
            mPaused=false;
            mPauseLock.notifyAll();//wakes all the threads that are waiting on this object's monitor
        }
    }
}