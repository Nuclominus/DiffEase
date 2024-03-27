package com.nuclominus.diffadapter.sample.screen.multiselectable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nuclominus.diffadapter.sample.data.MockModel
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.di.ResourceProvider
import com.nuclominus.diffadapter.sample.screen.core.MockViewModel
import com.nuclominus.diffadapter.sample.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class SampleMultiSelectableViewModel @Inject constructor(
    resources: ResourceProvider,
) : MockViewModel(resources) {

    private val _secondMockData = MutableLiveData<List<MultiMock>>(emptyList()).apply {
        postValue(
            with(resources.getAppContext()) {
                // For second data set we are using only image cells
                DataUtils.fetchMultiTypeMockData()
                    .filter { it.viewType == MockModel.VIEW_TYPE_IMAGE_CELL }
            }
        )
    }
    val secondMockData : LiveData<List<MultiMock>> = _secondMockData
}