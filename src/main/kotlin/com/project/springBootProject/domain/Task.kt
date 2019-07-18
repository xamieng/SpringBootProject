package com.project.springBootProject.domain

import com.project.springBootProject.enum.TaskStatus
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

@Entity
class Task {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Int? = null
    @NotNull
    var subject: String? = null
    var detail: String? = null
    var status: TaskStatus? = null
}