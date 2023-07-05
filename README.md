# Lobici: Sistema Web para Locação de Bicicletas

## Pré-Requisitos
Java Develoment Kit (JDK) versão 8 ou superior.
Maven versão 3.8 ou superior.
Cliente `docker-compose` (versão 3 ou superior) e daemon `dockerd`. Geralmente a instalação do cliente e do daemon é feita em conjunto via Docker Engine, Docker Desktop, ou ainda pacote Docker, a depender da plataforma.

## Configurando o Ambiente

### Linux
Assegure-se que seu usuário está configurado para utilizar o docker

    cat /etc/group | grep docker

Caso o grupo `docker` não exista (caso não haja output), crie-o

    sudo groupadd docker

Depois (ou caso o grupo já existisse, mas o seu usuário não estivesse listado dentre os usuários do grupo), adicione o usuário atual ao grupo

    sudo usermod -aG docker $USER

Em seguida, para consolidar as mudanças altere o id de grupo da sessão atual (isto evita necessidade de logout e login para que as mudanças tomem efeito)

    newgrp docker

Agora você deve poder rodar comandos do cliente `docker` sem permissão de root. 

    docker-compose --version

Se você conseguir rodar este comando sem problemas, está tudo pronto da parte do cliente.

Resta inicializar o daemon `dockerd` que ouvirá as chamadas de API do cliente. Essa inicialização pode variar distribuição à distribuição, mas a grande maioria das distribuições pode utilizar o comando `systemctl`, que serve de frontend para a suite `systemd`, comumente encontrada para gerenciamento de daemons em ambientes Linux.

    sudo systemctl start docker.socket // inicializa o socket responsável pelo daemon dockerd imediatamente
    sudo systemctl enable docker.socket // garante que o socket será inicializado automaticamente durante o boot

Isso deve bastar para que o cliente `docker` faça requisições ao daemon `dockerd`. Teste subindo os containers (rode o seguinte comando em uma pasta contendo o arquivo [docker-compose.yml](docker-compose.yml), como por exemplo a root deste repositório).

    docker-compose up

Se tudo der certo, a instanciação dos containers descritos em [docker-compose.yml](docker-compose.yml) deve ser observada na linha de comando. Observe que o processo/terminal onde foi executado o último comando ficará inutilizado, pois estará hospedando os serviços instanciados (derrubar o processo significa também derrubar os serviços).


### Windows
Após baixar e instalar o Docker Desktop, é necessário rodar o Docker Desktop para inicializar o deamon do Docker, permitindo a criação dos containeres no modo usual. Uma vez inicializado, basta rodar

    docker-compose up

Em um terminal que esteja no diretório onde está o arquivo [docker-compose.yml](docker-compose.yml).

### Observação
Durante a instanciação dos containers podem haver problemas com as portas selecionadas para uso. Possivelmente, serviços no anfitrião (no seu sistema operacional) já estarão usando as portas designadas aos containers. Será necessário encontrar e derrubar esses serviços.

- Windows: via gerenciador de tarefas.
- Linux via `sudo systemctl stop nome_servico` (sugere-se inclusive `sudo systemctl disable nome_servico` para impedir que retornem à cada reinicialização do sistema, incorrendo no conflito de portas novamente).

## Desenvolvimento

Para começar o desenvolvimento, clone este repositório para sua máquina local via `git`

    git clone https://github.com:boasgsv/Lobici

Isto é importante porque a classe [GenericDAO](src/main/java/br/ufscar/dc/dsw/dao/GenericDAO.java) utiliza o nome do container como url para se conectar ao banco de dados.

## Roteiro de Execução

O setup supoe a execução de um container docker com a instância do mysql [como aqui disponibilizado](docker-compose.yml) de modo que a instância do container de banco de dados MySQL tenha o nome de lobici-db-1. Para isto, o mais simples é

clonar o repositório

     git clone https://github.com:boasgsv/Lobici

 e executar docker-compose up

     docker-compose up

     
