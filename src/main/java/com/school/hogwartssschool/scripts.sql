select * from student where age<=20 and age>=11;

select name from student;

select * from student
where name like '%G%';

select * from student
where age<student.id;

select * from student
order by age;

select * from student,faculty
where student.faculty_id = faculty.id;

select count(*) from student;

select avg(age) from student;
select * from student order by id desc limit 5;

select *from avatar;