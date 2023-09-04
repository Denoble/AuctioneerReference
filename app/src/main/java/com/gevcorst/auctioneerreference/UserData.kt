package com.gevcorst.auctioneerreference.model



data class UserData(
    val email: String, val companyName: String,
    val userName: String, val password: String
)

data class UserDetails(
    val streetNumber: String = "",
    val streetName: String = "",
    val postalCode: String = "",
    val phone: String = "",
    val city: String = "",
    val state: String = "",
    val province: String = "",
    val country: String = ""
)

data class User(val id: String = "", val isLoggedIn: Boolean = false)

