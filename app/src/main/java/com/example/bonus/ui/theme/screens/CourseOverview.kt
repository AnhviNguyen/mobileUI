package com.example.bonus.ui.theme.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha
import androidx.wear.compose.material.Icon
import coil.compose.rememberAsyncImagePainter
import com.example.bonus.component.MyButton
import com.example.bonus.component.TopBar
import com.example.bonus.model.Course
import com.example.bonus.model.Lesson

@Composable
fun CourseOverview(
    course: Course,
)
{
    TopBar(onNavigateBack = { /*TODO*/ }, title = course.name)
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 100.dp)
    ) {
       item {
           Image(
               painter = rememberAsyncImagePainter(model = course.imageUrl),
               contentDescription = null,
               modifier = Modifier
                   .height(250.dp)
                   .fillMaxWidth()
                   .clip(RoundedCornerShape(10.dp)),
               contentScale = ContentScale.Crop
           )

           Spacer(modifier = Modifier.height(20.dp))

           Text(
               text = "About the course",
               style = MaterialTheme.typography.titleLarge,
               fontWeight = FontWeight(700),
               letterSpacing = 0.3.sp
           )
           Spacer(modifier = Modifier.height(7.dp))
           Text(
               text = course.content,
               style = MaterialTheme.typography.bodyMedium,
               textAlign = TextAlign.Start,
               letterSpacing = 0.3.sp
           )

           Spacer(modifier = Modifier.height(20.dp))

           Text(
               text = "Duration",
               style = MaterialTheme.typography.titleLarge,
               fontWeight = FontWeight(700),
               letterSpacing = 0.3.sp
           )
           Spacer(modifier = Modifier.height(7.dp))
           Text(
               text = course.convertMinutesToHoursAndMinutes(course.duration),
               style = MaterialTheme.typography.bodyMedium,
               textAlign = TextAlign.Start,
               letterSpacing = 0.3.sp
           )
           Spacer(modifier = Modifier.height(20.dp))

           Text(
               text = "Lessons",
               style = MaterialTheme.typography.titleLarge,
               fontWeight = FontWeight(700),
               letterSpacing = 0.3.sp
           )
           Spacer(modifier = Modifier.height(7.dp))
           Text(
               text = course.lessons.size.toString() + " lessons",
               style = MaterialTheme.typography.bodyMedium,
               textAlign = TextAlign.Start,
               letterSpacing = 0.3.sp
           )
           Spacer(modifier = Modifier.height(20.dp))
           LessonExpandableList(course.lessons)
           Spacer(modifier = Modifier.height(20.dp))
           MyButton(
               text = "Enter the course",
               modifier = Modifier.fillMaxWidth(),
               onClick = {}
           )
           Spacer(modifier = Modifier.height(100.dp))
       }
    }
}
@Composable
fun LessonExpandableList(
    lessons: List<Lesson>
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .border(0.5.dp, Color.LightGray, RoundedCornerShape(10.dp))
    ){
        lessons.forEach { lesson ->
            LessonCardExpandable(lesson = lesson)
        }
    }
}

@Composable
fun LessonCardExpandable(
    lesson: Lesson,
    descriptionMaxLines: Int = 1,
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes().medium,
        colors = CardDefaults.cardColors(Color.White),
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = lesson.name,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        tint = Color.Gray,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Text(
                    text = "   "+lesson.description,
                    maxLines = descriptionMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CourseOverviewPreview() {
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
    CourseOverview(course)
}


@Preview(showBackground = true)
@Composable
fun PreviewLessonCardExpandable() {
    LessonCardExpandable(
        lesson = Lesson(
            id = 1,
            course_id = 101,
            name = "Introduction to Web",
            description = "This is an introductory lesson.",
            youtueVideo_id = "eqeT7gKqbyA",
            duration = 30
        )
    )
}