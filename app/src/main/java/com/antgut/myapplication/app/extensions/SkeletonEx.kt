package com.antgut.myapplication.app.extensions

import android.os.Handler
import android.os.Looper
import com.faltenreich.skeletonlayout.Skeleton


private val handler = Handler(Looper.getMainLooper())

fun Skeleton.showWithDelay(msDelayTime: Long = 300) {
    handler.postDelayed({
        if (!this.isSkeleton()) showWithDelay()
    }, msDelayTime)
}

fun Skeleton.hideWithDelay() {
    handler.removeCallbacksAndMessages(null)
    if (this.isSkeleton()) hideWithDelay()
}