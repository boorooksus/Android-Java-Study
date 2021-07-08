package com.example.s03_multi_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = findViewById(R.id.buttonWeb);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 웹 여는 intent
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));

                // 지도 여는 intent(위도, 경도 정보 필요)
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.44960881406647, 126.6531570043012"));

                startActivity(intent);
            }
        });
    }

    public void backHandler(View view){
        finish();
    }

}