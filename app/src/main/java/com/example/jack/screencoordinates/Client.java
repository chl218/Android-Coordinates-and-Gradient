package com.example.jack.screencoordinates;

import android.os.AsyncTask;
import android.widget.TextView;

import java.net.*;
import java.io.*;

public class Client extends AsyncTask<Void, Void, Void> {

    private String dstAddress;
    private int dstPort;
    private String response = "";
    private int xCrd;
    private int yCrd;
    private int LED;
    private int LEDGradient;

    Client(String addr, int port, int xCrd, int yCrd, int LED, int LEDGradient) {
        this.dstAddress  = addr;
        this.dstPort     = port;
        this.xCrd        = xCrd;
        this.yCrd        = yCrd;
        this.LED         = LED;
        this.LEDGradient = LEDGradient;
    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Socket socket = null;

        try {
            System.out.println("Connecting to " + dstAddress + " on port " + dstPort);
            Socket client = new Socket(dstAddress, dstPort);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            PrintWriter outToServer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));

            outToServer.println("" + xCrd + " " + yCrd + " " + LED + " " + LEDGradient);
            outToServer.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Server Says : " + in.readLine());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }

}