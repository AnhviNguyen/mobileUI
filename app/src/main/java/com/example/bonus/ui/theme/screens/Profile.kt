package com.example.bonus.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bonus.component.TopBar
import com.example.bonus.model.User

@Composable
fun Profile (
    user: User
){
    TopBar(onNavigateBack = { /*TODO*/ }, title = "Profile")
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp),
    ){
        item{
            titleProfile("APPREARANCE")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileItem(
                icon = Icons.Outlined.DarkMode,
                title = "Dark Mode",
                trailingIcon = null,
                onClick = { /* handle onClick here */ }
            )
            ProfileItem(
                icon = Icons.Outlined.LightMode,
                title = "Light Mode",
                trailingIcon = null,
                onClick = { /* handle onClick here */ }
            )
            Spacer(modifier = Modifier.height(10.dp))
            titleProfile("PROFILE")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileItem(
                icon = Icons.Outlined.AccountCircle,
                color = Color.Gray,
                title = user.username,
                trailingIcon = Icons.Default.ArrowForwardIos,
                onClick = { /* handle onClick here */ }
            )
            ProfileItem(
                icon = Icons.Outlined.Email,
                color = Color.Gray,
                title = user.email,
                trailingIcon = Icons.Default.ArrowForwardIos,
                onClick = { /* handle onClick here */ }
            )
            ProfileItem(
                icon = Icons.Outlined.Lock,
                title = "Password",
                color = Color.Gray,
                trailingIcon = Icons.Default.ArrowForwardIos,
                onClick = { /* handle onClick here */ }
            )
            ProfileItem(
                icon = Icons.Outlined.Collections,
                title = "Your Playlist",
                trailingIcon = null,
                onClick = { /* handle onClick here */ }
            )
            Spacer(modifier = Modifier.height(10.dp))
            titleProfile("LOGOUT")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileItem(
                icon = Icons.Outlined.Logout,
                title = "Logout",
                trailingIcon = null,
                onClick = { /* handle onClick here */ }
            )

            Spacer(modifier = Modifier.height(10.dp))
            titleProfile("DELETE ACCOUNT")
            Spacer(modifier = Modifier.height(10.dp))
            ProfileItem(
                icon = Icons.Outlined.Logout,
                color = Color.Red,
                title = "Delete Acount",
                trailingIcon = null,
                onClick = { /* handle onClick here */ }
            )




        }

    }

}

@Composable
fun titleProfile(title: String){
    Text(
        text = title,
        fontSize = 18.sp,
        color = Color.Gray,
        modifier = Modifier.padding(start = 16.dp, top = 10.dp, bottom = 5.dp)
    )
}

@Composable
fun ProfileItem(
    icon: ImageVector,
    color: Color = Color.Black,
    title: String,
    trailingIcon: ImageVector?,
    onClick: () -> Unit
) {
   Box(
       modifier = Modifier.fillMaxWidth()
           .background(Color.White)
           .borderTopBottom(Color.LightGray, 1.dp)
   ){
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .clickable(onClick = onClick)
               .padding(20.dp),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       )
       {
           Row(
               verticalAlignment = Alignment.CenterVertically
           ) {
               Icon(
                   imageVector = icon,
                   contentDescription = "Leading Icon",
                   modifier = Modifier.size(24.dp),
                   tint = color
               )

               Text(
                   text = title,
                   style = MaterialTheme.typography.body1,
                   color = color,
                   modifier = Modifier.padding(start = 16.dp)
               )
           }

           if (trailingIcon != null) {
               Icon(
                   imageVector = trailingIcon,
                   contentDescription = "Trailing Icon",
                   modifier = Modifier.size(15.dp),
                   tint = color
               )
           }
       }
   }
}

fun Modifier.borderTopBottom(
    color: Color,
    borderSize: Dp
): Modifier = this.drawWithContent {
    drawContent()
    drawRect(color, size = Size(size.width, borderSize.toPx()), topLeft = Offset.Zero)
    drawRect(color, size = Size(size.width, borderSize.toPx()), topLeft = Offset(0f, size.height - borderSize.toPx()))
}

@Preview(showBackground = true)
@Composable
fun PreviewProfileItem() {
    ProfileItem(
        icon = Icons.Outlined.AccountCircle,
        title = "Profile",
        trailingIcon = Icons.Outlined.ArrowForward,
        onClick = { /* handle onClick here */ }
    )
}

@Preview (showBackground = true)
@Composable
fun ProfilePreview(){
    Profile(User.getUser())
}