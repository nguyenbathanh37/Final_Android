package com.example.final_android.Model;

public class OrderModel {
    private String idOrder;
    private String dateOrder;
    private Integer quantityOrder;
    private Double totalOrder;

    public OrderModel(String idOrder, String dateOrder, Integer quantityOrder, Double totalOrder) {
        this.idOrder = idOrder;
        this.dateOrder = dateOrder;
        this.quantityOrder = quantityOrder;
        this.totalOrder = totalOrder;
    }


    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Integer getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(Integer quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public Double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Double totalOrder) {
        this.totalOrder = totalOrder;
    }
}
