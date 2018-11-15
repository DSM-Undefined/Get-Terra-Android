package com.justgo.Util

import android.content.Context
import android.content.SharedPreferences


private fun getPref(context: Context): SharedPreferences {
    return context.getSharedPreferences("pref", Context.MODE_PRIVATE)
}

fun saveToken(context: Context, token: String?, isAccess: Boolean = true) {
    getPref(context).edit().let {
        it.putString(getKey(isAccess), token)
        it.apply()
    }
}

fun removeToken(context: Context, isAccess: Boolean = true) {
    getPref(context).edit().let {
        it.remove(getKey(isAccess))
        it.apply()
    }
}

fun getToken(context: Context, isAccess: Boolean = true): String {
    return "Bearer " + getPref(context).getString(getKey(isAccess), "")
}


fun setTeamStatus(context: Context, token: String?) {
    getPref(context).edit().let {
        it.putString("team", token)
        it.apply()
    }
}

fun getTeamStatus(context: Context): String {
    return getPref(context).getString("team", "")
}

private fun getKey(isAccess: Boolean): String = if (isAccess) "Access" else "Refresh"
