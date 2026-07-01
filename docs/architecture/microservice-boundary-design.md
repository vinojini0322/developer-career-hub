# Microservice Boundary Design

## 1. Purpose

The purpose of this document is to define the boundaries, responsibilities, and interactions of each microservice in the Developer Career Hub SaaS application.

This design follows Domain-Driven Design (DDD) principles by separating the application into independent business domains. Each microservice owns a specific business capability, its own data, and its own business logic.

The document serves as the architectural blueprint for backend development, ensuring that every service has a clearly defined responsibility, minimal coupling, and high cohesion.

This document also establishes communication rules, database ownership, and service dependency guidelines to support scalability, maintainability, and independent deployment.


## 2. Architecture Principles

The Developer Career Hub is designed using a microservices architecture to ensure scalability, maintainability, and independent deployment of business capabilities.

The following architectural principles are applied throughout the system:

### 2.1 Single Responsibility

Each microservice is responsible for a single business domain and contains all the business logic related to that domain.

### 2.2 Database per Service

Each microservice owns its own database. Direct database access between services is not permitted.

### 2.3 Loose Coupling

Services communicate through REST APIs. A service must not depend on another service's internal implementation or database schema.

### 2.4 High Cohesion

All related business logic, validation, and persistence for a specific domain are contained within the same microservice.

### 2.5 Independent Deployment

Each microservice can be built, tested, deployed, and scaled independently without affecting other services.

### 2.6 Stateless Services

Business services remain stateless. Authentication is handled using JWT, allowing services to process requests without maintaining user sessions.

### 2.7 API Gateway Pattern

All client requests pass through the API Gateway, which acts as the single entry point to the backend. The gateway is responsible for request routing and security enforcement.

### 2.8 Service Discovery

All microservices register themselves with Eureka Server, allowing dynamic service discovery and reducing hardcoded service endpoints.

### 2.9 Security

Authentication and authorization are centralized in the Auth Service using JWT-based authentication. Business services rely on validated JWT tokens to identify users and enforce access control.

### 2.10 Future Extensibility

The architecture is designed to support future enhancements such as event-driven communication, distributed tracing, centralized logging, caching, and cloud-native deployment without significant architectural changes.

## 3. Microservices Overview

The Developer Career Hub is divided into multiple independent microservices, where each service is responsible for a specific business domain. This separation enables independent development, deployment, scaling, and maintenance while minimizing dependencies between services.

| Microservice | Primary Responsibility | Database |
|--------------|------------------------|----------|
| Auth Service | User authentication, authorization, JWT, user profile | Auth DB |
| Job Service | Job applications, companies, application status tracking | Job DB |
| Interview Service | Interview journal, questions, feedback, interview ratings | Interview DB |
| Study Service | Study plans, learning progress, study sessions | Study DB |
| Resource Service | Learning resources, bookmarks, tags, categories | Resource DB |
| Goals Service | Career goals, milestones, target dates, progress | Goals DB |
| Analytics Service | Dashboard metrics, reports, aggregated insights | Analytics DB |

Each microservice owns its business logic and database. Communication between services occurs through REST APIs, while all external client requests are routed through the API Gateway.

## 4. Service Responsibilities

### 4.1 Auth Service

#### Purpose

The Auth Service is responsible for user authentication and authorization across the Developer Career Hub platform. It provides secure user registration, login, JWT token generation, and user profile management.

#### Responsibilities

- Register new users
- Authenticate users during login
- Generate and validate JWT tokens
- Manage user profiles
- Manage user roles and permissions
- Support password changes and password reset (future enhancement)

#### Database Ownership

The Auth Service exclusively owns the following data:

- Users
- Roles
- User Roles
- Refresh Tokens (future enhancement)

No other microservice is allowed to access the Auth database directly.

#### High-Level APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | /auth/register | Register a new user |
| POST | /auth/login | Authenticate user and generate JWT |
| POST | /auth/refresh | Generate a new access token (future) |
| GET | /users/me | Retrieve authenticated user's profile |
| PUT | /users/me | Update authenticated user's profile |

#### Dependencies

- PostgreSQL (Auth DB)
- API Gateway
- Eureka Server

#### Service Boundaries

The Auth Service is responsible only for identity and access management.

It must not manage:

- Job applications
- Interview records
- Study progress
- Learning resources
- Career goals
- Analytics and reporting

### 4.2 Job Service

#### Purpose

