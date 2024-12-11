CREATE TABLE organizacao (
    id bigint not null auto_increment,
    nome varchar(100) not null,
    url_imagem varchar(200),
    ativo tinyint not null,

    primary key(id)
)