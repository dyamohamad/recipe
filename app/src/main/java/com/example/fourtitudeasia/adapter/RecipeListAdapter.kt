package com.example.fourtitudeasia.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudeasia.`interface`.onClick
import com.example.fourtitudeasia.holder.ItemRecipeHolder
import com.example.fourtitudeasia.model.RecipeTypeModel

class RecipeListAdapter(var recipeModel: List<RecipeTypeModel>?):
    RecyclerView.Adapter<ItemRecipeHolder>() {
     lateinit var  listener: onClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemRecipeHolder {
        return ItemRecipeHolder.create(parent)
    }

    fun update(list: List<RecipeTypeModel>?){
        recipeModel = list
    }

    override fun getItemCount(): Int {
        return recipeModel?.size ?: 0
    }

    override fun onBindViewHolder(holder: ItemRecipeHolder, position: Int) {
        recipeModel?.let {
            holder.bindTo(recipeModel!![position])
        }
    }

}