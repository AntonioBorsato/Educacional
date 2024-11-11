create table alunos (
    id int not null primary key auto_increment,
    nome varchar(200),
    email varchar(500) unique,
    matricula varchar(20) unique,
    data_nascimento date
);