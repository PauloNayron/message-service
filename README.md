# Message Service

## Executar projeto
- Execute o docker compose
```shell
docker-compose up
```
- Adicione as variáveis de ambiente (execução e testes)
```
SERVER_PORT=9000

DATASOURCE_URL=jdbc:postgresql://localhost:5432/message_service
DATASOURCE_USERNAME=admin
DATASOURCE_PASSWORD=1q2w3e4r
```

## Use cases
### Use Case 1: *Communication Send Scheduling*
```mermaid
sequenceDiagram
    participant C as Client
    participant A as Service
    participant B as Postgres

    C->>A: POST: {}
    A->>B: save {}
    B-->>A: {}
    A-->>C: CREATED {}
```

```shell
curl --location --request POST 'localhost:9000/report' \
--header 'correlationId: 83b868e2-9a61-45d2-86e0-549609c38938' \
--header 'Content-Type: application/json' \
--data-raw '{
    "recipient": 1,
    "send_date": "2022-02-01T00:00:00",
    "message": "Feliz Ano Novo!",
    "channel": "WHATSAPP"
}'
```

### Use Case 2: *Consultation of report submission*
```mermaid
sequenceDiagram
    participant C as Client
    participant A as Service
    participant B as Postgres

    C->>A: GET: /${id}
    A->>B: recupera(id)
    B-->>A: {}
    A-->>C: OK {}
```

```shell
curl --location --request GET 'localhost:9000/report/1'
```

### Use Case 3: *Cancellation of sending the report*
```mermaid
sequenceDiagram
    participant C as Client
    participant A as Service
    participant B as Postgres

    C->>A: PUT: /${id} {}
    A->>B: atualiza {}
    B-->>A: {}
    A-->>C: NOT CONTENT {}
```

```shell
curl --location --request DELETE 'localhost:9000/report/1'
```

## Class Diagram
```mermaid
classDiagram
    class Channel {
        <<interface>>
        EMAIL
        SMS
        PUSH
        WHATSAPP
    }
    class Communication{
        +Long id
        +String message
        +LocalDateTime sendDate
        +Long recipient
        +Channel channel
        +Status status
    }
```

## Rotas
### Health Check
```shell
curl --location --request GET 'http://localhost:9000/actuator/health'
```

### Open API
> http://localhost:9000/swagger-ui.html