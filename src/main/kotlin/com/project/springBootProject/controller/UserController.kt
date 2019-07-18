package com.project.springBootProject.controller

import com.project.springBootProject.assembler.TaskAssembler
import com.project.springBootProject.dto.TaskDTO
import com.project.springBootProject.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/task")
class UserController(
        private val userService: UserService,
        private val taskAssembler: TaskAssembler
) {

    private val logger = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    fun getTaskById(@PathVariable id: String): ResponseEntity<TaskDTO> {
        logger.info("REST request to get task by id: $id")
//        val task = userService.getTaskById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val taskDTO = TaskDTO()
        return ResponseEntity.ok(taskDTO)
    }
}