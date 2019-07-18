package com.project.springBootProject.service

import com.project.springBootProject.domain.User
import com.project.springBootProject.dto.TaskDTO
import com.project.springBootProject.enum.LeaveStatus
import com.project.springBootProject.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TaskService (
        private val userRepository: UserRepository
) {

    private val logger = LoggerFactory.getLogger(TaskService::class.java)

    fun getTaskById(id: String): User? {
        logger.debug("getTaskById: $id")
        return userRepository.findOneById(id)
    }

}
