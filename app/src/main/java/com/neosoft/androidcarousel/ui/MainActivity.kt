package com.neosoft.androidcarousel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.neosoft.androidcarousel.carousel.BoundsOffsetDecoration
import com.neosoft.androidcarousel.carousel.CarouselAdapter
import com.neosoft.androidcarousel.carousel.LinearHorizontalSpacingDecoration
import com.neosoft.androidcarousel.carousel.ProminentLayoutManager
import com.neosoft.androidcarousel.databinding.ActivityMainBinding
import com.neosoft.androidcarousel.models.Movie
import com.neosoft.androidcarousel.remote.RetrofitService
import com.neosoft.androidcarousel.repository.MainRepository
import com.neosoft.androidcarousel.utils.CirclePagerIndicatorDecoration
import com.neosoft.androidcarousel.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter_movie = MainAdapter()

    private lateinit var layoutManager1: LinearLayoutManager
    private lateinit var adapter: CarouselAdapter
    private lateinit var snapHelper: SnapHelper
    private var mSearchList = java.util.ArrayList<Movie>()

    private var customList0 = ArrayList<Movie>()
    private var customList1 = ArrayList<Movie>()
    private var customList2 = ArrayList<Movie>()
    private var customList3 = ArrayList<Movie>()
    private var customList4 = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService)))[MainViewModel::class.java]
        binding.rvRecipesList.adapter = adapter_movie
        binding.rvRecipesList.setHasFixedSize(true)


        myCustomList()
        myObserver()
        getCarouselImageList()
        getSearchedData()

    }

    private fun myCustomList(){
        customList1.add(Movie("First one", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First two", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First three", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First four", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First five", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First six", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First seven", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First eight", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First nine", FIRST_LIST_IMAGE,"",""))
        customList1.add(Movie("First ten", FIRST_LIST_IMAGE,"",""))

        customList2.add(Movie("Second one", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second two", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second three", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second four", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second five", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second six", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second seven", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second eight", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second nine", SECOND_LIST_IMAGE,"",""))
        customList2.add(Movie("Second ten", SECOND_LIST_IMAGE,"",""))

        customList3.add(Movie("Third one", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third two", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third three", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third four", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third five", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third six", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third seven", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third eight", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third nine", THIERD_LIST_IMAGE,"",""))
        customList3.add(Movie("Third ten", THIERD_LIST_IMAGE,"",""))

        customList4.add(Movie("Fourth one", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth two", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth three", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth four", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth five", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth six", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth seven", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth eight", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth nine", FOURTH_LIST_IMAGE,"",""))
        customList4.add(Movie("Fourth ten", FOURTH_LIST_IMAGE,"",""))
    }



    private fun myObserver(){
        viewModel.movieList.observe(this, Observer {
            customList0.addAll(it)
            mSearchList = concatenate(customList0,customList1,customList2,customList3,customList4)
            adapter_movie.setMovieList(mSearchList)
        })
        viewModel.errorMessage.observe(this, Observer {
        })

        viewModel.getAllMovies()


    }

    fun <Movie> concatenate(vararg lists: List<Movie>): ArrayList<Movie> {
        val result: ArrayList<Movie> = ArrayList()
        for (list in lists) {
            result.addAll(list)
        }
        return result
    }

    private fun getSearchedData(){
        binding.searchView.queryHint = getString(R.string.search_hint)
        var movieList = java.util.ArrayList<Movie>()
        binding.searchView.isIconified = true
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                movieList.clear()
                mSearchList?.let {
                    if (it.isNotEmpty()) {
                        for (item in it) {
                            if (item.name.lowercase(Locale.getDefault()).contains(query.lowercase(
                                    Locale.getDefault()))) {

                                movieList.add(item)

                            }
                        }
                    }

                    if (movieList.isNullOrEmpty()){
                         binding.tvNoData.toVisible()
                         binding.rvRecipesList.toGone()
                    }else{
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

        closeButton.setOnClickListener(View.OnClickListener {
            movieList.clear()
            binding.tvNoData.toGone()
            binding.rvRecipesList.toVisible()
            adapter_movie.notifyDataSetChanged()
            binding.searchView.setQuery("",true)
            binding.searchView.clearFocus()
            closeButton.visibility = View.GONE
            adapter_movie.setMovieList(mSearchList)
            initRecyclerViewPosition(0)
        })


    }

    private fun getCarouselImageList(){
        val images: java.util.ArrayList<Image> = ArrayList(ImageData.images.shuffled())
        layoutManager1 = ProminentLayoutManager(this)
        adapter = CarouselAdapter(images,adapter_movie,customList0,customList1,customList2,customList3,customList4)
        snapHelper = PagerSnapHelper()

        with(binding.recyclerView) {
            setItemViewCacheSize(4)
            layoutManager = this@MainActivity.layoutManager1
            adapter = this@MainActivity.adapter

            val spacing = resources.getDimensionPixelSize(R.dimen.carousel_spacing)
            addItemDecoration(LinearHorizontalSpacingDecoration(spacing))
            addItemDecoration(BoundsOffsetDecoration())

            snapHelper.attachToRecyclerView(this)
        }
        var pos = 0
        initRecyclerViewPosition(pos)
    }

    private fun initRecyclerViewPosition(position: Int) {
        layoutManager1.scrollToPosition(position)

        binding.recyclerView.doOnPreDraw {
            val targetView = layoutManager1.findViewByPosition(position) ?: return@doOnPreDraw
            val distanceToFinalSnap =
                snapHelper.calculateDistanceToFinalSnap(layoutManager1, targetView)
                    ?: return@doOnPreDraw

            layoutManager1.scrollToPositionWithOffset(position, -distanceToFinalSnap[0])
        }
        binding.recyclerView.addItemDecoration(CirclePagerIndicatorDecoration())
    }

}
