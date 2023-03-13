ALTER TABLE student
    ADD CONSTRAINT age_limit CHECK (age >= 16);

ALTER TABLE student
    ALTER COLUMN name SET NOT NULL;

ALTER TABLE student
    ADD CONSTRAINT name_unique unique (name);

alter table faculty
    add constraint color_name unique (color,name);

alter table student
    alter column age set default 20;