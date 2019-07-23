package com.example.demo

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet


class UserRepository(val jdbcTemplate: JdbcTemplate) {

    // lateinit var jdbcTemplate: JdbcTemplate

    fun insert(u: User) {
        this.jdbcTemplate.update("insert into users (firstname, surname, age, is_premium, email) " +
                "values (?, ?, ?, ?, ?)", u.firstname, u.surname, u.age, u.isPremium, u.email )
    }

    fun delete(id: Int) {

    }

    fun findById(id: Int): User {
        val query = "SELECT * FROM users WHERE ID = ?";
        val users = jdbcTemplate.queryForObject(
                query, Object[] { id }, UserRowMapper());

        return User(1,"firstname", "surname", 38, "email@foo.com")
    }

    fun findAll(): List<User> {
        return listOf<User>()
    }
}


class UserRowMapper: RowMapper<User> {

    override fun mapRow(rs: ResultSet,rowNum: Int): User {
        val user = User()
        user.id = rs.getInt("ID")
        user.firstname = rs.getString("firstname")
        user.surname = rs.getString("surname")
        user.age = rs.getInt("age")
        user.email = rs.getString("email")
        user.isPremium = rs.getBoolean("is_premium")
        return user

    }
}