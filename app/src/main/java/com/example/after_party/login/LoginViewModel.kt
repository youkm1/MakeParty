package com.example.after_party.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.after_party.repository.AuthRepository
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.IllegalArgumentException

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository(),
):ViewModel() {

    val currentUser = repository.currentUser

    val hasUser:Boolean
        get()=repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
        private set

    fun onUserNameChange(userName:String){
        loginUiState = loginUiState.copy(userName = userName)
    }
    fun onPasswordNameChange(password:String){
        loginUiState = loginUiState.copy(password=password)
    }
    fun onUserNameChangeSignup(userName:String){
        loginUiState = loginUiState.copy(userNameSignUp = userName)
    }
    fun onPasswordChangeSignup(password:String){
        loginUiState = loginUiState.copy(passwordSignUp =password)
    }
    fun onConfirmPasswordChange(password:String){
        loginUiState = loginUiState.copy(confirmedPasswordSignUp = password)
    }

    private fun validateLoginForm() =
        loginUiState.userName.isNotBlank() &&
                loginUiState.password.isNotBlank()
    private fun validateSignupForm()=
        loginUiState.userNameSignUp.isNotBlank() &&
                loginUiState.passwordSignUp.isNotBlank() &&
                loginUiState.confirmedPasswordSignUp.isNotBlank()



    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignupForm()){
                throw IllegalArgumentException("이메일과 비밀번호를 입력해주세요.")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            if (loginUiState.passwordSignUp!=
                loginUiState.confirmedPasswordSignUp) {
                throw IllegalArgumentException("비밀번호가 맞지 않습니다.")
            }
            loginUiState = loginUiState.copy(signUpError = null )
            repository.createUser(
                loginUiState.userNameSignUp,
                loginUiState.passwordSignUp
            ) {isSuccessful ->
                if(isSuccessful) {
                    Toast.makeText(context,"success Login",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(context,"Login Failed",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }

        } catch (e:Exception){
            loginUiState = loginUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)

        }
    }

    fun loginUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateLoginForm()){
                throw IllegalArgumentException("이메일과 비밀번호를 입력해주세요.")
            }
            loginUiState = loginUiState.copy(isLoading = true)
            loginUiState = loginUiState.copy(loginError = null )
            repository.logIn(
                loginUiState.userName,
                loginUiState.password
            ) {isSuccessful ->
                if(isSuccessful) {
                    Toast.makeText(context,"success Login",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                } else {
                    Toast.makeText(context,"Login Failed",Toast.LENGTH_SHORT).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }

        } catch (e:Exception){
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        } finally {
            loginUiState = loginUiState.copy(isLoading = false)

        }
    }
}

data class LoginUiState(
    val userName:String = "",
    val password:String = "",
    val userNameSignUp:String = "",
    val passwordSignUp:String = "",
    val confirmedPasswordSignUp:String = "",
    val isLoading:Boolean = false,
    val isSuccessLogin:Boolean = false,
    val signUpError:String? = null,
    val loginError:String? = null,


)