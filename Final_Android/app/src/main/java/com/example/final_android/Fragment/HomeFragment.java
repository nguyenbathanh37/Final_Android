package com.example.final_android.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.Adapter.CategoryAdapter;
import com.example.final_android.Adapter.PopularAdapter;
import com.example.final_android.ClassifyProductActivity;
import com.example.final_android.Model.CategoryModel;
import com.example.final_android.Model.PopularModel;
import com.example.final_android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {

    TextView textView;
    private RecyclerView.Adapter adapter, adapter1;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    private ArrayList<PopularModel> popular = new ArrayList<>();
    String username;
    String address,demo;
    TextView address1;

    //API
    private String urlAPI = "http://192.168.2.9/Android_API/GET/get_product.php";
    private String urlGetAddress = "http://192.168.2.9/Android_API/GET/get_address.php";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        // Inflate the layout for this fragment

        //ánh xạ  recyclerView
        recyclerViewCategoryList = v.findViewById(R.id.recyclerView);
        recyclerViewPopularList = v.findViewById(R.id.recyclerView1);
        address1 = v.findViewById(R.id.address);

        //lấy tên của người dùng
        username = getActivity().getIntent().getStringExtra("username");
        textView = v.findViewById(R.id.textView);
        textView.setText("Hi, "+username);


        //hàm đưa dữ liệu lên Category
        recyclerViewCategory();
        recyclerViewPopular();

        //getData
        getData(urlAPI);
        recyclerViewPopular();
        getAddress();
        address1.setText(demo);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    // hàm xét recyclerview của Category
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryModel> category = new ArrayList<>();
        category.add(new CategoryModel("Hot Drink", R.drawable.cat_1));
        category.add(new CategoryModel("Cold Drink", R.drawable.cat_2));
        category.add(new CategoryModel("Bánh ngọt", R.drawable.cat_3));
        category.add(new CategoryModel("Đồ ăn", R.drawable.cat_4));

        adapter = new CategoryAdapter(category,getContext());
        recyclerViewCategoryList.setAdapter(adapter);

    }
    // hàm xét recyclerview của Popular
    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        adapter1 = new PopularAdapter(popular,getActivity());
        recyclerViewPopularList.setAdapter(adapter1);
    }


    //hàm gọi API lấy dữ liệu các sản phẩm
    private void getData(String urlAPI){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlAPI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
//
                        popular.add(new PopularModel(jsonObject.getString("TenSP"),jsonObject.getString("HinhAnh"), jsonObject.getString("MoTa"),jsonObject.getDouble("GiaSP")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter1.notifyDataSetChanged();
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

    //lấy địa chỉ giao hàng
    private void getAddress() {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlGetAddress,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // write code here

                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(response);
                            JSONObject jsonObject = jsonArray.getJSONObject(0);
                            address = jsonObject.getString("DiaChi");
                            demo= address;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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
                paramV.put("UserName", username);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }

}