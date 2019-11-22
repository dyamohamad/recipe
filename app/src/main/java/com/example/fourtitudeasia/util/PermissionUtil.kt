package com.example.fourtitudeasia.util

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


object PermissionUtil {
    private fun hasPermissions(activity: Activity, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
    }

    fun checkPermission(activity: Activity,
                        requestCode: RequestCode,
                        vararg permissions: String, grantedListener: ()-> Unit) {
        if (!hasPermissions(activity, *permissions)) {
            ActivityCompat.requestPermissions(activity, permissions, requestCode.value)
        } else {
            grantedListener()
        }
    }



}