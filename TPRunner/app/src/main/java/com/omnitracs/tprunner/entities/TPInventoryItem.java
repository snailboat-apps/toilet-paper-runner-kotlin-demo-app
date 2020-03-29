package com.omnitracs.tprunner.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TPInventoryItem implements Parcelable {
    @SerializedName("TPInfo")
    private TPInfo mTPInfo;
    @SerializedName("rollCount")
    private int mRollCount;
    @SerializedName("numInStock")
    private int mNumInStock;

    public TPInventoryItem() {

    }

    public TPInfo getTPInfo() {
        return mTPInfo;
    }

    public void setTPInfo(TPInfo tpInfo) {
        mTPInfo = tpInfo;
    }

    public int getRollCount() {
        return mRollCount;
    }

    public void setRollCount(int rollCount) {
        mRollCount = rollCount;
    }

    public int getNumInStock() {
        return mNumInStock;
    }

    public void setNumInStock(int numInStock) {
        mNumInStock = numInStock;
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mRollCount);
        dest.writeInt(mNumInStock);
        dest.writeInt(mTPInfo.getBrand().ordinal());
        dest.writeString(mTPInfo.getName());
        dest.writeInt(mTPInfo.getPlyCount());
        dest.writeFloat(mTPInfo.getQualityRating());
    }

    public static final Parcelable.Creator<TPInventoryItem> CREATOR =
            new Parcelable.Creator<TPInventoryItem>() {
                public TPInventoryItem createFromParcel(Parcel src) {
                    TPInventoryItem rv = new TPInventoryItem();
                    rv.readFromParcel(src);
                    return rv;
                }

                public TPInventoryItem[] newArray(int size) {
                    return new TPInventoryItem[size];
                }
            };

    private void readFromParcel(Parcel src) {
        mRollCount = src.readInt();
        mNumInStock = src.readInt();
        mTPInfo = new TPInfo();
        TPInfo.Brand brand = TPInfo.Brand.values()[src.readInt()];
        mTPInfo.setBrand(brand);
        mTPInfo.setName(src.readString());
        mTPInfo.setPlyCount(src.readInt());
        mTPInfo.setQualityRating(src.readFloat());
    }
}
