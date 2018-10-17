package com.teaml.chargingmonitor.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.teaml.chargingmonitor.R
import com.teaml.chargingmonitor.data.model.ui.HomeItem
import com.teaml.chargingmonitor.utils.extension.*

import com.teaml.chargingmonitor.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }
    private lateinit var viewDataBinding: HomeFragmentBinding
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = HomeFragmentBinding.inflate(inflater, container, false)

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup() {
        with(viewDataBinding) {
            val linearLayoutManager = LinearLayoutManager(context)
            recyclerView {
                layoutManager = linearLayoutManager
                adapter = RvHomeAdapter(listOf(
                        HomeItem("90% - 100%", R.color.color90_100),
                        HomeItem("80% - 89%", R.color.color80_89),
                        HomeItem("70% - 79%", R.color.color70_79),
                        HomeItem("60% - 69%", R.color.color60_69),
                        HomeItem("50% - 59%", R.color.color50_59),
                        HomeItem("30% - 49%", R.color.color30_49),
                        HomeItem("0% - 29%", R.color.color0_29)
                ))
            }

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

    }

}