### SQL Scripts
Para esse projeto, utilizamos o banco de dados MySQL. Os usuários que foram criados e populados são: <br />
~ Cliente (Capaz de logar no sistema e realizar uma locação de bicicleta em alguma das locadoras desejadas, pode também fazer uma consulta de todas as locações ja realizadas por esse cliente) <br />

~ Locadora (Capaz de logar no sistema e disponibilizar uma bicicleta para o cliente fazer uma locação, pode também fazer uma consulta de todas as locações ja realizadas por essa locadora) <br />

~ Administrador (Caso seja usuário mas não seja cliente nem locadora, esse usuário será administrador, capaz de acessar o CRUD de locadora e cliente, capaz de listar todos os clientes e locadoras cadastrados no banco) <br />

Os comandos utilizados para criação do banco foram os seguintes: <br />

USE Lobici; <br />

CREATE TABLE usuario ( <br />
    id INT AUTO_INCREMENT,<br />
    email VARCHAR(100),<br />
    senha VARCHAR(20),<br />
    PRIMARY KEY (id)<br />
);<br />

CREATE TABLE cliente (<br />
    usuario_id INT,<br />
    cpf CHAR(11),<br />
    nome VARCHAR(50),<br />
    telefone VARCHAR(20),<br />
    sexo CHAR(1),<br />
    data_nascimento DATE,<br />
    PRIMARY KEY (usuario_id),<br />
    FOREIGN KEY(usuario_id) REFERENCES usuario(id),<br />
    UNIQUE (cpf)<br />
);<br />

CREATE TABLE locadora (<br />
    usuario_id INT,<br />
    cnpj CHAR(14),<br />
    nome VARCHAR(50),<br />
    cidade VARCHAR(20),<br />
    PRIMARY KEY (usuario_id),<br />
    FOREIGN KEY(usuario_id) REFERENCES usuario(id),<br />
    UNIQUE(cnpj)<br />
);<br />

CREATE TABLE locacao (<br />
    id INT AUTO_INCREMENT,<br />
    locadora_id INT,<br />
    cliente_id INT,<br />
    datahora DATETIME,<br />
    PRIMARY KEY(id),<br />
    UNIQUE(locadora_id, datahora),<br />
    UNIQUE(cliente_id,  datahora),<br />
    FOREIGN KEY (locadora_id) REFERENCES locadora(usuario_id),<br />
    FOREIGN KEY (cliente_id) REFERENCES cliente(usuario_id)<br />
);<br />

## Checklist e divisão de funcionalidades

R1: <br />
    (X) Implementado ( ) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Matheus (Controller, DAO e Views), Marcos e Gabriele (Login), Gabriele (Autorização e Integração) <br />

R2: <br />
    (X) Implementado ( ) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Leandro (Controller, DAO e Views), Marcos (Login e CRUD interno de usuário), Gabriele (Autorização e Integração)<br />

R3: <br />
    (X) Implementado ( ) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Leandro (DAO), Marcos (Login e CRUD interno de usuário), Gabriele (Controller, Views e Integração com Login)<br />

R4: <br />
    ( ) Implementado ( ) Parcialmente implementado (X) Não implementado <br />
    Divisão na implementação da funcionalidade:  <br />
    

R5: <br />
    ( ) Implementado (X) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Gabriele (Controller, DAO, VIEWS e Integração), Marcos (login) <br />
     - Observação: só não foi implementado o email

R6: <br />
    (X) Implementado ( ) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Gabriele (Controller, Views, Integração com Login), Matheus(DAO) <br />

R7: <br />
    (X) Implementado ( ) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Todos (banco de dados) <br />

R8: <br />
    (X) Implementado ( ) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Gabriele (Controller, Views, Intregração com Login), Leandro (DAO) <br />

R9: <br />
    (X) Implementado ( ) Parcialmente implementado ( ) Não implementado <br />
    Divisão na implementação da funcionalidade: Gabriele (33%), Matheus (33%), Marcos (33%) <br />

