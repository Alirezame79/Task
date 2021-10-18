package com.method.task.model

import android.content.Context
import android.content.SharedPreferences

class IdPreferences(context: Context?) {

    private var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    init {
        sharedPreferences = context?.getSharedPreferences("id", Context.MODE_PRIVATE)
        editor = sharedPreferences?.edit()
    }

    fun getId(): Int {
        return sharedPreferences?.getInt("last_id", 1000)!!
    }

    fun putId(id: Int?) {
        if (id != null) {
            editor?.putInt("last_id", id)
        }
        editor?.apply()
    }

}
