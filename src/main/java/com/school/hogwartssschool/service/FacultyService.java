package com.school.hogwartssschool.service;


import com.school.hogwartssschool.model.Faculty;
import com.school.hogwartssschool.repositories.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    public Faculty createFaculty(Faculty faculty) {
        logger.info("Вызван метод по созданию факультета");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        logger.info("Вызван метод поиска факультета по id {}",facultyId);
        Faculty faculty = facultyRepository.findById(facultyId).get();
        logger.debug("По id найден факультет: {}", faculty);
        return faculty;
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        logger.info("Вызван метод по изменению факультета");
        return facultyRepository.save(faculty);
    }

    public void deleteFacultyById(Long facultyId) {
        logger.info("Вызван метод удаления факультета по id {}", facultyId);
        Faculty faculty = facultyRepository.findById(facultyId).get();
        logger.debug("По id удален студент: {}", faculty);
        facultyRepository.deleteById(facultyId);
    }

    public Collection<Faculty> findFacultiesByColorIgnoreCase(String facultyColor) {
        logger.info("Вызван метод получения факультетов по цвету {}", facultyColor);
        Collection<Faculty> faculties = facultyRepository.findFacultiesByColorIgnoreCase(facultyColor);
        logger.debug("Найдены факультеты с цветом {}",facultyColor);
        return faculties;
    }
    public Collection<Faculty> findByNameIgnoreCase(String name) {
        logger.info("Вызван метод получения факультетов по имени {}", name);
        Collection<Faculty> faculties = facultyRepository.findByNameIgnoreCase(name);
        logger.debug("Найдены факультеты с именем {}",name);
        return faculties;
    }
//    public Faculty findFacultyByStudentId(Long id) {
//
//return studentService.getStudentById(id).getFaculty();
//
//    }

}


