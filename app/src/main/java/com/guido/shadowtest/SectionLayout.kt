package com.guido.shadowtest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

class SectionLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val verticalPadding = resources.getDimension(
        R.dimen.wallet_api_section_layout_padding_vertical
    ).toInt()
    private val horizontalPadding = resources.getDimension(
        R.dimen.wallet_api_section_layout_padding_horizontal
    ).toInt()

    init {
        //addShadow()
        //addClip()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setMargin()
        clipParent()
        getClipView()?.let {
            val params = it.layoutParams
            params.width = width
            params.height = height
            it.layoutParams = params
        }
    }

    private fun addShadow() {
        val shadowLayout = inflate(context, R.layout.shadow_layout, this)
        addView(shadowLayout)
    }

    private fun addClip() {
        val clipLayout = inflate(context, R.layout.clip_layout, null)
        addView(clipLayout)
    }

    private fun clipParent() {
        val parent = parent as ViewGroup?
        parent?.clipChildren = false
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
