# Projeto de conclusão do Bootcamp Generation Brasil - AWS re/Start

Projeto de conclusão do programa Desenvolvedor Web/Cloud AWS da Generation Brasil, em parceria com a Amazon AWS. O projeto consiste de uma API REST e instrui os métodos de manipulação de dados CRUD (Create, Read, Update and Delete). O projeto conta com três tabelas no modelo relacional de banco de dados, tendo como base de criação o bando de dados PostgresSQL.


## 🛠️ Construído com

* [Git](https://git-scm.com/)
* [Maven](https://maven.apache.org/)
* [Java](https://www.java.com/pt-BR/download/manual.jsp)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Swagger](https://swagger.io/solutions/api-documentation/)
* [Dockerfile](https://docs.docker.com/reference/dockerfile/)


## 📖 Documentação

Testada e implementada com o Swagger. É possível acessá-la através do [link](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#)


## Controle de usuário
### Cadastro de novo usuário

Endpoint:
[POST](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/insert)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/insert
```

Request body:

| Coluna  | Tipo   | Descrição                     |
|---------|--------|-------------------------------|
| nome    | string | armazenar o nome do usuario   |
| email   | string | armazenar o email do usuario  |
| foto    | string | armazenar a foto do usuario   |

### Lista de usuários

Endpoint:
[GET](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/findAll)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/findAll
```

### Usuário por Id

Endpoint:
[GET](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/findById)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/findById
```

### Atualização de usuário (por Id)

Endpoint:
[PUT](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/update)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/update
```

### Apagar usuário (por Id)

Endpoint:
[DELETE](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/deleteById)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/deleteById
```


## Controle de tema
### Criação de novo tema

Endpoint:
[POST](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/insert_1)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/insert_1
```

Request body:

| Coluna     | Tipo   | Descrição                      |
|------------|--------|--------------------------------|
| descrição  | string | armazenar a descrição do tema  |


### Lista de temas

Endpoint:
[GET](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/findAll_1)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/findAll_1
```

### Tema por Id

Endpoint:
[GET](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/findById_1)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/findById_1
```

### Atualização de tema (por Id)

Endpoint:
[PUT](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/update_1)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/update_1
```

### Apagar tema (por Id)

Endpoint:
[DELETE](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/deleteById_1)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/tema-controller/deleteById_1
```


## Controle de postagens
### Cadastro de postagens

Endpoint:
[POST](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/insert_2)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/insert_2
```

Request body:

| Coluna      | Tipo    | Descrição                              |
|-------------|---------|----------------------------------------|
| titulo      | string  | armazenar o título do post             |
| texto       | string  | armazenar o texto do post              |
| usuario_id  | integer | armazenar o id do usuário              |
| tema_id     | integer | armazenar o id do tema das postagens   |

### Lista de postagens

Endpoint:
[GET](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/findAll)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/usuario-controller/findAll
```

### Usuário por Id

Endpoint:
[GET](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/findAll_2)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/findAll_2
```

### Atualização de postagens (por Id)

Endpoint:
[PUT](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/update_2)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/update_2
```

### Apagar postagens (por Id)

Endpoint:
[DELETE](https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/deleteById_2)

```
https://crud-postagens-usuario.onrender.com/swagger-ui/index.html#/postagem-controller/deleteById_2
```


## 🎁 Expressões de gratidão

* A oportunidade de conhecer pessoas maravilhosas nesse bootcamp 😊 💖 💖 💖;
* Agradecimentos a todos os envolvidos no curso e aos professores por compartilhar seus conhecimentos e pela paciência em nos ensinar 👏💪❤️❤️


---
⌨️ Por [JOSE AUGUSTO TEIXEIRA ALMEIDA](https://github.com/JOSE-AUGUSTO-TEIXEIRA-ALMEIDA/) 😊
