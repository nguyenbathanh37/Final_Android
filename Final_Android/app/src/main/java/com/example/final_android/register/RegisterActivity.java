package com.example.final_android.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.MainActivity;
import com.example.final_android.R;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText registerNameUser, registerPassword, registerConfirmPassword, address;
    TextView errorNameUser, errorPassword, errorConfirmPassword;
    Button btn_register;

    String SDT, UserName, Password, DiaChi;
    ProgressBar progressBar;

    //link api
    private String urlRegister = "http://192.168.2.9/Android_API/GET/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //ánh xạ các editText
        registerNameUser = findViewById(R.id.registerNameUser);
        registerPassword = findViewById(R.id.registerPassword);
        registerConfirmPassword = findViewById(R.id.registerConfirmPassword);
        address = findViewById(R.id.address);

        // ánh xạ progessBar
        progressBar = findViewById(R.id.loading);


        //ánh xạ các view báo lỗi

        errorNameUser = findViewById(R.id.errorNameUser);
        errorPassword = findViewById(R.id.errorPassword);
        errorConfirmPassword = findViewById(R.id.errorConfirmPassword);

        clickButtonRegister();

    }


    // sự kiện click vào nút đăng kí
    private  void clickButtonRegister() {
        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if(verifiData() == true) {
                    // write code here
                    hanldeApiRegister();
                }
            }
        });
    }



    // xử lý api khi đăng kí
    private void hanldeApiRegister() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        SDT = getIntent().getStringExtra("phone");
        UserName = registerNameUser.getText().toString();
        Password = registerPassword.getText().toString();
        DiaChi = address.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlRegister,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // write code here
                        if(response.equals("success")) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // write code here
                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("SDT", SDT);
                paramV.put("UserName", UserName);
                paramV.put("Password", Password);
                paramV.put("DiaChi", DiaChi);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }


    //verify username, sdt, password
    private boolean verifiData() {

        if(registerPassword.getText().equals(null)) {
            errorPassword.setText("Không được để trống trường này");
            progressBar.setVisibility(View.GONE);
            return false;
        }else if(registerPassword.getText().length() < 6) {
            errorNameUser.setText("Mật khẩu phải lớn hơn 6 kí tự");
            progressBar.setVisibility(View.GONE);
            return false;
        }
        else if(registerNameUser.getText().equals(null)) {
            errorNameUser.setText("Không được để trống trường này");
            progressBar.setVisibility(View.GONE);
            return false;
        }
        else if(address.getText().equals(null)) {
            errorNameUser.setText("Không được để trống trường này");
            progressBar.setVisibility(View.GONE);
            return false;
        }
        else if(registerConfirmPassword.getText().equals(null)) {
            errorConfirmPassword.setText("Không trùng với mật khẩu đã nhập hoặc bạn đang để trống trường này");
            progressBar.setVisibility(View.GONE);
            return false;
        }
        return true;
    }

}