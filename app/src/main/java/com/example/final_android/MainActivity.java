package com.example.final_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView phone_number, password;
    Button btn_log_in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickButtonLogIn();
    }

    private void clickButtonLogIn() {
        btn_log_in = findViewById(R.id.btn_log_in);
        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogIn();
            }
        });
    }

    // check sdt và mật khẩu khi đăng nhập
    private void validateLogIn() {
        phone_number = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        if(phone_number.getText().toString().length() == 0) {
            Toast.makeText(this, "Số diện thoại không được để trống", Toast.LENGTH_SHORT).show();
        }
        else if(phone_number.getText().toString().length() >10) {
            Toast.makeText(this, "Vui lòng nhập lại số điện thoại", Toast.LENGTH_SHORT).show();
        }
        else if(password.getText().toString().length() < 8 || password.getText().toString().length() > 16) {
            Toast.makeText(this, "Mật khẩu phải có lớn hơn 8 ký tự và ít hơn 16 kí tự", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Đăng nhập thành công: "+phone_number.getText().toString() , Toast.LENGTH_SHORT).show();
        }

    }

    // sự kiện nhấn nút đăng nhập


}