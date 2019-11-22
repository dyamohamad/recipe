package com.example.fourtitudeasia.util

import androidx.room.TypeConverter
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import java.util.Collections.emptyList
import java.util.Collections.emptyList
import java.util.Collections.emptyList
import kotlin.collections.ArrayList


class TypeConverter {

    @TypeConverter
    fun fromString(value:String):ArrayList<String>{
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list:ArrayList<String>):String{
        val gson = Gson()
        return gson.toJson(list)
    }



    @TypeConverter
    fun fromObject(value:String):ArrayList<RecipeTypeModel>{
        val listType = object : TypeToken<ArrayList<RecipeTypeModel>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListObject(list:ArrayList<RecipeTypeModel>):String{
        val gson = Gson()
        return gson.toJson(list)
    }
}