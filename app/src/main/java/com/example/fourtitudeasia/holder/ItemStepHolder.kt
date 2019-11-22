package com.example.fourtitudeasia.holder

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.fourtitudeasia.R
import com.example.fourtitudeasia.constants.Constants
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.example.fourtitudeasia.ui.ScrollingFragment
import com.work.cyberbully.ui.main.GlideApp
import kotlinx.android.synthetic.main.item_name.view.*
import kotlinx.android.synthetic.main.item_name.view.tv_name
import kotlinx.android.synthetic.main.item_recipe.view.*
import kotlinx.android.synthetic.main.item_steps.view.*


class ItemStepHolder(var view: View):
    RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): ItemStepHolder {



            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_steps, parent, false)
            return ItemStepHolder(view)
        }
    }

    fun bindTo(name: String,position:Int) {
        view.tv_count.text=(position+1).toString()
        view.tv_name.text=name



    }
}