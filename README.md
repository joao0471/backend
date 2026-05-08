# API de Monitoramento de Vegetação em Rodovias - Sprint 1

## Descrição do Sistema
Esta API RESTful foi desenvolvida em Spring Boot para realizar o monitoramento e gerenciamento básico da vegetação no entorno de rodovias. O sistema permite o cadastro e acompanhamento de pontos de coleta ao longo das vias.

## O que é o Sensor no contexto do projeto
Neste projeto, a entidade `Sensor` representa um dispositivo físico no mundo real instalado em pontos estratégicos de uma rodovia. Sua função é representar um ponto de coleta de dados ambientais (como umidade do solo, nível de crescimento da vegetação, etc.), permitindo configurações de limites mínimos e máximos para facilitar a emissão de alertas futuros.

## Endpoints Disponíveis
A API possui um CRUD completo operando sob a rota principal `/sensores`:
* **POST /sensores:** Cadastra um novo sensor no banco de dados.
* **GET /sensores:** Retorna a lista de todos os sensores cadastrados.
* **GET /sensores/{id}:** Busca os detalhes de um sensor específico pelo seu ID.
* **PUT /sensores/{id}:** Atualiza os dados de um sensor existente.
* **DELETE /sensores/{id}:** Remove um sensor do sistema.

## Tecnologias Utilizadas
* Java 17
* Spring Boot (Web, Data JPA)
* Banco de Dados H2 (com persistência em arquivo local)
* Arquitetura em camadas (Model, Repository, Service, Controller)