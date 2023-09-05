package com.gevcorst.auctioneerreference

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.NavHostController
import com.gevcorst.auctioneerreference.model.SnackbarMessage.Companion.showMessage
import com.gevcorst.auctioneerreference.ui.customComposables.CustomSnackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class AppScreenState(
    val snackbarHostState: SnackbarHostState,
    val navController: NavHostController,
    private val customSnackbar: CustomSnackbar,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            customSnackbar.snackbarMessages.filterNotNull().collect { snackbarMessage ->
                val text = snackbarMessage.showMessage (resources)
                snackbarHostState.showSnackbar(text)
            }
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}
