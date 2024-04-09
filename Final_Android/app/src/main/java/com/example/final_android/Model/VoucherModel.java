package com.example.final_android.Model;

public class VoucherModel {
    private String codeVoucher;
    private String nameVoucher;
    private int valueVoucher;

    public VoucherModel(String codeVoucher, String nameVoucher, int valueVoucher) {
        this.codeVoucher = codeVoucher;
        this.nameVoucher = nameVoucher;
        this.valueVoucher = valueVoucher;
    }

    public String getCodeVoucher() {
        return codeVoucher;
    }

    public void setCodeVoucher(String codeVoucher) {
        this.codeVoucher = codeVoucher;
    }

    public String getNameVoucher() {
        return nameVoucher;
    }

    public void setNameVoucher(String nameVoucher) {
        this.nameVoucher = nameVoucher;
    }

    public int getValueVoucher() {
        return valueVoucher;
    }

    public void setValueVoucher(int valueVoucher) {
        this.valueVoucher = valueVoucher;
    }
}
