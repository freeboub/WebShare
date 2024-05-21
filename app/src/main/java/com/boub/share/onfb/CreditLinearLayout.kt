/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <freeboub@gmail.org> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Olivier Bouillet
 * ----------------------------------------------------------------------------
 */

package com.boub.share.onfb

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView

class CreditLinearLayout : LinearLayout {

    constructor(context: Context) :
            this(context, null)

    constructor(context: Context, attrs: AttributeSet) :
            this(context, attrs, 0)

    constructor( context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : super(context, attrs, defStyleAttr)
    {
        init( attrs )
    }

    @SuppressLint("CustomViewStyleable")
    fun init(attrs: AttributeSet? )
    {
        LayoutInflater.from(context).inflate(R.layout.credit_linear_layout, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.credit_linear_layout, 0, 0)
            val text: TextView = findViewById(R.id.text)
            val image: AppCompatImageView = findViewById(R.id.image)

            if (typedArray.hasValue(R.styleable.credit_linear_layout_credit_linear_layout_text)) {
                text.text = typedArray.getString(R.styleable.credit_linear_layout_credit_linear_layout_text)
            }

            if (typedArray.hasValue(R.styleable.credit_linear_layout_credit_linear_layout_logo)) {
                val logo = typedArray.getResourceId(
                    R.styleable.credit_linear_layout_credit_linear_layout_logo,
                    -1
                )
                if (logo != -1) {
                    image.setImageResource(logo)
                }
            }

            if (typedArray.hasValue(R.styleable.credit_linear_layout_credit_linear_layout_content_description )) {
                image.contentDescription = typedArray.getString(R.styleable.credit_linear_layout_credit_linear_layout_content_description )
            }

            typedArray.recycle()
        }
    }
}