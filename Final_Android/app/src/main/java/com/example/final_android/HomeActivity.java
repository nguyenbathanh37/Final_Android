package com.example.final_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.final_android.Fragment.HomeFragment;
import com.example.final_android.Fragment.OrderFragment;
import com.example.final_android.Fragment.SettingFragment;
import com.example.final_android.Fragment.VoucherFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    //khởi tạo các fragment
    BottomNavigationView bottom_navigation;
    HomeFragment homeFragment = new HomeFragment();
    OrderFragment orderFragment = new OrderFragment();
    ShoppingCartActivity shoppingCartActivity = new ShoppingCartActivity();

    ImageView shopping_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //bottom navigation
        bottom_navigation = findViewById(R.id.bottom_navigation);
        replaceFrament(new HomeFragment());

        itemSelectBottomNavigation();

        //hàm gọi sự kiện nhấn nútgiỏ hàng
        clickShopping_cart();
    }

    private void replaceFrament(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    //xử lý bottom navigation
    private void itemSelectBottomNavigation(){
        bottom_navigation.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        replaceFrament(new HomeFragment());

                        return true;
                    case R.id.oder:
                        replaceFrament(new OrderFragment());

                        return true;
                    case R.id.voucher:
                        replaceFrament(new VoucherFragment());

                        return true;
                    case R.id.setting:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container, settingFragment).commit();
                        replaceFrament(new SettingFragment());
                        return true;
                }
                return false;
            }
        });
    }

    //nhấn vòa giỏ hàng
    private void clickShopping_cart() {
        shopping_cart = findViewById(R.id.shopping_cart);
        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ShoppingCartActivity.class);
                intent.putExtra("username", getIntent().getStringExtra("username"));
                startActivity(intent);
                //finish();
            }
        });
    }

}