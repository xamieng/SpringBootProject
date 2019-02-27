package com.project.springBootProject.service

import com.project.springBootProject.domain.Task
import com.project.springBootProject.dto.TaskDTO
import com.project.springBootProject.enum.TaskStatus
import com.project.springBootProject.repository.TaskRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TaskService (
        private val taskRepository: TaskRepository
) {

    private val logger = LoggerFactory.getLogger(TaskService::class.java)

    fun getAllTask(pageable: Pageable): Page<Task> {
        logger.debug("getAllTask: $pageable")
        return taskRepository.findAll(pageable)
    }

    fun getTaskById(id: String): Task? {
        logger.debug("getTaskById: $id")
        return taskRepository.findOneById(id)
    }

    fun save(task: Task): Task {
        logger.debug("save: $task")
        return taskRepository.save(task)
    }

    fun update(task: Task, dto: TaskDTO): Task {
        logger.debug("update: $task")
        task.subject = dto.subject
        task.detail = dto.detail
        task.status = dto.status
        return taskRepository.save(task)
    }

    fun updateStatus(task: Task, status: TaskStatus): Task {
        logger.debug("updateStatus: $task")
        task.status = status
        return taskRepository.save(task)
    }

    fun delete(task: Task) {
        logger.debug("delete: $task")
        return taskRepository.delete(task)
    }

}
