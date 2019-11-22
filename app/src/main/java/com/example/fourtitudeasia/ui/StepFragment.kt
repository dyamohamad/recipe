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
import com.example.fourtitudeasia.adapter.StepAdapter
import com.example.fourtitudeasia.base.BaseFragment
import com.example.fourtitudeasia.databinding.FragmentStepBinding
import com.example.fourtitudeasia.viewmodel.SharedViewModel

class StepFragment : BaseFragment() {
    companion object {
        fun newInstance() = IngredientFragment()
    }
    var init = ArrayList<String>()


    lateinit var binding: FragmentStepBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_step, container, false
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

        val listin=viewmodel.getSteps()
        init.addAll(listin)
        initPopulateAdapter()
    }
    private fun initPopulateAdapter() {

        val adapter = StepAdapter(init)
        binding.recyclerStep.layoutManager = LinearLayoutManager(context)
        binding.recyclerStep.adapter = adapter
        binding.recyclerStep.setHasFixedSize(true)


    }

}