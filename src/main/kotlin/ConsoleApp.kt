package org.example

object ConsoleApp {
    val users: MutableList<Account> = mutableListOf<Account>()
    private val couriers: MutableList<Courier> = mutableListOf<Courier>()
    private val pizzas: MutableList<Pizza> = mutableListOf<Pizza>()

    init {
        couriers.addLast(Courier("Mike", "+48980423809342"))
        couriers.addLast(Courier("Jake", "+43509834059865"))
        couriers.addLast(Courier("Nick", "+32490235585320"))
        users.addLast(Administrator("admin", "admin", "Admin"))
        users.addLast(User("user", "user", "User"))
        pizzas.addLast(Pizza("Pepperoni", "You know it - all-beloved pepperoni pizza", 10.99))
        pizzas.addLast(Pizza("Hawaiian", "Chicken and pineapples - what could go wrong?", 12.99))
        pizzas.addLast(Pizza("Four Cheese", "Extra cheeeesy for cheeese lovers", 13.80))
    }

    fun getPizzas(): MutableList<Pizza> {
        return this.pizzas
    }

    fun addPizza(pizza: Pizza) {
        this.pizzas.addLast(pizza)
    }

    fun getCouriers(): MutableList<Courier> {
        return this.couriers
    }

    fun addCourier(courier: Courier) {
        this.couriers.addLast(courier)
    }

    fun run() {
        while (true){
            println("Enter login: ")
            var login = readln()
            if (login == "exit") {
                println("Closing app...")
                return
            }
            println("Enter password: ")
            var password = this.hashPassword(readln())

            for (user in this.users) {
                if (user.checkCredentials(login, password)) {
                    user.main()
                    break
                }
            }
        }
    }

    fun hashPassword(password: String): String {
        return (password.hashCode().toString() + password).hashCode().toString()
    }
}