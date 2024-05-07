package com.example.bonus.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bonus.R
import com.example.bonus.component.MyButton
import com.example.bonus.component.OutlineMyButton
import com.example.bonus.ui.theme.Blue


@Composable
fun LoginScreen() {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisibility = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Login with social networks", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(20.dp))

        Row (modifier = Modifier.fillMaxWidth())
        {
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
            )
            {
                Icon(
                    imageVector = Icons.Default.Facebook,
                    contentDescription =null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp),
                    tint = Blue
                )
                Text(text = "Facebook", color = Color.Black)
            }
            Spacer(modifier = Modifier.width(20.dp))

            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
            )
            {
                Image(
                    painter = painterResource(R.drawable.gg),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .padding(end = 10.dp),
                )
                Text(text = "Google", color = Color.Black, modifier = Modifier.padding(vertical = 10.dp))
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ })
                {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription =null )
                }
            },
            placeholder = { Text(text = "Username")},
            trailingIcon = {
                IconButton(onClick = { username.value = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField (
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text("Password") },
            visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription =null )
                }
            },
            trailingIcon = @androidx.compose.runtime.Composable {
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(
                        imageVector =
                        if (passwordVisibility.value)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = "Hiện/Ẩn mật khẩu"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Forgot password ?",
            color = Color.Gray,
            modifier = Modifier.clickable {
                //code
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        MyButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Login",
            onClick =
            {

            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlineMyButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Register",
            onClick =
            {

            }
        )
    }
}

@Preview
@Composable
fun PreviewLoginScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LoginScreen()
    }
}




