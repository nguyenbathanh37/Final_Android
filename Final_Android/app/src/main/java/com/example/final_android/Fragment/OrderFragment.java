package com.example.final_android.Fragment;

import static com.facebook.FacebookSdk.getApplicationContext;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.Adapter.OrderAdapter;
import com.example.final_android.Model.OrderModel;
import com.example.final_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class OrderFragment extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewOrder;
    private ArrayList<OrderModel> order = new ArrayList<>();

    // API
    private String urlAPI = "http://192.168.2.9/Android_API/GET/get_order.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_order, container, false);

        // ánh xạ recyclerView
        recyclerViewOrder = v.findViewById(R.id.recyclerViewOrder);

//        getData(urlAPI);
        getData();
        recyclerViewOrder();
        return v;
    }

    private void recyclerViewOrder() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerViewOrder.setLayoutManager(linearLayoutManager);

        adapter = new OrderAdapter(order,getContext());
        recyclerViewOrder.setAdapter(adapter);
    }


    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // write code here
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                order.add(new OrderModel(jsonObject.getString("MaHD"),jsonObject.getString("Ngay"), jsonObject.getInt("SoLuong"),jsonObject.getDouble("TongTien")));
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
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                String mUser = getActivity().getIntent().getStringExtra("username");
                paramV.put("UserName", mUser);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }

}