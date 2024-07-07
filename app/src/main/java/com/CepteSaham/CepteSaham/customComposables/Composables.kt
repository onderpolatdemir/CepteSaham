package com.CepteSaham.CepteSaham.customComposables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.CepteSaham.CepteSaham.R

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick:  () -> Unit,
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.primaryColor)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(56.dp)
            .shadow(7.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp
            )
        )
    }
}


@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.secondaryColor)
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .height(56.dp)
            .shadow(7.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = 16.sp
            )
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    textColor: Color = Color.Black,
    outlineColor: Color = colorResource(id = R.color.secondaryColor),
    focusedOutlineColor: Color = colorResource(id = R.color.primaryColor),
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        textStyle = TextStyle(color = textColor),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = outlineColor,
            focusedBorderColor = focusedOutlineColor
        ),
        modifier = modifier
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .border(
                1.dp,
                colorResource(id = R.color.primaryColor),
                shape = RoundedCornerShape(8.dp)
            )
            .shadow(7.dp, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
    )
}

/*colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = outlineColor,
            focusedBorderColor = focusedOutlineColor
        ),*/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(
    title: String,
    title2: String,
    colorPrimary: Color = colorResource(id = R.color.primaryColor),
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                //Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = title,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = title2,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = colorPrimary,
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Gray.copy(alpha = 0.05f),
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    //painter = painterResource(id = R.drawable.ic_back), // Replace with your back icon
                    contentDescription = "Back",
                    tint = Color.White,
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun TopAppBarPreview(){
    SimpleTopBar(
        title = "Login",
        title2 = "Login",
        onBackClick = {}
    )
}