package com.project.springBootProject.service

import com.project.springBootProject.assembler.LeaveDetailAssembler
import com.project.springBootProject.domain.LeaveDetail
import com.project.springBootProject.dto.LeaveDetailDTO
import com.project.springBootProject.enum.LeaveStatus
import com.project.springBootProject.enum.LeaveType
import com.project.springBootProject.repository.LeaveDetailRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class LeaveDetailService (
        private val leaveDetailRepository: LeaveDetailRepository,
        private val leaveDetailAssembler: LeaveDetailAssembler,
        private val userService: UserService
) {

    private val logger = LoggerFactory.getLogger(LeaveDetailService::class.java)

    fun getAllLeaveDetailByUserId(userId: Int): List<LeaveDetail> {
        logger.debug("getAllLeaveDetailByUserId: $userId")
        return leaveDetailRepository.findAllByUserId(userId)
    }

    fun getAllLeaveDetailHistoryByUserId(userId: Int): List<LeaveDetail> {
        logger.debug("getAllLeaveDetailHistoryByUserId: $userId")
        val today = LocalDate.now()
        return leaveDetailRepository.findAllByUserIdAndEndDateAfter(userId, today)
    }

    fun getAllBySupervisorId(supervisorId: Int): List<LeaveDetail>? {
        logger.debug("getAllBySupervisorId: $supervisorId")
        return leaveDetailRepository.findAllBySupervisorId(supervisorId)
    }

    fun createLeaveDetail(leaveDetailDTO: LeaveDetailDTO): LeaveDetail {
        logger.debug("createLeaveDetail: $leaveDetailDTO")
        val leaveDetail = leaveDetailAssembler.assembleDomain(leaveDetailDTO)
        leaveDetail.status = LeaveStatus.PENDING
        return leaveDetailRepository.save(leaveDetail)
    }

    fun updateLeaveDetail(leaveDetailDTO: LeaveDetailDTO): LeaveDetail {
        logger.debug("updateLeaveDetail: $leaveDetailDTO")
        val id = leaveDetailDTO.id ?: throw IllegalStateException("LeaveDetail id should exists.")
        val leaveDetail = leaveDetailRepository.findOneById(id) ?: throw IllegalStateException("LeaveDetail should exist.")

        leaveDetailDTO.startDate?.let {
            leaveDetail.startDate = it
        }

        leaveDetailDTO.endDate?.let {
            leaveDetail.endDate = it
        }

        leaveDetailDTO.status?.let {
            leaveDetail.status = it
        }

        leaveDetailDTO.leaveType?.let {
            leaveDetail.leaveType = it
        }

        leaveDetailDTO.comment?.let {
            leaveDetail.comment = it
        }

        leaveDetailDTO.totalCount?.let {
            leaveDetail.totalCount = it
        }

        leaveDetailDTO.user?.let {
            leaveDetail.user = userService.getUserById(it.id)
        }

        return leaveDetailRepository.save(leaveDetail)
    }

    fun deleteLeaveDetailById(id: Int) {
        logger.debug("deleteLeaveDetail: $id")
        val leaveDetail = leaveDetailRepository.findOneById(id) ?: throw IllegalStateException("LeaveDetail should exist.")
        return leaveDetailRepository.delete(leaveDetail)
    }

    fun changeStatusLeaveDetail(id: Int, newStatus: LeaveStatus, supervisorComment: String) {
        logger.debug("changeStatusLeaveDetail: $id, status: $newStatus and supervisorComment: $supervisorComment")
        val leaveDetail = leaveDetailRepository.findOneById(id)
        val user = leaveDetail?.user ?: throw IllegalStateException("User must exist.")
        val leaveType = leaveDetail.leaveType ?: throw IllegalStateException("Current leaveType must exist.")

        if (newStatus == LeaveStatus.ACCEPTED) {
            leaveDetail.status = LeaveStatus.ACCEPTED
            leaveDetail.supervisorComment = supervisorComment
            leaveDetailRepository.save(leaveDetail)
            userService.updateUserQuota(user, leaveType, supervisorComment)
        }
    }

}
