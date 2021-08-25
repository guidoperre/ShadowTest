package com.guido.shadowtest

import android.graphics.*
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RoundRectShape

class SectionDrawable(builder: Builder) : ShapeDrawable() {

    companion object {
        /**
         * Builds the shape.
         *
         * @return IShapeBuilder
         */
        fun builder(): ShapeBuilder {
            return Builder()
        }
    }

    private var elevation = builder.elevation
    private var roundCorner = builder.roundCorner
    private var shadowBlur = builder.shadowBlur
    private var shadowColor  = builder.shadowColor
    private var backgroundColor = builder.backgroundColor

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        setPadding(getVerticalPadding())
        paint.color = backgroundColor
        paint.setShadowLayer(shadowBlur, 0f, (elevation / 3).toFloat(), shadowColor)
        shape = RoundRectShape(getRoundCorner(), null, null)

    }

    fun getElevation(): Int {
        return elevation
    }

    fun getDrawable(): Drawable {
        val drawable = LayerDrawable(arrayOf<Drawable>(this))
        drawable.setLayerInset(0, elevation, elevation * 2, elevation, elevation * 2)
        return drawable
    }

    private fun getRoundCorner(): FloatArray {
        val backgroundPaint = Paint()
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.setShadowLayer(roundCorner, 0f, 0f, 0)
        return floatArrayOf(
            roundCorner, roundCorner, roundCorner, roundCorner,
            roundCorner, roundCorner, roundCorner, roundCorner
        )
    }

    private fun getVerticalPadding(): Rect {
        val rect = Rect()
        rect.top = elevation
        rect.bottom = elevation
        return rect
    }

    class Builder : ConfigBuilder, ShapeBuilder {

        var elevation: Int = 10
        var roundCorner: Float = 10f
        var shadowBlur: Float = 10f
        var shadowColor: Int = 0
        var backgroundColor: Int = 0

        override fun beginConfig(): ConfigBuilder {
            return this
        }

        override fun setRoundCorner(corner: Float): ConfigBuilder {
            this.roundCorner = corner
            return this
        }

        override fun setShadowBlur(blur: Float): ConfigBuilder {
            this.shadowBlur = blur
            return this
        }

        override fun setShadowColor(color: Int): ConfigBuilder {
            this.shadowColor = color
            return this
        }

        override fun setBackgroundColor(color: Int): ConfigBuilder {
            this.backgroundColor = color
            return this
        }

        override fun endConfig(): ShapeBuilder {
            return this
        }

        override fun build(): SectionDrawable {
            return SectionDrawable(this)
        }
    }

    interface ConfigBuilder {
        /**
         * Sets corner radius
         *
         * @param corner the size of the corner radius.
         * @return IConfigBuilder
         */
        fun setRoundCorner(corner: Float): ConfigBuilder

        /**
         * Sets shadow blur
         *
         * @param blur the blur size
         * @return IConfigBuilder
         */
        fun setShadowBlur(blur: Float): ConfigBuilder

        /**
         * Sets shadow color.
         *
         * @param color the shadow color
         * @return IConfigBuilder
         */
        fun setShadowColor(color: Int): ConfigBuilder

        /**
         * Sets background color.
         *
         * @param color the background color
         * @return IConfigBuilder
         */
        fun setBackgroundColor(color: Int): ConfigBuilder

        fun endConfig(): ShapeBuilder
    }

    interface ShapeBuilder {
        /**
         * Begin config.
         *
         * @return IConfigBuilder
         */
        fun beginConfig(): ConfigBuilder

        /**
         * Builds it.
         *
         * @return SectionDrawable
         */
        fun build(): SectionDrawable
    }
}