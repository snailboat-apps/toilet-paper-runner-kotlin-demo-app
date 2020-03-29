package com.omnitracs.tprunner.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class TPInventoryItemKotlin(
        @SerializedName("TPInfo") val tpInfo: TPInfoKotlin,
        @SerializedName("rollCount") var rollCount : Int,
        @SerializedName("numInStock") var numInStock : Int
) : Parcelable {


}