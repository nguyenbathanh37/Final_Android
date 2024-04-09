package com.example.final_android.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
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
import com.example.final_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class VoucherFragment extends Fragment {
    private RecyclerView recyclerViewVoucher;
    private VoucherAdapter voucherAdapter;
    private ArrayList<VoucherModel> listVoucher = new ArrayList<>();

    //link api get_voucher
    private String urlGetVoucher = "http://192.168.2.9/Android_API/GET/get_voucher.php ";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_voucher, container, false);

        // ánh xạ recyclerView
        recyclerViewVoucher = v.findViewById(R.id.recyclerViewVoucher);
        getData(urlGetVoucher);
        setData();
        return v;
    }

    private void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerViewVoucher.setLayoutManager(linearLayoutManager);

        voucherAdapter = new VoucherAdapter(listVoucher, getContext(),getActivity().getIntent().getStringExtra("username"));
        recyclerViewVoucher.setAdapter(voucherAdapter);
    }


    //hàm gọi API lấy dữ liệu các voucher
    private void getData(String urlAPI){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
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
                        Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}