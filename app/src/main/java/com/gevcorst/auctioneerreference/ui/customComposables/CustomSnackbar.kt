package com.gevcorst.auctioneerreference.ui.customComposables

import androidx.annotation.StringRes
import com.gevcorst.auctioneerreference.model.SnackbarMessage
import kotlinx.coroutines.flow.MutableStateFlow

object CustomSnackbar{
    val msg = SnackbarMessage.StringSnackbarMessage("")
    private val messages: MutableStateFlow<SnackbarMessage> = MutableStateFlow(msg)
    val snackbarMessages: MutableStateFlow<SnackbarMessage> get() = messages
    fun showMessage(@StringRes message: Int) {
        messages.value = SnackbarMessage.ResourceSnackbarMessage(message)
    }
    fun showMessage(message: SnackbarMessage) {
        messages.value = message
    }

}