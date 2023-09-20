package com.gevcorst.auctioneerreference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gevcorst.auctioneerreference.model.SnackbarMessage.Companion.getErrorMessage
import com.gevcorst.auctioneerreference.model.services.LogService
import com.gevcorst.auctioneerreference.ui.customComposables.CustomSnackbar
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class ParentViewModel(private val logService: LogService) : ViewModel() {
    fun launchCatching(snackbar: Boolean = true, block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                if (snackbar) {
                    CustomSnackbar.showMessage(throwable.getErrorMessage())
                }
                logService.logNonFatalCrash(throwable)
            },
            block = block
        )
}
