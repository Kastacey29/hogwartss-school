package com.school.hogwartssschool.service;


import com.school.hogwartssschool.exception.MyException;
import com.school.hogwartssschool.model.Faculty;
import com.school.hogwartssschool.model.Student;
import com.school.hogwartssschool.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    public Student createStudent(Student student) {
        logger.info("Вызван метод по созданию студента");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentID) {
        logger.info("Вызван метод поиска студента по id");
        Student student = studentRepository.findById(studentID).get();
        if (student == null) {
            logger.error("Не найден студент с id {}"+studentID);
            throw new MyException("Не найден студент с id "+studentID);
        }
        logger.debug("По id найден студент: {}", student);
        return student;
    }

    public Student updateStudent(Long id, Student student) {
        logger.info("Вызван метод по изменению студента");
        return studentRepository.save(student);
    }

    public void deleteStudentById(Long studentId) {
        logger.info("Вызван метод удаления студента по id");
        Student student = studentRepository.findById(studentId).get();
        studentRepository.deleteById(studentId);
        logger.debug("По id удален студент: {}", student);
    }

    public Collection<Student> getStudentsByAge(Integer studentAge) {
        logger.info("Вызван метод поиска студентов по возрасту {}",studentAge);
        Collection<Student> students = studentRepository.findStudentByAge(studentAge);
        logger.debug("Найдены студенты с возрастом {}: {}",studentAge, students);
        return students;
    }

    public Collection<Student> findStudentsByAgeBetween(Integer minAge, Integer maxAge) {
        logger.info("Вызван метод поиска студентов по возрасту {} - {}", minAge,maxAge);
        Collection<Student> students = studentRepository.findStudentsByAgeBetween(minAge, maxAge);
        logger.debug("Найдены студенты с возрастом {} - {}: {}",minAge, maxAge, students);
        return students;
    }

    public Collection<Student> findStudentsByFacultyId(Long id) {
        logger.info("Вызван метод поиска студентов факультета {}", id);
        Collection<Student> students = studentRepository.findStudentsByFacultyId(id);
        logger.debug("Найдены студенты факультета {}: {}",id, students);
        return students;
    }

    public Faculty getFacultyByStudentId(Long id) {
        Student student = studentRepository.findById(id).get();
        logger.info("Вызван метод получения факультета студента {}", student);
        Faculty faculty = getStudentById(id).getFaculty();
        logger.debug("Найден факультет студента: {}",faculty);
        return faculty;
    }

    public Integer findCountOfStudent() {
        logger.info("Вызван метод получения количества студентов");
        Integer count = studentRepository.findCountOfStudent();
        logger.debug("Количество студентов: {}",count);
        return count;
    }

    public Double findAverageAge() {
        logger.info("Вызван метод получения среднего возраста студентов");
        Double avgAge= studentRepository.findAverageAge();
        logger.debug("Средний возраст студентов: {}",avgAge);
        return avgAge;
    }

    public Collection<Student> findFiveLastStudents() {
        logger.info("Вызван метод получения последних пяти студентов");
        Collection<Student> students = studentRepository.findFiveLastStudents();
        logger.debug("Найдены последние пять студентов");
        return students;
    }
}

