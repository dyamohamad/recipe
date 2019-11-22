package com.example.fourtitudeasia.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.fourtitudeasia.util.TypeConverter
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "recipe_table")

@Parcelize
data class RecipeTypeModel(

    @PrimaryKey
    @ColumnInfo(name = "id") var id: String,

    @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "picture") var picture: String,
    @TypeConverters(TypeConverter::class)
    @ColumnInfo(name = "ingredients")
    var ingredients: ArrayList<String>,
    @TypeConverters(TypeConverter::class)
    @ColumnInfo(name = "step")
    var step: ArrayList<String>
):Parcelable




