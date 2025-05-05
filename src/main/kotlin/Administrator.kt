package org.example

class Administrator(override var login: String, password: String, override var name: String) : Account {
    override var hashedPassword: String = ConsoleApp.hashPassword(password)

    override fun main() {
        println("Logged in as administrator ${this.name}")
        while (true){
            val a: Int = getInt(1, 3, "Menu:\n1. Add pizzas\n2. Add couriers\n3. Log out\nChoose option")
            when (a) {
                1 -> {
                    managePizzas()
                }

                2 -> {
                    manageCouriers()
                }

                3 -> {
                    println("Logging out...")
                    return
                }
            }
        }
    }

    fun managePizzas() {
        println("Pizzas:")
        for ((i: Int, pizza: Pizza) in ConsoleApp.getPizzas().withIndex()) {
            println("${i+1}. ${pizza.name} - ${formatPrice(pizza.price)}")
            println(pizza.description)
        }
        println("Options: \n1. Add new pizza\n2. Return to main menu")
        val option: Int = getInt(1, 2, "Choose option")
        when (option) {
            1 -> {
                println("Enter pizza name:")
                val name: String = readln()
                println("Enter pizza description:")
                val description: String = readln()
                println("Enter pizza price:")
                val price: Double = getDouble()
                val pizza: Pizza = Pizza(name, description, price)
                ConsoleApp.addPizza(pizza)
                println("Successfully added pizza $name")
                return managePizzas()
            }
            2 -> {
                return
            }
        }

    }

    fun manageCouriers() {
        println("Couriers:")
        for ((i: Int, courier: Courier) in ConsoleApp.getCouriers().withIndex()) {
            println("${i+1}. ${courier.name} (${courier.phoneNumber})")
        }
        println("Options: \n1. Add new courier\n2. Return to main menu")
        val option: Int = getInt(1, 2, "Choose option")
        when (option) {
            1 -> {
                println("Enter courier name:")
                val name: String = readln()
                println("Enter courier phone number:")
                val phoneNumber: String = readln()
                val courier: Courier = Courier(name, phoneNumber)
                ConsoleApp.addCourier(courier)
                println("Successfully added courier $name")
                return manageCouriers()
            }
            2 -> {
                return
            }
        }

    }

    override fun equals(other: Any?): Boolean {
        return (other is Administrator && this.hashCode() == other.hashCode())
    }

    override fun toString(): String {
        return "Administrator ${this.name}"
    }

    override fun hashCode(): Int {
        return "Administrator${this.login}|${this.name}|${this.hashedPassword}".hashCode()
    }
}