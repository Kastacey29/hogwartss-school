package com.school.hogwartssschool.service;


import com.school.hogwartssschool.model.Faculty;
import com.school.hogwartssschool.repositories.FacultyRepository;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;

    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).get();
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFacultyById(Long facultyId) {
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> findFacultiesByColorIgnoreCase(String facultyColor) {
        return facultyRepository.findFacultiesByColorIgnoreCase(facultyColor);
    }
    public Collection<Faculty> findByNameIgnoreCase(String name) {
        return facultyRepository.findByNameIgnoreCase(name);
    }
//    public Faculty findFacultyByStudentId(Long id) {
//
//return studentService.getStudentById(id).getFaculty();
//
//    }

}


