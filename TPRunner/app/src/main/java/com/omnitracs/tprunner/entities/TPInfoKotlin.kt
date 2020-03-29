package com.omnitracs.tprunner.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TPInfoKotlin(
    val brand : TPInfo.Brand,
    val name : String,
    val plyCount : Int,
    val qualityRating : Int
) : Parcelable {

}