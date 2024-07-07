package com.CepteSaham.CepteSaham

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.CepteSaham.CepteSaham.navigation.Screen
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.size
import androidx.compose.ui.draw.alpha

@Composable
fun AnimatedSplashScreen(navController: NavController){
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(3000)
        navController.popBackStack()
        navController.navigate(Screen.Main.route)
    }
    Splash(alpha = alphaAnim.value)
}



@Composable
fun Splash(alpha: Float) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize(),
        //contentAlignment = Alignment.Center
    ) {
        val boxHeight = constraints.maxHeight.toFloat()

        Image(
            painter = painterResource(id = R.drawable.main_page_photo),
            contentDescription = "Background Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.together),
                contentDescription = "ball",
                modifier = Modifier
                    .offset(y = (boxHeight * 0.1f).dp)
                    .size(250.dp)
                    .alpha(alpha = alpha)
                    .align(Alignment.TopCenter)
                //.offset(y = 0.2f * BoxWithConstraintsScope().maxHeight)
            )
        }
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    Splash(alpha = 1f)
}