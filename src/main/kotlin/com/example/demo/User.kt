package com.example.demo


data class User(var id: Int?=null, var firstname: String="",
                var surname: String="", var age: Int=0,
                var email: String="", var isPremium: Boolean=false)
