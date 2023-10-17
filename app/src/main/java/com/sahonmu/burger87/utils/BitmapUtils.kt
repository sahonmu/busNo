package com.sahonmu.burger87.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

object BitmapUtils {

    @SuppressLint("UseCompatLoadingForDrawables")
    fun getBitmapDescriptor(context: Context, id: Int, width: Int, height: Int): BitmapDescriptor? {
        val vectorDrawable = context.getDrawable(id) ?: return null
        vectorDrawable.setBounds(0, 0, width, height)
        val bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bm)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bm)
    }
}
