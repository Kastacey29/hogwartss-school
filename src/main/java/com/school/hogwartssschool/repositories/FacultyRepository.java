package com.school.hogwartssschool.repositories;

import com.school.hogwartssschool.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    List<Faculty> findFacultiesByColorIgnoreCase(String color);
    List<Faculty> findByNameIgnoreCase(String name);

}
