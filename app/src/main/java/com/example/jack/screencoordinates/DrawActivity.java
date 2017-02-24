package com.example.jack.screencoordinates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.textservice.TextInfo;
import android.widget.SeekBar;
import android.widget.TextView;

import static com.example.jack.screencoordinates.R.id.editTextAddr;
import static com.example.jack.screencoordinates.R.id.editTextPort;

public class DrawActivity extends AppCompatActivity {

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
        //port = Integer.parseInt(intent.getStringExtra("port"));
        port = 5001;

        final View touchView = findViewById(R.id.activity_draw);
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                Client myClient = null;
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        LED.setText("LED: ON");
                        myClient = new Client(addr,
                                              port,
                                              (int) event.getX(),
                                              (int) event.getY(),
                                              1,
                                              seekBar.getProgress());
                        myClient.execute();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        myClient = new Client(addr,
                                              port,
                                              (int) event.getX(),
                                              (int) event.getY(),
                                              1,
                                              seekBar.getProgress());
                        myClient.execute();
                        break;
                    case MotionEvent.ACTION_UP:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        LED.setText("LED: OFF");
                        myClient = new Client(addr,
                                              port,
                                              (int) event.getX(),
                                              (int) event.getY(),
                                              1,
                                              seekBar.getProgress());
                        myClient.execute();

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
