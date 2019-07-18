package com.project.springBootProject.repository

import com.project.springBootProject.domain.User
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface UserRepository : CrudRepository<User, Int> {
    fun findOneById(id: Int): User?
    fun findOneByFirstName(firstName: String): User?

    @Modifying
    @Query("update User set vacationLeaveQuota = vacationLeaveQuota - :totalLeave where id = :userId")
    fun updateVacationQuota(@Param("userId") userId: Int, @Param("totalLeave") totalLeave: Double)

    @Modifying
    @Query("update User set sickLeaveQuota = sickLeaveQuota - :totalLeave where id = :userId")
    fun updateSickQuota(@Param("userId") userId: Int, @Param("totalLeave") totalLeave: Double)

    @Modifying
    @Query("update User set personalLeaveQuota = personalLeaveQuota - :totalLeave where id = :userId")
    fun updatePersonalQuota(@Param("userId") userId: Int, @Param("totalLeave") totalLeave: Double)
}