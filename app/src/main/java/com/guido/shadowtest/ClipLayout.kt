package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class ClipLayout : ConstraintLayout {

    private val layoutRect = RectF()
    private val path = Path()

    private val cornerRadius = resources.getDimension(
        R.dimen.wallet_api_section_layout_corner_radius
    )

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ): super(context, attrs, defStyleAttr)

    private fun setBounds(w: Int, h: Int) {
        path.reset()
        layoutRect.right = w.toFloat()
        layoutRect.bottom = h.toFloat()
        path.addRoundRect(layoutRect, cornerRadius, cornerRadius, Path.Direction.CW)
        path.close()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setBounds(w, h)
    }

    override fun dispatchDraw(canvas: Canvas) {
        val save = canvas.save()
        canvas.clipPath(path)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(save)
    }
}
