package com.example.fueture_yerpicker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

typealias onDateClickListener = (data: Int) -> Unit

class YerPicker @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : View(context, attrs) {


    var setDateListener: onDateClickListener? = null

    init {
        attrs?.let { setAttribute(it) }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        SettingsPicker.setupField(w, h)
        Draw.createMatrix()
    }


    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Draw.drawArrow(canvas)
        Draw.drawBorder(canvas)
        Draw.drawMatrixDate(canvas)
        Draw.drawRangeData(canvas)

    }

    @SuppressLint("Recycle")
    private fun setAttribute(attrs: AttributeSet) {
        val typArray = context.obtainStyledAttributes(attrs, R.styleable.YerPicker)
        SettingsPicker.borderWidth =
            typArray.getDimension(
                R.styleable.YerPicker_picker_border_width, context.dpToPx(
                    DEFAULT_BORDER_WIDTH
                )
            )
        SettingsPicker.borderColor = typArray.getColor(
            R.styleable.YerPicker_picker_border_color,
            DEFAULT_BORDER_COLOR
        )
        SettingsPicker.radius = typArray.getDimension(R.styleable.YerPicker_picker_border_corner_radius,context.dpToPx(10))
        SettingsPicker.startDate = typArray.getInt(R.styleable.YerPicker_picker_start_date,1990)
        SettingsPicker.textSizeSP = typArray.getDimension(R.styleable.YerPicker_picker_text_size,context.dpToSp(15))
        typArray.recycle()
        SettingsPicker.paint.apply {
            color = SettingsPicker.borderColor
            strokeWidth = SettingsPicker.borderWidth
            style = Paint.Style.STROKE
        }
        SettingsPicker.textPaint.apply {
            color = SettingsPicker.textColor
            style = Paint.Style.FILL
            textSize = SettingsPicker.textSizeSP
        }
    }



    private val listener = TouchListener()

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        listener.onTouchEvent(event,setDateListener){invalidate()}
        return super.onTouchEvent(event)
    }

    companion object {
        const val DEFAULT_BORDER_WIDTH = 2
        const val DEFAULT_BORDER_COLOR = Color.CYAN
        const val DEFAULT_COLUMN = 3
        const val DEFAULT_ROW = 5

    }
}