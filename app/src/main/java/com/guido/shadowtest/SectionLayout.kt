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
        background = generateBackgroundWithShadow(this)
    }

    private fun setBackgroundColor() {

    }

    private fun setRoundCorner() {

    }

    private fun setShadowColor() {

    }

    private fun setElevation() {

    }

    private fun generateBackgroundWithShadow(view: View): Drawable {
        val cornerRadiusValue: Float = view.context.resources.getDimension(cornerRadius)
        val elevationValue = view.context.resources.getDimension(sectionElevation).toInt()
        val shadowColorValue = ContextCompat.getColor(view.context, shadowColor)
        val backgroundColorValue = ContextCompat.getColor(view.context, backgroundColor)
        val backgroundPaint = Paint()
        val dy = elevationValue / 3
        val shapeDrawable = ShapeDrawable()
        val shapeDrawablePadding = Rect()
        val outerRadius = floatArrayOf(
            cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue,
            cornerRadiusValue, cornerRadiusValue, cornerRadiusValue, cornerRadiusValue
        )

        shapeDrawablePadding.top = elevationValue
        shapeDrawablePadding.bottom = elevationValue
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.setShadowLayer(cornerRadiusValue, 0f, 0f, 0)
        shapeDrawable.setPadding(shapeDrawablePadding)
        shapeDrawable.paint.color = backgroundColorValue
        shapeDrawable.paint.setShadowLayer(10f, 0f, dy.toFloat(), shadowColorValue)
        view.setLayerType(LAYER_TYPE_SOFTWARE, shapeDrawable.paint)
        shapeDrawable.shape = RoundRectShape(outerRadius, null, null)

        val drawable = LayerDrawable(arrayOf<Drawable>(shapeDrawable))
        drawable.setLayerInset(
            0,
            elevationValue,
            elevationValue * 2,
            elevationValue,
            elevationValue * 2
        )
        return drawable
    }

}