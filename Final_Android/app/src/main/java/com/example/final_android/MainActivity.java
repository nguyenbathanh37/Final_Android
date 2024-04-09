package com.example.final_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.forgetPassword.GetOtpPasswordActivity;
import com.example.final_android.register.getOtpRegisterActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.Log_in.MESSAGE";
    public static final int REQUEST_CODE_GET_FULL_NAME = 101;
    private static final String TAG = "Error";
    String mUser, mPassword; // biến để lưu giá trị của editText

    EditText username, password;
    TextView T_register, forget_password;
    Button btn_log_in, btn_login_facebook, btn_login_google;

    // biến của loginFacebook
    CallbackManager callbackManager;
    //------

    //biến của loginGoogle
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    //api
    private String urlLogin  = "http://192.168.2.9/Android_API/GET/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ các edit text
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);




        clickButtonLogIn();
        clickTViewRegister();
        clickForgetPassword();

        // hàm dành cho login Facebook
        //callBack();
        clickButtonLoginFacebook(); // hành động click button loginFacebook

        //dành cho login google
        clickButtonLoginGoogle();
    }


    // sự kiện nhấn nút đăng nhập
    private void clickButtonLogIn() {
        btn_log_in = findViewById(R.id.btn_log_in);
        btn_log_in.setOnClickListener(v -> {
            if(validateLogIn()) {
                hanldeApiLogin();
            }
            // gọi hàm validate để kiểm tra tính hợp lệ của dữ liệu đầu vào
        });
    }

    // sự kiện nhấn textView đăng kí
    private void clickTViewRegister() {
        T_register = findViewById(R.id.T_register);
        T_register.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, getOtpRegisterActivity.class);
            startActivity(intent);
        });
    }

    // nhấn nút quên mật khẩu
    private void clickForgetPassword() {
        forget_password = findViewById(R.id.forget_password);
        forget_password.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GetOtpPasswordActivity.class);
            startActivity(intent);
        });
    }

    // check sdt và mật khẩu khi đăng nhập
    private boolean validateLogIn() {
        if (username.getText().toString().length() == 0) {
            Toast.makeText(this, "Tên đăng nhập không được để trống", Toast.LENGTH_SHORT).show();
            return false;

        } else if (password.getText().toString().length() < 6 || password.getText().toString().length() > 16) {
            Toast.makeText(this, "Mật khẩu phải có lớn hơn 6 ký tự và ít hơn 16 kí tự", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    // nhấn button Login_Facebook
    private void clickButtonLoginFacebook() {
        btn_login_facebook = findViewById(R.id.btn_login_facebook);
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
//                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
//                        finish();
                        Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(MainActivity.this, exception.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
        btn_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
            }
        });
    }


    // nhấn button Login_Google
    private void clickButtonLoginGoogle() {
        btn_login_google = findViewById(R.id.btn_login_google);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);

        btn_login_google.setOnClickListener(v -> {
            Intent signInIntent = gsc.getSignInIntent();
            startActivityForResult(signInIntent, 1000);
        });
    }

    // hàm chung cho cả login facebook, google
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // hàm callback cho login bằng facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);

        // phần xử lý login bằng google
        if(requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
                finish();
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("username", acct.getDisplayName());
                startActivity(intent);
                finish();
            } catch (ApiException e) {
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }

    }

    // xử lý call api để đăng nhập

    private void hanldeApiLogin() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlLogin,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // write code here
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String status = jsonObject.getString("status");
                            String message = jsonObject.getString("message");
                            if(status.equals("success")) {
                                mUser = jsonObject.getString("username");
                                mPassword = jsonObject.getString("password");

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                intent.putExtra("username", mUser);
                                startActivity(intent);
                                finish();
                            }
                            else if (status.equals("0")) {
                                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Sai tên đăng nhập", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // write code here
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("UserName", username.getText().toString());
                paramV.put("Password", password.getText().toString());
                return paramV;
            }
        };
        queue.add(stringRequest);
    }





}

