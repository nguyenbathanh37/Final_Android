package com.example.final_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_android.Model.OrderModel;
import com.example.final_android.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private ArrayList<OrderModel> orderModelList;
    private Context context;

    public OrderAdapter(ArrayList<OrderModel> orderModelList, Context context) {
        this.orderModelList = orderModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderModel orderModel = orderModelList.get(position);
        holder.idOrder.setText("#" + orderModel.getIdOrder());
        holder.dateOrder.setText(orderModel.getDateOrder());
        holder.quantityOrder.setText(String.valueOf(orderModel.getQuantityOrder()));
        holder.priceOrder.setText(String.valueOf((int) Math.round(orderModel.getTotalOrder())) + " đ");

        //Glide.with(context).load(orderModel.getPic()).into(holder.imgOrder);

//        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(holder.itemView.getContext(), DetailProductActivity.class);
//                intent.putExtra("data",popularModel);
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return orderModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idOrder, dateOrder, quantityOrder, priceOrder, btnAdd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idOrder = itemView.findViewById(R.id.idOrder);
            dateOrder = itemView.findViewById(R.id.dateOrder);
            quantityOrder = itemView.findViewById(R.id.quantityOrder);
            priceOrder = itemView.findViewById(R.id.priceOrder);
            btnAdd = itemView.findViewById(R.id.btnAdd);

        }
    }
}
