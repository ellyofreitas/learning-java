create table if not exists users (
    id char (36) not null primary key,
    name varchar(100) not null,
    email varchar(100) not null
);