package com.example.bonus.model

class Course(
    val id: Int ,
    val name: String ,
    val content:String ,
    val title: String ="Advance web application",
    val duration : Int,
    val imageUrl : String,
    val lessons: List<Lesson> = emptyList()
){
    fun convertMinutesToHoursAndMinutes(minutes: Int): String {
        val hours = minutes / 60
        val leftoverMinutes = minutes % 60
        return "${hours}h ${leftoverMinutes} mins"
    }

    companion object {
        fun getData(): Course {
            val course = Course(
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
            )
            return course
        }
    }
}