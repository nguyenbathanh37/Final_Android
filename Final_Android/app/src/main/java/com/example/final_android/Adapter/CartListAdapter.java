package com.example.final_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_android.Helper.ManagementCart;
import com.example.final_android.Interface.ChangeNumberItemListener;
import com.example.final_android.Model.PopularModel;
import com.example.final_android.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<PopularModel> popularModels;
    private ManagementCart managementCart;
    private ChangeNumberItemListener changeNumberItemListener;
    private Context context;

    public CartListAdapter(ArrayList<PopularModel> popularModels,Context context1 , ChangeNumberItemListener changeNumberItemListener,Context context) {
        this.popularModels = popularModels;
        this.managementCart = new ManagementCart(context1);
        this.changeNumberItemListener = changeNumberItemListener;
        this.context =context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //context = context.getApplicationContext();

        PopularModel item = popularModels.get(position);
        holder.titleTxt.setText(item.getTitle());
        holder.feeEachITem.setText(String.valueOf((int) Math.round(item.getFee())+ " đ"));

        //biến tính tổng tiền dựa trên số lượng sản phẩm thêm vào giỏ hàng
        double sum = ((item.getNumberInCart() *item.getFee())*100)/100;
        holder.totalEachItem.setText(String.valueOf(Math.round(sum))+ " đ");
        holder.numberCartTxt.setText(String.valueOf(item.getNumberInCart()));

        //hiển thị ảnh
        Glide.with(context).load(item.getPic()).into(holder.picCart1);

        //gọi hàm xử lý sự kiện click tăng giảm sản phẩm
        clickUpDown(holder, position);


    }

    @Override
    public int getItemCount() {
        return popularModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTxt, feeEachITem;
        ImageView picCart1, plusCartBtn, minusCartBtn;
        TextView totalEachItem, numberCartTxt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            feeEachITem = itemView.findViewById(R.id.feeEachItem);
            picCart1 = itemView.findViewById(R.id.picCart1);
            plusCartBtn = itemView.findViewById(R.id.plusCartBtn);
            minusCartBtn = itemView.findViewById(R.id.minusCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            numberCartTxt = itemView.findViewById(R.id.numberCartTxt);

        }
    }

    // hàm xử lý sự kiện click tăng giảm sản phẩm
    private void clickUpDown(ViewHolder holder, int n) {
        //xét khi nhấn click vào btn plus
        holder.plusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberFood(popularModels, n, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });

        //xét khi nhấn click vào btn minus
        holder.minusCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.minusNumberFood(popularModels, n, new ChangeNumberItemListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemListener.changed();
                    }
                });
            }
        });
    }
}
