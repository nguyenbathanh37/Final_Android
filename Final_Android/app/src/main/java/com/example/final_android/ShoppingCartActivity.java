package com.example.final_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_android.Adapter.CartListAdapter;
import com.example.final_android.Helper.ManagementCart;
import com.example.final_android.Interface.ChangeNumberItemListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCartActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.Log_in.MESSAGE";
    public static final int REQUEST_CODE_GET_FULL_NAME = 101;

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    TextView totalProductTxt, shipPriceTxt, taxVatTxt, totalPriceTxt, emptyTxt, oderTxt;

    private ScrollView scrollView;

    //api
    private String urlOrder = "http://192.168.2.9/Android_API/POST/add_order.php";

    //textView sử lý việc thêm voucher
    TextView addVoucher, textMinus, addValueVoucher;

    // các biến để tính giá tiền
    private double tax, total, itemTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        //đói tượng managenment
        managementCart = new ManagementCart(this);

        //hàm ánh xạ
        intitView();
        initList();
        CalculateCart();
        handleAddVoucher();
        getData();
        clickOder();
    }

    private void intitView() {
        recyclerViewList = findViewById(R.id.recyclerViewList);
        totalProductTxt = findViewById(R.id.totalProductTxt);
        shipPriceTxt = findViewById(R.id.shipPriceTxt);
        taxVatTxt = findViewById(R.id.taxVatTxt);
        totalPriceTxt = findViewById(R.id.totalPriceTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);

        addVoucher = findViewById(R.id.addVoucher);
        textMinus = findViewById(R.id.textMinus);
        addValueVoucher = findViewById(R.id.addValueVoucher);


    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShoppingCartActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), ShoppingCartActivity.this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                CalculateCart();
            }
        }, this);

        recyclerViewList.setAdapter(adapter);
        if(managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {
        double percentTax = 0.08;
        double ship = 20000;
        tax = Math.round((managementCart.getTotalFee() *percentTax)*100)/100;
        total = Math.round((managementCart.getTotalFee()+ tax+ ship) *100)/100;
        itemTotal = Math.round(managementCart.getTotalFee()*100)/100;

        totalProductTxt.setText((int) Math.round(itemTotal)+ " đ");
        taxVatTxt.setText((int) Math.round(tax)+ " đ");
        shipPriceTxt.setText((int) Math.round(ship) + " đ");
        totalPriceTxt.setText((int) Math.round(total) + " đ");

    }

    //hàm xử lý việc thêm voucher
    private void handleAddVoucher(){
        addVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShoppingCartActivity.this, DisPlayVoucherActivity.class);
                //mới thêm
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivityForResult(intent,REQUEST_CODE_GET_FULL_NAME);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    //xử lý dữ liệu nhận về
    private void getData() {
        double valueVoucher = getIntent().getIntExtra("valueVoucher", 0);
        total = total- valueVoucher;
        addValueVoucher.setText(String.valueOf((int) Math.round(valueVoucher))+" đ");
        totalPriceTxt.setText((int) Math.round(total)+ " đ");
        textMinus.setVisibility(View.VISIBLE);

    }

    private void clickOder() {
        oderTxt = findViewById(R.id.oderTxt);
        oderTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postOrder();
            }
        });
    }

    private void postOrder(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String date_now = formatter.format(date);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlOrder,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // write code here
                        if(response.equals("success")) {
                            Toast.makeText(ShoppingCartActivity.this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ShoppingCartActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // write code here
                Toast.makeText(ShoppingCartActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("Ngay", date_now);
                paramV.put("TongTien", totalPriceTxt.getText().toString());
                paramV.put("UserName", getIntent().getStringExtra("username"));
                paramV.put("SoLuong", String.valueOf(managementCart.getTotalItem()));
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}