package com.bclccasino.game.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {
    private const val PREFS_NAME = "prefs"
    private const val URL = "url"
    private const val RESULT = "result"

    private lateinit var sharedPref : SharedPreferences

    fun getPreferences(context : Context) : SharedPreferences{
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPref
    }

    fun setUrl(url : String){
        sharedPref.edit().putString(URL, url).apply()
    }

    fun getUrl() : String{
        return sharedPref.getString(URL, "") ?: ""
    }

    fun setLastRes(result : Int){
        sharedPref.edit().putInt(RESULT, result).apply()
    }

    fun getLastRes() : Int{
        return sharedPref.getInt(RESULT, 0)
    }
}