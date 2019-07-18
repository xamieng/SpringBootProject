package com.project.springBootProject.service

import com.project.springBootProject.assembler.LeaveDetailAssembler
import com.project.springBootProject.domain.LeaveDetail
import com.project.springBootProject.domain.User
import com.project.springBootProject.dto.CreateLeaveDetailDTO
import com.project.springBootProject.enum.LeaveStatus
import com.project.springBootProject.repository.LeaveDetailRepository
import com.project.springBootProject.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class LeaveDetailService (
        private val leaveDetailRepository: LeaveDetailRepository,
        private val leaveDetailAssembler: LeaveDetailAssembler
) {

    private val logger = LoggerFactory.getLogger(LeaveDetailService::class.java)

    fun getAllLeaveDetailById(id: Int): List<LeaveDetail> {
        logger.debug("getAllLeaveDetailById: $id")
        return leaveDetailRepository.findAllById(id)
    }

    fun createLeaveDetail(createLeaveDetailDTO: CreateLeaveDetailDTO): LeaveDetail {
        logger.debug("createLeaveDetail: $createLeaveDetailDTO")
        val leaveDetail = leaveDetailAssembler.assembleDomain(createLeaveDetailDTO, LeaveStatus.PENDING)
        return leaveDetailRepository.save(leaveDetail)
    }

}
