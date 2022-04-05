package com.example.myapplication2;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button;
    String msg;
    Socket s;
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);
        EditText message1 =(EditText) findViewById(R.id.message);
        button = findViewById(R.id.connectbut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    s=new Socket("192.168.1.9",6659);
                }
                catch(Exception e){System.out.println(e);}
                if(s.isConnected()==true) {
                    Toast.makeText(getApplicationContext(), "connection stablished", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "try to connect again", Toast.LENGTH_LONG).show();
                }
            }

                                  });
        button = findViewById(R.id.sendbut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg=message1.getText().toString();

                try {
                    if(s.isConnected()==true){
                        DataOutputStream dos =new DataOutputStream(s.getOutputStream());
                        dos.writeUTF(msg);
                        DataInputStream dis=new DataInputStream(s.getInputStream());
                        str=(String)dis.readUTF();
                        dos.close();
                        dos.flush();
                        s.close();

                    }
                   else{
                        Toast.makeText(getApplicationContext(), "connection not stablished yet", Toast.LENGTH_LONG).show();
                    }
                }
                catch(Exception e){System.out.println(e);}
                Toast.makeText(getApplicationContext(),str, Toast.LENGTH_LONG).show();

            }

        }



        );
    }

    }





