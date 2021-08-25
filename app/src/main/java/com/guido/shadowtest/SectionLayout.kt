package com.guido.shadowtest

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class SectionLayout : ConstraintLayout {

    companion object {
        private const val CORNER_RADIUS = R.dimen.section_radius_corner
        private const val SHADOW_BLUR = 10f
        private const val SHADOW_COLOR =  R.color.shadowColor
        private const val BACKGROUND_COLOR = R.color.white
    }

    constructor(context: Context): super(context) {
        initBackground()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        initBackground()
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ): super(context, attrs, defStyleAttr) {
        initBackground()
    }

    private fun initBackground() {
        val sectionDrawable = SectionDrawable.builder()
            .beginConfig()
            .setRoundCorner(context.resources.getDimension(CORNER_RADIUS))
            .setShadowBlur(SHADOW_BLUR)
            .setShadowColor(ContextCompat.getColor(context, SHADOW_COLOR))
            .setBackgroundColor(ContextCompat.getColor(context, BACKGROUND_COLOR))
            .endConfig()
            .build()
        background = sectionDrawable.getDrawable()
    }
}
