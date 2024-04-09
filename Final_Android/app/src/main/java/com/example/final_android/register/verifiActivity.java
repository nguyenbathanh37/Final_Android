package com.example.final_android.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_android.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class verifiActivity extends AppCompatActivity {

    TextView errorOTP_2;
    Button enterCodeRegister;
    EditText verifiCodeRegister;
    String verificationId, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifiregister);
        errorOTP_2 = findViewById(R.id.errorOTP_2);
        enterCodeRegister = findViewById(R.id.enterCodeRegister);
        verifiCodeRegister = findViewById(R.id.verifiCodeRegister);
        verificationId = getIntent().getStringExtra("verificationId");
        phone = getIntent().getStringExtra("number_phone");
        clickButtonEnter();
    }

    // sự kiện nhất nút nhập code
    private void clickButtonEnter() {
        enterCodeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifiOtpPassword();
            }
        });
    }

    //verifi OTP
    private void verifiOtpPassword() {
        if(verifiCodeRegister.getText().toString().isEmpty()) {
            errorOTP_2.setText("Vui lòng nhập mã OTP");
            return;
        }

        String code = verifiCodeRegister.getText().toString();
        if(verificationId !=null) {
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationId
                    ,code
            );
            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Intent intent = new Intent(verifiActivity.this, RegisterActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("phone", phone);
                                startActivity(intent);
                            }
                            else {
                                errorOTP_2.setText("Sai mã OTP. Vui lòng nhập lại");
                            }
                        }
                    });
        }
    }
}