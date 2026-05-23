# API de Monitoramento de Vegetação em Rodovias

## Sprint 1 - CRUD e Estrutura Básica

### Descrição do Sistema
Esta API RESTful foi desenvolvida em Spring Boot para realizar o monitoramento e gerenciamento básico da vegetação no entorno de rodovias. O sistema permite o cadastro e acompanhamento de pontos de coleta ao longo das vias.

### O que é o Sensor no contexto do projeto
Neste projeto, a entidade `Sensor` representa um dispositivo físico no mundo real instalado em pontos estratégicos de uma rodovia. Sua função é representar um ponto de coleta de dados ambientais (como umidade do solo, nível de crescimento da vegetação, etc.), permitindo configurações de limites mínimos e máximos para facilitar a emissão de alertas futuros.

### Endpoints Disponíveis (Sensores)
A API possui um CRUD completo operando sob a rota principal `/sensores`:
* **POST /sensores:** Cadastra um novo sensor no banco de dados.
* **GET /sensores:** Retorna a lista de todos os sensores cadastrados.
* **GET /sensores/{id}:** Busca os detalhes de um sensor específico pelo seu ID.
* **PUT /sensores/{id}:** Atualiza os dados de um sensor existente.
* **DELETE /sensores/{id}:** Remove um sensor do sistema.

### Tecnologias Utilizadas
* Java 17
* Spring Boot (Web, Data JPA)
* Banco de Dados H2 (com persistência em arquivo local)
* Arquitetura em camadas (Model, Repository, Service, Controller)

---

## Sprint 2 - Monitoramento e Inteligência de Status

Esta etapa do projeto introduz o registro de medições atreladas aos sensores e uma camada de inteligência (Service) para avaliação em tempo real do estado da vegetação.

### Como funciona o cálculo de status
A regra de negócio classifica a medição em três estados, utilizando os atributos `limiteMinimo` e `limiteMaximo` previamente configurados no Sensor:

* **NORMAL:** O valor da medição encontra-se dentro do intervalo seguro, distante das extremidades.
* **ALERTA:** O valor está dentro do limite permitido, porém atingiu uma margem de segurança de 10% de proximidade em relação ao limite mínimo ou máximo. Isso indica que a vegetação pode sofrer problemas em breve se a tendência continuar.
* **CRÍTICO:** O valor registrado rompeu as barreiras absolutas (ficou menor que o `limiteMinimo` ou maior que o `limiteMaximo`), indicando anomalia severa no ambiente.

Para garantir o isolamento de responsabilidades, o banco de dados armazena apenas o valor bruto e a data (`Medicao.java`), enquanto o status processado é devolvido via camada de transporte (`MedicaoResponseDTO.java`).

### Endpoints Disponíveis (Medições)
* **POST /medicoes:** Registra uma nova medição e retorna o status calculado.
* **GET /medicoes:** Retorna todas as medições registradas.
* **GET /medicoes/{id}:** Retorna uma medição específica.
* **GET /medicoes/sensor/{id}:** Retorna o histórico completo de medições de um sensor específico.

### Como testar
1.  Execute a aplicação.
2.  Utilize o Postman para criar um Sensor via `POST /sensores`. Exemplo de payload:
    ```json
    {
      "nome": "Sensor de Umidade do Solo",
      "tipo": "umidade",
      "local": "Rodovia BR-116, Km 42",
      "unidade": "%",
      "limiteMinimo": 30.0,
      "limiteMaximo": 80.0,
      "ativo": true
    }
    ```
3.  Registre medições via `POST /medicoes` referenciando o ID do sensor criado. Altere os valores para validar os status:
    * Enviar `55.0` retornará o DTO com status **NORMAL**.
    * Enviar `78.0` retornará o DTO com status **ALERTA**.
    * Enviar `92.0` retornará o DTO com status **CRÍTICO**.
4.  Consulte o histórico de medições realizando um `GET` em `/medicoes/sensor/1`.
    *Nota: As evidências destes testes via Postman encontram-se anexadas aos entregáveis da Sprint.*