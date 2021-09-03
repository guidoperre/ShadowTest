package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class ShadowLayout : View {

    companion object {
        private const val TWO = 2
        private const val ZERO_FLOAT = 0f
    }

    private val horPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_left_right
    )
    private val topPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_top
    )
    private val botPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_bottom
    )

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
        scaleX = ((horPad * TWO) + w) / w
        scaleY = (botPad + topPad + h) / h
    }

    override fun draw(canvas: Canvas?) {
        canvas?.translate(ZERO_FLOAT, topPad)
        super.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setBackgroundScale(w, h)
    }
}
