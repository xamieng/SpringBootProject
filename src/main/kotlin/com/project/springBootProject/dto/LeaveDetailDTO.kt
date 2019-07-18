package com.project.springBootProject.dto

import com.project.springBootProject.enum.LeaveStatus
import com.project.springBootProject.enum.LeaveType
import java.time.LocalDate

class LeaveDetailDTO {
    var id: Int? = null
    var startDate: LocalDate? = null
    var endDate: LocalDate? = null
    var leaveType: LeaveType? = null
    var status: LeaveStatus? = null
    var totalCount: Double? = null
    var comment: String? = null
    var supervisorComment: String? = null
    var user: UserDTO? = null
}