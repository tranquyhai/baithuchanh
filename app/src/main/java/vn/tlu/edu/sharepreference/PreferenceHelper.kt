package com.example.myapp

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

    fun saveUser(username: String, password: String) {
        sharedPreferences.edit().apply {
            putString("USERNAME", username)
            putString("PASSWORD", password)
            apply()
        }
    }

    fun getUser(): Pair<String?, String?> {
        val username = sharedPreferences.getString("USERNAME", null)
        val password = sharedPreferences.getString("PASSWORD", null)
        return Pair(username, password)
    }

    fun clearUser() {
        sharedPreferences.edit().clear().apply()

    }
}
