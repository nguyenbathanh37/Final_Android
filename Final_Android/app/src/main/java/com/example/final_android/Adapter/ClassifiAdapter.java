package com.example.final_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_android.HomeActivity;
import com.example.final_android.Model.PopularModel;
import com.example.final_android.R;

import java.util.ArrayList;

public class ClassifiAdapter extends RecyclerView.Adapter<ClassifiAdapter.ViewHolder>{
    private ArrayList<PopularModel> list;
    private Context context;

    public ClassifiAdapter(ArrayList<PopularModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularModel item = list.get(position);
        Glide.with(context).load(item.getPic()).into(holder.imgProduct);
        holder.titleProduct.setText(item.getTitle());
        holder.feeProduct.setText(String.valueOf((int) Math.round(item.getFee())+ " đ"));


        //xét click orderNowBtn
        holder.orderNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HomeActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView titleProduct, feeProduct, orderNowBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            titleProduct = itemView.findViewById(R.id.titleProduct);
            feeProduct = itemView.findViewById(R.id.feeProduct);
            orderNowBtn = itemView.findViewById(R.id.orderNowBtn);
        }
    }
}
