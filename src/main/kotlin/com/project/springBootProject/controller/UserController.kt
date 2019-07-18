package com.project.springBootProject.controller

import com.project.springBootProject.assembler.UserAssembler
import com.project.springBootProject.dto.UserDTO
import com.project.springBootProject.service.UserService
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
        val domain = userAssembler.assembleDomain(dto)
        val user = userService.createUser(domain) ?: throw IllegalStateException("User must not be null")
        val result = userAssembler.assembleDTO(user)
        return ResponseEntity.ok(result)
    }

    @GetMapping(value="/{id}")
    @Transactional(readOnly = true)
    fun getUser(@PathVariable id: Int): ResponseEntity<UserDTO> {
        logger.info("REST request to get user: id=$id")
        val user = userService.getUserById(id) ?: throw IllegalStateException("User must not be null")
        val result = userAssembler.assembleDTO(user)
        return ResponseEntity.ok(result)
    }
}