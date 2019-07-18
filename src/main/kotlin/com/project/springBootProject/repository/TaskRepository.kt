package com.project.springBootProject.repository

import com.project.springBootProject.domain.Task
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, String> {
    fun findOneById(id: String): Task?
}