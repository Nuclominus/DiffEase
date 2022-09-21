package com.nuclominus.diffadapter.error

class UnSupportedViewHolderError(private val viewType: Int) : Exception() {
    override val message: String
        get() = ERROR_TEXT.plus(viewType)

    companion object {
        private const val ERROR_TEXT = "Not supported view type ="
    }
}