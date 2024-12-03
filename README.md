## Environment Setup

### Prerequisites
Ensure that the following software is installed on your machine:

- **JDK 17** or later: The project is built with Java 17 as specified in the `pom.xml`.
- **Maven**: Maven is used for building the project and managing dependencies.
- **MySQL**: MySQL is used as the database backend for this project.

### Dependencies

This project uses several key dependencies:

1. **Spring Boot 3.3.6**: The project is based on the Spring Boot framework version 3.3.6.
   - `spring-boot-starter-web`: For building web applications with Spring MVC.
   - `spring-boot-starter-thymeleaf`: To support Thymeleaf templating engine.
   - `spring-boot-devtools`: For automatic application reloads during development.
   - `spring-boot-starter-test`: For testing the application.

2. **MySQL**: 
   - `mysql-connector-j`: The MySQL JDBC connector for database connectivity.

3. **MyBatis**: 
   - `mybatis-spring-boot-starter`: To simplify integration between MyBatis and Spring Boot.

4. **Druid**: 
   - `druid`: A database connection pool for MySQL.

5. **Lombok**: 
   - `lombok`: For reducing boilerplate code (optional in the project).

6. **JSON**:
   - `json`: To handle JSON operations in the project.

### How to Run the Project

1. **Clone the repository**:
   ```bash
   git clone <your-repository-url>
