package com.guido.shadowtest

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class SectionLayout : ConstraintLayout {

    companion object {
        private const val CORNER_RADIUS = R.dimen.section_radius_corner
        private const val SHADOW_COLOR =  R.color.shadowColor
        private const val BACKGROUND_COLOR = R.color.white
    }

    init {
        val sectionDrawable = SectionDrawable.builder()
            .beginConfig()
            .setRoundCorner(context.resources.getDimension(CORNER_RADIUS))
            .setShadowColor(ContextCompat.getColor(context, SHADOW_COLOR))
            .setBackgroundColor(ContextCompat.getColor(context, BACKGROUND_COLOR))
            .endConfig()
            .build()
        background = sectionDrawable.getDrawable()
    }

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(
        context,
        attrs,
        defStyleAttr
    )

}
