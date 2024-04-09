package com.example.final_android.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class PopularModel implements Parcelable {
    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int numberInCart;

    public PopularModel(String title, String pic, Double fee) {
        this.title = title;
        this.pic = pic;
        this.fee = fee;
    }
    public PopularModel(String title, String pic, String description, Double fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }
    public PopularModel(String title, String pic, String description, Double fee, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }


    protected PopularModel(Parcel in) {
        title = in.readString();
        pic = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            fee = null;
        } else {
            fee = in.readDouble();
        }
    }

    public static final Creator<PopularModel> CREATOR = new Creator<PopularModel>() {
        @Override
        public PopularModel createFromParcel(Parcel in) {
            return new PopularModel(in);
        }

        @Override
        public PopularModel[] newArray(int size) {
            return new PopularModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(pic);
        parcel.writeString(description);
        if (fee == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(fee);
        }
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}

