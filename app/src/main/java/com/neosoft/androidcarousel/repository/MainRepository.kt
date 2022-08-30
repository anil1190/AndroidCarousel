package com.neosoft.androidcarousel.repository

import com.neosoft.androidcarousel.remote.RetrofitService

open class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies()
}