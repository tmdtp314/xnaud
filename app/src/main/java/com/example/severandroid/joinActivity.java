package com.example.severandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
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

public class joinActivity extends AppCompatActivity {
    EditText edit_idinput, edit_pwinput; //데이터가 전송되는 통로
    RequestQueue requestQueue;
    StringRequest stringRequest;
    Button btn_join;
    //stringRequest는 내가 보내고 싶은 데이터- 이것을 requestqueue를 통해 보낸다.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        edit_idinput=findViewById(R.id.edit_inputid);
        edit_pwinput=findViewById(R.id.edit_inputpw);
        btn_join=findViewById(R.id.btn_join2);

        requestQueue = Volley.newRequestQueue(this); //현재 페이지 정보 보내주는것
        String url="http://172.30.1.49:8083/LoginServer/JoinServlet";
      //  String url="http://172.30.1.5:8081/LoginServer/JoinServlet";

        //stringRequest생성성

       stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               //응답 받아오는것
               if(response.equals("1")){ //jin성공
                   Toast.makeText(joinActivity.this,"가입성공",Toast.LENGTH_SHORT).show();

               }
               else{Toast.makeText(joinActivity.this,"가입실패",Toast.LENGTH_SHORT).show();

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

               data.put("id",edit_idinput.getText().toString());
               data.put("pw",edit_pwinput.getText().toString());
               return data;
           }
       };//여기까지가 생성자 호출
       //1.첫번째 매개변수, Method(get?post?service)
        //2번째 매개변수:url
        //3번째 매개변수: 응답을 감지하는 리스너
        //4번째 매개변수 : 에러 감지하는 리스너


        //태그 달아주기
        stringRequest.setTag("MAIN");
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //requestQueue를 통해 stringRequest전송하기
                requestQueue.add(stringRequest);
            }
        });

    }
}