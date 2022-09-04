package com.neosoft.androidcarousel.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.neosoft.androidcarousel.repository.MainRepository
import com.neosoft.androidcarousel.models.Movie
import com.neosoft.androidcarousel.models.ViewPagerModel


class MainViewModel constructor(private val mainRepository: MainRepository) : ViewModel() {

    val customList0 = MutableLiveData<List<Movie>>()
    val customList1 = MutableLiveData<List<Movie>>()
    val customList2 = MutableLiveData<List<Movie>>()
    val customList3 = MutableLiveData<List<Movie>>()
    val customList4 = MutableLiveData<List<Movie>>()
    val pagerList = MutableLiveData<List<ViewPagerModel>>()
    val errorMessage = MutableLiveData<String>()



    init {
        getCustomList0()
        getCustomList1()
        getCustomList2()
        getCustomList3()
        getCustomList4()
        getPagerList()
    }


    fun getPagerList(){
        pagerList.postValue(mainRepository.getPagerList())
    }

    fun getCustomList0(){
        customList0.postValue(mainRepository.getCustomList0())
    }

    fun getCustomList1(){
        customList1.postValue(mainRepository.getCustomList1())
    }

    fun getCustomList2(){
        customList2.postValue(mainRepository.getCustomList2())
    }

    fun getCustomList3(){
        customList3.postValue(mainRepository.getCustomList3())
    }

    fun getCustomList4(){
        customList4.postValue(mainRepository.getCustomList4())
    }
}