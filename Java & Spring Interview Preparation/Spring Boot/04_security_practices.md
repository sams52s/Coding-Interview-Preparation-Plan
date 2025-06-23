# 4. Security Practices & Implementation

#### Overview
All security-related topics, including CORS, authentication, and security configuration.

#### Subtopics
- **Spring Security Implementation**
  ```java
  @Configuration
  @EnableWebSecurity
  public class SecurityConfig {
      // Security configuration
  }
  ```
- **CORS Configuration**
  ```java
  @Configuration
  public class WebConfig implements WebMvcConfigurer {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
          registry.addMapping("/api/**")
              .allowedOrigins("http://localhost:3000")
              .allowedMethods("GET", "POST", "PUT", "DELETE");
      }
  }
  ```
- OAuth2 with Keycloak
- Multi-factor Authentication
- Audit Logging System

#### External Links
- [Spring Security Docs](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Baeldung: Spring Security](https://www.baeldung.com/security-spring)

---
