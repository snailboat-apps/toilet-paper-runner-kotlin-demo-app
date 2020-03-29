package com.omnitracs.tprunner.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Store implements Parcelable {
    @SerializedName("address")
    private String mAddress;
    @SerializedName("name")
    private String mName;
    @SerializedName("TPInventoryItems")
    private List<TPInventoryItem> mTPInventory;

    public Store() {
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        name = mName;
    }

    public List<TPInventoryItem> getTPInventory() {
        return mTPInventory;
    }

    public void setTPInventory(List<TPInventoryItem> tpInventory) {
        mTPInventory = tpInventory;
    }

    public int getTotalPacks() {

        int totalPacks = 0;
        if (mTPInventory != null) {
            for (TPInventoryItem item : mTPInventory) {
                totalPacks += item.getNumInStock();
            }
        }
        return totalPacks;
    }

    public int getTotalBrands() {
        Set<TPInfo.Brand> brands = new HashSet<TPInfo.Brand>();
        if (mTPInventory != null) {
            for (TPInventoryItem item : mTPInventory) {
                brands.add(item.getTPInfo().getBrand());
            }
        }

        return brands.size();
    }

    // Parcelable implementation
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mAddress);
        dest.writeInt(mTPInventory.size());
        for (TPInventoryItem item : mTPInventory) {
            dest.writeParcelable(item, flags);
        }
    }

    public static final Parcelable.Creator<Store> CREATOR =
            new Parcelable.Creator<Store>() {
                public Store createFromParcel(Parcel src) {
                    Store rv = new Store();
                    rv.readFromParcel(src);
                    return rv;
                }

                public Store[] newArray(int size) {
                    return new Store[size];
                }
            };

    private void readFromParcel(Parcel src) {
        mName = src.readString();
        mAddress = src.readString();
        int inventorySize = src.readInt();
        ArrayList<TPInventoryItem> tpInventory = new ArrayList<>();
        for (int i = 0; i < inventorySize; i++) {
            tpInventory.add(src.readParcelable(TPInventoryItem.class.getClassLoader()));
        }
        mTPInventory = tpInventory;
    }
}
