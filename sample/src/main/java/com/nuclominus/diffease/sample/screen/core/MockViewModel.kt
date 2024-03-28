package com.nuclominus.diffease.sample.screen.core

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nuclominus.diffease.sample.data.MockModel
import com.nuclominus.diffease.sample.di.ResourceProvider
import com.nuclominus.diffease.sample.utils.DataUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class MockViewModel @Inject constructor(
    resources: ResourceProvider
) : ViewModel() {

    private var mockData: List<MockModel> = emptyList()

    private val _data = MutableLiveData<List<MockModel>>(emptyList())
    val data: LiveData<List<MockModel>> = _data

    init {
        with(resources.getAppContext()) {
            mockData = provideResources()
            _data.postValue(mockData)
        }
    }

    context(Context)
    open fun provideResources(): List<MockModel> {
        return DataUtils.fetchSimpleMockData()
    }

    fun shuffle() {
        _data.postValue(mockData.shuffled())
    }
}