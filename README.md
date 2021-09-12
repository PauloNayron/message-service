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

### Use Case 2: *Consultation of communication submission*
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

### Use Case 3: *Cancellation of sending the communication*
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