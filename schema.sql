create table dreamjob_user
(
    id       serial not null
        constraint user_pkey primary key,
    name     varchar(50),
    email    varchar(50),
    password varchar(50)
);

create table candidate
(
    id   serial not null
        constraint candidate_pkey primary key,
    name text,
    city_id      integer   constraint candidate_city_id_fk  references city
);

create table post
(
    id   serial not null
        constraint post_pkey primary key,
    name text
);

create table city
(
    id   serial not null
        constraint city_pkey primary key,
    name text
);