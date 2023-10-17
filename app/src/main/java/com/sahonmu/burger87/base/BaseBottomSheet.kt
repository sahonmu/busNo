package com.sahonmu.burger87.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sahonmu.burger87.R

abstract class BaseBottomSheet<VB : ViewDataBinding> : BottomSheetDialogFragment() {

    protected lateinit var binding: VB

    abstract fun initView()
    abstract fun addEvent()
    abstract fun addObserver()

    @LayoutRes
    abstract fun getLayoutResourceId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        binding.root.setBackgroundResource(R.drawable.background_bottom_sheet)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        isCancelable = true
        initView()
        addEvent()
        return binding.root
    }



}
