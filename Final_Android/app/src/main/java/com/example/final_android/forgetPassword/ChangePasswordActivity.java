package com.example.final_android.forgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordActivity extends AppCompatActivity {
    EditText newPassword, newConfirmPassword;
    Button btn_confirm_password;
    TextView newErrorPassword, newErrorConfirmPassword;
    String SDT;

    // link API
    private String urlUpdatePassword = "http://192.168.2.9/Android_API/UPDATE/update_user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        newErrorPassword = findViewById(R.id.newErrorPassword);
        newErrorConfirmPassword = findViewById(R.id.newErrorConfirmPassword);
        newPassword = findViewById(R.id.newPassword);
        clickBtn_confirm_password();
    }


    // sự kiên nhấn nút đổi mật khẩu
    private void clickBtn_confirm_password() {
        btn_confirm_password = findViewById(R.id.btn_confirm_password);
        btn_confirm_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validatePassword() == true) {
                    updatePasswrod();
                    Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }


    //validate mật khẩu
    private boolean validatePassword() {
        newPassword = findViewById(R.id.newPassword);
        newConfirmPassword = findViewById(R.id.newConfirmPassword);
        if(newPassword.getText().toString().length() < 8 || newPassword.getText().toString().length() > 16) {
            newErrorPassword.setText("Mật khẩu phải có lớn hơn 8 ký tự và ít hơn 16 kí tự");
            return false;
        }
        else if(!newPassword.getText().toString().equals(newConfirmPassword.getText().toString())) {
            newErrorConfirmPassword.setText("Nhập khẩu nhập lại không trùng khớp");
            return false;
        }
        return true;
    }

    private void updatePasswrod() {
        SDT = getIntent().getStringExtra("phone");
        //lấy địa chỉ giao hàng

            RequestQueue queue = Volley.newRequestQueue(this);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdatePassword,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // write code here

                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(response);
                                String message = jsonObject.getString("message");
                                Toast.makeText(ChangePasswordActivity.this,message , Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }

                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // write code here
                    Toast.makeText(ChangePasswordActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                protected Map<String, String> getParams(){
                    Map<String, String> paramV = new HashMap<>();
                    paramV.put("sdt", SDT);
                    paramV.put("password", newPassword.getText().toString());
                    return paramV;
                }
            };
            queue.add(stringRequest);

    }
}