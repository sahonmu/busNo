package com.sahonmu.burger87.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected val binding by lazy {
        DataBindingUtil.setContentView(this, getLayoutId()) as VB
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun addEvent()
    abstract fun addObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        initView()
        addEvent()
        addObserver()

    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        System.gc()
    }
}