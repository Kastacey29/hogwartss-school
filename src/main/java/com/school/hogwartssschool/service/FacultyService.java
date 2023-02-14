package com.school.hogwartssschool.service;


import com.school.hogwartssschool.model.Faculty;
import com.school.hogwartssschool.repositories.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;

    }

    private Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Вызван метод по созданию факультета");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        logger.info("Вызван метод поиска факультета по id {}", facultyId);
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
        logger.debug("Найдены факультеты с цветом {}", facultyColor);
        return faculties;
    }

    public Collection<Faculty> findByNameIgnoreCase(String name) {
        logger.info("Вызван метод получения факультетов по имени {}", name);
        Collection<Faculty> faculties = facultyRepository.findByNameIgnoreCase(name);
        logger.debug("Найдены факультеты с именем {}", name);
        return faculties;
    }

    public Collection<String> getLongestFacultyName() {
        logger.info("Вызван метод получения самого длинного названия факультета");
        Collection<Faculty> faculties = facultyRepository.findAll();
        int maxLength = faculties.stream().mapToInt(e -> e.getName().length()).max().orElse(0);
        List<String> result = faculties.stream().filter(s -> s.getName().length() == maxLength).map(Faculty::getName)
                .collect(Collectors.toList());
        logger.debug("Найден факультет с самым длинным названием: {}", result);
        return result;
    }

    public int getInt() {
        long start = System.currentTimeMillis();
//        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).reduce(0, (a, b) -> a + b);
//        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).mapToInt(Integer::intValue).sum();
//        int sum = Stream.iterate(1, a -> a + 1).parallel().limit(1_000_000).reduce(0, (a, b) -> a + b);
//        int sum = Stream.iterate(1, a -> a + 1).parallel().limit(1_000_000).mapToInt(Integer::intValue).sum();
//        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).reduce(0, Integer::sum);
//        int sum = Stream.iterate(1, a -> a + 1).parallel().limit(1_000_000).collect(Collectors.summingInt(Integer::intValue));
//        int sum = Stream.iterate(1, a -> a + 1).limit(1_000_000).collect(Collectors.summingInt(Integer::intValue));
        int sum = 0;
        for (int i = 1; i <= 1000_000; i++) {
            sum+=i;
        }
        long spentTime = System.currentTimeMillis() - start;
        logger.debug("timeRequest: " + spentTime + "ms");
        return sum;
    }
//    public Faculty findFacultyByStudentId(Long id) {
//
//return studentService.getStudentById(id).getFaculty();
//
//    }

}


