package com.example.demo.user

data class UserInsertResponse(val id: Int)

data class UserGetAllResponse(val users: List<User>)

data class User(var id: Int?=null, var firstname: String="",
                var surname: String="", var age: Int=0,
                var email: String="", var isPremium: Boolean=false)