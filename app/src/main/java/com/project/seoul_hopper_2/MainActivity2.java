package com.project.seoul_hopper_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    TextView text;
    String string_data;
    String key="496173674463646f3130387342564a78";
    private ListView listView;
    ArrayAdapter adapter;
    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String sub_text =  getIntent().getStringExtra("info");

        text=findViewById(R.id.text);

        listView = (ListView)findViewById(R.id.listView1);
        // adapter 스타일 선언 및 items 적용
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        // listView에 adapter 적용
        listView.setAdapter(adapter);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    string_data = data(sub_text);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    String data(String sub_text) throws IOException {

        StringBuffer buffer=new StringBuffer("http://openapi.seoul.go.kr:8088/"+key+"/json/culturalEventInfo/1/5/");
        buffer.append(URLEncoder.encode(sub_text,"UTF-8"));

        URL url = new URL(buffer.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;

        try {
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            String jsonData = sb.toString();
            JSONObject obj = new JSONObject(jsonData);
            System.out.println(obj);
            JSONObject result1 = (JSONObject) obj.get("culturalEventInfo");
            JSONArray concertList = (JSONArray) result1.get("row");

            for(int i=0; i<concertList.length(); i++){
                JSONObject tmp = concertList.getJSONObject(i);
                String showName = tmp.getString("TITLE");
                System.out.println("***showname----"+ showName);
                items.add(showName);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        return sb.toString();
    }

}