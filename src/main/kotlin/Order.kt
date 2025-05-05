package org.example

data class Order(val address: String, val phoneNumber: String, val datetime: Datetime, val pizzas: Map<Pizza, Int>,
                 val courier: Courier) {
    var status: OrderStatus = OrderStatus.PLACED
    var totalPrice: Double = 0.0

    init {
        for ((pizza: Pizza, q: Int) in this.pizzas) {
            this.totalPrice += pizza.price * q
        }
    }

    override fun equals(other: Any?): Boolean {
        return (other is Order &&  this.hashCode()  == other.hashCode())
    }

    override fun hashCode(): Int {
        return this.toString().hashCode()
    }

    override fun toString(): String {
        return "Order to ${this.address} at ${this.datetime} by ${this.courier.name}"
    }
}
