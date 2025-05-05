package org.example

data class Courier(var name: String, var phoneNumber: String) {
    val orders: MutableList<Order> = mutableListOf()

    fun isBusy(dt: Datetime): Boolean {
        val times: MutableList<Datetime> = mutableListOf()
        this.orders.forEach {
            times.addLast(it.datetime)
        }
        return dt.isBusy(times)
    }

    override fun toString(): String {
        return "Courier ${this.name} (${this.phoneNumber})"
    }

    override fun equals(other: Any?): Boolean {
        return (other is Courier && this.hashCode() == other.hashCode())
    }

    override fun hashCode(): Int {
        return this.toString().hashCode()
    }
}
