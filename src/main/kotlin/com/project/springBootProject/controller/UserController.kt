package com.project.springBootProject.controller

import com.project.springBootProject.assembler.UserAssembler
import com.project.springBootProject.dto.UserDTO
import com.project.springBootProject.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
        private val userService: UserService,
        private val userAssembler: UserAssembler
) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping
    @Transactional(readOnly = true)
    fun createUser(@RequestBody dto: UserDTO): ResponseEntity<UserDTO> {
        logger.info("REST request to create user: $dto")
        val user = userService.createUser(dto)
        val taskDTO = UserDTO()
        return ResponseEntity.ok(taskDTO)
    }
}