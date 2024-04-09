package com.example.final_android.Other;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InfoActivity extends AppCompatActivity {
    private TextView sdt, username, password, position;
    private String sdt1, username1, password1, position1;

    private String urlUser = "http://192.168.2.9/Android_API/GET/get_user.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sdt = findViewById(R.id.sdt);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        position = findViewById(R.id.position);

        sdt.setText(sdt1);
        username.setText(username1);
        password.setText(password1);
        position.setText(position1);
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // write code here
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                sdt1 = jsonObject.getString("SDT");
                                username1 = jsonObject.getString("UserName");
                                password1 = jsonObject.getString("PassWord");
                                position1 = jsonObject.getString("DiaChi");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // write code here
                Toast.makeText(InfoActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("UserName", getIntent().getStringExtra("username"));
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}