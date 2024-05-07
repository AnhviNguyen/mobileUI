package com.example.bonus.model

import kotlin.time.Duration

class Lesson(
    val id: Int,
    val course_id: Int,
    val name: String,
    val youtueVideo_id : String,
    val description: String,
    val duration: Int,
    val process: Int =0,
    val document: List<Document> = Document.getDoc()
) {
    fun convertMinutesToHoursAndMinutes(minutes: Int): String {
        val hours = minutes / 60
        val leftoverMinutes = minutes % 60
        return "${hours}h ${leftoverMinutes} mins"
    }
}