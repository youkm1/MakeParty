package com.example.after_party


import androidx.multidex.MultiDexApplication
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthMultiDex:MultiDexApplication() {
    companion object {
        lateinit var auth:FirebaseAuth
        var email: String?=null
        var name: String?=null
        fun checkAuth():Boolean {
            val currentUser = auth.currentUser
            return currentUser?.let{
                name = currentUser.displayName
                email = currentUser.email
                if(currentUser.isEmailVerified) {
                    true
                } else {
                    false
                }
            }?:let{
                false
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        auth = Firebase.auth
    }
}