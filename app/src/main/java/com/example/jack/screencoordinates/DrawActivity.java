package com.example.jack.screencoordinates;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class DrawActivity extends AppCompatActivity {


    private final int DIFFERENCE = 10;
    private int prevXCrd;
    private int prevYCrd;


    private String addr;
    private int   port;

    private TextView xCrd;
    private TextView yCrd;
    private TextView LED;
    private TextView gradient;

    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        xCrd = (TextView)findViewById(R.id.xCrd);
        yCrd = (TextView)findViewById(R.id.yCrd);
        LED  = (TextView)findViewById(R.id.LED);
        gradient = (TextView)findViewById(R.id.gradient);

        Intent intent = getIntent();
        addr = intent.getStringExtra("addr");
        port = Integer.parseInt(intent.getStringExtra("port"));

        final View touchView = findViewById(R.id.activity_draw);
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                Client myClient;

                int currXCrd = (int)event.getX();
                int currYCrd = (int)event.getY();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        xCrd.setText(String.valueOf(currXCrd));
                        yCrd.setText(String.valueOf(currYCrd));
                        LED.setText("LED: ON");
                        if(currXCrd - prevXCrd > DIFFERENCE || prevXCrd - currXCrd > DIFFERENCE) {
                            myClient = new Client(addr,
                                                  port,
                                                  (int) event.getX(),
                                                  (int) event.getY(),
                                                  1,
                                                  seekBar.getProgress());
                            myClient.execute();
                        }
                        prevXCrd = currXCrd;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        if(currXCrd - prevXCrd > DIFFERENCE || prevXCrd - currXCrd > DIFFERENCE) {
                           myClient = new Client(addr,
                                                 port,
                                                 (int) event.getX(),
                                                 (int) event.getY(),
                                                 1,
                                                 seekBar.getProgress());
                           myClient.execute();
                        }
                        prevXCrd = currXCrd;
                        break;
                    case MotionEvent.ACTION_UP:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        if(currXCrd - prevXCrd > DIFFERENCE || prevXCrd - currXCrd > DIFFERENCE) {
                            myClient = new Client(addr,
                                                  port,
                                                  (int) event.getX(),
                                                  (int) event.getY(),
                                                  0,
                                                  seekBar.getProgress());
                             myClient.execute();
                        }
                       prevXCrd = currXCrd;

                        break;
                }
                return true;
            }
        });

        seekBar = (SeekBar)findViewById(R.id.seekBar1);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = seekBar.getProgress();
                gradient.setText(String.valueOf(value));
            }
        });
    }
}