The Job Service is responsible for managing all job application-related activities within the Developer Career Hub platform. It allows users to track their job applications and monitor their progress throughout the hiring process.

#### Responsibilities

- Create and manage job applications
- Track application status (Applied, Interviewing, Offered, Rejected, etc.)
- Store company details related to applications
- Add notes and comments for each application
- Track application dates and follow-ups

#### Database Ownership

The Job Service owns the following data:

- Job Applications
- Companies
- Application Status History

No other microservice is allowed to directly access the Job Service database.

#### High-Level APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /jobs | Create a new job application |
| GET | /jobs | Get all job applications for logged-in user |
| GET | /jobs/{id} | Get specific job application |
| PUT | /jobs/{id} | Update job application |
| DELETE | /jobs/{id} | Delete job application |
| PATCH | /jobs/{id}/status | Update application status |

#### Dependencies

- Auth Service (for user identity via JWT)
- PostgreSQL (Job DB)
- API Gateway
- Eureka Server

#### Service Boundaries

The Job Service is responsible only for job application tracking.

It must not manage:

- User authentication
- Interview details
- Study progress
- Resources
- Goals
- Analytics or reporting

### 4.3 Interview Service

#### Purpose

The Interview Service is responsible for managing interview preparation and tracking interview experiences. It helps users record interview questions, answers, feedback, and overall interview performance for future improvement.

#### Responsibilities

- Create and manage interview records
- Store interview questions and user answers
- Record interviewer feedback and notes
- Track interview rounds and outcomes
- Maintain interview history for each job application
- Support self-assessment and rating of performance

#### Database Ownership

The Interview Service owns the following data:

- Interviews
- Interview Questions
- Interview Answers
- Interview Feedback
- Interview Ratings

No other microservice is allowed to directly access the Interview Service database.

#### High-Level APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /interviews | Create a new interview record |
| GET | /interviews | Get all interviews for logged-in user |
| GET | /interviews/{id} | Get specific interview details |
| PUT | /interviews/{id} | Update interview details |
| DELETE | /interviews/{id} | Delete interview record |
| POST | /interviews/{id}/questions | Add interview questions |
| POST | /interviews/{id}/feedback | Add feedback for interview |

#### Dependencies

- Auth Service (user identity via JWT)
- Job Service (optional linkage to job application)
- PostgreSQL (Interview DB)
- API Gateway
- Eureka Server

#### Service Boundaries

The Interview Service is responsible only for interview preparation and tracking.

It must not manage:

- Job application lifecycle
- User authentication
- Study plans
- Learning resources
- Career goals
- Analytics or dashboards

### 4.4 Study Service

#### Purpose

The Study Service is responsible for helping users plan, track, and improve their learning progress. It enables structured study planning for technical skills, interview preparation, and career development.

#### Responsibilities

- Create and manage study plans
- Track daily/weekly study progress
- Manage topics and learning goals
- Record study sessions and completion status
- Monitor skill improvement over time
- Support structured learning paths

#### Database Ownership

The Study Service owns the following data:

- Study Plans
- Study Topics
- Study Sessions
- Progress Tracking

No other microservice is allowed to directly access the Study Service database.

#### High-Level APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /study-plans | Create a study plan |
| GET | /study-plans | Get all study plans for user |
| GET | /study-plans/{id} | Get specific study plan |
| PUT | /study-plans/{id} | Update study plan |
| DELETE | /study-plans/{id} | Delete study plan |
| POST | /study-plans/{id}/sessions | Add study session |
| PATCH | /study-plans/{id}/progress | Update progress |

#### Dependencies

- Auth Service (user identity via JWT)
- PostgreSQL (Study DB)
- API Gateway
- Eureka Server

#### Service Boundaries

The Study Service is responsible only for learning and progress tracking.

It must not manage:

- Job applications
- Interview tracking
- Resource bookmarking
- User authentication
- Career goals
- Analytics or reporting

### 4.5 Resource Service

#### Purpose

The Resource Service is responsible for managing learning resources such as articles, videos, documentation links, and references saved by the user. It helps users organize and access useful materials for study and career development.

#### Responsibilities

- Save and manage learning resources (URLs, articles, videos)
- Organize resources using tags and categories
- Allow users to bookmark useful content
- Search and filter resources
- Mark resources as read/unread or favorite
- Support resource metadata management

#### Database Ownership

The Resource Service owns the following data:

