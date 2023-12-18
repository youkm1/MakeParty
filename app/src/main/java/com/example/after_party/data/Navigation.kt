
package com.example.after_party.data

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.after_party.LogInScreen
import com.example.after_party.MainScreen
import com.example.after_party.SignUpScreen

import com.example.after_party.login.LoginViewModel
import com.example.after_party.myPage


enum class LoginRoutes {
    Signup,
    SignIn
}

enum class HomeRoutes {
    Home,
    Detail
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginViewModel
) {
    NavHost(navController = navController, startDestination = LoginRoutes.SignIn.name) {
        composable(route = LoginRoutes.SignIn.name) {
            LogInScreen(onNavToMyPage = {
                navController.navigate(HomeRoutes.Home.name) {
                    launchSingleTop = true //WTF?
                    popUpTo(route = LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            },
                loginViewModel = loginViewModel
                ) {
                navController.navigate(LoginRoutes.Signup.name) {
                    launchSingleTop = true
                    popUpTo(LoginRoutes.SignIn.name) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = LoginRoutes.Signup.name) {
            SignUpScreen(onNavToMyPage = {
                navController.navigate(HomeRoutes.Home.name) {
                    popUpTo(LoginRoutes.Signup.name) {
                        inclusive = true
                    }
                }
            },
                loginViewModel = loginViewModel
                ) {
                navController.navigate(LoginRoutes.SignIn.name){

                }

            }
        }
        composable(route = HomeRoutes.Home.name){
            myPage()
        }
    }
}
