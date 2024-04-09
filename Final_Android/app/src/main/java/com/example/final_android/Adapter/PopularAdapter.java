package com.example.final_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_android.DetailProductActivity;
import com.example.final_android.Model.PopularModel;
import com.example.final_android.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    private ArrayList<PopularModel> popularModelList;
    private Context context;

    public PopularAdapter(ArrayList<PopularModel> popularModelList, Context context) {
        this.popularModelList = popularModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_popular, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularModel popularModel = popularModelList.get(position);
        holder.title.setText(popularModel.getTitle());
        holder.fee.setText(String.valueOf((int) Math.round(popularModel.getFee())));
        Glide.with(context).load(popularModel.getPic()).into(holder.pic);
        // xét onclick cho itemPopular
        holder.product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("data", popularModel); // chưa tìm được cách Fix
                context.startActivity(intent);
            }
        });

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailProductActivity.class);
                intent.putExtra("data",popularModel);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee, addBtn;
        ImageView pic;
        ConstraintLayout product;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tenSP);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
            product = itemView.findViewById(R.id.product);


        }
    }
}
