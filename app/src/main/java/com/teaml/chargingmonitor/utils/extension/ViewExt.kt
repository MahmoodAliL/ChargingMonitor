package com.teaml.chargingmonitor.utils.extension

import androidx.recyclerview.widget.RecyclerView

inline operator fun RecyclerView.invoke(function: RecyclerView.() -> Unit) {
    function()
}
