package com.gevcorst.auctioneerreference.ui.customComposables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import com.gevcorst.auctioneerreference .R.string as AppText
import com.gevcorst.auctioneerreference .R.drawable as AppIcons


@Composable
fun BasicField(
    @StringRes text: Int,
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(text)) }
    )
}

@Composable
fun EmailField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    OutlinedTextField(
        singleLine = true,
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(stringResource(com.gevcorst.auctioneerreference .R.string.company_email)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email") }
    )
}

@Composable
fun PasswordField(value: String, onNewValue: (String) -> Unit, modifier: Modifier = Modifier) {
    PasswordField(value, AppText.password, onNewValue, modifier)
}

@Composable
fun RepeatPasswordField(
    value: String,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    PasswordField(value, AppText.repeat_password, onNewValue, modifier)
}

@Composable
private fun PasswordField(
    value: String,
    @StringRes placeholder: Int,
    onNewValue: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    val icon =
        if (isVisible) painterResource(AppIcons.ic_visibility_on)
        else painterResource(AppIcons.ic_visibility_off)

    val visualTransformation =
        if (isVisible) VisualTransformation.None else PasswordVisualTransformation()

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = { onNewValue(it) },
        placeholder = { Text(text = stringResource(placeholder)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock") },
        trailingIcon = {
            IconButton(onClick = { isVisible = !isVisible }) {
                Icon(painter = icon, contentDescription = "Visibility")
            }
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = visualTransformation
    )
}

@Composable
fun CustomTitleText(
    text: String, modifier: Modifier, textAlign: TextAlign = TextAlign.Start,
    style: TextStyle
) {
    Text(
        text = text, modifier = modifier, style = style, textAlign = textAlign
    )
}

@Composable
fun CustomText(
    text: String, modifier: Modifier, onClickAction: () -> Unit,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text, modifier = modifier.clickable {
            onClickAction.invoke()
        }, style = TextStyle(
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold, fontSize = 13.sp,
        ), textAlign = textAlign
    )
}

@Composable
fun CustomOutlinedTextField(
    label: String,
    value:String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeHolderText: String, modifier: Modifier,
    keyboardType: KeyboardType,
    onTextChange:(text:String)->Unit

) {
    OutlinedTextField(
        value = value,
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = { Text(text = placeHolderText) },
        visualTransformation = visualTransformation,
        onValueChange = {
            onTextChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Composable
fun CustomTextField(
    label: String,
    placeHolderText: String,
    modifier: Modifier
) {
    var text by remember { mutableStateOf("") }
    TextField(
        value = text,
        onValueChange = { newValue -> text = newValue },
        modifier = modifier,
        label = { Text(label) },
        placeholder = { Text(placeHolderText) },
    )
}

@Composable
fun CustomButton(
    label: String,
    name: String,
    modifier: Modifier,
    onClickAction: () -> Unit
) {
    Button(
        onClick = {
            //your onclick code here
            onClickAction.invoke()
        }, elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp,
            pressedElevation = 15.dp,
            disabledElevation = 0.dp
        ), modifier = modifier
    ) {
        Text(text = name)
    }
}

@Composable
fun CustomImage(
    url: String,
    contentScale: ContentScale = ContentScale.Fit,
    modifier: Modifier
) {
    val painter =
        rememberAsyncImagePainter(
            ImageRequest.Builder //Used while loading
                (LocalContext.current).data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true) //Crossfade animation between images
                    placeholder(AppIcons.loading_animation) //Used while loading
                    fallback(AppIcons.ic_baseline_broken_image_24) //Used if data is null
                    error(AppIcons.ic_baseline_broken_image_24) //Used when loading returns with error
                }).build()
        )


    Image(
        modifier = modifier,
        //Use painter in Image composable
        painter = painter,
        contentScale = contentScale,
        contentDescription = stringResource(id = AppText.imageloader)
    )
}

@Composable
fun ShowSnackBar(
    message: String, snackbarHostState: SnackbarHostState,
    showSnackBar: Boolean
) {
    //val snackState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var canShowSnackbar by remember { mutableStateOf(showSnackBar) }
    val msg = remember {
        mutableStateOf("")
    }
    msg.value = message
    fun launchSnackbar(
        message: String, actionLabel: String? = null,
        duration: SnackbarDuration = SnackbarDuration.Short
    ) {
        canShowSnackbar = showSnackBar
        msg.value = message
        if (canShowSnackbar) {
            scope.launch {
                snackbarHostState.showSnackbar(
                    message = msg.value,
                    actionLabel = actionLabel,
                    duration = duration
                ).let { snackbarResult ->
                    when (snackbarResult) {
                        SnackbarResult.Dismissed -> {
                            msg.value = "Dismissed !"
                            canShowSnackbar = false
                        }
                        else -> {

                        }
                    }
                }
            }
        }
    }

    Box(contentAlignment = Alignment.BottomEnd) {
        launchSnackbar(
            message = message, actionLabel = "Hide",
            duration = SnackbarDuration.Long
        )
    }
    Box(modifier = Modifier.fillMaxSize(), Alignment.BottomCenter) {
        SnackbarHost(hostState = snackbarHostState)
    }
}
