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
import com.school.hogwartssschool.model.Faculty;
import com.school.hogwartssschool.repositories.FacultyRepository;
import com.school.hogwartssschool.service.FacultyService;
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


@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddFaculty() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final Long id = 1L;

        Faculty faculty = new Faculty(id, name, color);

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }

    @Test
    public void testGetFaculty() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final Long id = 1L;

        Faculty faculty = new Faculty(id, name, color);


        when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

    }

    @Test
    public void testFindFacultiesByColor() throws Exception {
        final String name1 = "Griffindor";
        final Long id1 = 1L;

        final String color = "red";

        final String name2 = "Ravenclaw";
        final Long id2 = 2L;
        Faculty faculty1 = new Faculty(id1, name1, color);
        Faculty faculty2 = new Faculty(id2, name2, color);

        when(facultyRepository.findFacultiesByColorIgnoreCase(eq(color))).thenReturn(List.of(faculty1, faculty2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("color", color)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1, faculty2))));
    }

    @Test
    public void testFindFacultiesIgnoreCase() throws Exception {
        final String name1 = "Griffindor";
        final String nameIncorrect = "GriffINdor";
        final Long id1 = 1L;
        final String color1 = "red";

        final String name2 = "Slitherin";
        final Long id2 = 2L;
        final String color2 = "green";
        final String colorIncorrect = "grEEn";
        Faculty faculty1 = new Faculty(id1, name1, color1);
        Faculty faculty2 = new Faculty(id2, name2, color2);

        when(facultyRepository.findFacultiesByColorIgnoreCase(eq(colorIncorrect))).thenReturn(List.of(faculty2));
        when(facultyRepository.findByNameIgnoreCase(eq(nameIncorrect))).thenReturn(List.of(faculty1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("color", colorIncorrect)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty2))));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty")
                        .queryParam("name", nameIncorrect)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(faculty1))));
    }

    @Test
    public void testUpdateFaculty() throws Exception {
        final String oldName = "Griffindor";
        final String oldColor = "red";
        final Long id = 1L;

        final String newName = "Ravenclaw";
        final String newColor = "blue";
        Faculty faculty = new Faculty(id, oldName, oldColor);
        Faculty updateFaculty = new Faculty(id, newName, newColor);


        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", id);
        facultyObject.put("name", newName);
        facultyObject.put("color", newColor);

        when(facultyRepository.save(any(Faculty.class))).thenReturn(updateFaculty);
        when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(newName))
                .andExpect(jsonPath("$.color").value(newColor));
    }

    @Test
    public void testDeleteFaculty() throws Exception {
        final String name = "Griffindor";
        final String color = "red";
        final Long id = 1L;

        Faculty faculty = new Faculty(id, name, color);

        when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}