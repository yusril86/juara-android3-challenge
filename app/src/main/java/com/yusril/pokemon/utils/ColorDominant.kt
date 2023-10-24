package com.yusril.pokemon.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.palette.graphics.Palette

fun getDominantColor(drawable: Drawable, onFinish: (Int) -> Unit) {
    val bitmap = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

    Palette.from(bitmap).generate { palette ->
        palette?.getDominantColor(Color.WHITE)?.let { onFinish(it) }
    }
}