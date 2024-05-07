package com.example.bonus.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.bonus.model.Course
import com.example.bonus.ui.theme.Blue


@Composable
fun CourseCard (
    course: Course
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
    ) {
        Column {
            Image(
               modifier = Modifier
                   .fillMaxWidth()
                   .height(200.dp),
                painter = rememberAsyncImagePainter(model = course.imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = course.convertMinutesToHoursAndMinutes(course.duration),
                    style = MaterialTheme.typography.bodySmall,
                    color = Blue,
                    fontWeight = FontWeight.Bold
                )
                Text(text = course.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = course.title, )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    val course = Course(
        id = 5,
        name = "Machine Learning",
        content = "Learn how to build machine learning models using Python and TensorFlow.",
        duration = 125,
        imageUrl = "https://html.com/wp-content/uploads/html-hpg-featured-new.png"
    )
    CourseCard(course)
}