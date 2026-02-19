package com.mobile.finsolve.app.tsm.ui.screen.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mobile.finsolve.app.tsm.data.store.persisted
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginModel : ScreenModel {

    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private var persistedUserName by persisted(default = "")

    fun checkAuth() = screenModelScope.launch(Dispatchers.IO) {
        _state.value = LoginState(isLoading = true)
        delay(1000L)
        val loggedIn = persistedUserName.isNotEmpty()
        _state.value = LoginState(isLoading = false, isLoggedIn = loggedIn)
    }

    fun login(
        name: String
    ) = screenModelScope.launch(Dispatchers.IO) {
        _state.value = state.value.copy(loggingInProgress = true)
        delay(3000L)
        persistedUserName = name
        _state.value = state.value.copy(loggingInProgress = false, isLoggedIn = true)
    }
}

data class LoginState(
    val isLoading: Boolean = true,
    val isLoggedIn: Boolean? = null,
    val loggingInProgress: Boolean = false
)