- Resources (URLs, titles, descriptions)
- Tags
- Categories
- User bookmarks

No other microservice is allowed to directly access the Resource Service database.

#### High-Level APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /resources | Save a new resource |
| GET | /resources | Get all resources for user |
| GET | /resources/{id} | Get specific resource |
| PUT | /resources/{id} | Update resource |
| DELETE | /resources/{id} | Delete resource |
| GET | /resources/search | Search resources by keyword |
| PATCH | /resources/{id}/status | Mark as read/unread |

#### Dependencies

- Auth Service (user identity via JWT)
- PostgreSQL (Resource DB)
- API Gateway
- Eureka Server

#### Service Boundaries

The Resource Service is responsible only for managing external learning materials.

It must not manage:

- Job applications
- Interview tracking
- Study plans
- Career goals
- Authentication
- Analytics or reporting

### 4.6 Goals Service

#### Purpose

The Goals Service is responsible for managing user career goals and milestones. It helps users define long-term and short-term objectives and track their progress toward achieving them.

#### Responsibilities

- Create and manage career goals
- Define milestones for each goal
- Track progress and completion status
- Set target dates and deadlines
- Update goal progress over time
- Support prioritization of goals

#### Database Ownership

The Goals Service owns the following data:

- Goals
- Milestones
- Goal Progress Tracking

No other microservice is allowed to directly access the Goals Service database.

#### High-Level APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /goals | Create a new goal |
| GET | /goals | Get all goals for user |
| GET | /goals/{id} | Get specific goal |
| PUT | /goals/{id} | Update goal |
| DELETE | /goals/{id} | Delete goal |
| POST | /goals/{id}/milestones | Add milestone |
| PATCH | /goals/{id}/progress | Update goal progress |

#### Dependencies

- Auth Service (user identity via JWT)
- PostgreSQL (Goals DB)
- API Gateway
- Eureka Server

#### Service Boundaries

The Goals Service is responsible only for career planning and milestone tracking.

It must not manage:

- Job applications
- Interview tracking
- Study plans
- Learning resources
- Authentication
- Analytics or reporting

### 4.7 Analytics Service

#### Purpose

The Analytics Service is responsible for generating insights and dashboards based on user activity across the Developer Career Hub platform. It aggregates data from multiple services to provide meaningful career progress analytics.

#### Responsibilities

- Aggregate data from Job, Interview, Study, Resource, and Goals services
- Generate user dashboards and summary reports
- Calculate progress metrics (e.g., applications submitted, interview success rate)
- Provide weekly/monthly activity insights
- Support data visualization for frontend dashboards
- Track productivity trends over time

#### Database Ownership

The Analytics Service owns the following data:

- Aggregated Analytics Data
- Dashboard Snapshots
- Computed Metrics
- Historical Reports

It does NOT own raw business data. It only stores processed and aggregated information.

#### High-Level APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /analytics/dashboard | Get user dashboard summary |
| GET | /analytics/jobs | Job-related analytics |
| GET | /analytics/interviews | Interview performance analytics |
| GET | /analytics/study | Study progress analytics |
| GET | /analytics/goals | Goal completion analytics |
| GET | /analytics/summary | Overall career summary |

#### Dependencies

- Auth Service (user identity via JWT)
- Job Service (data aggregation)
- Interview Service (data aggregation)
- Study Service (data aggregation)
- Goals Service (data aggregation)
- Resource Service (optional insights)
- PostgreSQL or MongoDB (Analytics DB)
- API Gateway
- Eureka Server

#### Service Boundaries

The Analytics Service is responsible only for reporting and insights.

It must not manage:

- Creating or modifying job applications
- Interview data entry
- Study plan management
- Resource management
- Goal creation or updates
- Authentication or user management

## 5. Database Ownership Model

The Developer Career Hub follows a strict **Database per Service** pattern to ensure loose coupling, scalability, and independent development of microservices.

### 5.1 Principle: Database per Service

Each microservice owns and manages its own database. No microservice is allowed to directly access another service's database.

This ensures:
- Independent scaling of services
- Reduced coupling between teams/modules
- Safe schema evolution without breaking other services
- Clear ownership boundaries

### 5.2 Service Database Mapping

| Microservice | Database |
|--------------|----------|
| Auth Service | Auth DB |
| Job Service | Job DB |
| Interview Service | Interview DB |
| Study Service | Study DB |
| Resource Service | Resource DB |
| Goals Service | Goals DB |
| Analytics Service | Analytics DB |

