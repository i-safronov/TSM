package com.mobile.finsolve.app.tsm.data.store

import platform.Foundation.NSUserDefaults

class IosTSMPreferences : TSMPreferences {

    private val defaults = NSUserDefaults.standardUserDefaults

    override fun getString(key: String, default: String): String =
        defaults.stringForKey(key) ?: default

    override fun putString(key: String, value: String) =
        defaults.setObject(value, key)

    override fun getInt(key: String, default: Int): Int =
        if (defaults.objectForKey(key) != null)
            defaults.integerForKey(key).toInt()
        else default

    override fun putInt(key: String, value: Int) =
        defaults.setInteger(value.toLong(), key)

    override fun getBool(key: String, default: Boolean): Boolean =
        if (defaults.objectForKey(key) != null)
            defaults.boolForKey(key)
        else default

    override fun putBool(key: String, value: Boolean) =
        defaults.setBool(value, key)

    override fun remove(key: String) =
        defaults.removeObjectForKey(key)

    override fun clear() {
        defaults.dictionaryRepresentation().keys.forEach { key ->
            defaults.removeObjectForKey(key as String)
        }
    }
}

private val iosPreferences by lazy { IosTSMPreferences() }

actual fun tsmPreferences(): TSMPreferences = iosPreferences