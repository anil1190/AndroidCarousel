package com.neosoft.androidcarousel.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.neosoft.androidcarousel.*
import com.neosoft.androidcarousel.carousel.*
import com.neosoft.androidcarousel.databinding.ActivityMainBinding
import com.neosoft.androidcarousel.models.Movie
import com.neosoft.androidcarousel.models.ViewPagerModel
import com.neosoft.androidcarousel.repository.MainRepository
import com.neosoft.androidcarousel.utils.toGone
import com.neosoft.androidcarousel.utils.toVisible
import com.neosoft.androidcarousel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    val adapter_movie = MainAdapter()

    private var customList0 = ArrayList<Movie>()
    private var customList1 = ArrayList<Movie>()
    private var customList2 = ArrayList<Movie>()
    private var customList3 = ArrayList<Movie>()
    private var customList4 = ArrayList<Movie>()
    private var mPagerList = ArrayList<ViewPagerModel>()

    lateinit var viewPagerAdapter: ViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(MainRepository())
        )[MainViewModel::class.java]
        binding.rvRecipesList.adapter = adapter_movie
        binding.rvRecipesList.isNestedScrollingEnabled = true

        myObserver()

    }



    private fun myObserver() {
        viewModel.customList0.observe(this, Observer {
            customList0.addAll(it)
            adapter_movie.setMovieList(customList0)
            getSearchedData(customList0)
        })
        viewModel.errorMessage.observe(this, Observer {
        })

        viewModel.customList1.observe(this, Observer {
            customList1.addAll(it)

        })

        viewModel.customList2.observe(this, Observer {
            customList2.addAll(it)

        })
        viewModel.customList3.observe(this, Observer {
            customList3.addAll(it)

        })
        viewModel.customList4.observe(this, Observer {
            customList4.addAll(it)

        })

        viewModel.pagerList.observe(this, Observer {
            mPagerList.addAll(it)

            viewPagerAdapter = ViewPagerAdapter(this, mPagerList)
            binding.idViewPager.adapter = viewPagerAdapter
            binding.tbDots.setupWithViewPager(binding.idViewPager, true)
        })

        setViewPagerListener()

    }

    fun setViewPagerListener() {

        binding.idViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        adapter_movie.setMovieList(customList0)
                        getSearchedData(customList0)
                    }
                    1->{
                        adapter_movie.setMovieList(customList1)
                        getSearchedData(customList1)
                    }
                    2->{
                        adapter_movie.setMovieList(customList2)
                        getSearchedData(customList2)
                    }
                    3->{
                        adapter_movie.setMovieList(customList3)
                        getSearchedData(customList3)
                    }
                    4->{
                        adapter_movie.setMovieList(customList4)
                        getSearchedData(customList4)
                    }
                }
            }
        })
    }


    private fun getSearchedData(mSearchList:ArrayList<Movie>) {
        var movieList = java.util.ArrayList<Movie>()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                movieList.clear()
                mSearchList?.let {
                    if (it.isNotEmpty()) {
                        for (item in it) {
                            if (item.name.lowercase(Locale.getDefault()).contains(
                                    query.lowercase(
                                        Locale.getDefault()
                                    )
                                )
                            ) {

                                movieList.add(item)

                            }
                        }
                    }

                    if (movieList.isNullOrEmpty()) {
                        binding.tvNoData.toVisible()
                        binding.rvRecipesList.toGone()
                    } else {
                        binding.tvNoData.toGone()
                        binding.rvRecipesList.toVisible()
                        adapter_movie.setMovieList(movieList)

                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

        })

        val searchCloseButtonId: Int = searchView.getContext().getResources()
            .getIdentifier("android:id/search_close_btn", null, null)
        val closeButton: ImageView = this.searchView.findViewById(searchCloseButtonId) as ImageView

        closeButton.setOnClickListener {
            movieList.clear()
            binding.tvNoData.toGone()
            binding.rvRecipesList.toVisible()
            adapter_movie.notifyDataSetChanged()
            binding.searchView.setQuery("", true)
            binding.searchView.clearFocus()
            closeButton.visibility = View.GONE
            adapter_movie.setMovieList(mSearchList)
        }


    }


}
