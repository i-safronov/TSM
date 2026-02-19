package com.mobile.finsolve.app.tsm.data.store

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PersistedDelegate<T>(
    private val prefs: TSMPreferences,
    private val value: T,
) : ReadWriteProperty<Any?, T> {

    private lateinit var key: String

    // Захватываем имя свойства при объявлении
    operator fun provideDelegate(thisRef: Any?, property: KProperty<*>): PersistedDelegate<T> {
        key = property.name
        return this
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = when (value) {
        is String  -> prefs.getString(key, value) as T
        is Int     -> prefs.getInt(key, value) as T
        is Boolean -> prefs.getBool(key, value) as T
        else       -> error("persisted: unsupported type ${value!!::class.simpleName}")
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = when (value) {
        is String  -> prefs.putString(key, value)
        is Int     -> prefs.putInt(key, value)
        is Boolean -> prefs.putBool(key, value)
        else       -> error("persisted: unsupported type ${value!!::class.simpleName}")
    }
}

inline fun <reified T> persisted(
    value: T,
    prefs: TSMPreferences = tsmPreferences(),
): PersistedDelegate<T> = PersistedDelegate(prefs, value)