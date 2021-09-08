package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class ShadowLayout : View {

    companion object {
        private const val TWO = 2
        private const val ZERO_FLOAT = 0f
        private const val RELATION_NUMBER = 788f
    }

    private val horPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_left_right
    )
    private val constant = horPad * TWO

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ): super(context, attrs, defStyleAttr)

    init {
        background = ContextCompat.getDrawable(context, R.drawable.wallet_api_sections_bg)
    }

    private fun setBackgroundScale(w: Int, h: Int) {
        if (w != 0 && h != 0) {
            scaleX = (getRelationshipRatio(w) + w) / w
            scaleY = (getRelationshipRatio(h) + h) / h
        }
    }

    private fun getRelationshipRatio(n: Int): Float {
        return if (n < RELATION_NUMBER) {
            constant * ((1 / (n / RELATION_NUMBER)) / 2.5f)
        } else {
            constant
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.translate(0.5f, ((horPad / 2) - 1))
        super.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setBackgroundScale(w, h)
    }
}
