package com.example.s02_unit_converter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button button;
    ImageView image;
    RadioGroup radioGroup;
    int radioButton;
    int minRadioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextNumberSigned);
        image = findViewById(R.id.imageView);
        radioGroup = findViewById(R.id.radioGroup);
        minRadioId = radioGroup.getCheckedRadioButtonId();

        button.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view){
                String strC = editText.getText().toString();

                if (!strC.equals("")) {
                    double c = Double.parseDouble(strC);

                    radioButton = radioGroup.getCheckedRadioButtonId();

                    if (radioButton == minRadioId){
                        double f = 9 / 5.0 * c + 32;
                        textView.setText("" + f);
                        image.setImageResource(R.drawable.java);
                    }
                    else if (radioButton == minRadioId + 1){
                        double f = (c - 32) * 5.0 / 9;
                        textView.setText("" + f);
                        image.setImageResource(R.drawable.java);
                    }
                    else if (radioButton == minRadioId + 2){
                        double f = c * 0.3937007874;
                        textView.setText("" + f);
                    }
                    else if (radioButton == minRadioId + 3){
                        double f = c * 2.54;
                        textView.setText("" + f);
                    }
                    else if (radioButton == minRadioId + 4){
                        double f = c * 2.2046;
                        textView.setText("" + f);
                    }
                    else if (radioButton == minRadioId + 5){
                        double f = c * 0.45359;
                        textView.setText("" + f);
                    }

                }
            }

        });

    }
}