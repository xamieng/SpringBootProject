package com.project.springBootProject.repository

import com.project.springBootProject.domain.LeaveDetail
import com.project.springBootProject.domain.User
import org.springframework.data.repository.CrudRepository

interface LeaveDetailRepository : CrudRepository<LeaveDetail, Int> {
    fun findOneById(id: Int): LeaveDetail?
    fun findAllById(id: Int): List<LeaveDetail>
//    fun findAllBySupervisorId(id: Int): List<LeaveDetail>
}