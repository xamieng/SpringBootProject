package com.project.springBootProject.service

import com.project.springBootProject.assembler.UserAssembler
import com.project.springBootProject.domain.User
import com.project.springBootProject.dto.UserDTO
import com.project.springBootProject.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UserService (
        private val userRepository: UserRepository,
        private val userAssembler: UserAssembler

) {

    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createUser(domain: User): User? {
        logger.debug("create user: $domain")
        return userRepository.save(domain)
    }

    fun getUserById(id: Int): User? {
        logger.debug("getUserById: $id")
        return userRepository.findOneById(id)
    }

}
