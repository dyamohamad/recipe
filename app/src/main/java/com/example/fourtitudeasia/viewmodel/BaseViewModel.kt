package com.example.fourtitudeasia.viewmodel


import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseAndroidViewModel(application: Application): AndroidViewModel(application) {
    protected val arguments: Bundle = Bundle()
    protected val compositeDisposable = CompositeDisposable()
    fun addBundle(bundle: Bundle?) {
        arguments.putAll(bundle)
    }
    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}