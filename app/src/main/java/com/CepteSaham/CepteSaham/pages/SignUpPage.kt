package com.CepteSaham.CepteSaham.pages

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.CepteSaham.CepteSaham.R
import com.CepteSaham.CepteSaham.customComposables.CustomTextField
import com.CepteSaham.CepteSaham.customComposables.PrimaryButton
import com.CepteSaham.CepteSaham.customComposables.SimpleTopBar
import com.CepteSaham.CepteSaham.model.AuthState
import com.CepteSaham.CepteSaham.model.AuthViewModel
import com.CepteSaham.CepteSaham.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpPage(
    navController: NavController,
    authViewModel: AuthViewModel
){
    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        authViewModel.resetAuthState()
    }

    LaunchedEffect(authState.value) {
        when (authState.value){
            is AuthState.Authenticated -> navController.navigate(Screen.Login.route)
            is AuthState.Error -> Toast.makeText(context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_LONG).show()
            else -> Unit
        }
    }


    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Aramıza ",
                title2 = "Katıl",
                onBackClick = { navController.popBackStack() }
            )
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                Image(
                    painter = painterResource(id = R.drawable.gradient_login_image),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = screenHeight * 0.25f)
                        .padding(horizontal = screenWidth * 0.1f)
                        .shadow(1.dp, shape = RoundedCornerShape(8.dp))
                        .background(colorResource(id = R.color.primarySmooth))
                ){

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ball),
                            contentDescription = "ball",
                            modifier = Modifier.size(35.dp)
                        )

                        Text(
                            text = " Kayıt Ol ",
                            fontFamily =  MaterialTheme.typography.bodyLarge.fontFamily,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                        )

                        Image(
                            painter = painterResource(id = R.drawable.ball),
                            contentDescription = "ball",
                            modifier = Modifier.size(35.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
                        ) {
                            CustomTextField(
                                value = name,
                                onValueChange = { name = it },
                                placeholder = "İsim",
                                modifier = Modifier.width((screenWidth * 0.8f) /2),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                )
                            )
                            Spacer(modifier = Modifier.width(screenHeight * 0.01f))

                            CustomTextField(
                                value = surname,
                                onValueChange = { surname = it },
                                placeholder = "Soyisim",
                                modifier = Modifier.width((screenWidth * 0.8f) /2),
                                //visualTransformation = PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Text,
                                    imeAction = ImeAction.Next
                                )
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                    CustomTextField(
                        value = email,
                        onValueChange = {email = it},
                        placeholder = "Email",
                        modifier = Modifier.width(screenWidth * 0.8f).padding(horizontal = 8.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                    CustomTextField(
                        value = password,
                        onValueChange = {password = it},
                        placeholder = "Şifre",
                        modifier = Modifier.width(screenWidth * 0.8f).padding(horizontal = 8.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        )
                    )
                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                    CustomTextField(
                        value = confirmPassword,
                        onValueChange = {confirmPassword = it},
                        placeholder = "Şifreni Onayla",
                        modifier = Modifier.width(screenWidth * 0.8f).padding(horizontal = 8.dp),
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        )
                    )

                    Spacer(modifier = Modifier.height(screenHeight * 0.03f))

                    PrimaryButton(text = "Kayıt Ol") {
                        if (password == confirmPassword && email.isNotEmpty() && password.isNotEmpty()) {
                            authViewModel.signUp(email, password)
                            Toast.makeText(context , "verification has been sent to email" , Toast.LENGTH_SHORT).show()
                            errorMessage = null
                        } else {
                            errorMessage = "Passwords do not match"
                        }
                    }

                    Spacer(modifier = Modifier.height(screenHeight * 0.02f))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) {
                        Text("Hesabın var mı?")
                        TextButton(
                            onClick = {
                                navController.navigateUp()
                                navController.navigate(Screen.Login.route)
                            })
                        {
                            Text(
                                "Giriş Yap",
                                color = colorResource(id = R.color.primaryColor),
                            )
                        }
                    }
                }

            }
        }
    )
}

//@Preview(
//    showBackground = true,
//    showSystemUi = true
//)
//@Composable
//fun SignUpPagePreview() {
//    SignUpPage()
//}