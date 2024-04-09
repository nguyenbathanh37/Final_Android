package com.example.final_android.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.final_android.Interface.ChangeNumberItemListener;
import com.example.final_android.Model.PopularModel;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(PopularModel item) {
        ArrayList<PopularModel> listFood = getListCart();
        boolean existAlready = false;
        int n=0;
        for (int i = 0; i < listFood.size(); i++) {
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n= i;
                break;
            }
        }

        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }

        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularModel> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<PopularModel> listFood, int position, ChangeNumberItemListener changeNumberItemListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();
    }

    public void minusNumberFood(ArrayList<PopularModel> listFood, int position, ChangeNumberItemListener changeNumberItemListener) {
        if(listFood.get(position).getNumberInCart() ==1)
            listFood.remove(position);
        else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<PopularModel> listfood = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee+(listfood.get(i).getFee() *listfood.get(i).getNumberInCart());
        }

        return fee;
    }

    public Integer getTotalItem() {
        ArrayList<PopularModel> listfood = getListCart();
        int total = 0;
        for (int i = 0; i < listfood.size(); i++) {
            total = total + listfood.get(i).getNumberInCart();
        }
        return total;
    }

}
