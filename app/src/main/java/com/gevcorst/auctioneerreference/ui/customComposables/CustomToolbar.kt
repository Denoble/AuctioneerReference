package com.gevcorst.ginventory.ui.composables.custom

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicToolbar(@StringRes title: Int,modifier: Modifier) {
        TopAppBar(title = { Text(stringResource(title)) },
            colors = TopAppBarDefaults.
        centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer))

                CenterAlignedTopAppBar(
                title = { Text(text = "SemicolonSpace") },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Green.copy(alpha = 0.3f)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionToolbar(
    @StringRes title: Int,
    @DrawableRes endActionIcon: Int,
    modifier: Modifier,
    endAction: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(title)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor
        = MaterialTheme.colorScheme.inversePrimary),
        actions = {
            Box(modifier) {
                IconButton(onClick = endAction) {
                    Icon(painter = painterResource(endActionIcon), contentDescription = "Action")
                }
            }
        }
    )
}

@Composable
private fun toolbarColor(darkTheme: Boolean = isSystemInDarkTheme()): Color {
    return if (darkTheme) MaterialTheme.colorScheme.secondary else
        MaterialTheme.colorScheme.onPrimaryContainer
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {

    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(),
        navigationIcon = {
            Icon(
                icon,
                contentDescription = title,
                modifier = Modifier.clickable(onClick = { iconClickAction.invoke() })
            )
        }
    )
}
@Composable
fun ValidationError(msg:String,modifier: Modifier) {
    Text(text = msg, fontSize = 12.sp, fontStyle = FontStyle.Italic, color = Color.Red,
    modifier = Modifier
        .wrapContentWidth()
        .wrapContentHeight())
}
