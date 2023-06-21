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

Isso deve bastar para que o cliente `docker` faça requisições ao daemon `dockerd`. Teste subindo os containers

    docker-compose up

Se tudo der certo, a instanciação dos containers descritos em [docker-compose.yml](docker-compose.yml) deve ser observada na linha de comando. Observe que o processo/terminal onde foi executado o último comando ficará inutilizado, pois estará hospedando os serviços instanciados (derrubar o processo significa também derrubar os serviços).


### Windows
Após baixar e instalar o Docker Desktop, é necessário rodar o Docker Desktop para inicializar o deamon do Docker, permitindo a criação dos containeres no modo usual. Uma vez inicializado, basta rodar

    docker-compose up

Em um terminal que esteja no diretório onde está o arquivo [docker-compose.yml](docker-compose.yml)

### Observação
Durante a instanciação dos containers podem haver problemas com as portas selecionadas para uso. Possivelmente, serviços no anfitrião (no seu sistema operacional) já estarão usando as portas designadas aos containers. Será necessário encontrar e derrubar esses serviços.

- Windows: via gerenciador de tarefas.
- Linux via `sudo systemctl stop nome_servico` (sugere-se inclusive `sudo systemctl disable nome_servico` para impedir que retornem à cada reinicialização do sistema, incorrendo no conflito de portas novamente).

## Desenvolvimento

