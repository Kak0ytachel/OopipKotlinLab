package org.example

import kotlin.math.abs

data class Datetime(val day: Int, val month: Int, val year: Int, val hour: Int, val minute: Int) {

    fun isBusy(list: MutableList<Datetime>): Boolean {
        for (dt in list) {
            if (this.year == dt.year && this.month == dt.month && this.day == dt.day) {
                val dtMinutes: Int = dt.hour * 60 + dt.minute
                val thisMinutes: Int = this.hour * 60 + dt.minute
                if (abs(dtMinutes - thisMinutes) < 60) {
                    return true
                }
            }
        }
        return false
    }

    override fun toString(): String {
        return "$day-$month-$year $hour:$minute"
    }

    override fun equals(other: Any?): Boolean {
        return (other is Datetime && other.hashCode() == this.hashCode())
    }

    override fun hashCode(): Int {
        return this.toString().hashCode()
    }
}