package com.mobile.finsolve.app.tsm

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.mobile.finsolve.app.tsm.ui.screen.login.LoginScreen
import com.mobile.finsolve.app.tsm.ui.theme.TsmTypography

@Composable
fun App() {
    MaterialTheme(typography = TsmTypography()) {
        Navigator(LoginScreen())
    }
}