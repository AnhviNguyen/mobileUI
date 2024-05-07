package com.example.bonus.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bonus.model.Test
import com.example.bonus.ui.theme.Blue

@Composable
fun TestCard(
    test: Test,
    onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .border(1.dp, Color.Gray, RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(
                text = "Quiz",
                color = Blue,
                fontSize = 17.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = test.name,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Let's put your memory on this topic test. Solve tasks and check your knowledge",
                color = Color.Gray,
                fontSize = 17.sp,
            )
            Spacer(modifier = Modifier.height(20.dp))
            MyButton(text = "Begin", modifier = Modifier.fillMaxWidth()) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestCardPreview() {
    val test = Test(1, "Introduction to Kotlin")
    TestCard(test, onClick = {})
}