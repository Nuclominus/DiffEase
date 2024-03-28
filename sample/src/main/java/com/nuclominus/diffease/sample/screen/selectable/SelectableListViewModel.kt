package com.nuclominus.diffease.sample.screen.selectable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nuclominus.diffease.sample.data.MockModel
import com.nuclominus.diffease.sample.di.ResourceProvider
import com.nuclominus.diffease.sample.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectableListViewModel @Inject constructor(
    resources: ResourceProvider
) : ViewModel() {

    private val mockData: List<MockModel>

    private val _data = MutableLiveData<List<MockModel>>(emptyList())
    val data: LiveData<List<MockModel>> = _data

    init {
        with(resources.getAppContext()) {
            mockData = DataUtils.fetchSimpleMockData()
            _data.postValue(mockData)
        }
    }

}