package com.project.springBootProject.service

import com.project.springBootProject.domain.User
import com.project.springBootProject.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService (
        private val userRepository: UserRepository
) {

    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun getTaskById(id: String): User? {
        logger.debug("getTaskById: $id")
        return userRepository.findOneById(id)
    }

}
