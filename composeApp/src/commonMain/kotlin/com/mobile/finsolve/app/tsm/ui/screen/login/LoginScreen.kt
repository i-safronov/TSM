package com.mobile.finsolve.app.tsm.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.mobile.finsolve.app.tsm.ui.components.button.TButton
import com.mobile.finsolve.app.tsm.ui.components.input_field.TTextField
import com.mobile.finsolve.app.tsm.ui.theme.TsmColor
import com.mobile.finsolve.app.tsm.ui.theme.TsmFont
import com.mobile.finsolve.app.tsm.ui.theme.tsmAmbientBackground
import com.mobile.finsolve.app.tsm.ui.theme.tsmGradientBackground
import org.jetbrains.compose.resources.stringResource
import tsm.composeapp.generated.resources.Res
import tsm.composeapp.generated.resources.app_name
import tsm.composeapp.generated.resources.enter_your_name
import tsm.composeapp.generated.resources.star_workt
import tsm.composeapp.generated.resources.tsm_description
import tsm.composeapp.generated.resources.your_name
import tsm.composeapp.generated.resources.your_name_description

class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val model = rememberScreenModel { LoginModel() }
        val state by model.state.collectAsState()
        var userName by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            model.checkAuth()
        }

        LoginContent(
            modifier = Modifier
                .statusBarsPadding(),
            state = state,
            userName = userName,
            onUserNameChange = { userName = it },
            onLoginClick = { model.login(userName) },
        )
    }
}

@Composable
private fun LoginContent(
    modifier: Modifier = Modifier,
    state: LoginState,
    userName: String,
    onUserNameChange: (String) -> Unit,
    onLoginClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .tsmGradientBackground(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.isLoggedIn == true -> {
                // TODO: пользователь авторизован — навигация на главный экран
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Column {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(Res.string.app_name),
                                fontSize = 30.sp,
                                fontFamily = TsmFont.Syne,
                                fontWeight = FontWeight.Bold,
                                color = TsmColor.TextPrimary
                            )

                            Text(
                                text = stringResource(Res.string.tsm_description),
                                fontSize = 14.sp,
                                color = TsmColor.TextTertiary
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(Res.string.your_name),
                                fontSize = 30.sp,
                                fontFamily = TsmFont.Syne,
                                fontWeight = FontWeight.Bold,
                                color = TsmColor.TextPrimary
                            )

                            Text(
                                text = stringResource(Res.string.your_name_description),
                                fontSize = 14.sp,
                                color = TsmColor.TextTertiary
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 60.dp),
                            verticalArrangement = Arrangement.spacedBy(40.dp)
                        ) {
                            TTextField(
                                modifier = Modifier.fillMaxWidth(),
                                label = stringResource(Res.string.enter_your_name),
                                value = userName,
                                onValueChange = onUserNameChange
                            )

                            TButton(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = stringResource(Res.string.star_workt),
                                onClick = onLoginClick,
                                isLoading = state.loggingInProgress
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun LoginScreenLoadingPreview() {
    LoginContent(
        modifier = Modifier
            .statusBarsPadding(),
        state = LoginState(isLoading = true),
        userName = "",
        onUserNameChange = {},
        onLoginClick = {},
    )
}

@Preview
@Composable
private fun LoginScreenFormPreview() {
    LoginContent(
        modifier = Modifier
            .statusBarsPadding(),
        state = LoginState(isLoading = false, isLoggedIn = false),
        userName = "Илья",
        onUserNameChange = {},
        onLoginClick = {},
    )
}

@Preview
@Composable
private fun LoginScreenLoggingInPreview() {
    LoginContent(
        modifier = Modifier
            .statusBarsPadding(),
        state = LoginState(isLoading = false, isLoggedIn = false, loggingInProgress = true),
        userName = "Илья",
        onUserNameChange = {},
        onLoginClick = {},
    )
}
