## 10.1 Technology Stack (Final Decision)

This section defines the final technology stack used to implement the Developer Career Hub microservices system.

---

### Backend

- Language: Java 21
- Framework: Spring Boot 3.x
- Microservices Framework: Spring Cloud

---

### Core Infrastructure

- API Gateway: Spring Cloud Gateway
- Service Discovery: Netflix Eureka Server
- Load Balancing: Spring Cloud LoadBalancer

---

### Security

- Authentication: JWT (Stateless authentication)
- Authorization: Spring Security (Role-Based Access Control)

---

### Databases

- PostgreSQL (Database per microservice)
- ORM: Hibernate / JPA
- Migrations: Flyway (recommended)

---

### Communication

- REST APIs (synchronous communication for MVP)
- OpenFeign (optional for internal service communication)

---

### Build Tools

- Maven (for all microservices)

---

### Containerization

- Docker (each microservice containerized)
- Docker Compose (local orchestration)

---

### Observability

- Spring Boot Actuator (health checks & metrics)
- Logging: SLF4J + Logback

---

### Frontend (supporting system)

- React (Vite)
- Axios (API communication)
- JWT stored in localStorage (MVP approach)

---

### Future Enhancements (Not part of MVP)

- Kafka (event-driven architecture)
- Redis (caching layer)
- Kubernetes (orchestration)
- AWS deployment (cloud infrastructure)

## 10.2 Project Structure (Monorepo Design)

The Developer Career Hub uses a **monorepo-based architecture** where all microservices, shared libraries, and infrastructure configurations are maintained in a single repository.

This approach is chosen for the MVP phase to simplify development, debugging, and deployment.

---

### 10.2.1 Repository Structure

### 10.2.1 Repository Structure

```text
developer-career-hub/
│
├── api-gateway/
├── service-registry/            # Eureka Server
├── auth-service/
├── job-service/
├── interview-service/
├── study-service/
├── resource-service/
├── goals-service/
├── analytics-service/
│
├── common/                      # Shared DTOs, exceptions, utilities
├── docker/                      # Docker related configs
├── docs/
│   ├── brd/
│   ├── architecture/
│   └── system/
│
├── docker-compose.yml
└── README.md
```


---

### 10.2.2 Why Monorepo?

We choose a monorepo for the MVP because:

- Simplifies local development setup
- Easier debugging across services
- Single place to manage all microservices
- Faster iteration during development
- Easier Docker orchestration

---

### 10.2.3 Service Isolation Principle

Even though this is a monorepo:

- Each microservice is completely independent at runtime
- Each service has its own:
  - `pom.xml`
  - `Dockerfile`
  - `application.yml`
  - database

---

### 10.2.4 Important Rule

- Shared code must be minimal
- Only common DTOs, exceptions, and utilities go into `common/`
- Business logic must NEVER be shared between services

## 10.3 Communication Contracts

This section defines the standard rules for how microservices communicate in the Developer Career Hub system.

The goal is to ensure **consistency, predictability, and maintainability** across all services.

---

### 10.3.1 Communication Style

- All communication is based on **REST APIs**
- Data format: **JSON**
- Communication is **synchronous (MVP phase)**

---

### 10.3.2 Standard Request Format

All API requests follow standard HTTP structure:

Example:

```json id="req1"
{
  "userId": "123",
  "data": {
    "title": "Software Engineer",
    "company": "Google"
  }
}
```

### 10.3.3 Standard Response Format

All services must return a consistent response format.

```{
  "timestamp": "2026-07-01T10:00:00Z",
  "status": "SUCCESS",
  "message": "Request processed successfully",
  "data": {
    "id": "456",
    "result": "Created"
  }
}
```

### 10.3.4 Error Response Format

All errors must follow a unified structure:

``` {
  "timestamp": "2026-07-01T10:01:00Z",
  "status": "ERROR",
  "message": "Validation failed",
  "errorCode": "JOB_4001",
  "details": [
    "Company name is required",
    "Job title cannot be empty"
  ]
}
```

### 10.3.5 API Naming Standards
Use plural nouns
Use REST conventions
No verbs in endpoint names

✔ Correct:

