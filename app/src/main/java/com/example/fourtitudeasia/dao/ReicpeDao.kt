package com.example.fourtitudeasia.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fourtitudeasia.model.RecipeTypeModel

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_table")
    fun getAll():  LiveData<List<RecipeTypeModel>>


    @Query("SELECT * FROM recipe_table WHERE type LIKE :type")
    fun findByType(type: String): LiveData<List<RecipeTypeModel>>

    @Query("SELECT * FROM recipe_table WHERE type LIKE :type  AND name LIKE :recipeName")
    fun findIngredient(type: String,recipeName:String): LiveData<RecipeTypeModel>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list:ArrayList<RecipeTypeModel>)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertObject(list:RecipeTypeModel)

    @Delete
    fun delete(todo: RecipeTypeModel)

    @Update
    fun update(vararg todos: RecipeTypeModel)

    @Query("DELETE FROM recipe_table")
    abstract fun deleteAll()
}