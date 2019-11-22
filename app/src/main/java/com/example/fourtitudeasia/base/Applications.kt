package com.example.fourtitudeasia.base


import android.app.Application
import android.content.Context
import com.example.android.roomwordssample.AppDatabase

/**
 * Created by Eugene Low
 */
class BaseApplication : Application() {

    companion object {
        lateinit var instance: BaseApplication
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        AppDatabase.getDatabase(applicationContext)
    }


}