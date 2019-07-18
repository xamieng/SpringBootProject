package com.project.springBootProject.controller

import com.project.springBootProject.assembler.LeaveDetailAssembler
import com.project.springBootProject.dto.LeaveDetailDTO
import com.project.springBootProject.enum.LeaveStatus
import com.project.springBootProject.service.LeaveDetailService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leave-detail")
class LeaveDetailController(
        private val leaveDetailService: LeaveDetailService,
        private val leaveDetailAssembler: LeaveDetailAssembler
) {

    private val logger = LoggerFactory.getLogger(LeaveDetailController::class.java)

    @GetMapping("/user/{id}")
    @Transactional(readOnly = true)
    fun getAllLeaveDetailByUserId(@PathVariable id: Int): ResponseEntity<List<LeaveDetailDTO>> {
        logger.info("REST request to getAllLeaveDetailByUserId: $id")
        val leaveDetails = leaveDetailService.getAllLeaveDetailByUserId(id)
        return ResponseEntity.ok(leaveDetails.map { leaveDetailAssembler.assembleDTO(it) })
    }

    @GetMapping("/user/{id}/history")
    @Transactional(readOnly = true)
    fun getAllLeaveDetailHistoryByUserId(@PathVariable id: Int): ResponseEntity<List<LeaveDetailDTO>> {
        logger.info("REST request to getAllLeaveDetailHistoryByUserId: $id")
        val leaveDetails = leaveDetailService.getAllLeaveDetailHistoryByUserId(id)
        return ResponseEntity.ok(leaveDetails.map { leaveDetailAssembler.assembleDTO(it) })
    }

    @GetMapping("/supervisor/{id}")
    @Transactional(readOnly = true)
    fun getAllLeaveDetailBySupervisorId(@PathVariable id: Int): ResponseEntity<List<LeaveDetailDTO>> {
        logger.info("REST request to getAllLeaveDetailBySupervisorId: $id")
        val leaveDetails = leaveDetailService.getAllBySupervisorId(id) ?: emptyList()
        return ResponseEntity.ok(leaveDetails.map { leaveDetailAssembler.assembleDTO(it) })
    }

    @PostMapping
    @Transactional
    fun createLeaveDetail(@RequestBody dto: LeaveDetailDTO): ResponseEntity<LeaveDetailDTO> {
        logger.info("REST request to createLeaveDetail: $dto")
        val leaveDetail = leaveDetailService.createLeaveDetail(dto)
        return ResponseEntity.ok(leaveDetailAssembler.assembleDTO(leaveDetail))
    }

    @PutMapping
    @Transactional
    fun updateLeaveDetail(@RequestBody dto: LeaveDetailDTO): ResponseEntity<LeaveDetailDTO> {
        logger.info("REST request to updateLeaveDetail: $dto")
        val leaveDetail = leaveDetailService.updateLeaveDetail(dto)
        return ResponseEntity.ok(leaveDetailAssembler.assembleDTO(leaveDetail))
    }

    @DeleteMapping
    @Transactional
    fun deleteLeaveDetail(@RequestBody id: Int): ResponseEntity<HttpStatus> {
        logger.info("REST request to updateLeaveDetail: $id")
        leaveDetailService.deleteLeaveDetailById(id)
        return ResponseEntity.ok(HttpStatus.OK)
    }

    @PutMapping("/status")
    @Transactional
    @ApiImplicitParams(
            ApiImplicitParam(name = "id", value = "id", paramType = "query", dataType = "int", required = true),
            ApiImplicitParam(name = "status", value = "status", paramType = "query", dataType = "string", required = true),
            ApiImplicitParam(name = "supervisorComment", value = "status", paramType = "query", dataType = "string", required = true)
    )
    fun updateLeaveDetailStatus(@RequestParam id: Int,
                                @RequestParam status: LeaveStatus,
                                @RequestParam supervisorComment: String): ResponseEntity<HttpStatus> {
        logger.info("REST request to updateLeaveDetailStatus: $id")
        leaveDetailService.changeStatusLeaveDetail(id, status, supervisorComment)
        return ResponseEntity.ok(HttpStatus.OK)
    }
}