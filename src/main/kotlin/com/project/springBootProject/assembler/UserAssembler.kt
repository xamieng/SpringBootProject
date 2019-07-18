package com.project.springBootProject.assembler

import com.project.springBootProject.domain.User
import com.project.springBootProject.dto.UserDTO
import com.project.springBootProject.service.UserService
import org.springframework.stereotype.Component
import javax.inject.Inject

@Component
class UserAssembler {
    @Inject private lateinit var userService: UserService

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

    fun assembleDTO(domain: User): UserDTO {
        val dto = UserDTO()
        dto.firstName = dto.firstName
        dto.lastName = dto.lastName
        dto.role = dto.role
        dto.vacationLeaveQuota = dto.vacationLeaveQuota
        dto.sickLeaveQuota = dto.sickLeaveQuota
        dto.personalLeaveQuota = dto.personalLeaveQuota
        dto.supervisorId = domain.id ?: throw IllegalStateException("User id must not be null.")
        return dto
    }
}