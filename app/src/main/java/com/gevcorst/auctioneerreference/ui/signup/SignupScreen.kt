package com.gevcorst.auctioneerreference.ui.signup

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.gevcorst.auctioneerreference.R
import com.gevcorst.auctioneerreference.model.SnackbarMessage
import com.gevcorst.auctioneerreference.ui.customComposables.CustomButton
import com.gevcorst.auctioneerreference.ui.customComposables.CustomOutlinedTextField
import com.gevcorst.auctioneerreference.ui.customComposables.CustomSnackbar
import com.gevcorst.auctioneerreference.ui.customComposables.CustomText
import com.gevcorst.auctioneerreference.ui.customComposables.CustomTitleText
import com.gevcorst.auctioneerreference.ui.customComposables.ext.fieldModifier
import com.gevcorst.auctioneerreference.ui.theme.MilkyWhite
import com.gevcorst.auctioneerreference.ui.theme.Purple500

@Composable
fun SignUpScreen(
    navigate: (to: String, popup: String) -> Unit,
    toLogin: (route: String) -> Unit,
    modifier: Modifier =Modifier,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState
    val fieldModifier = Modifier.fieldModifier()
    val openSnackBar = remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    val snackbarHostState: SnackbarHostState = remember<SnackbarHostState>{ SnackbarHostState() }
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(3.dp, Purple500),
        colors  = CardDefaults.cardColors(MilkyWhite)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (signUpLabel, companyName, email, password, coPassword, signupBotton,
                alreadyHaveAnAccount) = createRefs()
            CustomTitleText(
                text = stringResource(id = R.string.client_bio),
                modifier = Modifier.constrainAs(signUpLabel) {
                    top.linkTo(parent.top, margin = 24.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold, fontSize = 18.sp,
                )
            )
            CustomOutlinedTextField(
                label = stringResource(id = R.string.your_bio),
                value =uiState.value.name,
                placeHolderText = stringResource(id = R.string.your_bio),
                modifier = Modifier.constrainAs(companyName) {
                    top.linkTo(signUpLabel.bottom, margin = 16.dp)
                    start.linkTo(signUpLabel.start)
                    end.linkTo(signUpLabel.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
                keyboardType = KeyboardType.Text,
                onTextChange = viewModel::onNameChange
            )
            CustomOutlinedTextField(
                label = stringResource(id = R.string.company_email),
                uiState.value.email,
                placeHolderText = stringResource(id = R.string.company_email),
                modifier = Modifier.constrainAs(email) {
                    top.linkTo(companyName.bottom, margin = 16.dp)
                    start.linkTo(companyName.start)
                    end.linkTo(companyName.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
                keyboardType = KeyboardType.Email,
                onTextChange = viewModel::onEmailChange
            )
            CustomOutlinedTextField(
                label = stringResource(id = R.string.password),
                uiState.value.password,
                placeHolderText = stringResource(id = R.string.password),
                modifier = Modifier.constrainAs(password) {
                    top.linkTo(email.bottom, margin = 16.dp)
                    start.linkTo(email.start)
                    end.linkTo(email.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
                onTextChange = viewModel::onPasswordChange
            )
            CustomOutlinedTextField(
                label = stringResource(id = R.string.coPassword),
                uiState.value.repeatPassword,
                placeHolderText = stringResource(id = R.string.coPassword),
                modifier = Modifier.constrainAs(coPassword) {
                    top.linkTo(password.bottom, margin = 16.dp)
                    start.linkTo(password.start)
                    end.linkTo(password.end)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                },
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
                onTextChange = viewModel::onRepeatPasswordChange
            )
            CustomButton(label = stringResource(R.string.SignUp),
                name = stringResource(R.string.SignUp),
                modifier = Modifier.constrainAs(signupBotton) {
                    top.linkTo(coPassword.bottom, margin = 16.dp)
                    start.linkTo(coPassword.start)
                    end.linkTo(coPassword.end)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }, onClickAction = {
                    snackbarMessage = "Process sign in"
                    openSnackBar.value = true
                    viewModel.onSignUpClick(navigate)
                })
            CustomText(text = stringResource(id = R.string.alreadyHaveAccount),
                modifier = Modifier.constrainAs(alreadyHaveAnAccount) {
                    top.linkTo(signupBotton.bottom, margin = 16.dp)
                    start.linkTo(signupBotton.start, margin = 16.dp)
                    end.linkTo(signupBotton.end, margin = 16.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }, onClickAction = {
                    snackbarMessage = "Go to login "
                    CustomSnackbar
                        .showMessage(
                            SnackbarMessage
                                .StringSnackbarMessage(snackbarMessage)
                        )
                    openSnackBar.value = true
                    viewModel.onHaveAccountClick(toLogin)
                })
        }
    }
}
