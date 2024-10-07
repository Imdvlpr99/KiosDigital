package id.imdvlpr.kiosdigital.utils.extension

import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import id.imdvlpr.kiosdigital.R

fun Context.btnPrimaryBackgroundTint(): ColorStateList {
    val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled),  // Enabled state
        intArrayOf(-android.R.attr.state_enabled)  // Disabled state
    )

    val colors = intArrayOf(
        ContextCompat.getColor(this, R.color.colorPrimary),  // Color for enabled state
        ContextCompat.getColor(this, R.color.darkGrey)  // Color for disabled state
    )

    return ColorStateList(states, colors)
}