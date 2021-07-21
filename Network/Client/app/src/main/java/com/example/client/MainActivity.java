package com.example.client;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button buttonLeftClick;
    Button buttonRightClick;
    int posX;
    int posY;
//    boolean isLeftClick = false;
//    boolean isRightClick = false;
    int clickedButton;

    class NetworkThread extends Thread{
        private int networkType;

        public void setNetworkType(int networkType){
            this.networkType = networkType;
        }

        @Override
        public void run() {
            try {

                IpAddress ipAddress = new IpAddress();

                Socket socket = new Socket(ipAddress.getIpAddress(), 5000);

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                System.out.println("x: " + posX);
                System.out.println("y: " + (posY - 800));

                if(networkType == 0){
                    dos.writeInt(0);
                    dos.writeInt(posX);
                    dos.writeInt(posY - 800);
                }
                else{
                    dos.writeInt(1);
                    dos.writeInt(clickedButton);
//                    dos.writeBoolean(isLeftClick);
//                    dos.writeBoolean(isRightClick);

                }



                socket.close();

            } catch (Exception e) {
                System.out.println("================ Error =====================");
                e.printStackTrace();
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        posX = (int)event.getX();
        posY = (int)event.getY();

        if(posX > 0 && posY > 0){
            NetworkThread thread = new NetworkThread();
            thread.setNetworkType(0);
            thread.start();

        }


        return super.onTouchEvent(event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonLeftClick = findViewById(R.id.buttonLeftClick);
        buttonRightClick = findViewById(R.id.buttonRightClick);

        buttonLeftClick.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                isLeftClick = !isLeftClick;
//                if (isLeftClick){
//                    buttonLeftClick.setBackgroundColor(Color.parseColor("#282828"));
//                } else{
//                    buttonLeftClick.setBackgroundColor(Color.parseColor("#828282"));
//                }
                clickedButton = 1;

                NetworkThread thread = new NetworkThread();
                thread.setNetworkType(1);
                thread.start();
            }
        });

        buttonRightClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                isRightClick = !isRightClick;
//                if (isRightClick){
//                    buttonRightClick.setBackgroundColor(Color.parseColor("#282828"));
//                } else{
//                    buttonRightClick.setBackgroundColor(Color.parseColor("#828282"));
//                }

                clickedButton = 3;

                NetworkThread thread = new NetworkThread();
                thread.setNetworkType(1);
                thread.start();
            }
        });
    }
}