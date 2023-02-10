--liquibase formatted sql


-- changeset kstacey:1
create index index_student_name on student (name);

-- changeset kstacey:2
create index index_faculty_name_color on faculty (color, name);