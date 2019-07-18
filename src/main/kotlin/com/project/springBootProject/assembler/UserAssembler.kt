package com.project.springBootProject.assembler

import com.project.springBootProject.domain.User
import com.project.springBootProject.dto.UserDTO
import com.project.springBootProject.service.UserService
import org.springframework.stereotype.Component

@Component
class UserAssembler(
        private val userService: UserService
) {
    fun assembleDomain(dto: UserDTO): User {
        val domain = User()
        domain.firstName = dto.firstName
        domain.lastName = dto.lastName
        domain.role = dto.role
        domain.vacationLeaveQuota = dto.vacationLeaveQuota
        domain.sickLeaveQuota = dto.sickLeaveQuota
        domain.personalLeaveQuota = dto.personalLeaveQuota
        domain.supervisorId = userService.getUserById(dto.supervisorId)
        return domain
    }
}