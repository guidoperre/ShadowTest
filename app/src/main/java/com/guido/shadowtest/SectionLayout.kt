package com.guido.shadowtest

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

class SectionLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        addShadow()
        addClip()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        clipParent()
    }

    private fun addShadow() {
        val shadowLayout = inflate(context, R.layout.shadow_layout, this)
        addView(shadowLayout)
    }

    private fun addClip() {
        val clipLayout = LayoutInflater.from(context).inflate(
            R.layout.clip_layout, this, false
        )
        addView(clipLayout)
    }

    private fun clipParent() {
        val parent = parent as ViewGroup?
        parent?.clipChildren = false
    }

    private fun recognizeChildren(view: View): Boolean {
        return view is ClipLayout || view is ShadowLayout
    }

    private fun getClipView(): ClipLayout? {
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view is ClipLayout) {
                return view
            }
        }
        return null
    }

    override fun setElevation(elevation: Float) {
        // no-op
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        getClipView()?.isNestedScrollingEnabled = enabled
    }

    override fun setMaxHeight(value: Int) {
        getClipView()?.maxHeight = value
    }

    override fun setMinHeight(value: Int) {
        getClipView()?.minHeight = value
    }

    override fun setMaxWidth(value: Int) {
        getClipView()?.maxWidth = value
    }

    override fun setMinWidth(value: Int) {
        getClipView()?.minWidth = value
    }

    override fun setClipChildren(clipChildren: Boolean) {
        getClipView()?.clipChildren = clipChildren
    }

    override fun setClipToPadding(clipToPadding: Boolean) {
        getClipView()?.clipToPadding = clipToPadding
    }

    override fun setClipBounds(clipBounds: Rect?) {
        getClipView()?.clipBounds = clipBounds
    }

    override fun setClipToOutline(clipToOutline: Boolean) {
        getClipView()?.clipToOutline = clipToOutline
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        getClipView()?.setPadding(left, top, right, bottom)
    }

    override fun setBackground(background: Drawable?) {
        getClipView()?.background = background
    }

    override fun setBackgroundColor(color: Int) {
        getClipView()?.setBackgroundColor(color)
    }

    override fun setBackgroundResource(resid: Int) {
        getClipView()?.setBackgroundResource(resid)
    }

    override fun setBackgroundTintBlendMode(blendMode: BlendMode?) {
        getClipView()?.backgroundTintBlendMode = blendMode
    }

    override fun setBackgroundTintList(tint: ColorStateList?) {
        getClipView()?.backgroundTintList = tint
    }

    override fun setBackgroundTintMode(tintMode: PorterDuff.Mode?) {
        getClipView()?.backgroundTintMode = tintMode
    }

    override fun addView(child: View?) {
        child?.let {
            if (!recognizeChildren(it)) {
                getClipView()?.addView(it)
            } else {
                super.addView(it)
            }
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        child?.let {
            if (!recognizeChildren(it)) {
                getClipView()?.addView(it, params)
            } else {
                super.addView(it, params)
            }
        }
    }

    override fun addView(child: View?, index: Int) {
        child?.let {
            if (!recognizeChildren(it)) {
                getClipView()?.addView(it, index)
            } else {
                super.addView(it, index)
            }
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        child?.let {
            if (!recognizeChildren(it)) {
                getClipView()?.addView(it, width, height)
            } else {
                super.addView(it, width, height)
            }
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        child?.let {
            if (!recognizeChildren(it)) {
                getClipView()?.addView(it, index, params)
            } else {
                super.addView(it, index, params)
            }
        }
    }
}
