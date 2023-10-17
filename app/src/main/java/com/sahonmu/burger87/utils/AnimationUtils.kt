package com.sahonmu.burger87.utils

import android.animation.ValueAnimator
import android.view.View

object AnimationUtils {

    fun translateY(view: View, to: Float): ValueAnimator {
        return ValueAnimator().apply {
            this.setFloatValues(view.translationY, to)
            this.addUpdateListener {
                val value = it.animatedValue as Float
                view.translationY = value
            }
        }
    }
}
