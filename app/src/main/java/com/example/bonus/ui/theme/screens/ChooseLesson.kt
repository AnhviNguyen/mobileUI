package com.example.bonus.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bonus.R
import com.example.bonus.component.CourseCard
import com.example.bonus.component.TopBar
import com.example.bonus.model.Course
import com.example.bonus.model.Lesson

@Composable
fun ChooseLesson (
    course: Course
)
{
    TopBar(onNavigateBack = { /*TODO*/ }, title = course.name)
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 100.dp)
    ){
        item{
            CourseCard(course = course)
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Lessons",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(700),
                letterSpacing = 0.3.sp
            )
            Spacer(modifier = Modifier.height(20.dp))
            LessonListCard(course.lessons)
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun LessonListCard(
    lessons: List<Lesson>
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        lessons.forEach { lesson ->
            LessonCard(lesson = lesson)
            Spacer(modifier = Modifier.height(5.dp))
        }
    }

}

@Composable
fun LessonCard(
    lesson: Lesson
) {
    Card(
        modifier = Modifier.padding(8.dp).border(1.dp, Color.LightGray, RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .align(Alignment.CenterHorizontally)){
            Image(
                painter = painterResource(id = randomImageResource()),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
                )
            Spacer(modifier = Modifier.width(20.dp))
            Column(modifier = Modifier
                .padding(16.dp)
                .weight(1f)) {
                Text(text = lesson.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = lesson.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(10.dp))
                LinearProgressIndicator(progress = lesson.process.toFloat())
            }
        }

    }
}

fun randomImageResource(): Int {
    val randomNumber = (1..9).random()
    return when (randomNumber) {
        1 -> R.drawable.l1
        2 -> R.drawable.l2
        3 -> R.drawable.l3
        4 -> R.drawable.l4
        5 -> R.drawable.l5
        6 -> R.drawable.l6
        7 -> R.drawable.l7
        8 -> R.drawable.l8
        else -> R.drawable.l9
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLessonCard() {
    LessonCard(
        lesson = Lesson(
            id = 1,
            course_id = 101,
            name = "Introduction to Web",
            youtueVideo_id = "eqeT7gKqbyA",
            description = "This is an introductory lesson.",
            duration = 30
        ),
    )
}

@Preview(showBackground = true)
@Composable
fun ChooseLessonPreview() {
    val course = Course(
        id = 5,
        name = "Machine Learning",
        content = "Learn how to build machine learning models using Python and TensorFlow. \n\n" +
                "This course will introduce the concepts of machine learning, TensorFlow fundamentals " +
                "and provide hands-on ways to understand and apply these concepts. \n" +
                "\n" +
                "Throughout this course, we will explore multiple machine learning algorithms, " +
                "learning models using the Python programming language and TensorFlow library. \n" ,
        duration = 125,
        imageUrl = "https://html.com/wp-content/uploads/html-hpg-featured-new.png"
    )
    ChooseLesson(Course.getData())
}