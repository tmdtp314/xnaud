package com.example.severandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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

public class AllSelectActivity extends AppCompatActivity {
 //이 페이지 접속하자 마자 바로 다 보여줄것.
    TextView tv_all;
    RequestQueue requestQueue;
    StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_select);
        tv_all=findViewById(R.id.tv_all);

        requestQueue= Volley.newRequestQueue(this);
        String url="http://172.30.1.49:8083/LoginServer/AllSelectServlet";

        stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //string->Json으로 바꿀것임
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        tv_all.setText(tv_all.getText()+array.getJSONObject(i).getString("id")+" ");
                        tv_all.setText(tv_all.getText()+array.getJSONObject(i).getString("pw")+"\n");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> temp = new HashMap<>();
                return temp;
            }
        };
        stringRequest.setTag("MAIN");
        requestQueue.add(stringRequest);
    }
}