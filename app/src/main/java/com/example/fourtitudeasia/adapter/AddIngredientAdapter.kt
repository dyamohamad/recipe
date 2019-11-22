package com.example.fourtitudeasia.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudeasia.`interface`.onClick
import com.example.fourtitudeasia.holder.ItemAddHolder
import com.example.fourtitudeasia.holder.ItemNameHolder

class AddIngredientStepAdapter(var nameList: List<String>?):
    RecyclerView.Adapter<ItemAddHolder>() {
    lateinit var  listener: onClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAddHolder {
        return ItemAddHolder.create(parent)
    }

    fun update(list: List<String>?){
        nameList = list
    }

    override fun getItemCount(): Int {
        return nameList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ItemAddHolder, position: Int) {
        nameList?.let {
            holder.bindTo(nameList!![position])
        }
    }

}