package com.omnitracs.tprunner

import com.omnitracs.tprunner.entities.Store
import com.omnitracs.tprunner.entities.TPInventoryItem

typealias TPInventory = List<TPInventoryItem>

fun String.toXtremeString() : String {
    return this.replace("s", "z")
        .replace("ee", "yyy")
}

val String.Companion.EMPTY: String
    get() = ""
