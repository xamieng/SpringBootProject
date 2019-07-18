package com.project.springBootProject.repository

import com.project.springBootProject.domain.LeaveDetail
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.time.LocalDate

interface LeaveDetailRepository : CrudRepository<LeaveDetail, Int> {
    fun findOneById(id: Int): LeaveDetail?
    fun findAllByUserId(userId: Int): List<LeaveDetail>

    fun findAllByUserIdAndEndDateAfter (userId: Int, date: LocalDate): List<LeaveDetail>
//    findAllByIdSystemRewardKeyAndIdRewardCreatedDateAfter

    @Query("select l from LeaveDetail l join l.user u where u.supervisor.id = :supervisorId")
    fun findAllBySupervisorId (@Param("supervisorId") supervisorId: Int): List<LeaveDetail>?
}