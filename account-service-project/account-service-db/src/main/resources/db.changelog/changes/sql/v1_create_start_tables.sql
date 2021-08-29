create table "user"
(
  id bigserial primary key not null,
  name varchar(20),
  username varchar(30) unique,
  password varchar(100),
  roles varchar(1000)
);

create table "role"
(
    id bigserial primary key not null,
    name varchar(20) unique
);

create table "user_roles"
(
    username varchar(30) not null,
    role_name varchar(20) not null,

    foreign key (username) references "user"(username),
    foreign key (role_name) references "role"(name),
    unique (username, role_name)
);