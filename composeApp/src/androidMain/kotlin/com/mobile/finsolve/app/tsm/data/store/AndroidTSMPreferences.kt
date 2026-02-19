package com.mobile.finsolve.app.tsm.data.store

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val PREFS_NAME = "tsm_prefs"

class AndroidTSMPreferences(context: Context) : TSMPreferences {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun getString(key: String, default: String): String =
        prefs.getString(key, default) ?: default

    override fun putString(key: String, value: String) =
        prefs.edit { putString(key, value) }

    override fun getInt(key: String, default: Int): Int =
        prefs.getInt(key, default)

    override fun putInt(key: String, value: Int) =
        prefs.edit { putInt(key, value) }

    override fun getBool(key: String, default: Boolean): Boolean =
        prefs.getBoolean(key, default)

    override fun putBool(key: String, value: Boolean) =
        prefs.edit { putBoolean(key, value) }

    override fun remove(key: String) =
        prefs.edit { remove(key) }

    override fun clear() =
        prefs.edit { clear() }
}

object TSMPreferencesHolder {
    private lateinit var instance: TSMPreferences

    fun init(context: Context) {
        instance = AndroidTSMPreferences(context)
    }

    fun get(): TSMPreferences = instance
}

actual fun tsmPreferences(): TSMPreferences = TSMPreferencesHolder.get()
