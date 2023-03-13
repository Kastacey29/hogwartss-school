package com.school.hogwartssschool.controller;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.hogwartssschool.model.Student;
import com.school.hogwartssschool.repositories.StudentRepository;
import com.school.hogwartssschool.service.StudentService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@WebMvcTest(controllers = StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentService studentService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddStudent() throws Exception {
        final String name = "Ivan";
        final Integer age = 21;
        final Long id = 1L;

        Student student = new Student(id, name, age);

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(studentRepository.findById(eq(id))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));
    }

    @Test
    public void testGetStudent() throws Exception {
        final String name = "Ivan";
        final Integer age = 21;
        final Long id = 1L;

        Student student = new Student(id, name, age);

        when(studentRepository.findById(eq(id))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age));

    }

    @Test
    public void testFindStudentsByAge() throws Exception {
        final String name1 = "Ivan";
        final Long id1 = 1L;

        final Integer age = 18;

        final String name2 = "Maxim";
        final Long id2 = 2L;

        Student student1 = new Student(id1, name1, age);
        Student student2 = new Student(id2, name2, age);

        when(studentRepository.findStudentByAge(eq(age))).thenReturn(List.of(student1, student2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student")
                        .queryParam("age", String.valueOf(age))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(student1, student2))));
    }



    @Test
    public void testUpdateStudent() throws Exception {
        final String oldName = "Ivan";
        final Integer oldAge = 21;
        final Long id = 1L;

        final String newName = "Maxim";
        final Integer newAge = 18;

        Student student = new Student(id, oldName, oldAge);
        Student updateStudent = new Student(id, newName, newAge);

        JSONObject studentObject = new JSONObject();
        studentObject.put("id", id);
        studentObject.put("name", newName);
        studentObject.put("age", newAge);

        when(studentRepository.save(any(Student.class))).thenReturn(updateStudent);
        when(studentRepository.findById(eq(id))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newName))
                .andExpect(jsonPath("$.age").value(newAge));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        final String name = "Ivan";
        final Integer age = 21;
        final Long id = 1L;

        Student student = new Student(id, name, age);

        when(studentRepository.findById(eq(id))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }


}