package com.sahonmu.burger87.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

abstract class BaseFragment<VB : ViewDataBinding> : Fragment(), LifecycleOwner {

    @LayoutRes
    abstract fun getLayoutResourceId(): Int
    abstract fun initView()
    abstract fun addEvent()
    abstract fun addObserver()

    protected lateinit var binding: VB

    var onBackPressed: ((Unit) -> Unit)? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        initView()
        addEvent()
        addObserver()
        return binding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    private var callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            onBackPressed?.invoke(Unit)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        removeOnBackCallback()
        System.gc()
    }

    private fun removeOnBackCallback() {
        callback.remove()
    }

    fun DialogFragment.show() {
        kotlin.runCatching {
            showDialog(this)
        }
    }

    private fun <Dialog : DialogFragment> showDialog(dialog: Dialog) {
        kotlin.runCatching {
            dialog.show(childFragmentManager, null)
        }
    }

    fun onBackPressed() {
        requireActivity().finish()
    }

}
