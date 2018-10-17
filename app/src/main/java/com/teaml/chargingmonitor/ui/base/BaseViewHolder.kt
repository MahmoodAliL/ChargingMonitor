package com.teaml.chargingmonitor.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(position: Int)
}