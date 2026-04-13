# unified-vehicle-elasticsearch-platform
Unified Vehicle ElasticSearch Platform

## Tech Stack
- Java 21 + Spring Boot 3.2
- Spring Data Elasticsearch
- Elasticsearch 8.13
- Kibana 8.13
- Springdoc OpenAPI (Swagger UI)
- Docker / Docker Compose

## Prerequisites
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running

## Getting Started

### 1. Start Elasticsearch & Kibana
```bash
docker-compose up -d
```

### 2. Verify Elasticsearch is running
```bash
curl http://localhost:9200
```
Or open [http://localhost:9200](http://localhost:9200) in your browser.

### 3. Open Kibana (UI dashboard)
Navigate to [http://localhost:5601](http://localhost:5601)

### 4. Stop the services
```bash
docker-compose down
```

### 5. Stop and remove all data volumes
```bash
docker-compose down -v
```

## Building & Running the Spring Boot Service

### Build the application
```bash
cd unified-vehicle-elasticsearch-service/unified-vehicle-elasticsearch-service
./mvnw clean package -DskipTests
```

### Run the application
```bash
java -jar target/unified-vehicle-elasticsearch-service-0.0.1-SNAPSHOT.jar
```

The service will start on `http://localhost:8080`

## API Documentation & Testing

### Swagger UI
Access the interactive API documentation at:
- **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Available Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/vehicles` | Create a new vehicle |
| GET | `/api/vehicles` | Get all vehicles |
| GET | `/api/vehicles/{id}` | Get vehicle by ID |
| PUT | `/api/vehicles/{id}` | Update a vehicle |
| DELETE | `/api/vehicles/{id}` | Delete a vehicle |
| GET | `/api/vehicles/search/make?make={make}` | Find by make |
| GET | `/api/vehicles/search/model?model={model}` | Find by model |
| GET | `/api/vehicles/search/year?year={year}` | Find by year |
| GET | `/api/vehicles/search/status?status={status}` | Find by status |
| GET | `/api/vehicles/search/color?color={color}` | Find by color |
| GET | `/api/vehicles/search/make-pageable?make={make}&page=0&size=10` | Find by make (paginated) |
| GET | `/api/vehicles/search/model-pageable?model={model}&page=0&size=10` | Find by model (paginated) |
| GET | `/api/vehicles/health` | Health check |

### Sample Requests

**Create a Vehicle (POST)**
```bash
curl -X POST http://localhost:8080/api/vehicles \
  -H "Content-Type: application/json" \
  -d '{
    "make": "Toyota",
    "model": "Camry",
    "year": 2023,
    "vin": "VIN123456789ABC",
    "color": "Silver",
    "price": 32000.00,
    "status": "available"
  }'
```

**Get All Vehicles (GET)**
```bash
curl http://localhost:8080/api/vehicles
```

**Get Vehicle by ID (GET)**
```bash
curl http://localhost:8080/api/vehicles/{id}
```

**Update a Vehicle (PUT)**
```bash
curl -X PUT http://localhost:8080/api/vehicles/{id} \
  -H "Content-Type: application/json" \
  -d '{
    "make": "Toyota",
    "model": "Corolla",
    "year": 2024,
    "color": "Blue",
    "price": 25000.00,
    "status": "sold"
  }'
```

**Delete a Vehicle (DELETE)**
```bash
curl -X DELETE http://localhost:8080/api/vehicles/{id}
```

**Search by Make (GET)**
```bash
curl "http://localhost:8080/api/vehicles/search/make?make=Toyota"
```

**Search by Status (GET)**
```bash
curl "http://localhost:8080/api/vehicles/search/status?status=available"
```

**Search with Pagination (GET)**
```bash
curl "http://localhost:8080/api/vehicles/search/make-pageable?make=Honda&page=0&size=10"
```

## Service Ports
| Service | URL |
|---|---|
| Elasticsearch | http://localhost:9200 |
| Kibana | http://localhost:5601 |
| Spring Boot API | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui/index.html |

## Notes
> Kibana is included so you can browse indices and run queries visually. Security is disabled for local dev — don't use this config in production.

## Use of the Stack
| Tool | Purpose |
|---|---|
| **Elasticsearch** | Stores and indexes vehicle documents; powers full-text and filtered search queries |
| **Kibana** | Visual UI to explore indices, run Dev Tools queries, and monitor cluster health |
| **Spring Data Elasticsearch** | Repository and template abstractions for indexing and querying from Java |
| **Spring Boot** | REST API layer exposing vehicle search and CRUD endpoints |
| **Docker Compose** | Spins up Elasticsearch and Kibana locally with a single command |
