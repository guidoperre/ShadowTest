package com.guido.shadowtest

import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class SectionLayout : ConstraintLayout {

    companion object {
        private const val backgroundColor = R.color.white
        private const val cornerRadius = R.dimen.section_radius_corner
        private const val shadowColor =  R.color.shadowColor
        private const val sectionElevation = R.dimen.section_elevation
    }

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
        val cornerRadiusValue: Float = view.context.resources.getDimension(cornerRadius)
        val shadowColorValue = ContextCompat.getColor(view.context, shadowColor)
        val backgroundColorValue = ContextCompat.getColor(view.context, backgroundColor)
        val shapeDrawable = ShapeDrawable()
        val outerRadius = floatArrayOf(
            cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue,
            cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue
        )

        shapeDrawable.paint.color = backgroundColorValue
        shapeDrawable.paint.setShadowLayer(10f, 0f, 5f, shadowColorValue)
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