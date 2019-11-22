package com.example.fourtitudeasia.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import com.example.fourtitudeasia.constants.Constants
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.example.fourtitudeasia.repository.Repository
import com.example.fourtitudeasia.util.SingleLiveEvent

class SharedViewModel(application: Application) : BaseAndroidViewModel(application){
    val onClickAddEvent: SingleLiveEvent<View> = SingleLiveEvent()

    var repository:Repository

     val objectRecipe: RecipeTypeModel
     get() = arguments.get(Constants.RECIPE_OBJ) as RecipeTypeModel

    init {
        repository= Repository(application,compositeDisposable)
    }

    fun getIngredient():ArrayList<String>{
        return objectRecipe.ingredients
    }
    fun getSteps():ArrayList<String>{
        return objectRecipe.step
    }

    fun deleteRecipe(recipeTypeModel: RecipeTypeModel) {
        return repository.deleteData(recipeTypeModel)
    }

    fun getStatus(): LiveData<String> {
        return repository.getStatus()
    }
    fun onClickAddNewRecipe(v:View){
        onClickAddEvent.postValue(v)
    }
     fun getAll(): LiveData<List<RecipeTypeModel>> {
         return repository.getAll()
     }

     fun getByType(type:String): LiveData<List<RecipeTypeModel>> {
         return repository.getByTpe(type)
     }


     fun findRecipe(type:String,recipeName:String): LiveData<RecipeTypeModel> {
         return repository.findRecipe(type,recipeName)
     }


}