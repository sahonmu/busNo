package com.sahonmu.burger87.ui.bus.list

import android.content.Intent
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sahonmu.burger87.base.BaseFragment
import com.sahonmu.burger87.R
import com.sahonmu.burger87.databinding.FragmentBusListBinding
import com.sahonmu.burger87.extension.hideKeyboard
import com.sahonmu.burger87.extension.textChanges
import com.sahonmu.burger87.ui.bus.detail.BusNoDetailActivity
import com.sahonmu.burger87.ui.bus.list.adapter.BusNoAdapter
import com.sahonmu.burger87.viewmodels.BusViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BusListFragment: BaseFragment<FragmentBusListBinding>(), BusNoAdapter.IBusNoClickListener {

    private val DEBOUNCE_DEALY = 300L

    private val busViewModel by activityViewModels<BusViewModel>()
    private val busNoAdapter by lazy {
        BusNoAdapter(this)
    }

    companion object {
        fun newInstance() = BusListFragment()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_bus_list
    }

    override fun initView() {
        binding.busNoRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.busNoRecyclerView.adapter = busNoAdapter
    }

    override fun addEvent() {
        onBackPressed = {
            onBackPressed()
        }


        binding.busNoEditText.apply {
            this.setOnEditorActionListener { _, _, _ ->
                hideKeyboard()
                false
            }

            this.textChanges()
                .drop(1)
                .debounce(DEBOUNCE_DEALY)
                .onEach { char ->
                    val keyword = char.toString()
                    if (keyword.isNotEmpty()) {
                        readBusNoList(keyword)
                    } else {
                        busNoAdapter.submitList(emptyList())
                    }
                }
                .launchIn(CoroutineScope(Dispatchers.Main))
        }


        binding.busNoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    binding.busNoEditText.hideKeyboard()
                }
            }
        })
    }

    override fun addObserver() {
        busViewModel.busNoListData.observe(this) { busNoData ->
            val list = busNoData.lane.sortedBy { data ->
                data.busCityCode
            }.ifEmpty { emptyList() }
            busNoAdapter.submitList(list)
        }
    }

    private fun readBusNoList(busNo: String) {
        busViewModel.readBusNoList(busNo)
    }

    private fun hideKeyboard() {
        binding.busNoEditText.hideKeyboard()
    }

    private fun intentBusNoDetail(busNoId: Int) {
        val intent = Intent(requireContext(), BusNoDetailActivity::class.java).apply {
            this.putExtra(getString(R.string.intent_key_bus_id), busNoId)
        }
        startActivity(intent)
    }

    override fun onBusNoItemClick(busNoId: Int) {
        hideKeyboard()
        intentBusNoDetail(busNoId)
    }

}