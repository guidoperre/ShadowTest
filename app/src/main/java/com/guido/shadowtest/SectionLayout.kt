package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class SectionLayout : ConstraintLayout {

    companion object {
        private const val TWO = 2
        private const val ZERO_FLOAT = 0f
    }

    private val layoutRect = RectF()
    private val path = Path()

    private val cornerRadius = resources.getDimension(
        R.dimen.wallet_api_section_layout_corner_radius
    )
    private val horPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_left_right
    )
    private val topPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_top
    )
    private val botPad = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_bottom
    )
    private val horGap = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_left_right_gap
    )
    private val topGap = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_top_gap
    )
    private val botGap = resources.getDimension(
        R.dimen.wallet_api_section_layout_offset_bottom_gap
    )

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
        val drawable = ContextCompat.getDrawable(context, R.drawable.wallet_api_sections_bg)
        background = drawable
    }

    private fun setBackgroundScale(w: Int, h: Int) {
        scaleX = ((horPad * TWO) + w) / w
        scaleY = (botPad + topPad + h) / h
    }

    private fun setViewGroupBounds(w: Int, h: Int) {
        path.reset()
        layoutRect.top = topGap
        layoutRect.left = horGap
        layoutRect.right = w - horGap
        layoutRect.bottom = h - botGap
        path.addRoundRect(layoutRect, cornerRadius, cornerRadius, Path.Direction.CW)
        path.close()
    }

    override fun draw(canvas: Canvas?) {
        canvas?.translate(ZERO_FLOAT, topPad)
        super.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setBackgroundScale(w, h)
        setViewGroupBounds(w, h)
    }

    override fun dispatchDraw(canvas: Canvas) {
        val save = canvas.save()
        canvas.clipPath(path)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(save)
    }
}
