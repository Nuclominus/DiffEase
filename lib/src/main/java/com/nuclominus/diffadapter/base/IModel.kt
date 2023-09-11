package com.nuclominus.diffadapter.base

import com.nuclominus.diffadapter.helpers.AdapterConstants

/**
 * [IModel] interface-contract for adapters items
 * All adapter items should have [viewType] for adapter realisation
 * Exceptions: single item adapter without adapter modification
 */
interface IModel {
    val viewType: Int
        get() = AdapterConstants.BaseView.BASE_VIEW_TYPE
}