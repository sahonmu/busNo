package com.sahonmu.burger87.ui.bus.detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.google.android.material.card.MaterialCardView
import com.sahonmu.burger87.R
import com.sahonmu.burger87.databinding.ViewBusNoBinding
import com.sahonmu.burger87.extension.color
import com.sahonmu.burger87.extension.dpToPx
import com.sahonmu.burger87.extension.setMargins

class BusNoView : MaterialCardView {

    private lateinit var binding: ViewBusNoBinding

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
            R.layout.view_bus_no,
            this,
            false
        )
        binding.executePendingBindings()
        radius = context.dpToPx(8f).toFloat()
        strokeWidth = context.dpToPx(1f)
        strokeColor = context.color(R.color.white)
        addView(binding.root)
    }

    fun bind(text: String) {
        binding.text = text
    }

}