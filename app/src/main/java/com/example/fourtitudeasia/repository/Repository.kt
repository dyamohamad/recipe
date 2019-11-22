package com.example.fourtitudeasia.repository

import androidx.lifecycle.LiveData
import com.example.fourtitudeasia.dao.RecipeDao
import com.example.fourtitudeasia.model.RecipeTypeModel
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.android.roomwordssample.AppDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class Repository (application: Application,private val compositeDisposable: CompositeDisposable){
    var recipeDao: RecipeDao? = null
    var mAllRecipe: LiveData<List<RecipeTypeModel>>? = null
    val status: MutableLiveData<String> = MutableLiveData()
    init {
        var db = AppDatabase.getDatabase(application)
        recipeDao = db!!.dao()
    }

    fun getStatus(): LiveData<String> {
        return status
    }

    fun getAll(): LiveData<List<RecipeTypeModel>> {
        return recipeDao!!.getAll()
    }

    fun getByTpe(type:String):LiveData<List<RecipeTypeModel>> {
        return recipeDao!!.findByType(type)
    }

    fun findRecipe(type: String,recipeName:String):LiveData<RecipeTypeModel>{
        return recipeDao!!.findIngredient(type,recipeName)
    }

//    fun deleteRecipe(recipeTypeModel: RecipeTypeModel){
//        return recipeDao!!.delete(recipeTypeModel)
//    }


    fun deleteData(recipeObject:RecipeTypeModel){
        compositeDisposable.add(
            Single.fromCallable {
                //operation
                recipeDao!!.delete(recipeObject)
            }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    status.postValue("success")
                    //success
                }, {
                    it.printStackTrace()
                }))
    }

}