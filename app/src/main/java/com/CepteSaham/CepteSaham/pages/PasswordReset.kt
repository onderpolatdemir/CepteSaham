package com.CepteSaham.CepteSaham.pages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.CepteSaham.CepteSaham.MainActivity
import com.CepteSaham.CepteSaham.R
import com.CepteSaham.CepteSaham.customComposables.CustomTextField
import com.CepteSaham.CepteSaham.customComposables.PrimaryButton
import com.CepteSaham.CepteSaham.model.AuthState
import com.CepteSaham.CepteSaham.model.AuthViewModel
import com.CepteSaham.CepteSaham.navigation.Graph
import com.CepteSaham.CepteSaham.navigation.Screen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PasswordReset(navController: NavController, authViewModel: AuthViewModel){

    var email by remember { mutableStateOf("") }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

//    LaunchedEffect(authState.value) {
//        when (authState.value) {
//            is AuthState.Authenticated -> {
//                navController.navigate(Screen.Login.route) {
//                    popUpTo(navController.graph.startDestinationId) { saveState = true }
//                }
//            }
//            is AuthState.Error -> {
//                val message = (authState as AuthState.Error).message
//                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//            }
//            else -> {
//                // Handle other states if necessary
//            }
//        }
//    }


    LaunchedEffect(authState.value) {
        when (authState.value){
            is AuthState.Authenticated ->
                navController.navigate(Screen.Login.route) {
                popUpTo(navController.graph.startDestinationId) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
            is AuthState.Error -> Unit
//                Toast.makeText(context,
//                (authState.value as AuthState.Error).message, Toast.LENGTH_LONG).show()
            else -> Unit
        }
    }


    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.gradient_login_image),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = screenHeight * 0.35f)
                    .padding(horizontal = screenWidth * 0.1f)
                    .shadow(1.dp, shape = RoundedCornerShape(8.dp))
                    .background(colorResource(id = R.color.primarySmooth))
            ) {
                Text(
                    text = "Email",
                    modifier = Modifier.padding(top = 25.dp)
                    )

                Spacer(modifier = Modifier.height(24.dp))

                CustomTextField(
                    value = email,
                    onValueChange = {email = it},
                    placeholder = "Email",
                    modifier = Modifier
                        .width(screenWidth * 0.8f)
                        .padding(horizontal = 8.dp),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 25.dp)
                ) {
                    TextButton(
                        onClick = {
                        navController.navigateUp()
                        navController.navigate(Screen.Login.route)
                    },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        Text(text = "Cancel", color = Color.Black)
                    }
                    PrimaryButton(text = "Reset Password") {
                        authViewModel.resetPassword(context = context, email = email)
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PasswordResetPreview() {
    PasswordReset(navController = rememberNavController(), authViewModel = AuthViewModel())
}