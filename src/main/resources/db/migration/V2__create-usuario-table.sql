CREATE TABLE usuario (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null,
    senha varchar(100) not null,
    role varchar(5) not null,
    foto_url varchar(200),
    organizacao_id bigint,

    primary key(id),
    foreign key (organizacao_id) references organizacao(id) on delete cascade
)