package org.example

fun main() {
    ConsoleApp.run()
}

fun getInt(min: Int, max: Int, message: String): Int {
    println(message)
    var a = readln()
    var b: Int;
    try {
         b = a.toInt()
    } catch (_: NumberFormatException) {
        println("Please provide a valid integer value")
        return getInt(min, max, message)
    }
    if (min <= b && b <= max) {
        return b;
    }
    println("Please provide a value between $min and $max")
    return getInt(min, max, message)
}

fun getDatetime(): Datetime {
    val day: Int = getInt(6, 31, "Enter day")
    val month: Int = getInt(5, 5, "Enter month")
    val year: Int = getInt(2025, 2025, "Enter year")
    val hour: Int = getInt(0, 23, "Enter hour")
    val minute: Int = getInt(0, 59, "Enter minute")
    return Datetime(day, month, year, hour, minute)
}

fun getDouble(): Double {
    var a: Double
    try {
        a = readln().toDouble()
    } catch (_: NumberFormatException) {
        println("Please provide a valid number")
        return getDouble()
    }
    if (a <= 0) {
        println("Please provide a positive number")
        return getDouble()
    }
    return a
}

fun formatPrice(price: Double): String {
    return String.format("%.2f", price)
}