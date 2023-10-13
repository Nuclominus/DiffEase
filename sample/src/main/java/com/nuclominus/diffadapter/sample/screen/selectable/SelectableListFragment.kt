package com.nuclominus.diffadapter.sample.screen.selectable

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.nuclominus.diffadapter.sample.R
import by.kirich1409.viewbindingdelegate.viewBinding
import com.nuclominus.diffadapter.base.ListObserver
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.databinding.FragmentSampleBinding
import com.nuclominus.diffadapter.sample.extensions.addMenuProvider
import com.nuclominus.diffadapter.sample.extensions.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectableListFragment : Fragment(R.layout.fragment_sample) {

    private val viewBinding by viewBinding(FragmentSampleBinding::bind)
    private val vm: SelectableListViewModel by viewModels()

    private val adapter by lazy {
        SelectableMockAdapter(
            listObserver = object : ListObserver<MultiMock>() {
                override fun onItemClicked(item: MultiMock) {
                    // Do something with clicked item
                    showSnackBar(
                        view = viewBinding.root,
                        text = "You select an item = ${getString(item.name)}"
                    )
                }
            },
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
    }

    private fun initObservers() {
        vm.data.observe(viewLifecycleOwner) { data ->
            adapter.update(data, true)
        }
    }

    private fun initViews() {
        with(viewBinding) {
            listWidget.recyclerView.adapter = adapter
        }
        addMenuProvider(
            onSelected = ::onMenuItemSelected,
            menuRes = R.menu.menu_selectable,
        )
    }

    private fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_select_all -> {
                adapter.selectAll()
                true
            }

            R.id.action_unselect_all -> {
                adapter.clearSelection()
                true
            }

            R.id.action_select_any -> {
                adapter.selectAnyMode()
                true
            }

            R.id.action_select_single -> {
                adapter.selectSingleMode()
                true
            }

            R.id.action_select_ratio -> {
                adapter.selectRatioMode()
                true
            }


            else -> false
        }
    }

}