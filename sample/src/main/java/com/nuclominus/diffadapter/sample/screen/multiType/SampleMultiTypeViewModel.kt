package com.nuclominus.diffadapter.sample.screen.multiType

import android.content.Context
import com.nuclominus.diffadapter.sample.data.MockModel
import com.nuclominus.diffadapter.sample.data.MultiMock
import com.nuclominus.diffadapter.sample.di.ResourceProvider
import com.nuclominus.diffadapter.sample.screen.core.MockViewModel
import com.nuclominus.diffadapter.sample.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SampleMultiTypeViewModel @Inject constructor(
    resources: ResourceProvider
) : MockViewModel(resources) {

    context(Context)
    override fun provideResources(): List<MockModel> {
        return DataUtils.fetchMultiTypeMockData()
    }
}