# matriculaunifor

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/matriculaunifor-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- JDBC Driver - MySQL ([guide](https://quarkus.io/guides/datasource)): Connect to the MySQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)



### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

### INSTALAR MySQL SERVER
sudo apt update
sudo apt install mysql-server
sudo service mysql start
sudo mysql

### CREATE DATABASE
CREATE DATABASE unifordb;
CREATE USER 'quarkususer'@'localhost' IDENTIFIED BY 'quarkuspass';
GRANT ALL PRIVILEGES ON unifordb.* TO 'quarkususer'@'localhost';
FLUSH PRIVILEGES;
EXIT;

### Configure o Quarkus para usar o MySQL
No application.properties:

quarkus.datasource.db-kind=mysql
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3306/unifordb
quarkus.datasource.username=quarkususer
quarkus.datasource.password=quarkuspass

### Hibernate
quarkus.hibernate-orm.database.generation=update
quarkus.hibernate-orm.log.sql=true

### Adicione a dependência do MySQL no pom.xml:

<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-jdbc-mysql</artifactId>
</dependency>
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-hibernate-orm</artifactId>
</dependency>

### Criar imagem do Keycloack no Doker
### Execute o comando abaixo:
 docker run -p 8180:8080 -e KEYCLOAK_ADMIN=admin 
-e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:latest start-dev

### Acessar o keycloak admin pelo link http://localhost:8180/admin
Acesso: Login: admin Senha: admin

### Criar novo realm usando o arquivo .jason baixado no link 
https://github.com/quarkusio/quarkus-quickstarts/blob/main/security-keycloak-authorization-quickstart/config/quarkus-realm.json

### Criar nova role no keycloak chamada mananger
### Criar novo user no keycloak e vincular na role criada mananger

### Exemplo de requisição para gerar Bearer token para acesso a API de Criação de usuario/Disciplina/Semestra
curl --location 'http://localhost:8080/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'username=ferd' \
--data-urlencode 'password=1234'

### Exemplos de requisições

### Cadastro de usuario
## Para criar usuario Gerar token Bearer no http://localhost:8080/token
curl --location 'http://localhost:8080/users' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJaXzg2UlhKV0ptMmlkVW5rMUg4WkVXQU1zNm4tZ3c5ZTFoVzk2X25OOVFrIn0' \
--header 'Content-Type: application/json' \
--data '{
    "username":"Fabricio Sousa",
    "usertype":"Professor"
}'

### Listar Usuarios

curl --location --request GET 'http://localhost:8080/users/' \
--header 'Content-Type: application/json'

### Listar Usuario por ID

curl --location --request GET 'http://localhost:8080/users/{Id}' \
--header 'Content-Type: application/json' \

### Cadastro de Curso
curl --location 'http://localhost:8080/courses/' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJaXzg2UlhKV0ptMmlkVW5rMUg4WkVXQU1zNm4tZ3c5ZTFoVzk2X25OOVFrIn0' \
--header 'Content-Type: application/json' \
--data '{
    "coursename":"Fisica",
    "coursearea":"Exatas"
}'

### Listar Curso por área
curl --location --request GET 'http://localhost:8080/courses/area/saude' \
--header 'Content-Type: application/json' \

### Listar Curso por nome
curl --location --request GET 'http://localhost:8080/courses/name/Matematica' \
--header 'Content-Type: application/json' \

### Cadastrar Disciplina
curl --location 'http://localhost:8080/disciplines' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJaXzg2UlhKV0ptMmlkVW5rMUg4WkVXQU1zNm4tZ3c5ZTFoVzk2X25OOVFrIn0' \
--header 'Content-Type: application/json' \
--data '{
    "name":"Fisica 1",
    "professor":"Genivaldo",
    "credit":35,
    "syllabus":"Introdução a Fisica",
    "program":"1 - Introdução / 2 - Atividades",
    "semester":null
}'

### Listar Disciplinas
curl --location 'http://localhost:8080/disciplines' \
--header 'Content-Type: application/json' \