### 5.3 Data Access Rules

- A service can only read/write its own database
- Cross-service data access is not allowed at the database level
- Any required external data must be accessed via REST APIs

### 5.4 Analytics Data Handling

The Analytics Service does not access raw databases directly.

Instead, it collects data using:
- REST API calls from other services
- Periodic aggregation (future enhancement)
- Cached or computed snapshots

This prevents tight coupling and avoids direct database dependencies across services.

### 5.5 Benefits of This Model

- Independent service deployment
- Easier debugging and maintenance
- Technology flexibility per service (future-ready)
- Better fault isolation
- Supports scaling services individually based on load

### 5.6 Future Enhancement (Event-Driven Architecture)

In future versions, data synchronization between services can be improved using:

- Kafka / RabbitMQ event streaming
- Event sourcing for key business actions
- Asynchronous data pipelines for Analytics

This will reduce direct API dependencies and improve system scalability.

## 6. Service Communication

The Developer Career Hub uses a **synchronous REST-based communication model** between microservices in the initial phase. All external requests are routed through the API Gateway, while internal service-to-service communication is restricted and controlled.

### 6.1 External Communication (Client to Backend)

All requests from the frontend are handled through the API Gateway.

**Flow:**
- React Frontend → API Gateway → Microservices

The API Gateway is responsible for:
- Routing requests to appropriate services
- Validating JWT tokens
- Enforcing security policies
- Centralizing CORS configuration

### 6.2 Internal Service Communication

Microservices may communicate with each other using REST APIs when required.

Example:
- Analytics Service → Job Service (fetch job statistics)
- Analytics Service → Interview Service (fetch interview performance data)

However:
- Direct database access between services is strictly prohibited
- Service-to-service calls should be minimized to reduce coupling

### 6.3 Authentication and JWT Propagation

- The Auth Service issues JWT tokens upon successful login
- The frontend includes the JWT in all requests to the API Gateway
- API Gateway forwards the token to downstream services
- Each service validates the JWT independently or trusts gateway validation

### 6.4 Service Discovery via Eureka

- All services register with Eureka Server at startup
- API Gateway uses Eureka to dynamically discover service locations
- Services do not hardcode URLs of other services

### 6.5 Communication Flow Example (Job Creation)

1. User sends request from React Frontend
2. Request reaches API Gateway
3. Gateway validates JWT
4. Gateway routes request to Job Service
5. Job Service processes request
6. Job Service stores data in Job DB
7. Response is returned back through API Gateway

### 6.6 Communication Rules

- Services must communicate only via REST APIs
- No direct database access across services
- Avoid circular dependencies between services
- Prefer simple request-response model for MVP

### 6.7 Future Improvement (Event-Driven Architecture)

In future iterations, synchronous communication can be enhanced with:

- Kafka event streaming for decoupled communication
- Asynchronous processing for Analytics updates
- Event-driven notifications for job/interview updates

This will improve scalability and reduce service dependencies.

## 8. Service Dependency Rules

This section defines how microservices are allowed to depend on each other in the Developer Career Hub system. The goal is to maintain loose coupling, prevent circular dependencies, and ensure clear ownership boundaries.

### 8.1 Core Rule: No Direct Database Access

- A microservice must NEVER access another service's database directly
- All cross-service communication must happen via REST APIs

### 8.2 Allowed Communication Model

Services can communicate only through:

- API Gateway (external client requests)
- REST APIs (internal service-to-service communication)

### 8.3 Dependency Direction Rules

Dependencies must follow a **unidirectional flow** where possible.

#### Auth Dependency Rule

- All services depend on Auth Service for user identity (via JWT)
- Auth Service does NOT depend on any business service

#### Analytics Dependency Rule

- Analytics Service can depend on all other services for data aggregation
- Other services must NOT depend on Analytics Service

#### Business Services Rule

The following services should remain independent of each other:

- Job Service
- Interview Service
- Study Service
- Resource Service
- Goals Service

These services should NOT call each other unless explicitly required by a use case.

### 8.4 Example Allowed Dependencies

- API Gateway → All Services
- All Services → Auth Service (via JWT validation context)
- Analytics Service → Job Service, Interview Service, Study Service, Goals Service

### 8.5 Forbidden Dependencies

