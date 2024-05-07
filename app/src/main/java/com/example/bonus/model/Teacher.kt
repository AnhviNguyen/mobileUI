package com.example.bonus.model

class Teacher(
    val photoUrl: String,
    val name: String,
    val phone: String,
    val profession: String,
    val experience: String
) {

    companion object {
        fun getTeacher(): Teacher {
            val teacher = Teacher(
                "https://www.shutterstock.com/image-photo/young-happy-business-woman-sitting-260nw-2223351415.jpg",
                "John Doe",
                "123-456-7890",
                "Professor of Computer Science",
                "10 years of teaching experience at MIT University\n" +
                        "Teaching courses in Computer Science and Artificial Intelligence\n" +
                        "Software Engineer\n"
            )

            return teacher
        }
    }
}