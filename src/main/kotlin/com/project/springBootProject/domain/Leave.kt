package com.project.springBootProject.domain

import com.project.springBootProject.enum.LeaveStatus
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name="leaveDetail")
class LeaveDetail {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Int? = null
    var startDate: LocalDate? = null
    var endDate: LocalDate? = null
    var status: LeaveStatus? = null
    var totalCount: Double? = null

    @ManyToOne
    @JoinColumn(name="user_id")
    var user: User? = null
}