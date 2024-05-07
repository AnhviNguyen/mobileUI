package com.example.bonus.ui.theme.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.NorthWest
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bonus.component.TopBar
import com.example.bonus.model.Course

@Composable
fun Search(
    courses: List<Course> = emptyList(),
)
{
    val text = remember { mutableStateOf("") }

    TopBar(onNavigateBack = { /*TODO*/ }, title = "Search")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp,end = 20.dp,top = 80.dp)
    )
    {

        OutlinedTextField(
            value = text.value,
            onValueChange = { newText ->
                text.value = newText
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Search")
            },
            leadingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription =null )
                }
            },
            placeholder = { Text(text = "What do you want to learn ?")},
            trailingIcon = {
                IconButton(onClick = { text.value = "" }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        )
        Spacer(modifier = Modifier.size(20.dp))

        Text(
            text = "Popular Courses",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )

        LazyColumn {
            items(courses) { course ->
                SearchItem(course = course)
            }
        }
    }
}
@Composable
fun SearchItem (
    course :Course,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        {
            Icon(
                imageVector = Icons.Default.NorthWest,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = course.name,
                fontSize = 18.sp,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
                .align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true, name = "Search Screen Preview")
@Composable
fun SearchItemPreview() {
    val coursePreview = Course(id = 1, name = "Android Development", content = "Learn Android Development", duration = 20, imageUrl = "")
    SearchItem(course = coursePreview)
}


@Preview(showBackground = true, name = "Search Screen Preview")
@Composable
fun SearchScreenPreview() {
    val courses = listOf(
        Course(id = 1,
            name = "Android Development",content = "Learn how to build Android apps using Kotlin and Jetpack Compose.",
            duration = 12,
            imageUrl = "https://example.com/android-course.png"
        ),
        Course(
            id = 2,
            name = "iOS Development",
            content = "Learn how to build iOS apps using Swift and SwiftUI.",
            duration = 12,
            imageUrl = "https://example.com/ios-course.png"
        ),
        Course(
            id = 3,
            name = "Web Development",
            content = "Learn how to build websites using HTML, CSS, and JavaScript.",
            duration = 12,
            imageUrl = "https://example.com/web-course.png"
        ),
        Course(
            id = 4,
            name = "Data Science",
            content = "Learn how to analyze data using Pythonand machine learning.",
            duration = 12,
            imageUrl = "https://example.com/data-science-course.png"
        ),
        Course(
            id = 5,
            name = "Machine Learning",
            content = "Learn how to build machine learning models using Python and TensorFlow.",
            duration = 12,
            imageUrl = "https://example.com/machine-learning-course.png"
        )
    )
    Search(courses = courses)
}