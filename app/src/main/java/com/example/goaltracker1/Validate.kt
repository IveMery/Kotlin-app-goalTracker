package com.example.goaltracker1

import java.util.regex.Pattern

class Validate {
    fun validateEmail(email: String): Boolean {
        val emailPattern = Regex("^\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,7}\\b")
        return emailPattern.matches(email)
    }

    fun validatePassword(password: String): Boolean {
        val cleanPassword = password.trim()
        if (password.isNullOrBlank()) {
            return false
        }
        val longitudMinima = 5
        val longitudMaxima = 20
        return cleanPassword.length in longitudMinima..longitudMaxima
    }

    //validar campo nulo
    fun isTextNotEmpty(til: String?): Boolean {
        return !til.isNullOrBlank()
    }

    fun validateName(name:String):Boolean{
        val pattern = Pattern.compile("^[a-zA-Z]+\$")
        return !pattern.matcher(name).matches()
    }

    fun areFieldsEqual(field1: String, field2: String): Boolean {
        return field1.trim().equals(field2.trim())
    }
}