create table disciplinas (
    id int not null primary key auto_increment,
    nome varchar(100),
    codigo varchar(20) unique
);