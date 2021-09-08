package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class ShadowLayout : View {

    companion object {
        private const val ZERO = 0
        private const val TWO = 2
        private const val THREE = 3
        private const val FOUR = 4
        private const val ZERO_FLOAT = 0f
    }

    private val horPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_left_right
    )
    private val botTran = resources.getDimension(
        R.dimen.wallet_api_section_layout_translation_bottom
    )
    private val oneDp = resources.getDimension(
        R.dimen.wallet_api_section_layout_translation_bottom
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
        if (w != ZERO && h != ZERO) {
            scaleX = ((constant + w + getAddSpace(w)) / w)
            scaleY = ((constant + h + getAddSpace(h)) / h)
        }
    }

    private fun getAddSpace(n: Int): Int {
        val add =  when (ScreenUtils.pixelsToDp(n, resources.displayMetrics.densityDpi)) {
            in 32..37 -> oneDp * TWO
            in 38..43 -> oneDp * THREE
            in 44..52 -> oneDp * FOUR
            in 53..66 -> oneDp * THREE
            in 67..98 -> oneDp * TWO
            in 99..132 -> oneDp
            else -> ZERO_FLOAT
        }
        return if (resources.displayMetrics.densityDpi < DisplayMetrics.DENSITY_XHIGH) {
            ScreenUtils.dpToPixel(add, resources.displayMetrics.densityDpi) / TWO
        } else {
            ScreenUtils.dpToPixel(add, resources.displayMetrics.densityDpi) / FOUR
        }
    }

    override fun draw(canvas: Canvas?) {
        canvas?.translate(ZERO_FLOAT, botTran)
        super.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setBackgroundScale(w, h)
    }
}
