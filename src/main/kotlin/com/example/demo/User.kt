package com.example.demo

import java.time.LocalDateTime

data class User(var id: Int=0, var firstname: String="",
                var surname: String="", var age: Int=0,
                var email: String="", var isPremium: Boolean=false)