- Job Service → Interview Service (direct dependency ❌)
- Study Service → Job Service (direct dependency ❌)
- Resource Service → Goals Service (direct dependency ❌)
- Any Service → Analytics Service (reverse dependency ❌)
- Any Service → another service's database ❌

### 8.6 Circular Dependency Rule

Circular dependencies are strictly prohibited.

Example of BAD design:
- Job Service → Interview Service
- Interview Service → Job Service

This creates tight coupling and must be avoided.

### 8.7 Data Ownership Rule

Each service is the **single source of truth** for its own domain data:

- Job Service → Job data
- Interview Service → Interview data
- Study Service → Study data
- Resource Service → Resource data
- Goals Service → Goals data
- Auth Service → User identity data

### 8.8 Service Interaction Principle

If a service needs data from another service:

- It must call the other service's API
- It must NOT replicate or duplicate ownership of that data

### 8.9 Future Scalability Consideration

In future enhancements, dependency complexity can be reduced using:

- Caching layer (for frequently accessed cross-service data)
- Asynchronous communication (non-MVP scope)
- Data aggregation strategies in Analytics Service

## 8. Service Dependency Rules

This section defines how microservices are allowed to depend on each other in the Developer Career Hub system. The goal is to maintain loose coupling, prevent circular dependencies, and ensure clear ownership boundaries.

### 8.1 Core Rule: No Direct Database Access

- A microservice must NEVER access another service's database directly
- All cross-service communication must happen via REST APIs

### 8.2 Allowed Communication Model

Services can communicate only through:

- API Gateway (external client requests)
- REST APIs (internal service-to-service communication)

### 8.3 Dependency Direction Rules

Dependencies must follow a **unidirectional flow** where possible.

#### Auth Dependency Rule

- All services depend on Auth Service for user identity (via JWT)
- Auth Service does NOT depend on any business service

#### Analytics Dependency Rule

- Analytics Service can depend on all other services for data aggregation
- Other services must NOT depend on Analytics Service

#### Business Services Rule

The following services should remain independent of each other:

- Job Service
- Interview Service
- Study Service
- Resource Service
- Goals Service

These services should NOT call each other unless explicitly required by a use case.

### 8.4 Example Allowed Dependencies

- API Gateway → All Services
- All Services → Auth Service (via JWT validation context)
- Analytics Service → Job Service, Interview Service, Study Service, Goals Service

### 8.5 Forbidden Dependencies

- Job Service → Interview Service (direct dependency ❌)
- Study Service → Job Service (direct dependency ❌)
- Resource Service → Goals Service (direct dependency ❌)
- Any Service → Analytics Service (reverse dependency ❌)
- Any Service → another service's database ❌

### 8.6 Circular Dependency Rule

Circular dependencies are strictly prohibited.

Example of BAD design:
- Job Service → Interview Service
- Interview Service → Job Service

This creates tight coupling and must be avoided.

### 8.7 Data Ownership Rule

Each service is the **single source of truth** for its own domain data:

- Job Service → Job data
- Interview Service → Interview data
- Study Service → Study data
- Resource Service → Resource data
- Goals Service → Goals data
- Auth Service → User identity data

### 8.8 Service Interaction Principle

If a service needs data from another service:

- It must call the other service's API
- It must NOT replicate or duplicate ownership of that data

### 8.9 Future Scalability Consideration

In future enhancements, dependency complexity can be reduced using:

- Caching layer (for frequently accessed cross-service data)
- Asynchronous communication (non-MVP scope)
- Data aggregation strategies in Analytics Service

## 9. Summary

The Developer Career Hub is designed as a production-ready microservices-based SaaS application following modern software architecture principles.

The system is organized into independent business domains, where each microservice owns its business logic, data, and responsibilities. Communication between services is performed through REST APIs, while all client requests are routed through the API Gateway. Service discovery is handled by Eureka Server, enabling dynamic service registration and reducing infrastructure coupling.

The architecture follows the following key principles:

- Single Responsibility Principle (SRP)
- Domain-Driven Design (DDD)
- Database per Service
- Loose Coupling
- High Cohesion
- Independent Deployment
- Stateless Authentication using JWT
- REST-based Service Communication
- API Gateway Pattern
- Service Discovery using Eureka

By clearly defining service boundaries and ownership, the system becomes easier to develop, test, deploy, and maintain. This design also provides a strong foundation for future enhancements while keeping the current MVP architecture simple and maintainable.
