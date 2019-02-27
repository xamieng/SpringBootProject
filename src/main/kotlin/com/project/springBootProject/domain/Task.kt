package com.project.springBootProject.domain

import com.project.springBootProject.enum.TaskStatus
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "task")
class Task {
    @Id
    var id: String? = null
    @NotNull
    var subject: String? = null
    var detail: String? = null
    var status: TaskStatus? = null
}