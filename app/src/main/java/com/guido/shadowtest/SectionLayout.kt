package com.guido.shadowtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class SectionLayout : ConstraintLayout {

    companion object {
        private const val SHADOW_BLUR = 10F
        private const val DX_SHADOW_OFFSET = 0F
        private const val DY_SHADOW_OFFSET = 5F
    }

    private val cornerRadius = context.resources.getDimension(R.dimen.section_radius_corner)
    private val backgroundColor = ContextCompat.getColor(context, R.color.white)
    private val shadowColor = ContextCompat.getColor(context, R.color.shadowColor)

    private val path = Path()
    private val rect = RectF()

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ): super(context, attrs, defStyleAttr)

    private fun generateBackground(): Drawable {
        val shapeDrawable = ShapeDrawable()
        val outerRadius = floatArrayOf(
            cornerRadius, cornerRadius, cornerRadius, cornerRadius,
            cornerRadius, cornerRadius, cornerRadius, cornerRadius
        )

        shapeDrawable.paint.color = backgroundColor
        shapeDrawable.paint.setShadowLayer(SHADOW_BLUR, DX_SHADOW_OFFSET, DY_SHADOW_OFFSET, shadowColor)
        shapeDrawable.shape = RoundRectShape(outerRadius, null, null)

        return LayerDrawable(arrayOf<Drawable>(shapeDrawable))
    }

    private fun setParentConfig() {
        val parent = this.parent as ViewGroup?
        parent?.setLayerType(LAYER_TYPE_SOFTWARE, null)
        parent?.clipChildren = false
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        background = generateBackground()
        setParentConfig()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.reset()
        rect.right = w.toFloat()
        rect.bottom = h.toFloat()
        path.addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW)
        path.close()
    }

    override fun dispatchDraw(canvas: Canvas) {
        val save = canvas.save()
        canvas.clipPath(path)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(save)
    }
}
