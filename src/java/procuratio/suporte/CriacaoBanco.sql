CREATE TABLE endereco (
	id int4 NOT NULL,
	cep varchar(8) NOT NULL,
	endereco varchar(200) NOT NULL,
	bairro varchar(50) NOT NULL,
	numero varchar(6) NOT NULL,
	CONSTRAINT pk_endereco PRIMARY KEY (id)
);

CREATE TABLE pessoa (
	id int4 NOT NULL,
	endereco_id int4 NOT NULL,
        nome varchar(45) NOT NULL,
	cpf varchar(11) NOT NULL,
	genero varchar(200) NOT NULL,
	tipo boolean NOT NULL,
        email varchar(200),
        senha varchar(200),
	CONSTRAINT pk_pessoa PRIMARY KEY (id),
	CONSTRAINT uq_pessoa_cpf UNIQUE (cpf),
	CONSTRAINT fk_endereco_pessoa FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);