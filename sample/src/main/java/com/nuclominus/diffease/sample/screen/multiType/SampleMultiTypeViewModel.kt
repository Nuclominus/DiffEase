package com.nuclominus.diffease.sample.screen.multiType

import android.content.Context
import com.nuclominus.diffease.sample.data.MockModel
import com.nuclominus.diffease.sample.di.ResourceProvider
import com.nuclominus.diffease.sample.screen.core.MockViewModel
import com.nuclominus.diffease.sample.utils.DataUtils
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