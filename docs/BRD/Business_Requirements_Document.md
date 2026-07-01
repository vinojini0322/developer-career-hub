# Business Requirements Document (BRD)

## Project

Developer Career Hub
---

## Version

1.0

---

## Status

Draft

---

## Author

Vinojini Paramasivam

---

## Version History

| Version | Date | Author | Description |
|----------|------------|----------------------|---------------------------|
| 1.0 | 2026-07-01 | Vinojini Paramasivam | Initial draft |
| 1.1 | 2026-07-02 | Vinojini Paramasivam | Added business problem and objectives |
| 2.0 | 2026-07-10 | Vinojini Paramasivam | Finalized business requirements document |

## Table of Contents

- [1. Introduction](#1-introduction)
- [2. Purpose](#2-purpose)
- [3. Business Problem](#3-business-problem)
- [4. Business Objectives](#4-business-objectives)
- [5. Stakeholders](#5-stakeholders)
- [6. Scope](#6-scope)
- [7. Functional Requirements](#7-functional-requirements)
- [8. Non-Functional Requirements](#8-non-functional-requirements)
- [9. Assumptions](#9-assumptions)
- [10. Constraints](#10-constraints)
- [11. Future Enhancements](#11-future-enhancements)

## 1. Introduction

Developer Career Hub is a production-grade Software as a Service (SaaS) platform designed to help software developers manage their career journey in one centralized application.

The platform enables users to track study progress, manage job applications, document interview experiences, organize learning resources, set career goals, and gain insights through analytics.

The system is being developed using a microservices architecture following modern software engineering practices.

## 2. Purpose

The purpose of this project is to provide developers with a centralized platform for managing learning activities, job search progress, interview preparation, and career development.

The application aims to replace the need for multiple disconnected tools such as spreadsheets, note-taking applications, bookmarks, and personal reminders.

## 3. Business Problem

Software developers typically use multiple disconnected tools to manage their career activities, such as spreadsheets for job applications, note-taking apps for interview preparation, and browser bookmarks for saving learning resources.

This fragmented approach makes it difficult to maintain a clear and organized view of career progress. Important information such as job contacts, interview notes, and study topics can be easily forgotten or lost across different platforms.

As a result, developers often miss follow-ups with recruiters, forget key interview questions, and struggle to track learning progress effectively during interview preparation.

## 4. Business Objectives

The primary objectives of the Developer Career Hub system are to:

- Provide a centralized platform for tracking job applications across multiple companies, including their current status in the hiring process.
- Enable users to manage and monitor interview progress across different stages such as applied, interview scheduled, in progress, offered, or rejected.
- Help users prepare effectively for interviews by organizing study materials, topics, and preparation notes in one place.
- Allow users to record interview experiences, including feedback and areas of improvement, to identify what went wrong and enhance future performance.
- Improve career planning by giving users clear visibility into their learning progress, job search activities, and interview outcomes.

## 5. Stakeholders

- **Software Professionals (End Users):**  
  Individuals in the software engineering and IT domain (such as software engineers, QA engineers, DevOps engineers, data engineers, and students) who are actively preparing for jobs or managing their career growth. They use the platform to track job applications, interviews, learning progress, and career goals.

- **System Developer / Maintainer:**  
  The creator and maintainer of the system responsible for designing, developing, deploying, and maintaining the application. This includes implementing new features, fixing issues, and ensuring system reliability.

- **Future Stakeholders (Planned):**  
  Potential future users such as mentors, recruiters, or administrators who may interact with enhanced features like analytics dashboards, candidate insights, or platform management tools.

## 6. Scope

The scope of the Developer Career Hub defines the core features included in the Minimum Viable Product (MVP) and the planned enhancements for future releases.

### 6.1 In Scope (MVP)

The initial version of the system will include the following core modules:

- **Job Application Tracker:**  
  Allows users to track companies they have applied to, along with application status such as applied, interview scheduled, in progress, offered, or rejected.

- **Interview Tracker:**  
  Enables users to record interview experiences, rounds, questions, feedback, and outcomes for better preparation and analysis.

- **Learning Resource Bookmarking:**  
  Allows users to save, organize, and categorize learning resources such as articles, videos, and documentation links.

- **Career Goal Management:**  
  Enables users to define career goals and track progress toward achieving them.

- **Learning Progress Tracking:**  
  Helps users track their learning activities and monitor progress over time.

---

### 6.2 Out of Scope (Future Enhancements)

The following features are not part of the MVP and will be considered for future releases:

- AI-powered interview preparation and suggestions
- AI-based resume analysis and recommendations
- Advanced analytics and predictive insights
- Notification and reminder system
- Payment and subscription system (Premium features)
- Calendar integration

## 7. Functional Requirements

### 7.0 User Management Module

- The system shall allow users to register and login.
- The system shall support JWT-based authentication.
- The system shall support role-based access control (RBAC).
- The system shall allow users to manage profile information.

### 7.1 Study Tracker Module

The Study Tracker module shall allow users to manage and monitor their learning activities effectively.

- The system shall allow users to create study topics.
- The system shall allow users to log daily study entries.
- Each study entry shall include topic, duration, notes, and date.
- The system shall allow users to view study progress over weekly and monthly time periods.
- The system shall provide filtering options by topic and date range.
- The system shall display study history in a structured and searchable format.

---

### 7.2 Job Application Tracker Module

The Job Application Tracker module shall allow users to manage and monitor their job application pipeline in a structured workflow.

- The system shall allow users to add job applications with company details and job role information.
- The system shall track each application through a defined workflow: Applied → Online Test → Interview → Offer → Rejected.
- The system shall allow users to update the status of each job application as it progresses.
- The system shall allow users to view all job applications in a centralized dashboard.
- The system shall provide filtering and sorting options based on application status, company, and date.
- The system shall maintain a history of application status changes for tracking progress over time.

---

### 7.3 Interview Journal Module

The Interview Journal module shall allow users to record and analyze their interview experiences in detail.

- The system shall allow users to create interview entries linked to a specific job application.
- The system shall support round-wise interview tracking (e.g., Technical, System Design, HR, Final).
- The system shall allow users to store interview questions asked during each round.
- The system shall allow users to record their answers and interviewer feedback.
- The system shall allow users to rate interview difficulty.
- The system shall allow users to view and search past interview questions for preparation purposes.
- The system shall allow users to analyze interview performance across multiple interviews.

---

### 7.4 Resource Saver Module

The Resource Saver module shall allow users to collect, organize, and retrieve learning materials and interview preparation resources in a structured manner.

- The system shall allow users to save learning resources such as articles, videos, documentation links, and interview questions.
- The system shall allow users to categorize resources using tags or custom categories.
- The system shall allow users to search saved resources, including past interview questions and study materials.
- The system shall allow users to filter resources based on tags, type, or creation date.
- The system shall allow users to mark resources as important, reviewed, or completed.
- The system shall maintain a centralized repository of all saved resources for easy access.

---

### 7.5 Career Goals Module

The Career Goals module shall allow users to define, manage, and track their long-term and short-term career objectives.

- The system shall allow users to create career goals with a title, description, target date, and priority level.
- The system shall allow users to break down goals into smaller milestones or tasks.
- The system shall allow users to track progress of each goal in percentage format (% completion).
- The system shall allow users to update goal status (Not Started, In Progress, Completed, On Hold).
- The system shall allow users to update milestones and mark them as completed.
- The system shall provide a visual representation of goal progress for better tracking.

---

### 7.6 Analytics Dashboard Module

The Analytics Dashboard module shall provide users with insights into their career progress, learning behavior, and job search performance through visual and statistical summaries.

- The system shall display overall user activity summaries in a centralized dashboard.
- The system shall show study time analytics, including weekly and monthly study duration.
- The system shall identify and display the most studied topics over a selected time period.
- The system shall provide job application funnel analytics, including Applied → Interview → Offer → Rejected conversion rates.
- The system shall display interview performance metrics, including success rate and outcome distribution.
- The system shall show career goal progress, including percentage completion and milestone tracking.
- The system shall provide an activity heat map to visualize daily user engagement patterns.

## 8. Non-Functional Requirements

The system shall meet the following non-functional requirements to ensure performance, scalability, security, and maintainability.

### 8.1 Performance

- The system shall respond to user requests within acceptable time limits (target: under 2–3 seconds for standard operations).
- The system shall support efficient loading of dashboards and analytics even with increasing data volume.
- The system shall optimize database queries to handle large datasets without significant performance degradation.

---

### 8.2 Scalability

- The system shall be designed using a microservices architecture to allow independent scaling of services.
- The system shall support horizontal scaling to accommodate increasing numbers of users and data growth.
- The system shall ensure that each module (e.g., job tracker, analytics) can scale independently based on demand.

---

### 8.3 Security

- The system shall implement JWT-based authentication for secure user access.
- The system shall enforce role-based access control (RBAC) for all modules.
- The system shall ensure secure storage of sensitive data using encryption where necessary.
- The system shall protect against common security threats such as unauthorized access and data leakage.

---

### 8.4 Availability

- The system shall aim for high availability to ensure minimal downtime.
- The system shall be designed to recover gracefully from failures.
- Critical services shall be isolated to prevent total system failure.

---

### 8.5 Maintainability

- The system shall follow a modular microservices architecture to simplify maintenance and updates.
- The system shall use clean coding practices and standard design patterns.
- The system shall maintain clear documentation for all modules and APIs.
- The system shall support easy addition of new features without major system redesign.

## 9. Assumptions

- Users will have basic access to internet and a modern web browser.
- Users are familiar with basic career-related workflows such as job applications and interview processes.
- The system assumes users will actively input their own data (study logs, job applications, interview notes).
- Third-party integrations (e.g., email, calendar, AI services) are not required in the MVP phase.


## 10. Constraints

- The system will initially be developed as a SaaS platform with a microservices architecture, which may increase initial development complexity.
- Limited development resources (single developer) may affect delivery speed.
- External integrations such as AI services, email notifications, and payment systems are deferred to future releases.
- The system must be designed within a reasonable cost constraint using free-tier or low-cost cloud services during development.


## 11. Future Enhancements

The following enhancements are planned for future versions of the system:

- AI-powered interview preparation and question suggestions.
- AI-based resume analysis and career recommendations.
- Email and push notification system for reminders and alerts.
- Calendar integration for interview scheduling and planning.
- Premium subscription model with advanced analytics and features.
- Mobile application (Android/iOS) for better accessibility.
