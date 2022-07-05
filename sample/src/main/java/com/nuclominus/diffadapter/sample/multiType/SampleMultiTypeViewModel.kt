package com.nuclominus.diffadapter.sample.multiType

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nuclominus.diffadapter.sample.data.MockModel
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.di.ResourceProvider
import com.nuclominus.diffadapter.sample.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleMultiTypeViewModel @Inject constructor(
    resources: ResourceProvider
) : ViewModel() {

    private var mockData: List<MockModel>

    private val _data = MutableLiveData<List<MockModel>>(emptyList())
    val data: LiveData<List<MockModel>> = _data

    init {
        with(resources.getAppContext()) {
            mockData = DataUtils.fetchMultiTypeMockData()
            _data.postValue(mockData)
        }
    }

    fun shuffle() {
        mockData = mockData.shuffled()
        _data.postValue(mockData)
    }

    fun selectAndUpdateItem(item: MultiMock) {
        val list = mockData.toMutableList()
        list.indexOfFirst { model -> model.modelId == item.modelId }
            .takeIf { it != -1 }
            ?.let { index ->
                val updatedItem = (item as MockModel).copy(isChecked = !item.isChecked)
                list[index] = updatedItem
            }
        mockData = list
        _data.postValue(mockData)
    }
}