package com.CepteSaham.CepteSaham.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CepteSaham.CepteSaham.R
import com.CepteSaham.CepteSaham.customComposables.CustomTextField

@Composable
fun HomeScreen(){

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            //.padding(top = screenHeight * 0.05f)
    ) {

        ProfileSection()
        Spacer(modifier = Modifier.height(16.dp))

//        CustomTextField(
//            value = search,
//            onValueChange = {search = it},
//            placeholder = "Halısaha Ara",
//            modifier = Modifier.width(screenWidth * 0.8f).padding(horizontal = 8.dp),
//            keyboardOptions = KeyboardOptions(
//                keyboardType = KeyboardType.Text,
//                //imeAction = ImeAction.Next
//            )
//        )
        SearchTextField()
        Spacer(modifier = Modifier.height(16.dp))
        //ImageSlider()

    }
}

@Composable
fun ProfileSection(){
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.padding(8.dp)
                .background(colorResource(id = R.color.primarySmooth))
                .padding(
                    top = screenHeight * 0.06f,
                    start = screenWidth * 0.07f,
                    bottom = 16.dp,
                    end = screenWidth * 0.05f
                ),
            verticalAlignment = Alignment.CenterVertically

        ) {

            Image(
                painter = painterResource(id = R.drawable.onder_profile_picture),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(65.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Merhaba Hoşgeldin", fontWeight = FontWeight.Normal)
                Text(text = "Önder Polatdemir", fontWeight = FontWeight.Black, fontSize = 20.sp)
            }

            Icon(imageVector = Icons.Default.Notifications, contentDescription ="Notification Item" )
        }
}

@Composable
fun SearchTextField() {
    var search by remember { mutableStateOf("") }

    TextField(
        value = search,
        onValueChange = {search = it},
        placeholder = { Text(text = "Halısaha ara") },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = R.color.TextFieldBackground).copy(alpha = 0.5f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}


@Preview(
    showBackground = true,
    showSystemUi = true
)@Composable
fun HomeScreenPreview() {
    HomeScreen()
}