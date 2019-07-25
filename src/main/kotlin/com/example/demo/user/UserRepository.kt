package com.example.demo.user

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Connection
import java.sql.ResultSet


@Repository
class UserRepository(val jdbcTemplate: JdbcTemplate) {

    fun truncate() {
        this.jdbcTemplate.update("TRUNCATE TABLE users")
    }

    fun insert(u: User): Int {
        val keyHolder = GeneratedKeyHolder()
        val insertSQL = "insert into users (firstname, surname, age, is_premium, email) " +
        "values (?, ?, ?, ?, ?)"
        val fn = {conn: Connection ->
            val ps = conn.prepareStatement(insertSQL, arrayOf("id"))
            ps.setString(1, u.firstname)
            ps.setString(2, u.surname)
            ps.setInt(3, u.age)
            ps.setBoolean(4, u.isPremium)
            ps.setString(5, u.email)
            ps
        }
        this.jdbcTemplate.update(fn, keyHolder)
        return keyHolder.getKey()!!.toInt()
    }

    fun delete(id: Int): Int {
        return this.jdbcTemplate.update("delete from users where id = ?", id)
    }


    fun findById(id: Int): User? {
        val query = "select * from users where id = ?"
        try {
            return jdbcTemplate.queryForObject(
                    query, arrayOf<Int>(id) , UserRowMapper())!!
        } catch (e: EmptyResultDataAccessException) {
            return null
        }
    }


    fun findAll(): List<User> {
        val query = "SELECT * FROM users"
        return jdbcTemplate.query(
                query, UserRowMapper())
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