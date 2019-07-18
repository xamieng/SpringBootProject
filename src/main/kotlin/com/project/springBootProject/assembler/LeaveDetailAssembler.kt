package com.project.springBootProject.assembler

import com.project.springBootProject.domain.LeaveDetail
import com.project.springBootProject.dto.CreateLeaveDetailDTO
import com.project.springBootProject.dto.LeaveDetailDTO
import com.project.springBootProject.enum.LeaveStatus
import com.project.springBootProject.service.UserService
import org.springframework.stereotype.Component
import javax.inject.Inject

@Component
class LeaveDetailAssembler {

    @Inject
    private lateinit var userService: UserService

    @Inject
    private lateinit var userAssembler: UserAssembler

    fun assembleDomain(createDTO: CreateLeaveDetailDTO, newStatus: LeaveStatus): LeaveDetail {
        return LeaveDetail().apply {
            startDate = createDTO.startDate
            endDate = createDTO.endDate
            totalCount = createDTO.totalCount
            comment = createDTO.comment
            status = newStatus
            createDTO.userId?.let { user = userService.getUserById(it) }
        }
    }

    fun assembleDTO(domain: LeaveDetail): LeaveDetailDTO {
        val dto = LeaveDetailDTO()
        dto.id = domain.id
        dto.startDate = domain.startDate
        dto.endDate = domain.endDate
        dto.status = domain.status
        dto.totalCount = domain.totalCount
        dto.comment = domain.comment
        domain.user?.let { dto.user = userAssembler.assembleDTO(it) }
        return dto
    }
}