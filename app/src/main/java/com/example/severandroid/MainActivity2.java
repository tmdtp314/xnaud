package com.example.severandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {
    Button btn;
    Button btn_sr1;
    Button btn_sr2;
    Button btn_all;
    Button btn_sr3, btn_sr4,btn_sr5,btn_sr6;
    RequestQueue requestQueue;
    StringRequest stringRequest, stringRequest2, stringRequest3;
    boolean checkSr1 = false;
    boolean checkSr2 = false;
    boolean checkAll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn_sr1 = findViewById(R.id.btn_sr1);
        btn_sr2 = findViewById(R.id.btn_sr2);
        btn_sr3 = findViewById(R.id.btn_sr3);
        btn_sr4 = findViewById(R.id.btn_sr4);
        btn_sr5 = findViewById(R.id.btn_sr5);
        btn_sr6 = findViewById(R.id.btn_sr6);

        btn_all=findViewById(R.id.btn_all);
        requestQueue = Volley.newRequestQueue(this);


        String id="aaa";
        switch(id){
            case "aaa":
                btn=btn_sr1;
                break;
            case "bbb":
                btn=btn_sr2;
                break;

            case "ccc":
                btn=btn_sr3;
                break;


            case "ddd":
                btn=btn_sr4;
                        break;
        }

        String url = "http://172.30.1.49:8083/LoginServer/sensorCon";


        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("on")) {
                    btn_sr1.setBackgroundColor(Color.parseColor("red"));
                } else if (response.equals("off")) {
                    btn_sr1.setBackgroundColor(Color.parseColor("blue"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> sensor = new HashMap<>();

                if (checkSr1 == true) {
                    sensor.put("onOff", "on");
                    sensor.put("sr","sr1");
                } else if (checkSr1 == false) {
                    sensor.put("onOff", "off");
                    sensor.put("sr","sr1");
                }

                return sensor;
            }
        };

        btn_sr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSr1 = !checkSr1;
                String booleans = Boolean.toString(checkSr1);
                Toast.makeText(MainActivity2.this, booleans, Toast.LENGTH_SHORT).show();
                stringRequest.setTag("sr1" + booleans);

                requestQueue.add(stringRequest);

            }
        });

        stringRequest2 = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("on"))
                    btn_sr2.setBackgroundColor(Color.parseColor("red"));
                else if (response.equals("off"))
                    btn_sr2.setBackgroundColor(Color.parseColor("blue"));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> sensor = new HashMap<>();
                if (checkSr2 == true) {
                    sensor.put("onOff", "on");
                    sensor.put("sr", "sr2");
                } else if (checkSr2 == false) {
                    sensor.put("onOff", "off");
                    sensor.put("sr", "sr2");
                }

                return sensor;
            }
        };

        btn_sr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSr2 = !checkSr2;
                String booleans = Boolean.toString(checkSr2);
                Toast.makeText(MainActivity2.this,booleans,Toast.LENGTH_SHORT).show();
                stringRequest.setTag("sr1" + booleans);
                requestQueue.add(stringRequest2);
            }
        });



        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSr1=false;
                checkSr2=false;
                btn_sr1.setBackgroundColor(Color.parseColor("blue"));
                btn_sr2.setBackgroundColor(Color.parseColor("blue"));
                requestQueue.add(stringRequest);
                requestQueue.add(stringRequest2);

         //    requestQueue.add(stringRequest3);

            }
        });
    }


}