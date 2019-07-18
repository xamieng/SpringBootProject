package com.project.springBootProject.dto

import com.project.springBootProject.enum.UserRole

class UserDTO {
    var id: Int? = null
    var firstName: String? = null
    var lastName: String? = null
    var role: UserRole? = null
    var vacationLeaveQuota: Double? = null
    var sickLeaveQuota: Double? = null
    var personalLeaveQuota: Double? = null
    var supervisorId: Int = 0
}