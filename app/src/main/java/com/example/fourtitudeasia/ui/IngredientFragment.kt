package com.example.fourtitudeasia.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourtitudeasia.R
import com.example.fourtitudeasia.adapter.IngredientAdapter
import com.example.fourtitudeasia.base.BaseFragment
import com.example.fourtitudeasia.databinding.FragmentIngredientBinding
import com.example.fourtitudeasia.viewmodel.SharedViewModel

class IngredientFragment : BaseFragment() {
    companion object {
        fun newInstance() = IngredientFragment()
    }
    var init = ArrayList<String>()


    lateinit var binding: FragmentIngredientBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_ingredient, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var viewmodel  = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this

        val listin=viewmodel.getIngredient()
        init.addAll(listin)
        initPopulateAdapter()
    }
    private fun initPopulateAdapter() {

        val adapter = IngredientAdapter(init)
        binding.recyclerIngredient.layoutManager = LinearLayoutManager(context)
        binding.recyclerIngredient.adapter = adapter
        binding.recyclerIngredient.setHasFixedSize(true)


    }

}