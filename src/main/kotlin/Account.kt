package org.example

interface Account {
    var login: String
    var hashedPassword: String
    var name: String

    fun checkCredentials(login: String, hashedPassword: String): Boolean {
        return (login == this.login && hashedPassword == this.hashedPassword)
    }

    fun main()
}