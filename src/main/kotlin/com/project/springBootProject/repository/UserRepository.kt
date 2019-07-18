package com.project.springBootProject.repository

import com.project.springBootProject.domain.User
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Int> {
    fun findOneById(id: Int): User?

    @Modifying
    @Query("update User set vacationLeaveQuota = vacationLeaveQuota - 1")
    fun updateVacationQuota()

    @Modifying
    @Query("update User set sickLeaveQuota = sickLeaveQuota - 1")
    fun updateSickQuota()

    @Modifying
    @Query("update User set personalLeaveQuota = personalLeaveQuota - 1")
    fun updatePersonalQuota()
}