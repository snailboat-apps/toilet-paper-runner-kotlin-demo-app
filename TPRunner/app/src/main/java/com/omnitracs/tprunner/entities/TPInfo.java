package com.omnitracs.tprunner.entities;

import androidx.annotation.StringRes;

import com.google.gson.annotations.SerializedName;
import com.omnitracs.tprunner.R;

public class TPInfo {
    public enum Brand {
        Other,
        Charmin,
        AngelSoft,
        Cottonelle;

        public @StringRes int getBrandName() {
            int retVal;
            switch (this) {
                case Charmin:
                    retVal = R.string.brand_charmin;
                    break;
                case AngelSoft:
                    retVal = R.string.brand_angelsoft;
                    break;
                case Cottonelle:
                    retVal = R.string.brand_cottonelle;
                    break;
                default:
                    retVal = R.string.brand_other;
                    break;
            }

            return retVal;
        }
    }

    @SerializedName("brand")
    private Brand mBrand;
    @SerializedName("name")
    private String mName;
    @SerializedName("plyCount")
    private int mPlyCount;
    @SerializedName("qualityRating")
    private float mQualityRating;

    public TPInfo() {
    }

    public Brand getBrand() {
        return mBrand;
    }

    public void setBrand(Brand brand) {
        mBrand = brand;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPlyCount() {
        return mPlyCount;
    }

    public void setPlyCount(int plyCount) {
        mPlyCount = plyCount;
    }

    public float getQualityRating() {
        return mQualityRating;
    }

    public void setQualityRating(float qualityRating) {
        mQualityRating = qualityRating;
    }
}
