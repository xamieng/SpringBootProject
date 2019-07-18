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
        domain.id = dto.id
        domain.firstName = dto.firstName
        domain.lastName = dto.lastName
        domain.jobTitle = dto.jobTitle
        domain.role = dto.role
        domain.vacationLeaveQuota = dto.vacationLeaveQuota
        domain.sickLeaveQuota = dto.sickLeaveQuota
        domain.personalLeaveQuota = dto.personalLeaveQuota
        dto.supervisorId?.let { domain.supervisor = userService.getUserById(it) }
        return domain
    }

    fun assembleDTO(domain: User): UserDTO {
        val dto = UserDTO()
        dto.id = domain.id
        dto.firstName = domain.firstName
        dto.lastName = domain.lastName
        dto.jobTitle = domain.jobTitle
        dto.role = domain.role
        dto.vacationLeaveQuota = domain.vacationLeaveQuota
        dto.sickLeaveQuota = domain.sickLeaveQuota
        dto.personalLeaveQuota = domain.personalLeaveQuota
        domain.supervisor?.let { dto.supervisorId = it.id }
        return dto
    }
}