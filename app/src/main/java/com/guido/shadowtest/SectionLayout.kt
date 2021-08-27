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

        private const val ZERO = 0
    }

    constructor(context: Context): super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val sectionDrawable = SectionDrawable.builder()
            .beginConfig()
            .setRoundCorner(context.resources.getDimension(CORNER_RADIUS))
            .setShadowColor(ContextCompat.getColor(context, SHADOW_COLOR))
            .setBackgroundColor(ContextCompat.getColor(context, BACKGROUND_COLOR))
            .endConfig()
            .build()
        setPadding(ZERO, ZERO, ZERO, ZERO)
        background = sectionDrawable.getDrawable()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(
            left + SectionDrawable.ELEVATION,
            top + SectionDrawable.ELEVATION,
            right + SectionDrawable.ELEVATION,
            bottom + SectionDrawable.ELEVATION * 2
        )
    }
}
