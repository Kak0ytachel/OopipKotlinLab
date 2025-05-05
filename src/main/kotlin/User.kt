package org.example

class User(override var login: String, password: String, override var name: String) : Account {
    val cart: MutableMap<Pizza, Int> = mutableMapOf()
    val orders: MutableList<Order> = mutableListOf()
    override var hashedPassword: String = ConsoleApp.hashPassword(password)


    override fun main() {
        println("Logged in as user ${this.name}")
        while (true){
            val a: Int = getInt(1, 4, "Menu:\n1. Browse pizzas\n2. View cart\n3. View your orders\n4. Log out\nChoose option")
            when (a) {
                1 -> {
                    this.browsePizzas()
                }

                2 -> {
                    this.viewCart()
                }

                3-> {
                    this.viewOrders()
                }

                4 -> {
                    println("Logging out...")
                    return
                }
            }
        }
    }

    fun browsePizzas() {
        println("Pizza menu: ")
        val pizzas: MutableList<Pizza> = ConsoleApp.getPizzas()
        for ((i: Int, pizza: Pizza) in pizzas.withIndex()) {
            println("${i+1}. ${pizza.name} - ${formatPrice(pizza.price)}")
            println(pizza.description)
        }
        val index: Int = getInt(0, pizzas.size, "Enter pizza number or 0 to return")
        if (index == 0) {
            return
        }
        val pizza: Pizza = pizzas[index-1]
        while (true) {
            println("Pizza: ${pizza.name}")
            println("Description: ${pizza.description}")
            println("Price: ${formatPrice(pizza.price)}")
            val inCart: Int = this.cart[pizza] ?: 0
            println("In your cart: $inCart")
            println("Options: \n1. Add to cart\n2. Remove from cart\n3. Back to pizzas list\n4. Return to main menu")
            val k = getInt(1, 4, "Choose option")
            when (k) {
                1 -> {
                    val quantity: Int = getInt(1, 100, "Enter quantity of pizzas to add:")
                    this.cart[pizza] = (this.cart[pizza] ?: 0) + quantity
                    println("Successfully added $quantity ${pizza.name} pizzas to your cart")
                }

                2 -> {
                    if (inCart == 0) {
                        println("This pizza is not in your cart")
                    } else {
                        this.cart.remove(pizza)
                    }
                }

                3 -> {
                    return this.browsePizzas()
                }

                4 -> {
                    return
                }
            }
        }
    }

    fun viewCart() {
        println("Your cart: ")
        var i: Int = 0;
        val pizzas: MutableList<Pizza> = mutableListOf()
        var totalPrice: Double = 0.0
        for ((pizza: Pizza, q: Int) in this.cart) {
            println("${++i}. ${pizza.name} (${formatPrice(pizza.price)}) x$q")
            pizzas.add(pizza)
            totalPrice += pizza.price * q
        }
        println("Total price: ${formatPrice(totalPrice)}")

        println("Options: \n1. Edit cart\n2. Clear cart\n3. Make order\n4. Return to main menu")
        val option = getInt(1, 4, "Choose option:")
        when (option) {
            1 -> {
                val index: Int = getInt(1, i, "Enter pizza number to edit:")
                val pizza: Pizza = pizzas[index-1]
                println("Pizza: ${pizza.name}")
                println("Description: ${pizza.description}")
                println("Price: ${formatPrice(pizza.price)}")
                println("Quantity: ${this.cart[pizza]}")
                println("Options: \n1. Edit quantity\n2. Remove from cart\n3. Back to cart\n4. Return to main menu")
                val option2 = getInt(1, 4, "Choose option:")
                when (option2) {
                    1 -> {
                        val newQuantity: Int = getInt(1, 100, "Enter new quantity")
                        this.cart[pizza] = newQuantity
                        println("Successfully changed quantity to $newQuantity")
                        return viewCart()
                    }
                    2 -> {
                        this.cart.remove(pizza)
                        println("Successfully removed pizza ${pizza.name} from cart")
                        return viewCart()
                    }
                    3 -> {
                        return viewCart()
                    }
                    4 -> {
                        return;
                    }
                }
            }
            2 -> {
                this.cart.clear()
                println("Successfully cleared cart")
            }
            3 -> {
                println("Enter delivery address:")
                val address: String = readln()
                println("Enter contact phone number:")
                val phoneNumber: String = readln()
                var couriersAmount = 0
                var dt: Datetime = Datetime(0, 0, 0, 0, 0)
                val couriersAvailable: MutableList<Courier> = mutableListOf()
                while (couriersAmount == 0){
                    println("Enter delivery date and time:")
                    dt = getDatetime()
                    println("Looking for couriers...")
                    for (courier: Courier in ConsoleApp.getCouriers()) {
                        if (!courier.isBusy(dt)) {
                            couriersAvailable.addLast(courier)
                            println("${++couriersAmount}. ${courier.name}")
                        }
                    }
                    if (couriersAmount == 0) {
                        println("Unfortunately all couriers are busy at specified time. Please specify other delivery time")
                    }
                }
                val courierIndex = getInt(1, couriersAmount, "Enter courier number")
                val courier = couriersAvailable[courierIndex-1]
                val order = Order(address, phoneNumber, dt, this.cart.toMap(), courier)
                this.cart.clear()
                courier.orders.addLast(order)
                this.orders.addFirst(order)
                println("Successfully placed order")
                println("Your order will deliver courier ${courier.name} (${courier.phoneNumber})")
                return
            }
            4 -> {
                return
            }
        }
    }
    fun viewOrders() {
        println("Your orders:")
        for ((i: Int, order: Order) in this.orders.withIndex()) {
            println("Order #$i - ${order.status.name}")
            for ((pizza: Pizza, q: Int) in order.pizzas) {
                println("  - Pizza ${pizza.name} (${formatPrice(pizza.price)}) x${q}")
            }
            println("  Total: ${formatPrice(order.totalPrice)}")
            println("  Delivery address: ${order.address}")
            println("  Delivery phone number: ${order.phoneNumber}")
            println("  Courier: ${order.courier.name}")
            println("  Courier phone number: ${order.courier.phoneNumber}")
        }
        return
    }

    override fun equals(other: Any?): Boolean {
        return (other is User && this.hashCode() == other.hashCode())
    }

    override fun toString(): String {
        return "User ${this.name}"
    }

    override fun hashCode(): Int {
        return "User${this.login}|${this.name}|${this.hashedPassword}".hashCode()
    }
}
