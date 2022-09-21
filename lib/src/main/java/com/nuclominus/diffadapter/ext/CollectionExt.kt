package com.nuclominus.diffadapter.ext

/**
 * Create mutable copy of collection
 */
internal fun <T> Collection<T>.mutableCopyOf(): MutableList<T> {
    val original = this
    return mutableListOf<T>().apply { addAll(original) }
}