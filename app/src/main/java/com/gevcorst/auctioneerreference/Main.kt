package com.gevcorst.auctioneerreference

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gevcorst.auctioneerreference.ui.customComposables.CustomSnackbar
import com.gevcorst.auctioneerreference.ui.signup.SignUpScreen
import com.gevcorst.auctioneerreference.ui.theme.AuctioneerReferenceTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun Main() {
    AuctioneerReferenceTheme {
        Surface(color = MaterialTheme.colorScheme.secondary) {
            val snackbarHostState = remember { SnackbarHostState() }
            val appState = rememberAppState(snackbarHostState = snackbarHostState)
            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(
                                snackbarData, contentColor =
                                MaterialTheme.colorScheme.surface)
                        }
                    )
                }) { paddingValue ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SignUp.route,
                    modifier = Modifier.padding(paddingValue)
                ) {
                    mainNavgraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState,
    navController: NavHostController = rememberNavController(),
    customSnackbar: CustomSnackbar = CustomSnackbar,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackbarHostState, navController, customSnackbar, resources, coroutineScope) {
    AppScreenState(
        snackbarHostState,
        navController,
        customSnackbar,
        resources,
        coroutineScope
    )
}

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

fun NavGraphBuilder.mainNavgraph(appState: AppScreenState) {
    composable(route = SignUp.route) {
        SignUpScreen(
            navigate = { to, popUp -> appState.navigateAndPopUp(to, popUp) },
            toLogin = { route -> appState.navigate(route) })
    }
}