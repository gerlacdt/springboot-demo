package com.example.demo.user

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
@Api(value = "user", description = "REST api for users/ endpoint")
class UserController(val repo: UserRepository) {

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun delete(@PathVariable id: Int) {
        repo.delete(id)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Any> {
        val u = repo.findById(id)
        if (u != null) {
            return ResponseEntity(u, HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping
    fun findAll(): ResponseEntity<UserGetAllResponse> {
        val response = UserGetAllResponse(repo.findAll())
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create a user", response = UserInsertResponse::class)
    fun insert(@Valid @RequestBody u: UserInsertRequest): UserInsertResponse {
        val user = User(firstname=u.firstname, surname=u.surname, age=u.age, email=u.email, isPremium = u.isPremium)
        return UserInsertResponse(repo.insert(user))
    }

}