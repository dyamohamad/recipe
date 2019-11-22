package com.example.fourtitudeasia.holder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.example.fourtitudeasia.R
import com.example.fourtitudeasia.constants.Constants
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.work.cyberbully.ui.main.GlideApp
import kotlinx.android.synthetic.main.item_recipe.view.*
import android.net.Uri
import java.io.File
import android.provider.MediaStore
import com.example.fourtitudeasia.util.ImageUtil


class ItemRecipeHolder(var view: View):
    RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent: ViewGroup): ItemRecipeHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
            return ItemRecipeHolder(view)
        }
    }

    fun bindTo(recipe: RecipeTypeModel) {

        view.tv_food.setText(recipe.name)

        val options = RequestOptions()
        options.fitCenter()
        if(recipe.picture.contains("http")){
            GlideApp.with(view.img.context)
                .load(recipe.picture)
                .apply(options)
                .into( view.img)
        }else{
            val image =ImageUtil.getRealPathFromURI(view.context,recipe.picture)
            GlideApp.with(view.img.context)
                .load( image)
                .apply(options)
                .into( view.img)

        }



        view.setOnClickListener {

            val bundle = Bundle().apply {
                putParcelable(Constants.RECIPE_OBJ,recipe)
            }
           findNavController(view).navigate(
                R.id.action_MainFragment_to_scrollingFragment,bundle)

        }
    }
}