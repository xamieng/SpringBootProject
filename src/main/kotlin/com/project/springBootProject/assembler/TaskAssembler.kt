package com.project.springBootProject.assembler

import com.project.springBootProject.domain.Task
import com.project.springBootProject.dto.TaskDTO
import org.springframework.stereotype.Component

@Component
class TaskAssembler {
    fun assembleDTO(task: Task): TaskDTO {
        val dto = TaskDTO()
        dto.id = task.id
        dto.subject = task.subject
        dto.detail = task.detail
        dto.status = task.status
        return dto
    }

    fun assembleDomain(dto: TaskDTO): Task {
        val task = Task()
        task.id = dto.id
        task.subject = dto.subject
        task.detail = dto.detail
        task.status = dto.status
        return task
    }
}