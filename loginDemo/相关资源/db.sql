drop table if exists t_user ;
create table t_user (
    id int primary key auto_increment ,
    username varchar(20) not null unique,
    passwd varchar(20) not null
)charset utf8;

insert into t_user(username,passwd) values("root","toor");
insert into t_user(username,passwd) values("casper","qwerty");