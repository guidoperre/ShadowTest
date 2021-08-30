package com.guido.shadowtest

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class SectionLayout : ConstraintLayout {

    private val cornerRadius = context.resources.getDimension(R.dimen.section_radius_corner)
    private val backgroundColor = ContextCompat.getColor(context, R.color.white)
    private val shadowColor = ContextCompat.getColor(context, R.color.shadowColor)

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
        val shapeDrawable = generateBackgroundWithShadow(this)
        val drawable = toDrawable(shapeDrawable)
        setLayerType(LAYER_TYPE_SOFTWARE, shapeDrawable.paint)
        background = drawable
    }

    private fun generateBackgroundWithShadow(view: View): ShapeDrawable {
        val shapeDrawable = ShapeDrawable()
        val outerRadius = floatArrayOf(
            cornerRadius, cornerRadius, cornerRadius, cornerRadius,
            cornerRadius, cornerRadius, cornerRadius, cornerRadius
        )

        shapeDrawable.paint.color = backgroundColor
        shapeDrawable.paint.setShadowLayer(10f, 0f, 5f, shadowColor)
        shapeDrawable.shape = RoundRectShape(outerRadius, null, null)

        return shapeDrawable
    }

    private fun toDrawable(shapeDrawable: ShapeDrawable): Drawable {
        val drawable = LayerDrawable(arrayOf<Drawable>(shapeDrawable))
        drawable.setLayerInset(
            0,
            10,
            10,
            10,
            20
        )
        return drawable
    }

}