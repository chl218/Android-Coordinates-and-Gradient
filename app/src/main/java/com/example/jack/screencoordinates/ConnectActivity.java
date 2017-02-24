package com.example.jack.screencoordinates;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConnectActivity extends AppCompatActivity {

    private EditText editTextAddr;
    private EditText editTextPort;

    private Button buttonConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        editTextAddr = (EditText)findViewById(R.id.editTextAddr);
        editTextPort = (EditText)findViewById(R.id.editTextPort);
        buttonConnect   = (Button) findViewById(R.id.buttonConnect);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String addr = editTextAddr.getText().toString();
                int    port = Integer.parseInt(editTextPort.getText().toString());
                Client client = new Client(addr, port, 0, 0, 0, 0);
                client.execute();

                Intent intent = new Intent(ConnectActivity.this, DrawActivity.class);
                intent.putExtra("addr", addr);
                intent.putExtra("port", String.valueOf(port));
                startActivity(intent);
            }
        });
    }

}
