package com.example.fourtitudeasia.util

import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object ImageUtil {

     fun getRealPathFromURI(context: Context, contentURI: String): String? {
        val contentUri = Uri.parse(contentURI)
        val cursor = context.contentResolver.query(contentUri, null, null, null, null)
        if (cursor == null) {
            return contentUri.path
        } else {
            cursor.moveToFirst()
            val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(index)
        }
    }
}