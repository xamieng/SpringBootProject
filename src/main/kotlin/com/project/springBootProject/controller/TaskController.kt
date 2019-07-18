package com.project.springBootProject.controller

import com.project.springBootProject.assembler.TaskAssembler
import com.project.springBootProject.dto.TaskDTO
import com.project.springBootProject.enum.TaskStatus
import com.project.springBootProject.service.TaskService
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
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
import springfox.documentation.annotations.ApiIgnore
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/task")
class TaskController(
        private val taskService: TaskService,
        private val taskAssembler: TaskAssembler
) {

    private val logger = LoggerFactory.getLogger(TaskController::class.java)

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    fun getTaskById(@PathVariable id: String): ResponseEntity<TaskDTO> {
        logger.info("REST request to get task by id: $id")
        val task = taskService.getTaskById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val taskDTO = taskAssembler.assembleDTO(task)
        return ResponseEntity.ok(taskDTO)
    }

    @PostMapping
    @Transactional
    fun addTask(@RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        logger.info("REST request to add task: $dto")
        if (dto.id != null) throw IllegalArgumentException("Id must be null")
        val task = taskAssembler.assembleDomain(dto)
        val savedTask = taskService.save(task)
        return ResponseEntity.ok(taskAssembler.assembleDTO(savedTask))
    }

    @PutMapping
    @Transactional
    fun updateTask(@RequestBody dto: TaskDTO): ResponseEntity<TaskDTO> {
        logger.info("REST request to updates task: $dto")
        val id = dto.id ?: throw IllegalArgumentException("Id must not be null.")
        val task = taskService.getTaskById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val savedTask = taskService.update(task, dto)
        return ResponseEntity.ok(taskAssembler.assembleDTO(savedTask))
    }

    @PostMapping("/{id}")
    @Transactional
    fun updateTaskStatus(@PathVariable id: String, @RequestParam status: TaskStatus): ResponseEntity<TaskDTO> {
        logger.info("REST request to updates task status: id=$id, status=$status")
        val task = taskService.getTaskById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val savedTask = taskService.updateStatus(task, status)
        return ResponseEntity.ok(taskAssembler.assembleDTO(savedTask))
    }

    @DeleteMapping("/{id}")
    @Transactional
    fun deleteTask(@PathVariable id: String): ResponseEntity<Any> {
        logger.info("REST request to delete task: id=$id")
        val task = taskService.getTaskById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        taskService.delete(task)
        return ResponseEntity(HttpStatus.OK)
    }
}