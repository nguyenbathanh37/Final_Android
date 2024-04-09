package com.example.final_android.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.final_android.MainActivity;
import com.example.final_android.Other.AboutMeActivity;
import com.example.final_android.Other.DieuKhoanActivity;
import com.example.final_android.Other.HoTroThanhToanActivity;
import com.example.final_android.Other.InfoActivity;
import com.example.final_android.Other.LienHeActivity;
import com.example.final_android.R;

public class SettingFragment extends Fragment {
    TextView logout;
    private LinearLayout dieukhoan, lienhe, hotro, aboutme, info;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        // Inflate the layout for this fragment

        // ánh xạ
        logout = v.findViewById(R.id.logout);
        dieukhoan = v.findViewById(R.id.dieukhoan);
        lienhe = v.findViewById(R.id.lienhe);
        hotro = v.findViewById(R.id.hotro);
        aboutme = v.findViewById(R.id.aboutme);
        info = v.findViewById(R.id.info);

        // sự kiện click
        clickDieukhoan();
        clickLienHe();
        clickHoTro();
        clickAboutMe();
        clickInfo();
        logOut();
        return v;
    }

    private void clickDieukhoan(){
        dieukhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DieuKhoanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickLienHe(){
        lienhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LienHeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickHoTro(){
        hotro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HoTroThanhToanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickAboutMe(){
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutMeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickInfo() {
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InfoActivity.class);
                intent.putExtra("username" ,getActivity().getIntent().getStringExtra("username"));
                startActivity(intent);
            }
        });
    }


    //xét sự kiện đăng xuất
    private void logOut() {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                getContext().startActivity(intent);
            }
        });
    }
}