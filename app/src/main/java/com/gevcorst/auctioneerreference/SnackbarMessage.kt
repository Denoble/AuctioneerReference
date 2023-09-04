package com.gevcorst.auctioneerreference.model

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.gevcorst.auctioneerreference.R

sealed class SnackbarMessage {
    class StringSnackbarMessage(val msg: String) : SnackbarMessage()
    class ResourceSnackbarMessage(@StringRes val msg: Int) : SnackbarMessage()
   companion object{
       fun SnackbarMessage.showMessage(resources: Resources): String {
           return when (this) {
               is StringSnackbarMessage -> this.msg
               is ResourceSnackbarMessage -> resources.getString(this.msg)
               else -> ""
           }
       }

       fun Throwable.getErrorMessage(): SnackbarMessage {
           val message = this.message.orEmpty()
           return if (message.isBlank()) StringSnackbarMessage(message)
           else ResourceSnackbarMessage(R.string.generic_error)
       }
   }
}