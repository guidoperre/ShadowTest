package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class SectionLayout : ConstraintLayout {

    companion object {
        private const val TWO = 2
        private const val ZERO_FLOAT = 0f
    }

    private val layoutRect = RectF()
    private val path = Path()

    private val drawable = ContextCompat.getDrawable(context, R.drawable.wallet_api_sections_bg)
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

    private val verticalPadding = resources.getDimension(
        R.dimen.wallet_api_section_layout_padding_vertical
    ).toInt()
    private val horizontalPadding = resources.getDimension(
        R.dimen.wallet_api_section_layout_padding_horizontal
    ).toInt()

    private var horizontalScaleRatio = 1f
    private var verticalScaleRatio = 1f

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ): super(context, attrs, defStyleAttr)

    init {
        background = drawable
    }

    private fun setMargin() {
        val params = layoutParams as MarginLayoutParams
        params.setMargins(
            horizontalPadding,
            verticalPadding,
            horizontalPadding,
            verticalPadding
        )
        layoutParams = params
    }

    private fun setBackgroundScale() {
        scaleX = horizontalScaleRatio
        scaleY = verticalScaleRatio
    }

    private fun setChildScale(view: View) {
        if (view !is ViewGroup) {
            view.scaleX = 1 / horizontalScaleRatio
            view.scaleY = 1 / verticalScaleRatio
        } else {
            setChildViews(view)
        }
    }

    private fun setBounds(w: Int, h: Int) {
        path.reset()
        layoutRect.top = topGap
        layoutRect.left = horGap
        layoutRect.right = w - horGap
        layoutRect.bottom = h - botGap
        path.addRoundRect(layoutRect, cornerRadius, cornerRadius, Path.Direction.CW)
        path.close()
    }

    private fun setChildViews(view: ViewGroup) {
        for (i in 0 until view.childCount) {
            setChildScale(view.getChildAt(i))
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setMargin()
    }

    override fun draw(canvas: Canvas?) {
        canvas?.translate(ZERO_FLOAT, topPad)
        super.draw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        horizontalScaleRatio = ((horPad * TWO) + w) / w
        verticalScaleRatio = (botPad + topPad + h) / h
        setBounds(w, h)
        setBackgroundScale()
        setChildViews(this)
    }

    override fun dispatchDraw(canvas: Canvas) {
        val save = canvas.save()
        canvas.clipPath(path)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(save)
    }

    override fun addView(child: View?) {
        super.addView(child)
        child?.let {
            setChildScale(it)
        }
    }

    override fun addView(child: View?, index: Int) {
        super.addView(child, index)
        child?.let {
            setChildScale(it)
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        super.addView(child, params)
        child?.let {
            setChildScale(it)
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        super.addView(child, index, params)
        child?.let {
            setChildScale(it)
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        super.addView(child, width, height)
        child?.let {
            setChildScale(it)
        }
    }

    override fun addViewInLayout(
        child: View?,
        index: Int,
        params: ViewGroup.LayoutParams?
    ): Boolean {
        child?.let {
            setChildScale(it)
        }
        return super.addViewInLayout(child, index, params)
    }

    override fun addViewInLayout(
        child: View?,
        index: Int,
        params: ViewGroup.LayoutParams?,
        preventRequestLayout: Boolean
    ): Boolean {
        child?.let {
            setChildScale(it)
        }
        return super.addViewInLayout(child, index, params, preventRequestLayout)
    }
}
