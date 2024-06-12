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
create table cart
(
    id          bigint generated by default as identity primary key,
    user_id     bigint not null references users (id),
    book_id     bigint not null references books (id),
    count_books int default 1 check (count_books > 0),
    unique (user_id, book_id)
);
create table orders (
                        id bigint generated by default as identity primary key,
                        user_id     bigint not null references users (id),
                        book_id     bigint not null references books (id),
                        count_books int default 1 check (count_books > 0),
                        total_price numeric(8,2) not null
);
insert into books (title, genre, description, price, year_of_publication)
values
    ('Harry Potter and the Philosopher`s Stone', 'FANTASY', 'Description1', 217.09, 2000),
    ('Sherlock Holmes: A Study in Scarlet', 'DETECTIVE', 'Description2', 243.33, 2001),
    ('The Da Vinci Code', 'DETECTIVE', 'Description3', 262.59, 2002),
    ('Gone Girl', 'DETECTIVE', 'Description4', 313.46, 2003),
    ('The Girl with the Dragon Tattoo', 'DETECTIVE', 'Description5', 341.15, 2004),
    ('The Adventures of Sherlock Holmes', 'DETECTIVE', 'Description6', 205.47, 2005),
    ('Big Little Lies', 'DETECTIVE', 'Description7', 235.32, 2006),
    ('The Hound of the Baskervilles', 'DETECTIVE', 'Description8', 394.84, 2007),
    ('Harry Potter and the Chamber of Secrets', 'FANTASY', 'Description2', 243.33, 2001),
    ('Harry Potter and the Prisoner of Azkaban', 'FANTASY', 'Description3', 262.59, 2002),
    ('Harry Potter and the Goblet of Fire', 'FANTASY', 'Description4', 313.46, 2003),
    ('Harry Potter and the Order of the Phoenix', 'FANTASY', 'Description5', 341.15, 2004),
    ('Harry Potter and the Half-Blood Prince', 'FANTASY', 'Description6', 205.47, 2005),
    ('Harry Potter and the Deathly Hallows', 'FANTASY', 'Description7', 235.32, 2006),
    ('The Hobbit', 'FANTASY', 'Description8', 394.84, 2007),
    ('A Game of Thrones', 'FICTION', 'Description9', 660.82, 2005),
    ('A Clash of Kings', 'FICTION', 'Description10', 657.28, 2006),
    ('A Storm of Swords', 'FICTION', 'Description11', 624.02, 2007),
    ('A Feast for Crows', 'FICTION', 'Description12', 577.65, 2008),
    ('A Dance with Dragons', 'FICTION', 'Description13', 591.54, 2009),
    ('The Winds of Winter', 'FICTION', 'Description14', 661.41, 2010),
    ('A Dream of Spring', 'FICTION', 'Description15', 510.65, 2011),
    ('Fire & Blood', 'FICTION', 'Description16', 506.11, 2012),
    ('Blood & Fire', 'FICTION', 'Description17', 643.86, 2013),
    ('The Silmarillion', 'FANTASY', 'Description18', 628.79, 2013);

