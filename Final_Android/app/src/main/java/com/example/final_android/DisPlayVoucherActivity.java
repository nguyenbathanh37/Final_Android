package com.example.final_android;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.Adapter.VoucherAdapter;
import com.example.final_android.Model.VoucherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DisPlayVoucherActivity extends AppCompatActivity {
    private RecyclerView recyclerViewDisplayVoucher;
    private VoucherAdapter voucherAdapter;
    private ArrayList<VoucherModel> listVoucher = new ArrayList<>();


    //link api get_voucher
    private String urlGetVoucher = "http://192.168.2.9/Android_API/GET/get_voucher.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_voucher);

        //ánh xạ recyclerView
        recyclerViewDisplayVoucher = findViewById(R.id.recyclerViewDisplayVoucher);
        //gọi hàm thực thi
        getData(urlGetVoucher);
        setData();
    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerViewDisplayVoucher.setLayoutManager(linearLayoutManager);

        voucherAdapter = new VoucherAdapter(listVoucher, this, getIntent().getStringExtra("username"));
        recyclerViewDisplayVoucher.setAdapter(voucherAdapter);
    }


    //hàm gọi API lấy dữ liệu các voucher
    private void getData(String urlAPI){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        // lưu dữ liệu từ json trả về vào arraylist
                        listVoucher.add(new VoucherModel(jsonObject.getString("MaUD"),jsonObject.getString("Ten"), jsonObject.getInt("GiamGia")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                voucherAdapter.notifyDataSetChanged();
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisPlayVoucherActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

}