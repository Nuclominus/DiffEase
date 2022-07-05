package com.nuclominus.diffadapter.sample.data

import com.nuclominus.diffadapter.sample.utils.DateUtils.toFormatDate
import java.util.*
import kotlin.random.Random

interface MultiMock {
    val modelId: String
    val avatar: Int
    val name: Int
    val date: String
    val isChecked: Boolean
    val viewType: Int
}

data class MockModel(
    override val modelId: String = UUID.randomUUID().toString(),
    override val avatar: Int,
    override val name: Int,
    override val date: String = Date(Random(System.currentTimeMillis()).nextLong()).toFormatDate(),
    override val isChecked: Boolean = false,
    override val viewType: Int = VIEW_TYPE_SIMPLE_CELL
) : MultiMock {

    companion object {
        const val VIEW_TYPE_SIMPLE_CELL = 1
        const val VIEW_TYPE_IMAGE_CELL = 2
        const val VIEW_TYPE_ACTION_CELL = 3
    }
}