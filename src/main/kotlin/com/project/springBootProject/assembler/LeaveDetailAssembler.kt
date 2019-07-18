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
        dto.id = dto.id
        dto.startDate = dto.startDate
        dto.endDate = dto.endDate
        dto.status = dto.status
        dto.totalCount = dto.totalCount
        dto.comment = dto.comment
        return dto
    }
}