/jobs
/interviews
/study-plans

❌ Incorrect:

/createJob
/getJobsList

### 10.3.6 Versioning Strategy

All APIs will follow versioning:

/api/v1/jobs
/api/v1/interviews

This allows future upgrades without breaking existing clients.

### 10.3.7 Inter-Service Communication Rule

Services communicate only via REST APIs
No direct database access
Authentication passed via JWT token in headers

### 10.3.8 Key Principle

All services must behave like independent products that communicate through well-defined contracts.

## 10.4 Docker & Deployment Strategy

This section defines how the Developer Career Hub system is containerized and run locally using Docker.

The goal is to ensure **consistent environments across development, testing, and deployment stages**.

---

### 10.4.1 Containerization Approach

Each microservice is packaged as an independent Docker container:

- api-gateway → container
- auth-service → container
- job-service → container
- interview-service → container
- study-service → container
- resource-service → container
- goals-service → container
- analytics-service → container
- service-registry (Eureka) → container

Each service runs independently but communicates through a shared Docker network.

---

### 10.4.2 Docker Compose Orchestration

All services are managed using a single `docker-compose.yml` file.

It includes:

- All microservices
- PostgreSQL databases (per service or shared instance for MVP simplicity)
- Eureka Server
- API Gateway

---

### 10.4.3 Startup Order (VERY IMPORTANT)

Services must start in this order:

1. PostgreSQL databases
2. Eureka Server (Service Registry)
3. Auth Service
4. Core Business Services
   - Job Service
   - Interview Service
   - Study Service
   - Resource Service
   - Goals Service
5. Analytics Service
6. API Gateway

---

### 10.4.4 Service Networking

- All services communicate using Docker network
- Service discovery is handled via Eureka
- No hardcoded IP addresses between services
- Services use service names (e.g., `http://job-service`)

---

### 10.4.5 Environment Configuration

Each service contains:

- `application.yml`
- environment-specific configs via `.env` (optional)
- Docker environment variables

Example:

```yaml id="env1"
spring:
  datasource:
    url: jdbc:postgresql://job-db:5432/jobdb
```

---

### 10.4.6 Database Strategy in Docker

Each microservice has its own database container:

- auth-db
- job-db
- interview-db
- study-db
- resource-db
- goals-db
- analytics-db

---

### 10.4.7 Key Benefits

- Consistent development environment
- Easy onboarding for new developers
- No local machine dependency issues
- Production-like setup locally

---

### 10.4.8 Future Enhancement

In later stages, deployment can be upgraded to:

- Kubernetes (K8s)
- AWS ECS / EKS
- CI/CD pipelines using GitHub Actions

## 10.5 Service Startup Flow (Execution Order)

This section defines the correct startup sequence for all components in the Developer Career Hub system to ensure proper service discovery and communication.

---

### 10.5.1 Startup Sequence

All services must be started in the following order:

#### Step 1: Infrastructure Layer

- PostgreSQL Databases (all service-specific DBs)
- Docker Network Initialization

---

#### Step 2: Service Registry

- Eureka Server (Service Discovery)

> All microservices must register here before they can communicate.

---

#### Step 3: Core Identity Service

- Auth Service

> Required first because all services depend on authentication and JWT validation.

---

#### Step 4: Core Business Services

These services can be started in any order after Auth Service:

- Job Service
- Interview Service
- Study Service
- Resource Service
- Goals Service

---

#### Step 5: Analytics Service

- Analytics Service

> Depends on other services for data aggregation.

---

#### Step 6: API Gateway

- API Gateway (Final entry point)

> Routes all external traffic to internal microservices.

---

### 10.5.2 Runtime Flow Summary

1. Services register with Eureka
2. API Gateway fetches service instances from Eureka
3. Client sends request to API Gateway
4. Gateway routes request to appropriate service
5. Service processes request and interacts with its own database
6. Response flows back through Gateway to client

---

### 10.5.3 Key Principle

> "Nothing communicates unless it is registered and discoverable through Eureka."

---

### 10.5.4 Failure Safety Rule

If Eureka is down:

- No service discovery happens
- System cannot route requests
- Services must fail fast and log errors clearly