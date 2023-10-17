package com.sahonmu.burger87.ui.bus.detail.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.sahonmu.burger87.R
import com.sahonmu.burger87.databinding.ViewBusNoSummaryBinding
import com.sahonmu.burger87.enums.busType
import com.sahonmu.burger87.network.response.BusNoDetailData

class BusNoSummaryView : FrameLayout {

    private lateinit var binding: ViewBusNoSummaryBinding

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
            R.layout.view_bus_no_summary,
            this,
            false
        )
        binding.executePendingBindings()
        addView(binding.root)
    }

    fun bind(data: BusNoDetailData) {
        binding.data = data
        val busTypeName = busType(data.type).busTypeName
        binding.busTypeNameTextView.text = busTypeName
        binding.busTypeNameTextView.visibility =
            if (busTypeName.isNotEmpty()) View.VISIBLE else View.GONE
    }


}