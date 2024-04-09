package com.example.final_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_android.ClassifyProductActivity;
import com.example.final_android.HomeActivity;
import com.example.final_android.Model.CategoryModel;
import com.example.final_android.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<CategoryModel> categoryModelList;
    private Context context;
    String maLoai;
    int pic;
    String name;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModelList, Context context, String maLoai, int pic, String name) {
        this.categoryModelList = categoryModelList;
        this.context = context;
        this.maLoai = maLoai;
        this.pic = pic;
        this.name = name;
    }

    public CategoryAdapter(ArrayList<CategoryModel> categoryModelList, Context context) {
        this.categoryModelList = categoryModelList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel categoryModel = categoryModelList.get(position);
        holder.categoryName.setText(categoryModel.getTitle());
        holder.categoryPic.setImageResource(categoryModel.getPic());
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categoryModel.getTitle()== "Hot Drink") {
                    maLoai = "TN";
                    pic = categoryModel.getPic();
                    name = "Đồ uống nóng";
                }
                else if(categoryModel.getTitle()== "Cold Drink") {
                    maLoai = "TL";
                    pic = categoryModel.getPic();
                    name = "Đồ uống lạnh";
                }
                else if(categoryModel.getTitle()== "Bánh ngọt") {
                    maLoai = "BN";
                    pic = categoryModel.getPic();
                    name = "Bánh ngọt";
                }
                else if(categoryModel.getTitle()== "Đồ ăn") {
                    maLoai = "F";
                    pic = categoryModel.getPic();
                    name = "Đồ ăn";
                }
                Intent intent = new Intent(context, ClassifyProductActivity.class);
                intent.putExtra("maLoai", maLoai);
                intent.putExtra("pic", pic);
                intent.putExtra("name", name);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName, orderNowBtn;
        ImageView categoryPic;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
