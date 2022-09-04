package com.neosoft.androidcarousel.repository

import com.neosoft.androidcarousel.*
import com.neosoft.androidcarousel.models.Movie
import com.neosoft.androidcarousel.models.ViewPagerModel


open class MainRepository  {


    fun getPagerList() : List<ViewPagerModel>{
        val pagerList = arrayListOf<ViewPagerModel>()
        pagerList.add(ViewPagerModel(R.drawable.image))
        pagerList.add(ViewPagerModel(R.drawable.image))
        pagerList.add(ViewPagerModel(R.drawable.image))
        pagerList.add(ViewPagerModel(R.drawable.image))
        pagerList.add(ViewPagerModel(R.drawable.image))

        return pagerList
    }

    fun getCustomList0() : List<Movie>{
        var customList0 = ArrayList<Movie>()
        customList0.add(Movie("Zero one", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero two", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero three", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero four", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero five", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero six", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero seven", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero eight", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero nine", R.drawable.ic_healthy_food))
        customList0.add(Movie("Zero ten", R.drawable.ic_healthy_food))

        return customList0
    }
    fun getCustomList1(): List<Movie>{
        var customList1 = ArrayList<Movie>()
        customList1.add(Movie("First one", R.drawable.ic_healthy_food))
        customList1.add(Movie("First two", R.drawable.ic_healthy_food))
        customList1.add(Movie("First three", R.drawable.ic_healthy_food))
        customList1.add(Movie("First four", R.drawable.ic_healthy_food))
        customList1.add(Movie("First five", R.drawable.ic_healthy_food))
        customList1.add(Movie("First six", R.drawable.ic_healthy_food))
        customList1.add(Movie("First seven", R.drawable.ic_healthy_food))
        customList1.add(Movie("First eight", R.drawable.ic_healthy_food))
        customList1.add(Movie("First nine", R.drawable.ic_healthy_food))
        customList1.add(Movie("First ten", R.drawable.ic_healthy_food))

        return customList1
    }

    fun getCustomList2(): List<Movie>{
        var customList2 = ArrayList<Movie>()
        customList2.add(Movie("Second one", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second two", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second three", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second four", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second five", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second six", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second seven", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second eight", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second nine", R.drawable.ic_healthy_food))
        customList2.add(Movie("Second ten", R.drawable.ic_healthy_food))

        return customList2
    }

    fun getCustomList3(): List<Movie>{
        var customList3 = ArrayList<Movie>()
        customList3.add(Movie("Third one", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third two", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third three", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third four", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third five", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third six", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third seven", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third eight", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third nine", R.drawable.ic_healthy_food))
        customList3.add(Movie("Third ten", R.drawable.ic_healthy_food))


        return customList3
    }

    fun getCustomList4(): List<Movie>{
        var customList4 = ArrayList<Movie>()
        customList4.add(Movie("Fourth one", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth two", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth three", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth four", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth five", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth six", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth seven", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth eight", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth nine", R.drawable.ic_healthy_food))
        customList4.add(Movie("Fourth ten", R.drawable.ic_healthy_food))

        return customList4
    }
}