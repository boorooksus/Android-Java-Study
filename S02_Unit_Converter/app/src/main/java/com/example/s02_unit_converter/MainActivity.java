package com.example.s02_unit_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    Switch sw;
    ImageView image;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextNumberSigned);
        sw = findViewById(R.id.switch1);
        image = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                String strC = editText.getText().toString();
                double c = Double.parseDouble(strC);

                if (strC.equals(""))
                    return;
                if(sw.isChecked()){
                    double f = 9 / 5.0 * c + 32;
                    textView.setText("" + f);
                    image.setImageResource(R.drawable.java);
                    progressBar.setMax(100);
                    progressBar.setProgress((int)f);
                }
                else{

                    double f = (c - 32) * 5.0 / 9;
                    textView.setText("" + f);
                    image.setImageResource((R.drawable.android));
                    progressBar.setMax(212);
                    progressBar.setProgress((int)f);
                }

            }

        });

    }
}