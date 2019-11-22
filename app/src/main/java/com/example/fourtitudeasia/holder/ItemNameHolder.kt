package com.example.fourtitudeasia.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudeasia.R
import kotlinx.android.synthetic.main.item_name.view.*


class ItemNameHolder(var view: View):
    RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): ItemNameHolder {



            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_name, parent, false)
            return ItemNameHolder(view)
        }
    }

    fun bindTo(name: String) {

        view.tv_name.setText(name)



    }
}