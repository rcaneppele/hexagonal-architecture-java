CREATE TABLE cursos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo VARCHAR(50) NOT NULL UNIQUE,
    nome VARCHAR(50) NOT NULL,
    nivel VARCHAR(50) NOT NULL,
    duracao_em_horas BIGINT NOT NULL,

    PRIMARY KEY(id)
);
