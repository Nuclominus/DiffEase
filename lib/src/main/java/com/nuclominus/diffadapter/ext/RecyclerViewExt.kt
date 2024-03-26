package com.nuclominus.diffadapter.ext

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs
import kotlin.math.withSign

const val NO_POSITION = -1

/**
 * Scroll to position with smooth scroll. If position is too far away from current position, it will scroll to position - smoothLength
 * and then smooth scroll to position.
 *
 * @param positionTo position to scroll to
 * @param smoothLength length of smooth scroll
 * @see smoothScrollToPosition
 */
fun RecyclerView.scrollToPositionWithSmooth(positionTo: Int, smoothLength: Int) {
    smoothScrollToPosition(this, firstPosition(), positionTo, smoothLength)
}

/**
 * Get first visible position in RecyclerView
 *
 * @return first visible position or [NO_POSITION] if no layout manager is set
 */
fun RecyclerView.firstPosition(): Int {
    return layoutManager?.let {
        (it as LinearLayoutManager).findFirstVisibleItemPosition()
    } ?: NO_POSITION
}

/**
 * Scroll to position with smooth scroll. If position is too far away from current position, it will scroll to position - smoothLength
 * and then smooth scroll to position.
 *
 * @param list RecyclerView to scroll
 * @param positionFrom current position
 * @param positionTo position to scroll to
 * @param smoothLength length of smooth scroll
 */
fun smoothScrollToPosition(
    list: RecyclerView,
    positionFrom: Int,
    positionTo: Int,
    smoothLength: Int
) {
    if (positionTo < 0) return
    try {
        val diff = positionTo - positionFrom
        if (abs(diff) > smoothLength) {
            list.scrollToPosition(positionTo - smoothLength.toFloat().withSign(diff).toInt())
        }
        list.smoothScrollToPosition(positionTo)
    } catch (ex: Exception) {
        Log.e("RecyclerViewExtensions", ex.localizedMessage, ex)
    }
}

/**
 * Check if RecyclerView can scroll horizontally
 *
 * @return true if RecyclerView can scroll horizontally
 */
fun RecyclerView.canScrollHorizontally(): Boolean {
    return canScrollHorizontally(1) || canScrollHorizontally(-1)
}

/**
 * Check if RecyclerView can scroll vertically
 *
 * @return true if RecyclerView can scroll vertically
 */
fun RecyclerView.canScrollVertically(): Boolean {
    return canScrollVertically(1) || canScrollVertically(-1)
}

/**
 * This method use reflection to access a private member of class
 */
private fun <T : Any> T.getPrivateProperty(name: String): Any? {
    return javaClass.getDeclaredField(name).let { field ->
        field.isAccessible = true
        return@let field.get(this)
    }
}

/**
 * Clear stack of touch listeners in recyclerview
 */
internal fun RecyclerView.removeAllTouchListeners() {
    (getPrivateProperty("mOnItemTouchListeners") as? ArrayList<*>)?.clear()
}