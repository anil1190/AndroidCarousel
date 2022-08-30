package com.neosoft.androidcarousel.carousel

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.neosoft.androidcarousel.*
import com.neosoft.androidcarousel.models.Movie
import kotlin.math.roundToInt

internal class CarouselAdapter (private val images: List<Image>, private val mainAdapter: MainAdapter, private val list0 : ArrayList<Movie>, private val list1 : ArrayList<Movie>,
                                private val list2 : ArrayList<Movie>, private val list3 : ArrayList<Movie>, private val list4 : ArrayList<Movie>) :
    RecyclerView.Adapter<CarouselAdapter.VH>() {

    private var hasInitParentDimensions = false
    private var maxImageWidth: Int = 0
    private var maxImageHeight: Int = 0
    private var maxImageAspectRatio: Float = 1f

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        // At this point [parent] has been measured and has valid width & height
        if (!hasInitParentDimensions) {
            maxImageWidth =
                parent.width - 2 * parent.resources.getDimensionPixelSize(R.dimen.gradient_width)
            maxImageHeight = parent.height
            maxImageAspectRatio = maxImageWidth.toFloat() / maxImageHeight.toFloat()
            hasInitParentDimensions = true
        }

        return VH(OverlayableImageView(parent.context))
    }

    override fun onBindViewHolder(vh: VH, position: Int) {
        val image = images[position]

        // Change aspect ratio
        val imageAspectRatio = image.aspectRatio
        val targetImageWidth: Int = if (imageAspectRatio < maxImageAspectRatio) {
            // Tall image: height = max
            (maxImageHeight * imageAspectRatio).roundToInt()
        } else {
            // Wide image: width = max
            maxImageWidth
        }
        vh.overlayableImageView.layoutParams = RecyclerView.LayoutParams(
            targetImageWidth,
            RecyclerView.LayoutParams.MATCH_PARENT
        )

        // Load image
        vh.overlayableImageView.image = image

        vh.overlayableImageView.setOnClickListener {
            val rv = vh.overlayableImageView.parent as RecyclerView
            rv.smoothScrollToCenteredPosition(position,mainAdapter, list0,list1,list2,list3,list4)
        }



    }

    override fun getItemCount(): Int = images.size

    class VH(val overlayableImageView: OverlayableImageView) :
        RecyclerView.ViewHolder(overlayableImageView)
}

private fun RecyclerView.smoothScrollToCenteredPosition(position: Int, mainAdapter: MainAdapter, list0 : ArrayList<Movie>, list1 : ArrayList<Movie>,
                                                        list2 : ArrayList<Movie>, list3 : ArrayList<Movie>, list4 : ArrayList<Movie>) {
    val smoothScroller = object : LinearSmoothScroller(context) {
        override fun calculateDxToMakeVisible(view: View?, snapPreference: Int): Int {
            val dxToStart = super.calculateDxToMakeVisible(view, SNAP_TO_START)
            val dxToEnd = super.calculateDxToMakeVisible(view, SNAP_TO_END)

            return (dxToStart + dxToEnd) / 2
        }
    }

    when(position){
        0->{mainAdapter.setMovieList(list0)
            mainAdapter.notifyDataSetChanged()}
        1->{mainAdapter.setMovieList(list1)
            mainAdapter.notifyDataSetChanged()}
        2->{mainAdapter.setMovieList(list2)
            mainAdapter.notifyDataSetChanged()}
        3->{mainAdapter.setMovieList(list3)
            mainAdapter.notifyDataSetChanged()}
        4->{mainAdapter.setMovieList(list4)
            mainAdapter.notifyDataSetChanged()}
    }


    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)
}