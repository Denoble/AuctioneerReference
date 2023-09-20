package com.gevcorst.auctioneerreference

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.ui.graphics.vector.ImageVector


interface NavDestinations {
    val icon: ImageVector
    val route: String
}
object Login :NavDestinations{
    override val icon: ImageVector = Icons.Filled.AccountCircle
    override val route:String =  "Login"
}
object SignUp:NavDestinations{
    override val icon: ImageVector = Icons.Default.AccountBox
    override val route = "Sign up"
}
object Splash:NavDestinations{
    override val icon: ImageVector = Icons.Default.AccountBox
    override val route = "Splash"
}