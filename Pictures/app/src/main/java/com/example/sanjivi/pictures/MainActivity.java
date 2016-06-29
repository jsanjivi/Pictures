package com.example.sanjivi.pictures;


import android.media.MediaPlayer;
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

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] tracks={"Levels","Tremor","Hey Jude"};
    int i,flag=0;
    TextView timerTextView;
    long startTime = 0;
    final Handler h = new Handler(new Handler.Callback() {

        @Override
        public boolean handleMessage(Message msg) {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis / 1000);
            seconds     = seconds % 60;
            timerTextView = (TextView) findViewById(R.id.textView);
            timerTextView.setText(String.format("%02d", seconds));
            return false;
        }
    });



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
    class firstTask extends TimerTask {

        @Override
        public void run() {
            h.sendEmptyMessage(0);

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
    int j=1;
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item=parent.getItemAtPosition(position).toString(); //on selecting a spinner item
        j=position;
        flag=0;

    }
    public void onNothingSelected(AdapterView<?> arg0)
    {// TODO Auto-generated method stub
    }

    MediaPlayer mPlayer=new MediaPlayer();
    Timer timer;
    public void onClick(View v) {

        if (v.getId() == R.id.slideshow)
        {i=1;
            startTime = System.currentTimeMillis();
            timer = new Timer();
            timer.schedule(new firstTask(), 0,500);
            Runnable r1 = new Runnable() {
                @Override
                public void run() {

                    while (i <= 4) {
                        handler.sendEmptyMessage(0);
                       if(i==4){
                           timer.cancel();
                           timer.purge();
                       }
                        try {

                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        i++;

                    }
                }
            };
            Thread delay = new Thread(r1);
            delay.start();

    }

           else if (v.getId() == R.id.play) {
            if(flag==0) {flag=1;
                if (j == 0)
                    mPlayer = MediaPlayer.create(this, R.raw.levels);
                else if (j == 1)
                    mPlayer = MediaPlayer.create(this, R.raw.tremor);
                else if (j == 2)
                    mPlayer = MediaPlayer.create(this, R.raw.heyjude);
            }
                mPlayer.start();

            }
            else if (v.getId() == R.id.stop) {

                mPlayer.pause();
            }
}

}