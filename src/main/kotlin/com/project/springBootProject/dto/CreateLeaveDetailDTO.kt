package com.project.springBootProject.dto

import java.time.LocalDate

class CreateLeaveDetailDTO {
    var startDate: LocalDate? = null
    var endDate: LocalDate? = null
    var totalCount: Double? = null
    var comment: String? = null
    var userId: Int? = null
}