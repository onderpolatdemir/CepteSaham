package com.CepteSaham.CepteSaham.pages

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CepteSaham.CepteSaham.R
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.CepteSaham.CepteSaham.customComposables.PrimaryButton
import com.CepteSaham.CepteSaham.customComposables.SecondaryButton
import com.CepteSaham.CepteSaham.navigation.Screen

@Composable
fun MainContent(
    onBusinessClick: () -> Unit,
    onClick: () -> Unit,
    onGuestClick: () -> Unit
) {

    //val mutableScreenHeight = remember { mutableSetOf(configuration.screenHeightDp.dp) }

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    //val screenWidth = configuration.screenWidthDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gradient_login_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = screenHeight * 0.25f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ball),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "CepteSaham'a ",
                    fontFamily =  MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.primaryColor)
                )
                Text(
                    text = "Hoşgeldiniz!",
                    fontFamily =  MaterialTheme.typography.bodyLarge.fontFamily,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height( screenHeight * 0.01f ))
            //0.01f = 8.dp

            Text(
                text = "Devam etmek için bir giriş yöntemi seçiniz",
                fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            PrimaryButton(
                text = "İşletme Girişi",
                modifier = Modifier
            ) {onBusinessClick()}

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            PrimaryButton(
                text = "Kullanıcı Girişi",
                modifier = Modifier
            ) { onClick()}

            Spacer(modifier = Modifier.height(screenHeight * 0.02f))

            SecondaryButton(
                text = "Misafir Girişi",
                modifier = Modifier
            ) {onGuestClick()}
        }
    }
}

/*@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun MainContentPreview() {
    MainContent()
}*/