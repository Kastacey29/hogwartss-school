select student.name, student.age, f.name
from student
         inner join faculty f on student.faculty_id = f.id;

select student.name, student.age, a.data from student
right join avatar a on student.id = a.student_id;