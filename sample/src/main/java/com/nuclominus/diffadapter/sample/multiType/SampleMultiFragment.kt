package com.nuclominus.diffadapter.sample.multiType

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.sample.R
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.databinding.FragmentSampleBinding
import com.nuclominus.diffadapter.sample.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SampleMultiFragment : Fragment(R.layout.fragment_sample) {

    private val viewBinding by viewBinding(FragmentSampleBinding::bind)
    private val vm: SampleMultiTypeViewModel by viewModels()

    private val sampleAdapter by lazy {
        SampleMultiTypeAdapter(object : ListObserver<MultiMock>() {
            override fun onItemClicked(item: MultiMock) {
                showSnackBar(
                    view = viewBinding.root,
                    text = "You click on Item = ${getString(item.name)} with Type = ${item.viewType}"
                )
            }

            override fun onActionClicked(item: MultiMock) {
                vm.selectAndUpdateItem(item)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
    }

    private fun initObservers() {
        vm.data.observe(viewLifecycleOwner) { data ->
            sampleAdapter.update(data, true)
        }
    }

    private fun initViews() {
        with(viewBinding) {

            listWidget.recyclerView.apply {
                adapter = sampleAdapter
                itemAnimator = DefaultItemAnimator().apply {
                    supportsChangeAnimations = false
                }
            }

            btnShuffle.setOnClickListener {
                vm.shuffle()
            }
        }
    }
}