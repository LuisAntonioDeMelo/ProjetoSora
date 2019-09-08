CREATE TABLE sora_editora (
    sora_id_editora serial not null,
    sora_nome_editora character varying,
    sora_autor character varying,
    CONSTRAINT PK_sora_editora_id PRIMARY KEY (sora_id_editora)
);


CREATE TABLE sora_livro (
    sora_id_livro serial not null,
    sora_nome character varying,
    sora_assunto character varying,
    sora_isb character varying,
    sora_nota character varying,
    sora_data_cadastro date,
    sora_imagem character varying,
    sora_id_editora integer,
    CONSTRAINT PK_sora_livro_id  PRIMARY KEY (sora_id_livro),
    CONSTRAINT FK_sora_livro_id_editora FOREIGN KEY (sora_id_editora) REFERENCES sora_editora (sora_id_editora)
);

CREATE TABLE sora_permissao (
    codigo serial not null,
    descricao character varying,
    primary key (codigo)
);

CREATE TABLE sora_usuario (
    codigo serial not null,
	nome character varying,
	email character varying,
	senha character varying,
    primary key (codigo)
);

CREATE TABLE usuario_permissao (
    codigo_usuario integer,
    codigo_permissao integer,
    FOREIGN KEY (codigo_usuario) REFERENCES sora_usuario (codigo),
    FOREIGN KEY (codigo_permissao) REFERENCES sora_permissao (codigo),
    PRIMARY KEY (codigo_usuario,codigo_permissao)
);

drop table sora_permissao;
drop table sora_usuario;

insert into sora_usuario (,'Luis','tyluis20@gmail.com','$2a$10$e/beIq5135eUHt56MaF9buj6qc7CpRSWCsfCSa4KZfqz4R9kq.6g2');

select * from sora_livro sl
inner join sora_editora se on se.sora_id_editora = sl.sora_id_editora