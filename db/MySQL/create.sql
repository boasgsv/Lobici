USE Lobici;

CREATE TABLE usuario (
    id INT AUTO_INCREMENT,
    email VARCHAR(100),
    senha VARCHAR(20),
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE cliente (
    usuario_id INT,
    cpf CHAR(11),
    nome VARCHAR(50),
    telefone VARCHAR(20),
    sexo CHAR(1),
    data_nascimento DATE,
    PRIMARY KEY (usuario_id),
    FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    UNIQUE (cpf)
);

CREATE TABLE locadora (
    usuario_id INT,
    cnpj CHAR(14),
    nome VARCHAR(50),
    cidade VARCHAR(20),
    PRIMARY KEY (usuario_id),
    FOREIGN KEY(usuario_id) REFERENCES usuario(id),
    UNIQUE(cnpj)
);

CREATE TABLE locacao (
    id INT AUTO_INCREMENT,
    locadora_id INT,
    cliente_id INT,
    datahora DATETIME,
    PRIMARY KEY(id),
    UNIQUE(locadora_id, datahora),
    UNIQUE(cliente_id,  datahora),
    FOREIGN KEY (locadora_id) REFERENCES locadora(usuario_id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(usuario_id)
);

INSERT INTO usuario (email, senha) VALUES ('leandro', 'leandro');
INSERT INTO usuario (email, senha) VALUES ('gabriele', 'gabriele');
INSERT INTO usuario (email, senha) VALUES ('matheus', 'matheus');
INSERT INTO usuario (email, senha) VALUES ('marco', 'marco');
INSERT INTO usuario (email, senha) VALUES ('delano', 'delano');

INSERT INTO locadora (usuario_id, cnpj, nome, cidade) VALUES (1, '12345678000199', 'Locadora do Leandro', 'Sao Carlos');
INSERT INTO locadora (usuario_id, cnpj, nome, cidade) VALUES (2, '12345678000198', 'Locadora do Gabriele', 'Sao Carlos');

INSERT INTO cliente (usuario_id, cpf, nome, telefone, sexo, data_nascimento) VALUES (3, '12345678900', 'Matheus', '1155555555', 'M', '1998-01-01');
INSERT INTO cliente (usuario_id, cpf, nome, telefone, sexo, data_nascimento) VALUES (4, '98765432100', 'Marco', '1155555555', 'M', '1999-11-25');

INSERT INTO locacao (locadora_id, cliente_id, datahora) VALUES (1, 3, '2023-06-26 13:45:30');
INSERT INTO locacao (locadora_id, cliente_id, datahora) VALUES (2, 4, '2023-07-15 17:30:00');



