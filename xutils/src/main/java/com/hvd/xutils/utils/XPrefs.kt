package com.hvd.xutils.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class XPrefs(val app: Application) {

    private var prefs = app.getSharedPreferences("xframework_prefs", Context.MODE_PRIVATE)

    fun put(key: String, value: String) {
        prefs.edit().putString(key, value).apply()
    }

    fun put(key: String, value: Boolean) {
        prefs.edit().putBoolean(key, value).apply()
    }

    fun put(key: String, value: Int) {
        prefs.edit().putInt(key, value).apply()
    }

    fun put(key: String, value: Long) {
        prefs.edit().putLong(key, value).apply()
    }


    fun put(key: String, value: Float) {
        prefs.edit().putFloat(key, value).apply()
    }

    fun put(key: String, value: MutableSet<String>) {
        prefs.edit().putStringSet(key, value).apply()
    }

    fun getString(key: String): String {
        return prefs.getString(key, "")!!
    }

    fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun getInt(key: String): Int {
        return prefs.getInt(key, Int.MIN_VALUE)
    }

    fun getLong(key: String): Long {
        return prefs.getLong(key, Long.MIN_VALUE)
    }

    fun getStringSet(key: String): MutableSet<String> {
        return prefs.getStringSet(key, mutableSetOf())!!
    }
}