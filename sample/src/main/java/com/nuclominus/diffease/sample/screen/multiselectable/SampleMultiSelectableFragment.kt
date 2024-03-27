package com.nuclominus.diffease.sample.screen.multiselectable

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nuclominus.diffease.base.ListObserver
import com.nuclominus.diffease.sample.R
import com.nuclominus.diffease.sample.data.MultiMock
import com.nuclominus.diffease.sample.databinding.FragmentSampleBinding
import com.nuclominus.diffease.sample.screen.selectable.SelectableMockAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleMultiSelectableFragment : Fragment(R.layout.fragment_sample) {
    private val viewBinding by viewBinding(FragmentSampleBinding::bind)

    private val vm: SampleMultiSelectableViewModel by viewModels()

    private val selectableObserver = object : ListObserver<MultiMock>() {
        override fun onItemClicked(item: MultiMock) {
            // Do something with clicked item
        }
    }

    private val firstAdapter by lazy {
        SelectableMockAdapter(selectableObserver)
    }
    private val secondAdapter by lazy {
        SelectableMockAdapter(selectableObserver)
    }
    private val concatAdapter by lazy {
        // ConcatAdapter is used to combine multiple adapters into a single adapter
        // This is useful when you want to combine multiple adapters with different view types
        // into a single adapter
        val config = ConcatAdapter.Config.Builder()
            .setIsolateViewTypes(true)
            .setStableIdMode(ConcatAdapter.Config.StableIdMode.ISOLATED_STABLE_IDS)
            .build()
        ConcatAdapter(config, firstAdapter, secondAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initObservers() {
        observeFirstAdapter()
        observeSecondAdapter()
    }

    private fun observeFirstAdapter() {
        vm.data.observe(viewLifecycleOwner) { data ->
            firstAdapter.update(data, true)
        }
    }

    private fun observeSecondAdapter() {
        vm.secondMockData.observe(viewLifecycleOwner) { data ->
            secondAdapter.update(data, true)
        }
    }

    private fun initViews() {
        with(viewBinding) {
            listWidget.recyclerView.apply {
                adapter = concatAdapter
                itemAnimator = DefaultItemAnimator().apply {
                    supportsChangeAnimations = false
                }
            }
        }
    }

}