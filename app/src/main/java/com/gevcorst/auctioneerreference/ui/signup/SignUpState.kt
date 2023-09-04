package com.gevcorst.auctioneerreference.ui.signup


data class SignUpUiState(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val name: String = "",
)
data class ContactUiState(val streetNumber: String = "",
                          val streetName: String = "",
                          val postalCode: String = "",
                          val phone: String = "",
                          val city: String = "",
                          val state: String = "",
                          val province: String = "",
                          val country: String = "")