package com.example.after_party

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.after_party.login.LoginViewModel
import com.google.android.play.integrity.internal.t

import com.google.firebase.auth.FirebaseAuth

class LoginComposeActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        auth = FirebaseAuth.getInstance()

        setContent {

            After_PartyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {


                    //LogInScreen(onNavToMyPage = { HomeRoutes.Home.name }) {

                    }
                }
            }
        }


    }


@Composable
fun LogInScreen(
    //loginViewModel: LoginViewModel? = null,
    onNavToMyPage: () -> Unit,
    onNavToSignUpPage: () -> Unit,
) {
    //val loginUiState = loginViewModel?.loginUiState
    //val isError = loginUiState?.loginError != null
    val context = LocalContext.current
    val user = auth.currentUser
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(top = 90.dp)) {
            Text(
                text = "Login To Join",
                fontSize = 45.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Row {
                Text(
                    text = "The Party!",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Icon(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    tint = Color.Unspecified
                )
            }
           /* if (isError) {
                Text(
                    text = loginUiState?.loginError ?: "Unknown Err",
                    color = Color.Red
                )
            }
        }*/
        Spacer(modifier = Modifier.size(50.dp))

        OutlinedTextField(
            //loginUiState?.userName ?: "",
            value = email,

            modifier = Modifier.padding(30.dp),
            //loginViewModel?.onUserNameChange(it)
            onValueChange = { newEmail ->
                email = newEmail
                //loginViewModel?.onUserNameChange(newEmail)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            //placeholder = { Text("Enter your Email") },
            label = {
                Text(text = "email")
            },
            //isError = isError
        )
        OutlinedTextField(
            value = password,
            modifier = Modifier

                .padding(30.dp),
//loginViewModel?.onPasswordNameChange(password)
            onValueChange = { newPassword ->
                password = newPassword
                //loginViewModel?.onPasswordNameChange(newPassword)
            },
            //keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            //placeholder = { Text("Enter your Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            label = {
                Text(text = "password")
            },
            //isError = isError
        )

        Button(onClick = { /*loginViewModel?.loginUser(context)*/
                auth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            if (user != null) {
                                context.startActivity(Intent(context, MainActivity::class.java))
                            }
                        }
                        //user data X
                        else {
                            if (user == null) {
                                //No ID value
                                AlertDialog.Builder(context)
                                    .setMessage("Going to Join Page")
                                    .setPositiveButton("ok", object : DialogInterface.OnClickListener {
                                        override fun onClick(dialog: DialogInterface?, which: Int) {
                                            context.startActivity(
                                                Intent(
                                                    context,
                                                    JoinActivity::class.java
                                                )
                                            )
                                        }
                                    })
                                    .setNegativeButton(
                                        "cancel",
                                        object : DialogInterface.OnClickListener {
                                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                                Log.d("Login", "Join denied")
                                            }
                                        })
                                    .create()
                                    .show()
                            }

                        }
                    }
        }) {
            Text(text = "                           Login                             ")
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Don't have an account?")

            TextButton(onClick = { onNavToSignUpPage.invoke() }) {
                Text(text = "Signup now!")
            }
        }
        /*if (loginUiState?.isLoading == true) {
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = loginViewModel?.hasUser) {
            if (loginViewModel?.hasUser == true) {
                onNavToMyPage.invoke()
            }
        }*/
    }
}

@Composable
fun SignUpScreen(
    loginViewModel: LoginViewModel? = null,
    onNavToMyPage: () -> Unit,
    onNavToLoginPage: () -> Unit,
) {
    val loginUiState = loginViewModel?.loginUiState
    val isError = loginUiState?.signUpError != null
    val context = LocalContext.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(modifier = Modifier.padding(top = 90.dp)) {
            Text(
                text = "Sign Up",
                fontSize = 45.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Row {
                Text(
                    text = "NOW!",
                    fontSize = 45.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Icon(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    tint = Color.Unspecified
                )
            }
            if (isError) {
                Text(
                    text = loginUiState?.signUpError ?: "Unknown Err",
                    color = Color.Red
                )
            }
        }
        Spacer(modifier = Modifier.size(50.dp))
        OutlinedTextField(
            value = loginUiState?.userNameSignUp ?: "",
            modifier = Modifier.padding(30.dp),
            onValueChange = { loginViewModel?.onUserNameChangeSignup(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeholder = { Text("Enter your Email") },
            label = {
                Text(text = "email")
            },
            isError = isError
        )
        OutlinedTextField(
            value = loginUiState?.passwordSignUp ?: "",
            modifier = Modifier.padding(30.dp),
            onValueChange = { loginViewModel?.onPasswordChangeSignup(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text("Enter your Password") },
            label = {
                Text(text = "password")
            },
            isError = isError
        )
        OutlinedTextField(
            value = loginUiState?.confirmedPasswordSignUp ?: "",
            modifier = Modifier.padding(30.dp),
            onValueChange = { loginViewModel?.onConfirmPasswordChange(it) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeholder = { Text("Enter your Password") },
            label = {
                Text(text = "confirm password")
            },
            isError = isError
        )
        Button(onClick = { loginViewModel?.createUser(context) }) {
            Text(text = "                           Sign in                             ")
        }
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = "Already have an account?")

            TextButton(onClick = { onNavToLoginPage.invoke() }) {
                Text(text = "SignIn now!")
            }
        }
        if (loginUiState?.isLoading == true) {
            CircularProgressIndicator()
        }
        LaunchedEffect(key1 = loginViewModel?.hasUser) {
            if (loginViewModel?.hasUser == true) {
                onNavToMyPage.invoke()
            }
        }
    }
}


@Composable
fun previewlogin() {
    After_PartyTheme {
        LogInScreen(onNavToMyPage = { }, onNavToSignUpPage = { })
    }
}


@Composable
fun previewsingup() {
    After_PartyTheme {
        SignUpScreen(onNavToMyPage = { /*TODO*/ }) {

        }
    }
}}