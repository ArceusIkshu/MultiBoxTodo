package com.example.multicheckboxtodo.data.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class TodoConverters {

    @TypeConverter
    fun fromBooleanList(value: List<Boolean>): String{
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toBooleanList(value: String): List<Boolean>{
        val gson = Gson()
        val listType = object : TypeToken<List<Boolean>>() {}.type
        return Gson().fromJson(value, listType)
    }

}
