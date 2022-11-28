package com.example.fueture_yerpicker

import android.graphics.Canvas
import android.graphics.RectF
import android.util.Log

object Draw {



    fun createMatrix() {

        for (row in 1 until SettingsPicker.rows) {
            val cellHeight = SettingsPicker.cellSizeH * row
            for (col in 0 until SettingsPicker.columns) {

                SettingsPicker.datePicker[row][col].apply {
                    rect.left = ((SettingsPicker.cellSizeW * col).toFloat())
                    rect.top = cellHeight.toFloat()
                    rect.right =
                        SettingsPicker.datePicker[row][col].rect.left + SettingsPicker.cellSizeW
                    rect.bottom =
                        SettingsPicker.datePicker[row][col].rect.top + SettingsPicker.cellSizeH
                    date = SettingsPicker.startDate++
                    status = State.NOT_SELECT
                }
            }
        }
    }

    fun drawMatrixDate(canvas: Canvas?) {
        SettingsPicker.datePicker.forEach { dates ->
            dates.forEach { date ->
                Log.e("Kart","${date.rect}")
                SettingsPicker.textPaint.color =
                    if (date.status == State.NOT_SELECT) SettingsPicker.textColor
                    else SettingsPicker.textColorSelect
                canvas?.drawText(date.date.toString(), date.rect.centerX(),
                    date.rect.centerY(), SettingsPicker.textPaint)
            }
        }
    }

    fun drawRangeData(canvas: Canvas?) {
        val rect = RectF(
            SettingsPicker.borderWidth,
            SettingsPicker.borderWidth,
            SettingsPicker.borderWidth + SettingsPicker.cellSizeW,
            SettingsPicker.borderWidth + SettingsPicker.cellSizeH
        )
        SettingsPicker.textPaint.color = SettingsPicker.textColorSelect
        canvas?.drawText(
            "${SettingsPicker.datePicker[1].first().date} - ${
                SettingsPicker.datePicker.last().last().date
            }",
            rect.centerX(),
            rect.centerY(),
            SettingsPicker.textPaint
        )
    }

    fun drawArrow(canvas: Canvas?) {
        SettingsPicker.textPaint.color = SettingsPicker.textColor
        canvas?.drawPath(SettingsPicker.arrowPath, SettingsPicker.paintArrow)
    }

    fun drawBorder(canvas: Canvas?) {
        canvas?.drawRoundRect(
            SettingsPicker.viewRectF,
            SettingsPicker.radius,
            SettingsPicker.radius,
            SettingsPicker.paint
        )
    }

}