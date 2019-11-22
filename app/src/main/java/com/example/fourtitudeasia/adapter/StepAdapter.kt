package com.example.fourtitudeasia.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudeasia.`interface`.onClick
import com.example.fourtitudeasia.holder.ItemNameHolder
import com.example.fourtitudeasia.holder.ItemStepHolder

class StepAdapter(var nameList: List<String>?):
    RecyclerView.Adapter<ItemStepHolder>() {
     lateinit var  listener: onClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemStepHolder {
        return ItemStepHolder.create(parent)
    }

    fun update(list: List<String>?){
        nameList = list
    }

    override fun getItemCount(): Int {
        return nameList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ItemStepHolder, position: Int) {
        nameList?.let {
            holder.bindTo(nameList!![position],position+1)
        }
    }

}