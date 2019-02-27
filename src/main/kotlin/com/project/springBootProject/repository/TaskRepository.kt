package com.project.springBootProject.repository

import com.project.springBootProject.domain.Task
import org.springframework.data.mongodb.repository.MongoRepository

interface TaskRepository : MongoRepository<Task, String> {
    fun findOneById(id: String): Task?
}