package com.example.final_android;

import static com.facebook.FacebookSdk.getApplicationContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.Adapter.ClassifiAdapter;
import com.example.final_android.Adapter.PopularAdapter;
import com.example.final_android.Model.OrderModel;
import com.example.final_android.Model.PopularModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassifyProductActivity extends AppCompatActivity {

    private ClassifiAdapter adapter;
    private RecyclerView recyclerViewClassifi;
    private ArrayList<PopularModel> list = new ArrayList<>();

    ImageView classifiPic;
    TextView titleClassifi;

    //biến lấy dữ liệu gửi từ CategoryAdapter
    String maLoai, code;
    int pic;
    String name;

    //link API
    private String urlGetProduct = "http://192.168.2.9/Android_API/GET/get_product_by_lsp.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classify_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ánh xạ data
        recyclerViewClassifi = findViewById(R.id.recyclerViewClassifi);
        classifiPic = findViewById(R.id.classifiPic);
        titleClassifi = findViewById(R.id.titleClassifi);

        //lấy dữ liệu
        maLoai = getIntent().getStringExtra("maLoai");
        pic = getIntent().getIntExtra("pic",R.drawable.logo_coffee);
        name = getIntent().getStringExtra("name");

        classifiPic.setImageResource(pic);
        titleClassifi.setText(name);
        //hàm gọi getdata từ API
        getData();
        recyclerViewPopular();
    }

    // hàm xét recyclerview
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewClassifi.setLayoutManager(linearLayoutManager);

        adapter = new ClassifiAdapter(list,ClassifyProductActivity.this);
        recyclerViewClassifi.setAdapter(adapter);
    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlGetProduct,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // write code here
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                list.add(new PopularModel(jsonObject.getString("TenSP"),jsonObject.getString("HinhAnh"), jsonObject.getDouble("GiaSP")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // write code here
                Toast.makeText(ClassifyProductActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("MaLSP", maLoai);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}