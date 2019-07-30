package com.example.demo.user

import java.time.LocalDateTime
import java.time.ZoneId

data class UserInsertRequest(var firstname: String,
                             var surname: String, var age: Int,
                             var email: String, var isPremium: Boolean)

data class UserInsertResponse(val id: Int)

data class UserGetAllResponse(val users: List<User>)

data class User(var id: Int?=null, var firstname: String="",
                var surname: String="", var age: Int=0,
                var email: String="", var isPremium: Boolean=false,
                var createdAt: LocalDateTime? = LocalDateTime.now(ZoneId.of("Z")),
                var updatedAt: LocalDateTime? = LocalDateTime.now(ZoneId.of("Z")))