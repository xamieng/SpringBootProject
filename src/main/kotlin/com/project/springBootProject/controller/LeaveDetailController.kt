package com.project.springBootProject.controller

import com.project.springBootProject.assembler.LeaveDetailAssembler
import com.project.springBootProject.domain.LeaveDetail
import com.project.springBootProject.dto.CreateLeaveDetailDTO
import com.project.springBootProject.dto.LeaveDetailDTO
import com.project.springBootProject.dto.UserDTO
import com.project.springBootProject.service.LeaveDetailService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leave-detail")
class LeaveDetailController(
        private val leaveDetailService: LeaveDetailService,
        private val leaveDetailAssembler: LeaveDetailAssembler
) {

    private val logger = LoggerFactory.getLogger(LeaveDetailController::class.java)

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    fun getAllLeaveDetailById(@PathVariable id: Int): ResponseEntity<List<LeaveDetailDTO>> {
        logger.info("REST request to getAllLeaveDetailById: $id")
        val leaveDetails = leaveDetailService.getAllLeaveDetailById(id)
        return ResponseEntity.ok(leaveDetails.map { leaveDetailAssembler.assembleDTO(it) })
    }

    @PostMapping
    @Transactional
    fun createLeaveDetail(@RequestBody dto: CreateLeaveDetailDTO): ResponseEntity<LeaveDetailDTO> {
        logger.info("REST request to createLeaveDetail: $dto")
        val leaveDetail = leaveDetailService.createLeaveDetail(dto)
        return ResponseEntity.ok(leaveDetailAssembler.assembleDTO(leaveDetail))
    }
}