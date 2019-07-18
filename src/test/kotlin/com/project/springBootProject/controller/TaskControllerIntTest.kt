package com.project.springBootProject.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.project.springBootProject.SpringBootProjectApplication
import com.project.springBootProject.dto.TaskDTO
import com.project.springBootProject.enum.LeaveStatus
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*
import javax.inject.Inject

@RunWith(SpringRunner::class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = [SpringBootProjectApplication::class])
@AutoConfigureMockMvc
class TaskControllerIntTest {

    @Inject private lateinit var mvc: MockMvc

    companion object {
        private val mapper = ObjectMapper()
    }

    @Test
    fun testGetTask() {

        //create task
        val dto = createDTO()
        val mvcCreatedResult = mvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val createdResult = mapper.readValue(mvcCreatedResult.response.contentAsString, TaskDTO::class.java)

        //get task
        val mvcGetResult = mvc.perform(get("/task/${createdResult.id}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val getResult = mapper.readValue(mvcGetResult.response.contentAsString, TaskDTO::class.java)

        // assert
        assert(getResult.id == createdResult.id)
        assert(getResult.subject == createdResult.subject)
        assert(getResult.detail == createdResult.detail)
        assert(getResult.status == createdResult.status)
    }


    @Test
    fun testGetAllTask() {
        val dtoList = listOf(createDTO(), createDTO())

        //create task
        dtoList.forEach {
            mvc.perform(post("/task")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(mapper.writeValueAsBytes(it)))
                    .andDo(print())
                    .andExpect(MockMvcResultMatchers.status().isOk)
                    .andReturn()
        }

        //get task
        val mvcGetResult = mvc.perform(get("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val getResultList = mapper.readValue(mvcGetResult.response.contentAsString, List::class.java)

        // assert
        assert(getResultList.size >= dtoList.size)
    }

    @Test
    fun testAddTask() {
        // create
        val dto = createDTO()
        val mvcResult = mvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val result = mapper.readValue(mvcResult.response.contentAsString, TaskDTO::class.java)

        // assert
        assert(result.id != null)
        assert(result.subject != null)
        assert(result.detail != null)
        assert(result.status != null)
    }

    @Test
    fun testUpdateTask() {

        // create task
        val dto = createDTO()
        val mvcCreatedResult = mvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val createdResult = mapper.readValue(mvcCreatedResult.response.contentAsString, TaskDTO::class.java)

        // update task
        val updatedSubject = "updatedSubject"
        createdResult.subject = updatedSubject

        val mvcUpdateResult = mvc.perform(put("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(createdResult)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val updatedResult = mapper.readValue(mvcUpdateResult.response.contentAsString, TaskDTO::class.java)

        // assert
        assert(updatedResult.id == createdResult.id)
        assert(updatedResult.subject == updatedSubject)
        assert(updatedResult.detail == createdResult.detail)
        assert(updatedResult.status == createdResult.status)
    }

    @Test
    fun testUpdateTaskStatus() {
        val dto = createDTO()

        // create task
        val mvcCreatedResult = mvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val createdResult = mapper.readValue(mvcCreatedResult.response.contentAsString, TaskDTO::class.java)

        // update task status
        val updatedStatus = LeaveStatus.DONE
        createdResult.status = updatedStatus

        val mvcUpdateResult = mvc.perform(post("/task/${createdResult.id}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("status", updatedStatus.toString()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val updatedResult = mapper.readValue(mvcUpdateResult.response.contentAsString, TaskDTO::class.java)

        assert(updatedResult.id == createdResult.id)
        assert(updatedResult.subject == createdResult.subject)
        assert(updatedResult.detail == createdResult.detail)
        assert(updatedResult.status == updatedStatus)
    }

    @Test
    fun testDeleteTask() {
        val dto = createDTO()

        // create task
        val mvcCreatedResult = mvc.perform(post("/task")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsBytes(dto)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        val createdResult = mapper.readValue(mvcCreatedResult.response.contentAsString, TaskDTO::class.java)

        // delete task
        mvc.perform(delete("/task/${createdResult.id}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()

        // get task
        mvc.perform(get("/task/${createdResult.id}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
                .andReturn()
    }

    private fun createDTO(): TaskDTO {
        val dto = TaskDTO()
        dto.subject = "subject" + UUID.randomUUID()
        dto.detail = "detail" + UUID.randomUUID()
        dto.status = LeaveStatus.PENDING
        return dto
    }
}