<h1 align="center" style="font-weight: bold;">Lincadinho</h1>


![Static Badge](https://img.shields.io/badge/Java-orange?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/Spring-green?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/MySQL-blue?style=for-the-badge)
![Static Badge](https://img.shields.io/badge/AWS-16537e?style=for-the-badge)



## Sobre o projeto

O Lincadinho é projetado para incentivar o desenvolvimento colaborativo e o intercâmbio de ideias dentro das organizações. Os usuários podem oferecer e receber feedbacks, criando um ambiente enriquecedor onde todos podem aprender e evoluir de forma construtiva.

Este repositório apresenta a API RESTful do projeto

## Principais Funcionalidades
- [ ] Cadastro de organizações 
- [ ] Cadastro de usuários
- [ ] Cadastro de feedbacks
- [ ] Cadastro de projetos 

## Endpoints

| Rota                                     | Descrição             |
|------------------------------------------|-----------------------|
| <kbd>POST /organizacao</kbd>             | Cadastrar organização |
| <kbd>GET /organizacao </kbd>             | Listar organizações   |
| <kbd>GET /organizacao/id </kbd>          | Buscar organização    |
| <kbd>PUT /organizacao/id </kbd>          | Editar organização    |
| <kbd>DELETE /organizacao </kbd>          | Excluir organização   |
| <kbd>POST /autenticacao/registrar </kbd> | Cadastrar usuário     |
| <kbd>POST /autenticacao/login </kbd>     | Login usuário         |

<h3 id="post-organizacao-detalhes">POST /organizacao</h3>

**REQUEST MULTIPART-FORM DATA**
```
"nome": "jonys devs"
"imagem": arquivo
```
**RESPONSE**
```json
{
	"id": 2,
	"nome": "jonys devs",
	"url_imagem": "link.consulting-company_16449809.jpg",
	"ativo": true
}
```
***
<h3 id="get-organizacao-lista-detalhes">GET /organizacao</h3>

**RESPONSE**
```json
{
  "_embedded": {
    "dadosListagemOrganizacaoDTOList": [
      {
        "id": 3,
        "nome": "jonys club",
        "url_imagem": "link.consulting-company_16449809.jpg"
      },
      {
        "id": 4,
        "nome": "jonys house",
        "url_imagem": "link.consulting-company_16449809.jpg"
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/organizacao?page=0&size=10&sort=nome,asc"
    }
  },
  "page": {
    "size": 10,
    "totalElements": 2,
    "totalPages": 1,
    "number": 0
  }
}
```
<h3 id="get-organizacao-busca-detalhes">GET /organizacao/id</h3>

**RESPONSE**
```json
{
	"id": 3,
	"nome": "jonys club",
	"url_imagem": "link.consulting-company_16449809.jpg",
	"ativo": true
}
```

***
<h3 id="put-organizacao-detalhes">PUT /organizacao</h3>

**REQUEST MULTIPART-FORM DATA**
```
"id": 2
"nome": "Jonys house"
"imagem": arquivo
```

**RESPONSE**
```json
{
  "id": 2,
  "nome": "Jonys house",
  "url_imagem": "link.company_16449809.jpg",
  "ativo": true
}
```
***
<h3 id="delete-organizacao-detalhes">DELETE /organizacao/id</h3>

**RESPONSE**<br><br>
![Static Badge](https://img.shields.io/badge/204-green) No Content

<h3 id="post-autenticacao-registrar">POST /autenticacao/registrar</h3>

**REQUEST**
```json
{
  "nome": "ze",
  "email": "zeze@email.com",
  "senha": "00000000",
  "role": "ADMIN",
  "organizacao": 1
}
```

**RESPONSE**
```json
{
  "id": 3,
  "nome": "ze",
  "email": "zeze@email.com",
  "role": "ADMIN",
  "foto_url": "",
  "organizacaoDTO": {
    "id": 1,
    "nome": "jonas org",
    "url_imagem": "link",
    "ativo": true
  }
}
```
***

<h3 id="post-autenticacao-login">POST /autenticacao/login</h3>

**REQUEST**
```json
{
  "email": "zeze@email.com",
  "senha": "00000000"
}
```

**RESPONSE**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJsaW5jYWRpbmhvLWFwaSIsInN1YiI6InplemVAZW1haWwuY29tIiwiZXhwIjoxNzM0MjE1NTM1fQ.YMgqCSTAOyE74e3_Hiu0EGspnvQw_jEtOHSfKnR9kIE"
}
```




