package com.CepteSaham.CepteSaham.model

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.collection.intFloatMapOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@SuppressLint("StaticFieldLeak")
class AuthViewModel():ViewModel(){

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState : LiveData<AuthState> = _authState

    private val _firebaseUser = MutableLiveData<FirebaseUser?>()
    val firebaseUser : LiveData<FirebaseUser?> get() = _firebaseUser

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus(){
        if (auth.currentUser == null){
            _authState.value = AuthState.Unauthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun login(context : Context, email : String, password : String){
        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    if (auth.currentUser?.isEmailVerified == true){
                        _firebaseUser.postValue(auth.currentUser)
                        _authState.value = AuthState.Authenticated
                    }else{
                        Toast.makeText(context, "Please verify your email", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }

            }
    }

    fun signUp(email : String, password : String){
        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{task ->
                if (task.isSuccessful){
                    auth.currentUser?.sendEmailVerification()
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.Error(task.exception?.message?:"Something went wrong")
                }
            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    fun resetAuthState() {
        _authState.value = AuthState.Unauthenticated
    }


}

sealed class AuthState{
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()

    data class Error(val message : String) : AuthState()
}