create table users
(
    id       bigserial,
    username varchar(30) not null,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    user_id bigint not null,
    role_id int    not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('DELETE_USERS_PERMISSION');

insert into users (username, password, email)
values ('Aleksandr', '$2y$10$5FwMjAm6KkeMroCFzSq1qOa.e2fFbvpvLRQy3EMIJS.eWvlSlthni', 'aleksandr@gmail.com'),
       ('Bob Johnson', '$2y$10$5FwMjAm6KkeMroCFzSq1qOa.e2fFbvpvLRQy3EMIJS.eWvlSlthni', 'bob@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table books
(
    id                  bigserial primary key,
    title               varchar(255),
    genre               varchar(20),
    description         varchar(5000),
    price               numeric(8, 2),
    year_of_publication int
);
insert into books (title, genre, description, price, year_of_publication)
values ('Harry Potter2', 'FANTASY', 'Description1', 217.09, 2000),
       ('Harry Potter3', 'DETECTIVE', 'Description2', 243.33, 2001),
       ('Harry Potter4', 'DETECTIVE', 'Description3', 262.59, 2002),
       ('Harry Potter5', 'DETECTIVE', 'Description4', 313.46, 2003),
       ('Harry Potter6', 'DETECTIVE', 'Description5', 341.15, 2004),
       ('Harry Potter7', 'DETECTIVE', 'Description6', 205.47, 2005),
       ('Harry Potter8', 'DETECTIVE', 'Description7', 235.32, 2006),
       ('Harry Potter9', 'DETECTIVE', 'Description8', 394.84, 2007),
       ('Harry Potter3', 'FANTASY', 'Description2', 243.33, 2001),
       ('Harry Potter4', 'FANTASY', 'Description3', 262.59, 2002),
       ('Harry Potter5', 'FANTASY', 'Description4', 313.46, 2003),
       ('Harry Potter6', 'FANTASY', 'Description5', 341.15, 2004),
       ('Harry Potter7', 'FANTASY', 'Description6', 205.47, 2005),
       ('Harry Potter8', 'FANTASY', 'Description7', 235.32, 2006),
       ('Harry Potter9', 'FANTASY', 'Description8', 394.84, 2007),
       ('Game of Thrones1', 'FICTION', 'Description9', 660.82, 2005),
       ('Game of Thrones2', 'FICTION', 'Description10', 657.28, 2006),
       ('Game of Thrones3', 'FICTION', 'Description11', 624.02, 2007),
       ('Game of Thrones4', 'FICTION', 'Description12', 577.65, 2008),
       ('Game of Thrones5', 'FICTION', 'Description13', 591.54, 2009),
       ('Game of Thrones6', 'FICTION', 'Description14', 661.41, 2010),
       ('Game of Thrones7', 'FICTION', 'Description15', 510.65, 2011),
       ('Game of Thrones8', 'FICTION', 'Description16', 506.11, 2012),
       ('Game of Thrones9', 'FICTION', 'Description17', 643.86, 2013),
       ('Harry Potter3', 'DETECTIVE', 'Description2', 243.33, 2001),
       ('Harry Potter4', 'DETECTIVE', 'Description3', 262.59, 2002),
       ('Harry Potter5', 'DETECTIVE', 'Description4', 313.46, 2003),
       ('Harry Potter6', 'DETECTIVE', 'Description5', 341.15, 2004),
       ('Harry Potter7', 'DETECTIVE', 'Description6', 205.47, 2005),
       ('Harry Potter8', 'DETECTIVE', 'Description7', 235.32, 2006),
       ('Harry Potter9', 'DETECTIVE', 'Description8', 394.84, 2007),
       ('Hobbit', 'FANTASY', 'Description18', 628.79, 2013);