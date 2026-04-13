# unified-vehicle-elasticsearch-platform
Unified Vehicle ElasticSearch Platform

## Tech Stack
- Java 17 + Spring Boot 3.x
- Spring Data Elasticsearch
- Elasticsearch 8.13
- Kibana 8.13
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

## Service Ports
| Service | URL |
|---|---|
| Elasticsearch | http://localhost:9200 |
| Kibana | http://localhost:5601 |

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
