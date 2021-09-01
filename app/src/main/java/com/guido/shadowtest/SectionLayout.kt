package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class SectionLayout : ConstraintLayout {

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

    override fun draw(canvas: Canvas?) {
        canvas?.translate(0f, resources.getDimension(R.dimen.wallet_api_section_layout_offset_top))
        super.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setBackgroundScale(w, h)
        setViewGroupBounds(w, h)
    }

    private fun setBackgroundScale(w: Int, h: Int) {
        val heightGap = resources.getDimension(R.dimen.wallet_api_section_layout_offset_bottom) + resources.getDimension(R.dimen.wallet_api_section_layout_offset_top) + h
        val widthGap = (resources.getDimension(R.dimen.wallet_api_section_layout_offset_left_right) * 2) + w
        val widthRatio = widthGap / w
        val heightRatio = heightGap / h
        scaleX = widthRatio
        scaleY = heightRatio
    }

    private fun setViewGroupBounds(w: Int, h: Int) {
        path.reset()
        layoutRect.top = resources.getDimension(R.dimen.wallet_api_section_layout_offset_top_gap)
        layoutRect.left = resources.getDimension(R.dimen.wallet_api_section_layout_offset_left_right_gap)
        layoutRect.right = w - resources.getDimension(R.dimen.wallet_api_section_layout_offset_left_right_gap)
        layoutRect.bottom = h - resources.getDimension(R.dimen.wallet_api_section_layout_offset_bottom_gap)
        path.addRoundRect(layoutRect, cornerRadius, cornerRadius, Path.Direction.CW)
        path.close()
    }

    override fun dispatchDraw(canvas: Canvas) {
        val save = canvas.save()
        canvas.clipPath(path)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(save)
    }
}
