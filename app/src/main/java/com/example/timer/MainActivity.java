package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timer;
    Button start, stop, reset;
    long milliTime, startTime, buffTime, updateTime = 0L;
    Handler handler;
    int sec, min, milli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = findViewById(R.id.timeDisp);
        start = findViewById(R.id.startButton);
        stop = findViewById(R.id.stopButton);
        reset = findViewById(R.id.resetButton);
        handler = new Handler();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);

            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                buffTime += milliTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                milliTime = 0L;
                startTime = 0L;
                buffTime = 0L;
                updateTime = 0L;
                sec = 0;
                min = 0;
                milli = 0;

                timer.setText("00:00:00");

            }
        });

    }


    public Runnable runnable = new Runnable() {

        public void run() {
            milliTime = SystemClock.uptimeMillis() - startTime;
            updateTime = buffTime + milliTime;
            sec = (int) (updateTime / 1000);
            min = sec / 60;
            sec %= 60;
            milli = (int) (updateTime % 1000);
            timer.setText("" + min + ":"
                    + String.format("%02d", sec) + ":"
                    + String.format("%03d", milli));
            handler.postDelayed(this, 0);
        }

    };
}
