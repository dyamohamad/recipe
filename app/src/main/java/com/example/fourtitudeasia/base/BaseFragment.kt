package com.example.fourtitudeasia.base

import android.os.Bundle
import androidx.fragment.app.Fragment
abstract class BaseFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        try {
            super.onStart()
        } catch (e: Exception) {
        }

    }
}