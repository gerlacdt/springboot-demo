package com.example.demo.user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime
import java.time.ZoneId
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@ApiModel
data class UserInsertRequest(
        @get:ApiModelProperty(required = true) @get:NotBlank var firstname: String,
        @get:ApiModelProperty(required = true) @get:NotBlank var surname: String,
        @get:ApiModelProperty(required = true) @get:NotNull var age: Int,
        @get:ApiModelProperty(required = true) @get:NotBlank var email: String,
        @get:ApiModelProperty(required = true) @get:NotNull var premium: Boolean)

data class UserInsertResponse(val id: Int)

data class UserGetAllResponse(val users: List<User>)

data class User(var id: Int?=null, var firstname: String="",
                var surname: String="", var age: Int=0,
                var email: String="", var premium: Boolean=false,
                var createdAt: LocalDateTime? = LocalDateTime.now(ZoneId.of("Z")),
                var updatedAt: LocalDateTime? = LocalDateTime.now(ZoneId.of("Z")))