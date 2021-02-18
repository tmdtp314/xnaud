package com.example.severandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
Button btn_join;
Button btn_login;
EditText edit_id,edit_pw;
RequestQueue requestQueue;
StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit_pw=findViewById(R.id.edit_pw);
        edit_id=findViewById(R.id.edit_id);
        requestQueue = Volley.newRequestQueue(MainActivity.this); //현재 페이지 정보 보내주는것
        String url="http://172.30.1.49:8083/LoginServer/LoginServlet";
       // String url="http://172.30.1.5:8081/LoginServer/LoginServlet";

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("true")) {
                    Toast.makeText(MainActivity.this,"로그인 성공",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MainActivity.this,AllSelectActivity.class));

                } else {
                    Toast.makeText(MainActivity.this,"로그인실패",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();

                data.put("id",edit_id.getText().toString());
                data.put("pw",edit_pw.getText().toString());
                return data;

            }
        };
        stringRequest.setTag("LOGiN");

        btn_join=findViewById(R.id.btn_join);
        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestQueue.add(stringRequest);

            }
        });
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,joinActivity.class);
                startActivity(intent);
            }
        });
    }
}