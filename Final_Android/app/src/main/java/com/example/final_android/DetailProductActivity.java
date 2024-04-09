package com.example.final_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.final_android.Helper.ManagementCart;
import com.example.final_android.Model.PopularModel;

import java.io.Serializable;

public class DetailProductActivity extends AppCompatActivity implements Serializable {
    private TextView addToCartBtn;
    private TextView titleDetailProduct, priceDetailProduct, descriptionDetailProduct, numberDetailProduct;
    ImageView imageDetailProduct, minus, plus;

    //tạo biến để tính toán khi nhấn vào hai nút tăng giảm
    int numberOrder = 1;

    //đối tượng PopularModel
    private PopularModel object;

    // management cart
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        // tạo đối tượng management
        managementCart = new ManagementCart(this);

        // hàm ánh xạ các textview, imageview
        initView();
        receiveData();
        clickUp();
        clickDown();

        setClickAddToCartBtn();
    }


    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleDetailProduct = findViewById(R.id.titleDetailProduct);
        priceDetailProduct = findViewById(R.id.priceDetailProduct);
        descriptionDetailProduct = findViewById(R.id.descriptionDetailProduct);
        numberDetailProduct = findViewById(R.id.numberDetailProduct);
        imageDetailProduct = findViewById(R.id.imageDetailProduct);
        minus = findViewById(R.id.minus);
        plus = findViewById(R.id.plus);
    }

    private void receiveData() {
        Intent intent = getIntent();
        // ép kiểu trả về là một popularModel
        object = (PopularModel) intent.getParcelableExtra("data");
        //gán các giá trị
        titleDetailProduct.setText(object.getTitle());
        priceDetailProduct.setText(String.valueOf((int) Math.round(object.getFee()))+ " đ");
        Glide.with(this).load(object.getPic()).into(imageDetailProduct);
        descriptionDetailProduct.setText(object.getDescription());
        numberDetailProduct.setText("1");

    }

    // xét click cho 2 nút tăng giảm
    //tăng
    private void clickUp() {
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder + 1;
                numberDetailProduct.setText(String.valueOf(numberOrder));
            }
        });
    }

    //giảm
    private void clickDown() {
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder >1)
                    numberOrder = numberOrder - 1;

                numberDetailProduct.setText(String.valueOf(numberOrder));
            }
        });
    }

    // xét click cho nút addToCartBtn (thêm vào giỏ hàng)
    private void setClickAddToCartBtn() {
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertFood(object);
            }
        });
    }


}