package com.project.springBootProject.domain

import com.project.springBootProject.enum.UserRole
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name="user")
class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    var id: Int? = null
    var firstName: String? = null
    var lastName: String? = null
    var role: UserRole? = null
    var vacationLeaveQuota: Double? = null
    var sickLeaveQuota: Double? = null
    var personalLeaveQuota: Double? = null

    @ManyToOne
    @JoinColumn(name="supervisorId")
    var supervisorId: User? = null
}