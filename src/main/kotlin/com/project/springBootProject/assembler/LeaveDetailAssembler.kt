package com.project.springBootProject.assembler

import com.project.springBootProject.domain.LeaveDetail
import com.project.springBootProject.dto.LeaveDetailDTO
import com.project.springBootProject.service.UserService
import org.springframework.stereotype.Component
import javax.inject.Inject

@Component
class LeaveDetailAssembler {

    @Inject
    private lateinit var userService: UserService

    @Inject
    private lateinit var userAssembler: UserAssembler

    fun assembleDomain(leaveDetailDTO: LeaveDetailDTO): LeaveDetail {
        return LeaveDetail().apply {
            startDate = leaveDetailDTO.startDate
            endDate = leaveDetailDTO.endDate
            totalCount = leaveDetailDTO.totalCount
            comment = leaveDetailDTO.comment
            supervisorComment = leaveDetailDTO.supervisorComment
            leaveType = leaveDetailDTO.leaveType
            status = leaveDetailDTO.status
            leaveDetailDTO.user?.let { user = userService.getUserById(it.id) }
        }
    }

    fun assembleDTO(domain: LeaveDetail): LeaveDetailDTO {
        val dto = LeaveDetailDTO()
        dto.id = domain.id
        dto.startDate = domain.startDate
        dto.endDate = domain.endDate
        dto.status = domain.status
        dto.leaveType = domain.leaveType
        dto.totalCount = domain.totalCount
        dto.comment = domain.comment
        dto.supervisorComment = domain.supervisorComment
        domain.user?.let { dto.user = userAssembler.assembleDTO(it) }
        return dto
    }
}