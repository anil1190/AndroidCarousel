package com.neosoft.androidcarousel.carousel

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.neosoft.androidcarousel.Image
import com.neosoft.androidcarousel.R
import com.neosoft.androidcarousel.databinding.ViewOverlayableImageBinding

class OverlayableImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ViewOverlayableImageBinding.inflate(LayoutInflater.from(context), this)

    var image: Image? = null
        set(value) {
            field = value
            value?.let {
                Glide.with(binding.imageView)
                    .load(it.url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .transform(
                        FitCenter(),
                        RoundedCorners(resources.getDimensionPixelSize(R.dimen.rounded_corners_radius))
                    )
                    .into(binding.imageView)
            }
        }

    override fun setActivated(activated: Boolean) {
        super.setActivated(activated)
    }
}