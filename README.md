# Message Service

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

````shell
curl --location --request POST 'localhost:9000/report' \
--header 'correlationId: 83b868e2-9a61-45d2-86e0-549609c38938' \
--header 'Content-Type: application/json' \
--data-raw '{
    "recipient": 1,
    "send_date": "2022-02-01T00:00:00",
    "message": "Feliz Ano Novo!",
    "channel": "WHATSAPP"
}'
````

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

```mermaid
classDiagram
    class User {
        +Long id
        +String name
    }
    class CommunicationChannel {
        <<enumeration>>
        EMAIL
        SMS
        PUSH
        WHATSAPP
    }
    class Communication{
        +Long id
        +LocalDateTime 
        +String message
        +User sender
        +User recipient
        +CommunicationChannel communicationChannel
    }
```

## Rotas
### Health Check
```shell
curl --location --request GET 'http://localhost:9000/actuator/health'
```