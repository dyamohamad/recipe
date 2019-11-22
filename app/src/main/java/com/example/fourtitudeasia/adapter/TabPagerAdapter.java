package com.example.fourtitudeasia.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fourtitudeasia.ui.IngredientFragment;
import com.example.fourtitudeasia.ui.StepFragment;
/**
 * Created by nur.diyana on 21/2/2018.
 */
public class TabPagerAdapter extends FragmentPagerAdapter  {

    public TabPagerAdapter(FragmentManager fm){

        super(fm);
    }
    @Override
    public Fragment getItem(int position) {


        if(position==0){
            return new IngredientFragment();
        }else{

            return new StepFragment();
        }


    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Ingredient";
        }else{
            return "Steps";
        }
    }
}



