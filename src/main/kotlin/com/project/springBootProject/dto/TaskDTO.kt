package com.project.springBootProject.dto

import com.project.springBootProject.enum.LeaveStatus
import org.jetbrains.annotations.NotNull

class TaskDTO {
    var id: Int? = null
    @NotNull
    var subject: String? = null
    var detail: String? = null
    var status: LeaveStatus? = null
}