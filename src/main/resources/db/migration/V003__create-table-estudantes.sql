CREATE TABLE estudantes(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    data_de_nascimento DATE NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    telefone_ddd CHAR(2) NOT NULL,
    telefone_numero VARCHAR(20) NOT NULL,
    telefone_whatsapp BOOLEAN NOT NULL,

    PRIMARY KEY(id)
);
