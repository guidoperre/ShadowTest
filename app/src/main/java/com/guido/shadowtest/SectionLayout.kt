package com.guido.shadowtest

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout

class SectionLayout : ConstraintLayout {

    private val verticalPadding = resources.getDimension(
        R.dimen.wallet_api_section_layout_padding_vertical
    ).toInt()
    private val horizontalPadding = resources.getDimension(
        R.dimen.wallet_api_section_layout_padding_horizontal
    ).toInt()

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs)

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int
    ): super(context, attrs, defStyleAttr)

    init {
        addShadow()
        addClip()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        setMargin()
        clipParent()
    }

    private fun addShadow() {
        val shadowLayout = ShadowLayout(context)
        shadowLayout.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
        )
        addView(shadowLayout)
    }

    private fun addClip() {
        val clipLayout = ClipLayout(context)
        clipLayout.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT,
        )
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

    // A sad story :(
    private fun recognizeChildren(view: View): Boolean {
        return view is ClipLayout || view is ShadowLayout
    }

    // Another sad story :(
    private fun findCorrectParent(): ViewGroup? {
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
            if (!recognizeChildren(child)) {
                val parent = findCorrectParent()
                parent?.addView(child)
            } else {
                super.addView(child)
            }
        }
    }

    override fun addView(child: View?, params: ViewGroup.LayoutParams?) {
        child?.let {
            if (!recognizeChildren(child)) {
                val parent = findCorrectParent()
                parent?.addView(child, params)
            } else {
                super.addView(child, params)
            }
        }
    }

    override fun addView(child: View?, index: Int) {
        child?.let {
            if (!recognizeChildren(child)) {
                val parent = findCorrectParent()
                parent?.addView(child, index)
            } else {
                super.addView(child, index)
            }
        }
    }

    override fun addView(child: View?, width: Int, height: Int) {
        child?.let {
            if (!recognizeChildren(child)) {
                val parent = findCorrectParent()
                parent?.addView(child, width, height)
            } else {
                super.addView(child, width, height)
            }
        }
    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        child?.let {
            if (!recognizeChildren(child)) {
                val parent = findCorrectParent()
                parent?.addView(child, index, params)
            } else {
                super.addView(child, index, params)
            }
        }
    }
}
