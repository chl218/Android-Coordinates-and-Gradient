package com.example.jack.screencoordinates;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView xCrd;
    private TextView yCrd;
    private TextView LED;
    private TextView gradient;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xCrd = (TextView)findViewById(R.id.xCrd);
        yCrd = (TextView)findViewById(R.id.yCrd);
        LED  = (TextView)findViewById(R.id.LED);
        gradient = (TextView)findViewById(R.id.gradient);

        final View touchView = findViewById(R.id.activity_main);
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        LED.setText("LED: ON");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        break;
                    case MotionEvent.ACTION_UP:
                        xCrd.setText(String.valueOf((int) event.getX()));
                        yCrd.setText(String.valueOf((int) event.getY()));
                        LED.setText("LED: OFF");
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
