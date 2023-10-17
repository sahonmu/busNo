package com.sahonmu.burger87.ui.bus.detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.card.MaterialCardView
import com.sahonmu.burger87.R
import com.sahonmu.burger87.databinding.ViewStationSummaryBinding
import com.sahonmu.burger87.extension.dimensToFloat
import com.sahonmu.burger87.extension.dpToPx
import com.sahonmu.burger87.network.response.StationBusNoData
import com.sahonmu.burger87.network.response.StationResultData
import com.sahonmu.burger87.utils.AnimationUtils

class StationSummaryView : MaterialCardView {

    private lateinit var binding: ViewStationSummaryBinding
    var isShow: Boolean = false
        get() {
            return this.translationY == 0f
        }

    var onBusNoItemClick: ((StationBusNoData) -> Unit)? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.view_station_summary,
            this,
            false
        )
        binding.executePendingBindings()
        radius = context.dpToPx(8f).toFloat()
        addView(binding.root)
    }

    fun bind(data: StationResultData) {
        binding.data = data
        binding.busNoScrollView.scrollTo(0, 0)
        binding.stationAddressTextView.text = "${data.add} ${data.gu} ${data.dong}"

        val margin = context.dpToPx(2f)
        data.lane.forEach { busNoData ->
            val busNoView = BusNoView(context).apply {
                this.bind(busNoData.busNo)
                this.layoutParams = FlexboxLayout.LayoutParams(
                    FlexboxLayout.LayoutParams.WRAP_CONTENT,
                    FlexboxLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(margin, margin, margin, margin)
                }
                this.setOnClickListener { onBusNoItemClick?.invoke(busNoData) }
            }
            binding.flexboxLayout.addView(busNoView)
        }
    }

    fun show() {
        AnimationUtils.translateY(this,0f).apply {
            interpolator = OvershootInterpolator()
        }.start()
    }

    fun hide() {
        val y = context.dimensToFloat(R.dimen.translate_station_summary)
        AnimationUtils.translateY(this, y).apply {
            interpolator = OvershootInterpolator()
        }.start()
    }

}