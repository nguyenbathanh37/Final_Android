package com.example.final_android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_android.Model.VoucherModel;
import com.example.final_android.R;
import com.example.final_android.ShoppingCartActivity;

import java.util.ArrayList;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.ViewHolder>{

    public static final String KEY_FULL_NAME_DATA_RESULT = "KEY_FULL_NAME_DATA_RESULT";

    private ArrayList<VoucherModel> listVoucher;
    private Context context;
    private String username;

    public VoucherAdapter(ArrayList<VoucherModel> listVoucher, Context context) {
        this.listVoucher = listVoucher;
        this.context = context;
    }

    public VoucherAdapter(ArrayList<VoucherModel> listVoucher, Context context, String username) {
        this.listVoucher = listVoucher;
        this.context = context;
        this.username = username;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_voucher, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VoucherModel voucher = listVoucher.get(position);
        holder.codeVoucher.setText(voucher.getCodeVoucher());
        holder.nameVoucher.setText(voucher.getNameVoucher());
        holder.valueVoucher.setText(String.valueOf(voucher.getValueVoucher()+" đ"));

        //khi click vào voucher nào đó
        holder.useVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShoppingCartActivity.class);
                intent.putExtra("valueVoucher",voucher.getValueVoucher());
                intent.putExtra("username", username);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVoucher.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView codeVoucher, nameVoucher, valueVoucher;
        TextView useVoucher;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            codeVoucher = itemView.findViewById(R.id.codeVoucher);
            nameVoucher = itemView.findViewById(R.id.nameVoucher);
            valueVoucher = itemView.findViewById(R.id.valueVoucher);
            useVoucher =itemView.findViewById(R.id.useVoucher);
        }
    }
}
