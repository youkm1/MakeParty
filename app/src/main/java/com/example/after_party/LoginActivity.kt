package com.example.after_party


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import com.example.after_party.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseApp

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

lateinit var binding: ActivityLoginBinding
lateinit var auth: FirebaseAuth

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loginbtn.setOnClickListener {
            //login()
        }
    }

    /*fun login() {
        //ID value exists
        val email = binding.accountName.text.toString()
        val password = binding.accountPassword.text.toString()
        val user = auth.currentUser
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (user != null) {
                        finish()
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
                //user data X
                else {
                    if (user == null) {
                        //No ID value
                        AlertDialog.Builder(this)
                            .setMessage("Going to Join Page")
                            .setPositiveButton("ok", object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {
                                    startActivity(
                                        Intent(
                                            this@LoginActivity,
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
    }*/

}
