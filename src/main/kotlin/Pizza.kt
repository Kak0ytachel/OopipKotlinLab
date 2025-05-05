package org.example

import kotlin.math.ceil
import kotlin.math.roundToInt

data class Pizza(var name: String, var description: String, var price: Double) {

    override fun toString(): String {
        return "Pizza ${this.name}"
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Pizza) {
            return false
        }
        return (this.hashCode() == other.hashCode())
    }

    override fun hashCode(): Int {
        val s: String = this.name + "|" + this.description + "|" + ceil(this.price * 100).roundToInt().toString()
        return s.hashCode()
    }
}
