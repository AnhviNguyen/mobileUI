package com.example.bonus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import com.example.bonus.component.CourseCard
import com.example.bonus.data.AndroidDownloader
import com.example.bonus.model.Course
import com.example.bonus.ui.theme.BonusTheme
import com.example.bonus.ui.theme.screens.ChooseLesson
import com.example.bonus.ui.theme.screens.CourseDetail
import com.example.bonus.ui.theme.screens.CourseOverview
import com.example.bonus.ui.theme.screens.DiscussScreen
import com.example.bonus.ui.theme.screens.LessonScreen
import com.example.bonus.ui.theme.screens.LoginScreen
import com.example.bonus.ui.theme.screens.Register

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

//        val downloader = AndroidDownloader(this)
//        downloader.DownloadFile("https://github.com/cjnhust/ebook_collection/blob/master/Kotlin%20in%20Action.pdf")


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BonusTheme {
                val courses = Course.getData()
//                LessonScreen(lesson = courses.lessons[0], lifecycleOwner = LocalLifecycleOwner.current)
//                CourseDetail(courses, courses.lessons[1])
                CourseOverview(course = courses)
                

//                DiscussScreen()


            }
        }
    }
}

