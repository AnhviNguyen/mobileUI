package com.example.bonus.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bonus.ui.theme.Blue

@Composable
fun MyButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue
        )
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}

@Composable
fun OutlineMyButton(text: String, modifier: Modifier, onClick: () -> Unit){
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .height(50.dp),
        border = BorderStroke(1.dp, Blue)

    )
    {
        Text(
            text = text
        )
    }
}

@Preview
@Composable
fun PreviewMyButton() {
    MyButton(
        text = "Test Button",
        modifier = Modifier,
        onClick = {}
    )
}

@Preview
@Composable
fun PreviewOutlineMyButton() {
    OutlineMyButton(
        modifier = Modifier,
        text = "heloo",
        onClick = {}
    )
}