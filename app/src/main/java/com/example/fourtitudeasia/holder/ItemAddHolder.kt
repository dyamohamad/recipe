package com.example.fourtitudeasia.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fourtitudeasia.R
import com.example.fourtitudeasia.`interface`.onClick
import kotlinx.android.synthetic.main.item_addstepsingredient.view.*
import kotlinx.android.synthetic.main.item_name.view.tv_name


class ItemAddHolder(var view: View):
    RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): ItemAddHolder {



            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_addstepsingredient, parent, false)
            return ItemAddHolder(view)
        }
    }

    fun bindTo(name: String) {

        view.tv_name.setText(name)

        view.delete.setOnClickListener {
//            listener?.onClick(adapterPosition)
        }



    }
}