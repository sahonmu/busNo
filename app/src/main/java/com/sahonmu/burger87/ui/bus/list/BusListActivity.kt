package com.sahonmu.burger87.ui.bus.list

import androidx.activity.viewModels
import com.sahonmu.burger87.base.BaseActivity
import com.sahonmu.burger87.R
import com.sahonmu.burger87.databinding.ActivityContainerBinding
import com.sahonmu.burger87.utils.FragmentUtils
import com.sahonmu.burger87.viewmodels.BusViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BusListActivity : BaseActivity<ActivityContainerBinding>() {

    private val busViewModel by viewModels<BusViewModel>()

    override fun getLayoutId(): Int {
        return R.layout.activity_container
    }

    override fun initView() {
        initFragment()
    }

    override fun addEvent() { }

    override fun addObserver() {
        busViewModel.toastLiveData.observe(this) { message ->
            showToast(message)
        }
    }

    private fun initFragment() {
        FragmentUtils.replaceFragmentNoBackstack(
            supportFragmentManager,
            BusListFragment.newInstance(),
            binding.container.id
        )
    }

}
