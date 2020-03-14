package com.example.demo.user

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
@Api(value = "user", description = "REST api for users/ endpoint")
class UserController(val repo: UserRepository) {

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "delete user with given id")
    @ApiResponses(
        ApiResponse(code = 204, message = "Successfully deleted user with given ID"),
        ApiResponse(code = 404, message = "Given ID does not exist"),
        ApiResponse(code = 500, message = "Unknown server error")
    )
    fun delete(
        @ApiParam(value = "user ID to delete", required = true)
        @PathVariable id: Int
    ) {
        repo.delete(id)
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "get user by id", response = User::class)
    @ApiResponses(
        ApiResponse(code = 200, message = "Returns the user for the given ID", response = User::class),
        ApiResponse(code = 404, message = "User for given ID does not exist.", response = ErrorResponse::class),
        ApiResponse(code = 500, message = "Unknown server error", response = ErrorResponse::class)
    )
    fun findById(
        @ApiParam(value = "user id", required = true)
        @PathVariable id: Int
    ): User {
        return repo.findById(id)
    }

    @GetMapping
    @ApiOperation(value = "get all users", response = UserGetAllResponse::class)
    @ApiResponses(
        ApiResponse(code = 200, message = "Returns all users", response = UserGetAllResponse::class),
        ApiResponse(code = 500, message = "Unknown server error", response = ErrorResponse::class)
    )
    fun findAll(): UserGetAllResponse {
        return UserGetAllResponse(repo.findAll())
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "updated user with given id")
    @ApiResponses(
        ApiResponse(code = 204, message = "Successfully updated user"),
        ApiResponse(code = 500, message = "Unknown server error")
    )
    fun updateUser(user: User) {
        throw NotImplementedError("Not implemented PUT /users/{id}")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "create a user", response = UserInsertResponse::class)
    @ApiResponses(
        ApiResponse(code = 201, message = "Creates a new user in database", response = UserInsertResponse::class),
        ApiResponse(code = 400, message = "Bad Request, invalid user data", response = ErrorResponse::class),
        ApiResponse(code = 500, message = "Unknown server error", response = ErrorResponse::class)
    )
    fun insert(@Valid @RequestBody u: UserInsertRequest): UserInsertResponse {
        val user = User(
            firstname = u.firstname!!, surname = u.surname!!, age = u.age!!,
            email = u.email!!, premium = u.premium!!
        )
        return UserInsertResponse(repo.insert(user))
    }
}

class NotFoundException(override val message: String) : RuntimeException(message)
