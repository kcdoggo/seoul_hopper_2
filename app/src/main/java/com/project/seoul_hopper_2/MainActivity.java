package com.project.seoul_hopper_2;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    String main_text = "글자네";
    private Button concert;
    private Button classic;
    private Button exhibition;
    private Button korean;
    private Button activity0;
    private Button musical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        concert = (Button)findViewById(R.id.concert);
        classic = (Button)findViewById(R.id.classic);
        exhibition = (Button)findViewById(R.id.exhibition);
        activity0 = (Button)findViewById(R.id.activity0);
        korean = (Button)findViewById(R.id.korean);
        musical = (Button)findViewById(R.id.musical);


        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);

                switch (v.getId()) {
                    case R.id.concert:
                        String main_text0 = "콘서트";
                        intent.putExtra("info", main_text0);
                        break;
                    case R.id.classic:
                        String main_text1 = "클래식";
                        intent.putExtra("info", main_text1);
                        break;
                    case R.id.exhibition://전시/미술
                        String main_text2 = "전시/미술";
                        intent.putExtra("info", main_text2);
                        break;
                    case R.id.activity0: //교육/체험
                        String main_text3 = "교육/체험";
                        intent.putExtra("info", main_text3);
                        break;
                    case R.id.korean: //국악
                        String main_text4 = "국악";
                        intent.putExtra("info", main_text4);
                        break;
                    case R.id.musical: //뮤지컬/오페라
                        String main_text5 = "뮤지컬/오페라";
                        intent.putExtra("info", main_text5);
                        break;
                }
                startActivity(intent);
            }
        };

        concert.setOnClickListener(buttonClickListener);
        classic.setOnClickListener(buttonClickListener);
        exhibition.setOnClickListener(buttonClickListener);
        activity0.setOnClickListener(buttonClickListener);
        korean.setOnClickListener(buttonClickListener);
        musical.setOnClickListener(buttonClickListener);

    }


}