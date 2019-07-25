package com.example.demo.api

import com.example.demo.User

data class UserInsertResponse(val id: Int)

data class UserGetAllResponse(val users: List<User>)