package com.example.demo.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
class UserController(val repo: UserRepository) {

    @GetMapping("/api/users", "GET")
    fun findAll(): ResponseEntity<UserGetAllResponse> {
        val response = UserGetAllResponse(repo.findAll())
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/api/users")
    fun insert(@RequestBody u: UserInsertRequest): ResponseEntity<UserInsertResponse> {
        val user = User(firstname=u.firstname, surname=u.surname, age=u.age, email=u.email, isPremium = u.isPremium)
        val response = UserInsertResponse(repo.insert(user))
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @DeleteMapping("/api/users/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Any> {
        repo.delete(id)
        return ResponseEntity(HttpStatus.ACCEPTED)
    }

    @GetMapping("/api/users/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Any> {
        val u = repo.findById(id)
        if (u != null) {
            return ResponseEntity(u, HttpStatus.OK)
        } else {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}