package com.project.springBootProject.repository

import com.project.springBootProject.domain.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    fun findOneById(id: String): User?
}