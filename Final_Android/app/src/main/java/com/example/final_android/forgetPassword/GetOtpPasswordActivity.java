package com.example.final_android.forgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_android.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class GetOtpPasswordActivity extends AppCompatActivity {
    Button sendOTP_forget_password;
    EditText phone_forget_password;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp_password);
        phone_forget_password = findViewById(R.id.phone_forget_password);
        sendOTP_forget_password = findViewById(R.id.sendOTP_forget_password);
        sendOTP_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOtpPassword();
            }
        });
    }


    private void sendOtpPassword() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+84"+phone_forget_password.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(GetOtpPasswordActivity.this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                Toast.makeText(GetOtpPasswordActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(GetOtpPasswordActivity.this, e.getMessage() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                Intent intent = new Intent(GetOtpPasswordActivity.this, VerificationActivity.class);
                                intent.putExtra("verificationId", verificationId);
                                intent.putExtra("number_phone", phone_forget_password.getText().toString());
                                startActivity(intent);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}