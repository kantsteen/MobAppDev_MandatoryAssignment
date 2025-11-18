package com.example.mobappdev_mandatoryassignment.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch


class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    var user: FirebaseUser? by mutableStateOf(auth.currentUser)
    var isLoggedIn by mutableStateOf(auth.currentUser != null)
    var message by mutableStateOf("")

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser
                    isLoggedIn = true
                    message = ""
                } else {
                    user = null
                    isLoggedIn = false
                    message = task.exception?.message ?: "Unknown error"
                }
            }
    }

    fun signOut() {
        user = null
        auth.signOut()
        isLoggedIn = false
    }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user = auth.currentUser
                        isLoggedIn = true
                        message = ""
                    } else {
                        user = null
                        isLoggedIn = false
                        message = task.exception?.message ?: "Unknown error"
                    }
                }
        }
    }
}






