package com.example.fourtitudeasia.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.request.RequestOptions
import com.example.fourtitudeasia.R
import com.example.fourtitudeasia.adapter.TabPagerAdapter
import com.example.fourtitudeasia.base.BaseFragment
import com.example.fourtitudeasia.constants.Constants
import com.example.fourtitudeasia.databinding.ActivityScrollingBinding
import com.example.fourtitudeasia.model.RecipeTypeModel
import com.example.fourtitudeasia.viewmodel.SharedViewModel
import com.google.android.material.tabs.TabLayout
import com.work.cyberbully.ui.main.GlideApp
import kotlinx.android.synthetic.main.activity_scrolling.*
import kotlinx.android.synthetic.main.content_scrolling.*
import android.view.MenuInflater
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.fourtitudeasia.ui.activity.CreateRecipeActivity


class ScrollingFragment : BaseFragment() {
    lateinit var model: RecipeTypeModel

    lateinit var binding: ActivityScrollingBinding

    lateinit var intent: Intent
    lateinit var viewmodel:SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.activity_scrolling, container, false
        )
        return binding.root
    }
    //enable options menu in this fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         viewmodel = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        viewmodel.addBundle(arguments)
        binding.viewmodel = viewmodel

        binding.lifecycleOwner = this


        model = arguments!!.get(Constants.RECIPE_OBJ) as RecipeTypeModel

        if (toolbar != null) {
            toolbar.title = model.name
        }
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        ///model = intent.getParcelableExtra(Constants.RECIPE_OBJ)
        initTabLayout()
        setupTabLayout()
        displayImage()

        viewmodel.getStatus().observe(viewLifecycleOwner, Observer {
            Toast.makeText(context,"success",Toast.LENGTH_LONG).show()
//            findNavController().navigate(R.id.action_scrollingFragment_to_MainFragment)
        })
    }


    fun getActionbar(): ActionBar? {
        return (activity as AppCompatActivity).supportActionBar
    }


    fun initTabLayout() {

        val adapter = TabPagerAdapter(childFragmentManager)
        view_pager.setAdapter(adapter)
        view_pager.setOffscreenPageLimit(2)
    }

    private fun setupTabLayout() {
        tab_layout.setupWithViewPager(view_pager)
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {

            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
//                viewPager.currentItem = tabLayoutView.selectedTabPosition
            }


        })


    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_scrolling, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_delete -> {

                //delete
                viewmodel.deleteRecipe(model)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun displayImage() {
        val options = RequestOptions()
        options.fitCenter()
        GlideApp.with(context!!)
            .load(model.picture)
            .apply(options)
            .into(img)
    }
}
