package com.example.bonus.model

class User (
    val id: Int,
    val username: String,
    val password: String,
    val email: String
){
    companion object{
        fun getUser(): User{
            return User(1, "Laura_Rivas", "password", "laura@gmail.com")
        }
    }
}