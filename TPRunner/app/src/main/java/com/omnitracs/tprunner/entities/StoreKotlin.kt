package com.omnitracs.tprunner.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class StoreKotlin(
    var address : String,
    val name : String,
    val tpInventory : List<TPInventoryItem>
) : Parcelable {
    var infectedEmployees : Int = 0
        private set

    fun incrementInfectedEmployees() {
        infectedEmployees++
    }

    constructor(name: String, address: String = "No addr") : this(name, address, ArrayList<TPInventoryItem>())

    fun getTotalBrands() : Int {
        val brands = hashSetOf<TPInfo.Brand>()
        for (tpInventoryItem in tpInventory) {
            brands.add(tpInventoryItem.tpInfo.brand)
        }

        return brands.size
    }

    fun totalBrands() : Int =
        tpInventory.map { it.tpInfo.brand }.toSet().size
}