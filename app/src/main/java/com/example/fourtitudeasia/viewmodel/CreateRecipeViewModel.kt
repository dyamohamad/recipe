package com.example.fourtitudeasia.viewmodel

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.example.fourtitudeasia.repository.CreateResipeRepository
import com.example.fourtitudeasia.repository.Repository
import com.example.fourtitudeasia.util.SingleLiveEvent

class CreateRecipeViewModel(application: Application) : BaseAndroidViewModel(application) {
    val onClickAddEvent: SingleLiveEvent<View> = SingleLiveEvent()
    val onClickAddIngredient: SingleLiveEvent<View> = SingleLiveEvent()
    var repository: CreateResipeRepository

    init {
        repository = CreateResipeRepository(application, compositeDisposable)
    }


    fun insertObjectCreated(recipe:RecipeTypeModel){
        repository.insertData(recipe)
    }

    fun getStatus():LiveData<String>{
        return repository.status
    }



    fun onClickAddImage(v: View) {
        onClickAddEvent.postValue(v)
    }


    fun onClickAddIngredient(v: View) {
        onClickAddIngredient.postValue(v)
    }

}