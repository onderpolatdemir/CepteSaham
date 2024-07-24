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
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@SuppressLint("StaticFieldLeak")
class AuthViewModel():ViewModel(){

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

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

    fun resetPassword(context: Context, email: String) {
        if (email.isEmpty()){
            Toast.makeText(context, "Lütfen email adresinizi giriniz.", Toast.LENGTH_SHORT).show()
            _authState.value = AuthState.Error("Lütfen email adresinizi giriniz.")
            return
        }
        _authState.value = AuthState.Loading


        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                    Toast.makeText(context, "Email adresinize şifre sıfırlama maili gönderildi.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Şifre sıfırlama maili gönderilemedi.", Toast.LENGTH_SHORT).show()
                    _authState.value = AuthState.Error(task.exception?.message ?: "Şifre sıfırlama maili gönderilemedi.")
                }
            }
    }

//    fun resetPassword(context: Context, email : String){
//        if (email.isEmpty()){
//            _authState.value = AuthState.Error("Lütfen email adresinizi giriniz.")
//            return
//        }
//        _authState.value = AuthState.Loading
//
//        auth.sendPasswordResetEmail(email)
//            .addOnCompleteListener{ task ->
//                if(task.isSuccessful){
//                    Toast.makeText(context, "Email adresinize şifre sıfırlama maili gönderildi.", Toast.LENGTH_SHORT).show()
//                    _authState.value = AuthState.Authenticated
//                }else{
//                    //Toast.makeText(context, "Lütfen geçerli bir email adresi giriniz.", Toast.LENGTH_SHORT).show()
//                    _authState.value = AuthState.Error(task.exception?.message?:"Lütfen geçerli bir email adresi giriniz.")
//                }
//            }
//    }

//    fun resetPassword(context: Context, email: String) {
//        if (email.isEmpty()) {
//            Toast.makeText(context, "Lütfen email adresinizi giriniz.", Toast.LENGTH_SHORT).show()
//            _authState.value = AuthState.Error("Lütfen email adresinizi giriniz.")
//            return
//        }
//        _authState.value = AuthState.Loading
//
//        viewModelScope.launch {
//            try {
//                // Check if the user exists in Firestore
//                val userSnapshot = firestore.collection("users").whereEqualTo("email", email).get().await()
//
//                if (!userSnapshot.isEmpty) {
//                    // User exists, send password reset email
//                    auth.sendPasswordResetEmail(email)
//                        .addOnCompleteListener { task ->
//                            if (task.isSuccessful) {
//                                Toast.makeText(context, "Email adresinizi kontrol ediniz.", Toast.LENGTH_SHORT).show()
//                                _authState.value = AuthState.Authenticated
//                            } else {
//                                Toast.makeText(context, "Şifre sıfırlama maili gönderilemedi.", Toast.LENGTH_SHORT).show()
//                                _authState.value = AuthState.Error(task.exception?.message ?: "Şifre sıfırlama maili gönderilemedi.")
//                            }
//                        }
//                } else {
//                    // User does not exist
//                    Toast.makeText(context, "Bu email adresi ile kayıtlı kullanıcı bulunamadı.", Toast.LENGTH_SHORT).show()
//                    _authState.value = AuthState.Error("Bu email adresi ile kayıtlı kullanıcı bulunamadı.")
//                }
//            } catch (e: Exception) {
//                Toast.makeText(context, "Bir hata oluştu: ${e.message}", Toast.LENGTH_SHORT).show()
//                _authState.value = AuthState.Error("Bir hata oluştu: ${e.message}")
//            }
//        }
//    }



//    fun checkEmailExist(email : String): Boolean{
//        //var isEmailExist = false
//        auth.fetchSignInMethodsForEmail(email)
//            .addOnCompleteListener{ task ->
//
//                if (task.result?.signInMethods?.isEmpty() == true){
//                    _authState.value = AuthState.Error("Email adresi kayıtlı değil.")
//                }else{
//                    //isEmailExist = true
//                    _authState.value = AuthState.Authenticated
//                }
//
//            }
//        //return isEmailExist
//    }

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