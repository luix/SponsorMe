package com.xinay.sponsorme.data

import io.ktor.util.date.GMTDate


data class Profile(
    val name: String,
    val age: Int,
    val dataOfBirth: GMTDate
)