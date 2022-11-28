package com.example.fueture_yerpicker

import android.view.MotionEvent



class TouchListener {

    private var datePosition = Pair(0, 0)
    private var oldDatePosition: Pair<Int, Int>? = null

    fun onTouchEvent(event: MotionEvent?, setDateListener: onDateClickListener?, block: () -> Unit) {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                datePosition = SettingsPicker.datePicker.setOnClickListener(event.x, event.y)
                SettingsPicker.datePicker.newDate(
                    SettingsPicker.viewRectF
                        .setOnClickListener(event.x, event.y, SettingsPicker.cellSizeW)
                ) { block()}
                if (datePosition != Pair(0, 0)) {
                    if (oldDatePosition != null)
                        SettingsPicker.datePicker[oldDatePosition!!.first][oldDatePosition!!.second].status =
                            State.NOT_SELECT
                    SettingsPicker.datePicker[datePosition.first][datePosition.second].status =
                        State.SELECT
                    setDateListener?.invoke(SettingsPicker.datePicker[datePosition.first][datePosition.second].date)
                    block()
                    oldDatePosition = datePosition
                }
            }
        }
    }
}