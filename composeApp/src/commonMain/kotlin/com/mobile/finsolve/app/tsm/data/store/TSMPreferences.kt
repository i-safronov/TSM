package com.mobile.finsolve.app.tsm.data.store

interface TSMPreferences {
    fun getString(key: String, default: String): String
    fun putString(key: String, value: String)

    fun getInt(key: String, default: Int): Int
    fun putInt(key: String, value: Int)

    fun getBool(key: String, default: Boolean): Boolean
    fun putBool(key: String, value: Boolean)

    fun remove(key: String)
    fun clear()
}

expect fun tsmPreferences(): TSMPreferences