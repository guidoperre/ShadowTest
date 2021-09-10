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
import androidx.core.content.ContextCompat


class Section : ConstraintLayout {

    private var hasRipple = false
    private var mIsNestedScrollingEnabled = true
    private var mClipChildren = false
    private var mClipToPadding = false
    private var mPaddingLeft = 0
    private var mPaddingTop = 0
    private var mPaddingRight = 0
    private var mPaddingBottom = 0
    private var mMaxHeight = 0
    private var mMinHeight = 0
    private var mMaxWidth = 0
    private var mMinWidth = 0
    private var mIsClickable = false
    private var mIsFocusable = false
    private var mOnClickListener: OnClickListener? = null
    private var mOnLongClickListener: OnLongClickListener? = null

    constructor(context: Context): super(context) {
        initAttributes(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?): super(context, attrs) {
        initAttributes(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, style: Int): super(context, attrs, style) {
        initAttributes(context, attrs, style)
    }

    init {
        addShadow()
        addClip()
    }

    private fun initAttributes(context: Context, attrs: AttributeSet?, style: Int) {
        val at = context.obtainStyledAttributes(attrs, R.styleable.Section, 0, style)
        try {
            hasRipple = at.getBoolean(R.styleable.Section_sectionRipple, false)
        } finally {
            at.recycle()
            setAttributes()
        }
    }

    private fun setAttributes() {
        setRipple()
    }

    private fun setRipple() {
        if (hasRipple) {
            getClipView()?.background = ContextCompat.getDrawable(
                context,
                R.drawable.wallet_sections_api_state_background)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        clipParent()
        copyLayout()
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

    private fun copyLayout() {
        getClipView()?.setPadding(mPaddingLeft, mPaddingTop, mPaddingRight, mPaddingBottom)
        getClipView()?.clipChildren = mClipChildren
        getClipView()?.clipToPadding = mClipToPadding
        getClipView()?.isNestedScrollingEnabled = mIsNestedScrollingEnabled
        getClipView()?.maxHeight = mMaxHeight
        getClipView()?.minHeight = mMinHeight
        getClipView()?.maxWidth = mMaxWidth
        getClipView()?.minWidth = mMinWidth
        getClipView()?.isClickable = mIsClickable
        getClipView()?.isFocusable = mIsFocusable
        getClipView()?.setOnClickListener(mOnClickListener)
        getClipView()?.setOnLongClickListener(mOnLongClickListener)
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

    override fun setBackground(background: Drawable?) {
        // no-op
    }

    override fun setBackgroundColor(color: Int) {
        // no-op
    }

    override fun setBackgroundResource(resid: Int) {
        // no-op
    }

    override fun setBackgroundTintBlendMode(blendMode: BlendMode?) {
        // no-op
    }

    override fun setBackgroundTintList(tint: ColorStateList?) {
        // no-op
    }

    override fun setBackgroundTintMode(tintMode: PorterDuff.Mode?) {
        // no-op
    }

    override fun setClickable(clickable: Boolean) {
        getClipView()?.isClickable = clickable
        mIsClickable = clickable
    }

    override fun setFocusable(focusable: Boolean) {
        getClipView()?.isFocusable = focusable
        mIsFocusable = focusable
    }

    override fun setOnClickListener(l: OnClickListener?) {
        getClipView()?.setOnClickListener(l)
        mOnClickListener = l
    }

    override fun setOnLongClickListener(l: OnLongClickListener?) {
        getClipView()?.setOnLongClickListener(l)
        mOnLongClickListener = l
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        getClipView()?.isNestedScrollingEnabled = enabled
        mIsNestedScrollingEnabled = enabled
    }

    override fun setMaxHeight(value: Int) {
        getClipView()?.maxHeight = value
        mMaxHeight = value
    }

    override fun setMinHeight(value: Int) {
        getClipView()?.minHeight = value
        mMinWidth = value
    }

    override fun setMaxWidth(value: Int) {
        getClipView()?.maxWidth = value
        mMaxWidth = value
    }

    override fun setMinWidth(value: Int) {
        getClipView()?.minWidth = value
        mMinWidth = value
    }

    override fun setClipChildren(clipChildren: Boolean) {
        getClipView()?.clipChildren = clipChildren
        mClipChildren = clipChildren
    }

    override fun setClipToPadding(clipToPadding: Boolean) {
        getClipView()?.clipToPadding = clipToPadding
        mClipToPadding = clipToPadding
    }

    override fun setClipBounds(clipBounds: Rect?) {
        // no-op
    }

    override fun setClipToOutline(clipToOutline: Boolean) {
        // no-op
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        getClipView()?.setPadding(left, top, right, bottom)
        mPaddingLeft = left
        mPaddingTop = top
        mPaddingRight = right
        mPaddingBottom = bottom
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
