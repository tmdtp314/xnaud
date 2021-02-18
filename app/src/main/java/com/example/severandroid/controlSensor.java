package com.example.severandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class controlSensor extends AppCompatActivity {
TextView tv_J,tv_J2;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    Button btn_new;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_sensor);
        tv_J=findViewById(R.id.tv_json);
        tv_J2=findViewById(R.id.tv_json2);
        btn_new=findViewById(R.id.btn_new);


        requestQueue = Volley.newRequestQueue(this); //현재 페이지 정보 보내주는것
        String url="http://172.30.1.49:8083/LoginServer/powerCount";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //응답 받아오는것
                if(response.contains("sensor1")){ //jin성공
                    try {
                        JSONArray array=new JSONArray(response);

                        tv_J.setText("sensor1의 전력량 :"+array.getJSONObject(0).getString("sensor1"));
                        tv_J2.setText("sensor2의 전력량 :"+array.getJSONObject(0).getString("sensor2"));



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                else{Toast.makeText(controlSensor.this,"실패",Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            //StringRequest 내의 메소드 오버로딩!


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                //서버에 전송하고 싶은 데이터를 key값, value값으로 저장하여 return
                Map<String, String> data = new HashMap<>();

                data.put("go","go");

                return data;
            }
        };//여기까지가 생성자 호출
        //1.첫번째 매개변수, Method(get?post?service)
        //2번째 매개변수:url
        //3번째 매개변수: 응답을 감지하는 리스너
        //4번째 매개변수 : 에러 감지하는 리스너


        //태그 달아주기
        stringRequest.setTag("MAIN");
        requestQueue.add(stringRequest);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringRequest.setTag("MAIN");
                requestQueue.add(stringRequest);
            }
        });

    }


    }
