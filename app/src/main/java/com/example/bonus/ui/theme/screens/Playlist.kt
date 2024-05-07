package com.example.bonus.ui.theme.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bonus.component.CourseCard
import com.example.bonus.component.TopBar
import com.example.bonus.model.Course
import com.example.bonus.model.Lesson

@Composable
fun Playlist(
    course: List<Course>
){
    TopBar(onNavigateBack = { /*TODO*/ }, title = "Playlist")
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 80.dp),
    ){
        items(course) {
            CourseCard(course = it)
            Spacer(Modifier.height(20.dp))
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPlaylist() {
    val courses = listOf(
        Course(
            id = 1,
            name = "Web Development Fundamentals",
            content = "This course covers the basics of web development, including HTML, CSS, and JavaScript.",
            title = "Advance Web Application",
            duration = 120,
            imageUrl = "https://html.com/wp-content/uploads/html-hpg-featured-new.png",
            lessons = listOf(
                Lesson(1, 1, "Introduction to HTML", "D0K6bg8WVvw","Learn the fundamentals of HTML", 30),
                Lesson(2, 1, "CSS Basics",  "K1WSGdDnbfM","Understand CSS and how to style web pages", 45),
                Lesson(3, 1, "JavaScript Fundamentals","eqeT7gKqbyA", "Get started with JavaScript programming", 60)
            )
        ),
        Course(
            id = 2,
            name = "Android Development Fundamentals",
            content = "This course covers the basics of Android development.",
            title = "Advance Web Application",
            duration = 120,
            imageUrl = "https://html.com/wp-content/uploads/html-hpg-featured-new.png",
            lessons = listOf(
                Lesson(1, 1, "Introduction to HTML", "D0K6bg8WVvw","Learn the fundamentals of HTML", 30),
                Lesson(2, 1, "CSS Basics",  "K1WSGdDnbfM","Understand CSS and how to style web pages", 45),
                Lesson(3, 1, "JavaScript Fundamentals","eqeT7gKqbyA", "Get started with JavaScript programming", 60)
            )
        ),

    )


    Playlist(courses)
}