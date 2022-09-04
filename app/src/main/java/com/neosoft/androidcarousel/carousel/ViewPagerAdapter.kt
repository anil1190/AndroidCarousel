package com.neosoft.androidcarousel.carousel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.neosoft.androidcarousel.R
import com.neosoft.androidcarousel.databinding.ViewpageritemBinding
import com.neosoft.androidcarousel.models.ViewPagerModel


class ViewPagerAdapter(private val context: Context,private val imageList : List<ViewPagerModel>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
         val binding = DataBindingUtil.inflate<ViewpageritemBinding>(LayoutInflater.from(container.context),
             R.layout.viewpageritem,container,false)
        binding.vpImage.setImageResource(imageList[position].mvpImage)
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}



