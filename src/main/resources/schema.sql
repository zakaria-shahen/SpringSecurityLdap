drop table if exists users, authorities;


create table users(
    username varchar(100) not null,
    password varchar(100) not null,
    enabled boolean not null,
    primary key (username)
);

create table authorities(
    username varchar(100) not null,
    authority varchar(100) not null default 'USER',
    primary key (authority),
    foreign key (username) references users(username),
    unique(username, authority)
);
