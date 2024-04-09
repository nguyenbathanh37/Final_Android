package com.example.final_android.forgetPassword;

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

public class VerificationActivity extends AppCompatActivity {
    TextView errorOTP_1;
    Button enterCode;
    EditText verifiCode;
    String verificationId, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifi);
        errorOTP_1 = findViewById(R.id.errorOTP_1);
        verifiCode = findViewById(R.id.verifiCode);
        verificationId = getIntent().getStringExtra("verificationId");
        phone = getIntent().getStringExtra("number_phone");

        //click nút check OTP
        clickButtonEnter();
    }

    // sự kiện nhất nút nhập code
    private void clickButtonEnter() {
        enterCode = findViewById(R.id.enterCode);
        enterCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifiOtpPassword();
            }
        });
    }

    //verifi OTP
    private void verifiOtpPassword() {
        if(verifiCode.getText().toString().isEmpty()) {
            errorOTP_1.setText("Vui lòng nhập mã OTP");
            return;
        }

        String code = verifiCode.getText().toString();
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
                                Intent intent = new Intent(VerificationActivity.this, ChangePasswordActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.putExtra("phone",phone);
                                startActivity(intent);
                            }
                            else {
                                errorOTP_1.setText("Sai mã OTP. Vui lòng nhập lại");
                            }
                        }
                    });
        }
    }

}