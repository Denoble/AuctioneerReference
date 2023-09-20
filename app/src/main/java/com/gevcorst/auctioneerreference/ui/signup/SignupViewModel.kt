package com.gevcorst.auctioneerreference.ui.signup

import androidx.compose.runtime.mutableStateOf
import com.gevcorst.auctioneerreference.Login
import com.gevcorst.auctioneerreference.ParentViewModel
import com.gevcorst.auctioneerreference.SignUp
import com.gevcorst.auctioneerreference.model.services.AccountService
import com.gevcorst.auctioneerreference.model.services.LogService
import com.gevcorst.auctioneerreference.ui.customComposables.CustomSnackbar
import com.gevcorst.auctioneerreference.ui.customComposables.ext.isValidEmail
import com.gevcorst.auctioneerreference.ui.customComposables.ext.isValidPassword
import com.gevcorst.auctioneerreference.ui.customComposables.ext.passwordMatches
import dagger.hilt.android.lifecycle.HiltViewModel
import com.gevcorst.auctioneerreference.R.string as AppText
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : ParentViewModel(logService){
    var uiState = mutableStateOf(SignUpUiState())
        private set

    private val email
        get() = uiState.value.email
    private val password
        get() = uiState.value.password

    fun onEmailChange(newValue: String) {
        uiState.value = uiState.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(password = newValue)
    }

    fun onRepeatPasswordChange(newValue: String) {
        uiState.value = uiState.value.copy(repeatPassword = newValue)
    }

    fun onNameChange(newName: String) {
        uiState.value = uiState.value.copy(name = newName)
    }

    fun onHaveAccountClick(open: (String) -> Unit) {
        launchCatching {
            open(Login.route)
        }
    }

    fun onSignUpClick(openAndPopUp: (String, String) -> Unit) {
        if (!email.isValidEmail()) {
            CustomSnackbar.showMessage(AppText.email_error)
            return
        }

        if (!password.isValidPassword()) {
            CustomSnackbar.showMessage(AppText.password_error)
            return
        }

        if (!password.passwordMatches(uiState.value.repeatPassword)) {
            CustomSnackbar.showMessage(AppText.password_match_error)
            return
        }

        launchCatching {
            accountService.createAccount (email, password)
            openAndPopUp(Login.route, SignUp.route)
        }
    }
}