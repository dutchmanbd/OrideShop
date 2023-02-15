package com.oride.utilities

import java.io.Serializable

sealed class NavToOtpScreen: Serializable {
    object SignIn : NavToOtpScreen()
    object SignUp : NavToOtpScreen()
    object ResetPassword : NavToOtpScreen()
    object UpdateNumber : NavToOtpScreen()
    object UpdatePassword : NavToOtpScreen()
    object TwoFactorPhone : NavToOtpScreen()
    object TwoFactorEmail : NavToOtpScreen()
}
