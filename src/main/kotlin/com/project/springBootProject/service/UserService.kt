package com.project.springBootProject.service

import com.project.springBootProject.domain.User
import com.project.springBootProject.dto.UserDTO
import com.project.springBootProject.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.lang.IllegalStateException

@Service
class UserService (
        private val userRepository: UserRepository

) {

    private val logger = LoggerFactory.getLogger(UserService::class.java)

    fun createUser(domain: User): User? {
        logger.debug("create user: $domain")
        return userRepository.save(domain)
    }

    fun getUserById(id: Int?): User? {
        logger.debug("getUserById: $id")
        if (id == null) throw IllegalArgumentException("Id must not be null.")
        return userRepository.findOneById(id)
    }

    fun updateUser(dto: UserDTO, domain: User): User {
        logger.debug("updateUser")

        dto.firstName?.let {
            domain.firstName = dto.firstName
        }
        dto.lastName?.let {
            domain.lastName = dto.lastName
        }
        dto.role?.let {
            domain.role = dto.role
        }
        dto.vacationLeaveQuota?.let {
            domain.vacationLeaveQuota = dto.vacationLeaveQuota
        }
        dto.sickLeaveQuota?.let{
            domain.sickLeaveQuota = dto.sickLeaveQuota
        }
        dto.personalLeaveQuota?.let {
            domain.personalLeaveQuota = dto.personalLeaveQuota
        }
        dto.supervisorId?.let {
            domain.supervisorId = getUserById(dto.supervisorId) ?: throw IllegalStateException("User is not exist.")
        }

        return userRepository.save(domain)
    }

    fun deleteUserById(id: Int) {
        logger.debug("getUserById: $id")
        val user = getUserById(id) ?: throw IllegalArgumentException("User is not exist.")
        return userRepository.delete(user)
    }

}
