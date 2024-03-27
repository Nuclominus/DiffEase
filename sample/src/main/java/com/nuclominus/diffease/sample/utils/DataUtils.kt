package com.nuclominus.diffease.sample.utils

import android.content.Context
import androidx.annotation.IntRange
import com.nuclominus.diffease.sample.data.MockModel

object DataUtils {

    private const val MOCK_MIN_NUMBER = 1
    private const val MOCK_MAX_NUMBER = 11

    private const val MOCK_DRAWABLE_PREFIX = "mock_drawable_"
    private const val MOCK_STRING_PREFIX = "mock_name_"

    private const val RESOURCE_TYPE_DRAWABLE = "drawable"
    private const val RESOURCE_TYPE_STRING = "string"

    context(Context)
    private fun getResourceByNumber(
        name: String,
        defType: String,
        @IntRange(from = 1, to = 11) number: Int
    ): Int {
        return resources.getIdentifier(
            name + number,
            defType,
            packageName
        )
    }

    context(Context)
    fun fetchSimpleMockData(): List<MockModel> {
        val result = mutableListOf<MockModel>()
        for (number in MOCK_MIN_NUMBER..MOCK_MAX_NUMBER) {
            val string = getResourceByNumber(
                MOCK_STRING_PREFIX,
                RESOURCE_TYPE_STRING,
                number
            )
            val drawable = getResourceByNumber(
                MOCK_DRAWABLE_PREFIX,
                RESOURCE_TYPE_DRAWABLE,
                number
            )
            result.add(MockModel(name = string, avatar = drawable))
        }
        return result
    }

    context(Context)
    fun fetchMultiTypeMockData(): List<MockModel> {
        return fetchSimpleMockData().mapIndexed { index, model ->
            model.copy(
                viewType = when {
                    index % 2 == 0 -> MockModel.VIEW_TYPE_IMAGE_CELL
                    index % 3 == 0 -> MockModel.VIEW_TYPE_ACTION_CELL
                    else -> MockModel.VIEW_TYPE_SIMPLE_CELL
                }
            )
        }
    }
}