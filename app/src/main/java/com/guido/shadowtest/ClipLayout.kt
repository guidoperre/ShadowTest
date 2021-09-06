package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class ClipLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr)  {

    private val layoutRect = RectF()
    private val path = Path()

    private val cornerRadius = resources.getDimension(
        R.dimen.wallet_api_section_layout_corner_radius
    )

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
