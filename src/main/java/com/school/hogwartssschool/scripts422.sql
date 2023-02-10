create table cars
(
    id serial,
    brand text not null,
    model text not null,
    driver_id integer references drivers (id)
);

create table drivers
(
    id serial unique ,
    name text not null,
    age integer check ( age>0 ) not null,
    have_rights boolean not null
);

