package com.example.bonus.ui.theme.screens

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileDownload
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import coil.compose.rememberAsyncImagePainter
import com.example.bonus.component.MyButton
import com.example.bonus.component.OutlineMyButton
import com.example.bonus.component.TopBar
import com.example.bonus.data.AndroidDownloader
import com.example.bonus.model.Course
import com.example.bonus.model.Document
import com.example.bonus.model.Lesson
import com.example.bonus.model.Teacher
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class,
    ExperimentalPagerApi::class
)
@Composable
fun CourseDetail(
    course: Course,
    lesson: Lesson
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { HomeTabs.entries.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    TopBar(onNavigateBack = { /*TODO*/ }, title = course.name)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp)
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ){
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = lesson.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = lesson.id.toString() + " of " + course.lessons.size + " lessons")
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
        TabRow(
            selectedTabIndex = selectedTabIndex.value,
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color.White
        ) {
            HomeTabs.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex.value == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.outline,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(currentTab.ordinal)
                        }
                    },
                    text = { Text(text = currentTab.text, maxLines = 1) },
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            when (page) {
                HomeTabs.Lesson.ordinal -> LessonScreen(lesson, LocalLifecycleOwner.current)
                HomeTabs.Test.ordinal -> TestScreen()
                HomeTabs.Resources.ordinal -> ResourcesScreen()
                HomeTabs.Discuss.ordinal -> DiscussScreen()
                else -> throw IllegalArgumentException("Unexpected page index: $page")
            }
        }
    }
}

@Composable
fun TestScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "This lesson does not have a test")
    }
}

@Composable
fun LessonScreen(
    lesson: Lesson,
    lifecycleOwner: LifecycleOwner
)
{
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp)
    )
    {
        item{
            AndroidView(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp)),
                factory = { context ->
                    YouTubePlayerView(context = context).apply {
                        lifecycleOwner.lifecycle.addObserver(this)

                        addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                youTubePlayer.loadVideo(lesson.youtueVideo_id, 0f)
                            }
                        })
                    }

                })

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Introduction",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight(700),
                letterSpacing = 0.3.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = lesson.description,
                style = MaterialTheme.typography.bodyMedium,
                letterSpacing = 0.3.sp
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                MyButton(text = "Previous", modifier = Modifier, onClick = { /*TODO*/ })
                MyButton(text = "Next", modifier = Modifier, onClick = { /*TODO*/ })

            }
        }
    }
}



@Composable
fun ResourcesScreen(
){
    val docs = Document.getDoc()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp)
    ) {
        items(docs) { doc ->
            DocumentDownloadCard(doc)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun DocumentDownloadCard(
    document: Document) {

    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                val downloader = AndroidDownloader(context)
                downloader.DownloadFile(document.doc_url)
            },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.FileDownload,
                contentDescription = "Download icon"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = document.doc_name,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${document.memory} MB",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun DiscussScreen() {
    val teacher = Teacher.getTeacher()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 20.dp, vertical = 30.dp))
    {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)),
            colors = CardDefaults.cardColors(Color.White)
        )
        {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Image(
                    painter = rememberAsyncImagePainter(teacher.photoUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Gray, CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(teacher.name, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight(700))

                    Text(teacher.phone, style = MaterialTheme.typography.bodyMedium)

                }
            }
            Text(teacher.profession, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(start = 20.dp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(teacher.experience, style = MaterialTheme.typography.bodyMedium,  modifier = Modifier.padding(start = 20.dp))
        }
        Text(text = "You can contact the instructor via the phone number above.",
            modifier = Modifier.padding(16.dp),
            letterSpacing = 0.5.sp
        )


    }


}

enum class HomeTabs(
    val text: String
) {
    Lesson(
        text = "Lesson"
    ),
    Test(
        text = "Test"
    ),
    Resources(
        text = "Resources"
    ),
    Discuss(
        text = "Discuss"
    )
}







@Preview(showBackground = true)
@Composable
fun DiscussScreenPreview() {
    DiscussScreen()
}

@Preview(showBackground = true)
@Composable
fun ResourcesScreenPreview() {
    ResourcesScreen()
}

@Preview(showBackground = true)
@Composable
fun DocumentDownloadCardPreview() {
    val document = Document(1, "https://example.com/doc.pdf", "Document.pdf", "Some content")
    DocumentDownloadCard(
        document
    )
}

@Preview(showBackground = true)
@Composable
fun LessonScreenPreview() {
    LessonScreen(lesson = Course.getData().lessons[0], LocalLifecycleOwner.current)
}

@Preview(showBackground = true)
@Composable
fun CourseDetailPreview() {
    CourseDetail(Course.getData(), Course.getData().lessons[0])
}