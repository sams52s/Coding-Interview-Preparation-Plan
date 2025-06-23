# 8. Frontend Communication & UI Gateway Patterns

#### Overview
This document provides a comprehensive guide to integrating Spring Boot with frontend frameworks and implementing UI gateway patterns. It covers RESTful API design, CORS, security, API gateway strategies, error handling, performance, testing, deployment, and best practices for seamless frontend-backend communication. The goal is to equip developers with practical patterns, configurations, and examples to build scalable, secure, and maintainable frontend-backend integrations.

---

## Table of Contents

1. [Frontend Integration Patterns](#frontend-integration-patterns)  
2. [Serving Static Content](#serving-static-content)  
3. [RESTful API Design for Frontend](#restful-api-design-for-frontend)  
4. [CORS Configuration](#cors-configuration)  
5. [Authentication & Security](#authentication--security)  
6. [UI Gateway Patterns](#ui-gateway-patterns)  
7. [API Versioning & Documentation](#api-versioning--documentation)  
8. [Error Handling & Response Structure](#error-handling--response-structure)  
9. [Performance Optimization](#performance-optimization)  
10. [Frontend Deployment Strategies](#frontend-deployment-strategies)  
11. [Testing Frontend-Backend Integration](#testing-frontend-backend-integration)  
12. [Best Practices & Patterns](#best-practices--patterns)  
13. [References](#references)  

---

## 1. Frontend Integration Patterns

### Overview
Frontend integration patterns define how the frontend communicates with backend services. Spring Boot offers multiple options to expose data and enable real-time communication, each suited to different use cases.

### Common Patterns

| Pattern       | Description                                                                                   | Use Cases                                         | Pros                                               | Cons                                             |
|---------------|-----------------------------------------------------------------------------------------------|--------------------------------------------------|----------------------------------------------------|--------------------------------------------------|
| REST APIs     | Standard HTTP-based CRUD endpoints exposing resources.                                       | CRUD apps, mobile apps, simple integrations       | Widely supported, cacheable, easy to test          | Over-fetching, multiple round-trips               |
| GraphQL       | Query language for APIs allowing clients to request exactly what they need.                   | Complex data queries, multiple nested resources   | Efficient data fetching, single endpoint            | Learning curve, caching complexity                 |
| WebSockets    | Persistent, full-duplex communication channel for real-time data exchange.                    | Notifications, chat apps, live dashboards         | Low latency, push-based updates                      | More complex, stateful connections                  |
| API Aggregation | Combines multiple backend services into a unified API for frontend consumption.              | Microservices architecture, composite UIs         | Simplifies frontend, reduces calls                   | Adds latency, complexity in gateway                  |

### REST API Example

```java
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @GetMapping
    public List<UserDTO> getAllUsers() {
        // Fetch users from service layer
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
```

### GraphQL Example

```java
@GraphQlController
public class UserGraphQLController {

    @QueryMapping
    public UserDTO userById(@Argument Long id) {
        return userService.getUserById(id);
    }

    @MutationMapping
    public UserDTO createUser(@Argument UserInput userInput) {
        return userService.createUser(userInput);
    }
}
```

### WebSocket Configuration with Security

```java
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("https://your-frontend-domain.com")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/queue");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
```

### WebSocket Security Example

```java
@Configuration
@EnableWebSocketSecurity
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {

    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
            .simpDestMatchers("/app/**").authenticated()
            .anyMessage().denyAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true; // Disable CSRF for WebSocket if needed
    }
}
```

---

## 2. Serving Static Content

### Overview
Spring Boot facilitates serving static frontend assets such as HTML, CSS, JavaScript, and images without additional configuration. This is essential for Single Page Applications (SPAs) or traditional multi-page apps.

### Location of Static Assets

| Directory                         | Purpose                                      |
|----------------------------------|----------------------------------------------|
| `src/main/resources/static`       | Default location for static assets             |
| `src/main/resources/public`       | Alternative location for static assets         |
| `src/main/resources/META-INF/resources` | Used for serving static content from JAR dependencies |

### Behavior

- Files placed in these directories are served under the root context path (`/`).
- For example, `src/main/resources/static/index.html` is accessible at `http://localhost:8080/index.html`.

### SPA Fallback Routing

SPAs often handle routing client-side. To support browser refresh or deep linking, backend should forward unmatched routes to `index.html`.

```java
@Controller
public class SpaController {

    @RequestMapping(value = "/{path:^(?!api|ws|static).*$}/**")
    public String forward() {
        return "forward:/index.html";
    }
}
```

This pattern excludes API, WebSocket, and static resource paths.

### Advanced Example: Serving Assets with Cache Control

```java
@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
            .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS));
    }
}
```

### Use Cases

- Hosting React, Angular, Vue SPA bundles.
- Serving images, fonts, and other assets.
- Integrating legacy static pages alongside APIs.

---

## 3. RESTful API Design for Frontend

### Principles

- **Resource-Oriented:** Use nouns for URIs representing entities.
- **HTTP Methods:** Use GET (read), POST (create), PUT/PATCH (update), DELETE (remove).
- **Stateless:** Each request contains all info needed.
- **Use Status Codes:** Indicate success or error clearly.
- **DTOs:** Use Data Transfer Objects to decouple API from internal models.
- **Pagination & Filtering:** Support scalable data retrieval.

### URI Design Examples

| Operation           | HTTP Method | URI                         | Description                  |
|---------------------|-------------|-----------------------------|------------------------------|
| Retrieve all users   | GET         | `/api/v1/users`             | List all users               |
| Retrieve user by ID  | GET         | `/api/v1/users/{id}`        | Get specific user           |
| Create new user     | POST        | `/api/v1/users`             | Add a user                  |
| Update user         | PUT         | `/api/v1/users/{id}`        | Replace user data           |
| Partial update user | PATCH       | `/api/v1/users/{id}`        | Update partial data         |
| Delete user         | DELETE      | `/api/v1/users/{id}`        | Remove user                 |

### Pagination & Filtering Example

```java
@GetMapping("/api/v1/users")
public Page<UserDTO> getUsers(
        @RequestParam Optional<String> name,
        Pageable pageable) {
    return userService.findUsers(name.orElse(null), pageable);
}
```

### Advanced: Sorting and Filtering

```java
@GetMapping("/api/v1/products")
public Page<ProductDTO> getProducts(
        @RequestParam Optional<String> category,
        @RequestParam Optional<Double> minPrice,
        @RequestParam Optional<Double> maxPrice,
        Pageable pageable) {
    return productService.findProducts(category, minPrice, maxPrice, pageable);
}
```

### Response Wrapping

For consistent frontend consumption, wrap responses with metadata.

```json
{
  "data": [...],
  "page": 1,
  "size": 20,
  "totalElements": 100,
  "totalPages": 5
}
```

---

## 4. CORS Configuration

### Overview
Cross-Origin Resource Sharing (CORS) allows frontend apps running on different origins (domain, port, or protocol) to access backend APIs securely.

### Key Concepts

| Term           | Description                          |
|----------------|------------------------------------|
| Origin         | Protocol + domain + port of client |
| Preflight      | OPTIONS request to check permissions|
| Allowed Methods| HTTP methods permitted              |
| Allowed Headers| Headers permitted in requests       |
| Credentials    | Cookies, HTTP authentication info  |

### Global CORS Configuration Example

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://frontend.example.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```

### Fine-Grained CORS with `@CrossOrigin`

```java
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "https://frontend.example.com", maxAge = 3600)
public class UserController {
    // ...
}
```

### Complex CORS Example (Multiple Origins, Headers)

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("https://app1.example.com", "https://app2.example.com")
            .allowedMethods("GET", "POST")
            .allowedHeaders("Authorization", "X-Custom-Header")
            .exposedHeaders("X-Response-Time")
            .allowCredentials(true)
            .maxAge(1800);
    }
}
```

### CORS vs Security Headers

| Header                | Purpose                              | Notes                                  |
|-----------------------|------------------------------------|----------------------------------------|
| `Access-Control-Allow-Origin` | Allowed origins for cross-origin requests | Must not be `*` if credentials allowed |
| `Access-Control-Allow-Credentials` | Whether cookies are sent in cross-origin requests | Set to `true` for cookie-based auth     |
| `Access-Control-Allow-Methods` | Allowed HTTP methods                | Should match frontend usage             |
| `Access-Control-Allow-Headers` | Allowed custom headers             | Include Authorization for JWT            |

### Troubleshooting Tips

- Ensure preflight OPTIONS requests are handled.
- Avoid `allowedOrigins("*")` with credentials.
- Match frontend request headers and methods with backend CORS config.

---

## 5. Authentication & Security

### Overview
Secure communication between frontend and backend is critical. Common approaches include JWT and OAuth2 for stateless authentication. Spring Security integrates well to protect APIs and manage tokens.

### JWT Authentication Flow

| Step                     | Description                                  |
|--------------------------|----------------------------------------------|
| User Login               | Frontend sends credentials to `/login` endpoint |
| Backend Authenticates    | Validates and issues JWT token               |
| Token Storage            | Frontend stores token (localStorage or HttpOnly cookie) |
| Subsequent Requests      | Frontend sends JWT in `Authorization` header or cookie |
| Backend Validates Token  | Checks token validity and user roles         |

### JWT with HttpOnly Cookie (Recommended for Security)

```java
@PostMapping("/api/auth/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
    String jwt = authService.authenticateAndGenerateToken(request);
    
    ResponseCookie cookie = ResponseCookie.from("JWT_TOKEN", jwt)
        .httpOnly(true)
        .secure(true)
        .path("/")
        .maxAge(Duration.ofHours(1))
        .sameSite("Strict")
        .build();

    response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    return ResponseEntity.ok(new JwtResponse("Login successful"));
}
```

### Frontend Token Storage Best Practices

| Storage Type      | Security Level          | Accessibility               | Use Case                       |
|-------------------|------------------------|-----------------------------|--------------------------------|
| HttpOnly Cookie   | High (not accessible via JS) | Sent automatically with requests | Sensitive tokens, CSRF protection |
| localStorage      | Low (accessible via JS) | Manual token management      | Simple SPAs, but vulnerable to XSS |
| sessionStorage    | Medium (cleared on tab close) | Manual token management      | Temporary sessions             |

### Protecting Against CSRF

- Use `SameSite=Strict` or `Lax` cookies.
- Enable CSRF tokens for state-changing requests.
- For stateless APIs with JWT in headers, CSRF risk is minimal.

### Role-Based Access Control (RBAC)

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
          .antMatchers("/api/admin/**").hasRole("ADMIN")
          .antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
          .anyRequest().authenticated()
          .and()
          .oauth2ResourceServer().jwt();
    }
}
```

Use `@PreAuthorize("hasRole('ADMIN')")` on methods to restrict access.

### OAuth2 Integration

- Use Spring Security OAuth2 Client for login via providers (Google, Facebook).
- Use OAuth2 Resource Server to protect APIs with JWT tokens issued by authorization server.

---

## 6. UI Gateway Patterns

### Overview
UI Gateway patterns centralize API routing, aggregation, security, and transformation. They simplify frontend interactions with multiple backend microservices.

### Common Gateway Types

| Gateway Type      | Description                                       | Use Cases                          | Pros                               | Cons                          |
|-------------------|-------------------------------------------------|----------------------------------|-----------------------------------|-------------------------------|
| API Gateway       | Routes requests, applies security, rate limits | Microservices, multi-API frontend | Centralized control, security     | Single point of failure       |
| Backend for Frontend (BFF) | Customizes API for specific frontend needs | Mobile apps, SPA specific APIs    | Tailored responses, reduces frontend logic | Additional maintenance overhead |

### Spring Cloud Gateway Example (YAML)

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/v1/users/**
          filters:
            - AddRequestHeader=X-Gateway, SpringCloudGateway
            - RewritePath=/api/v1/users/(?<segment>.*), /$\{segment}
```

### Request Transformation Examples

- Add or remove headers.
- Modify request paths.
- Modify response payloads (with custom filters).

### Rate Limiting & Throttling

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: rate-limited-route
          uri: lb://some-service
          predicates:
            - Path=/api/v1/some-resource/**
          filters:
            - name: RequestRateLimiter
              args:
                redis-rate-limiter.replenishRate: 10
                redis-rate-limiter.burstCapacity: 20
```

### Service Discovery Integration

- Use Eureka or Consul for dynamic service registration.
- Gateway uses logical service names (`lb://user-service`) with load balancing.

### CORS at Gateway Level

Configure CORS globally at gateway to simplify backend services.

```yaml
spring:
  cloud:
    gateway:
      globalcors:
        corsConfigurations:
          '[/**]':
            allowedOrigins: "https://frontend.example.com"
            allowedMethods:
              - GET
              - POST
              - PUT
              - DELETE
            allowedHeaders:
              - Authorization
              - Content-Type
            allowCredentials: true
```

---

## 7. API Versioning & Documentation

### API Versioning Strategies

| Strategy     | Description                          | Pros                         | Cons                          |
|--------------|------------------------------------|------------------------------|-------------------------------|
| URI Path     | `/api/v1/users`                    | Simple, visible               | URL changes per version        |
| Request Header | `Accept: application/vnd.myapp.v1+json` | Clean URLs                   | More complex client config    |
| Query Parameter | `/api/users?version=1`             | Easy to implement             | Can clutter URLs              |

### Best Practice: Use URI versioning for public APIs.

### Swagger/OpenAPI Integration

Add dependency:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.6.14</version>
</dependency>
```

### Basic OpenAPI Config

```java
@Configuration
@OpenAPIDefinition(
    info = @Info(title = "My API", version = "v1", description = "API documentation")
)
public class OpenApiConfig {}
```

### Access API Docs

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

### Advanced: Grouping APIs by version

```java
@Bean
public GroupedOpenApi apiV1() {
    return GroupedOpenApi.builder()
        .group("v1")
        .pathsToMatch("/api/v1/**")
        .build();
}
```

### API Explorer

Interactive API documentation enables frontend teams to test endpoints and understand contracts.

---

## 8. Error Handling & Response Structure

### Standardizing Error Responses

A consistent error response structure improves frontend error handling.

```json
{
  "timestamp": "2024-06-01T12:00:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed for field 'email'",
  "path": "/api/v1/users"
}
```

### Global Exception Handling with `@ControllerAdvice`

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String message = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message,
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
```

### ErrorResponse DTO

```java
public class ErrorResponse {
    private Instant timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    // Constructors, getters, setters omitted for brevity
}
```

### Frontend Error Handling Recommendations

- Display user-friendly messages.
- Handle HTTP status codes gracefully.
- Retry transient errors.
- Log errors for diagnostics.

---

## 9. Performance Optimization

### GZIP Compression

Enable compression to reduce payload size:

```yaml
server:
  compression:
    enabled: true
    mime-types: application/json,application/xml,text/html,text/xml,text/plain
    min-response-size: 1024
```

**Impact:** Reduces bandwidth and improves perceived performance, especially for large JSON payloads.

### HTTP Caching Headers

Set cache headers for static and API responses to minimize redundant data transfer.

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/")
            .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS));
}
```

For APIs:

```java
@GetMapping("/api/v1/products/{id}")
public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
    ProductDTO product = productService.getProduct(id);
    return ResponseEntity.ok()
            .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
            .body(product);
}
```

### Minimize Payloads

- Use DTOs to exclude unnecessary fields.
- Support partial responses or field selection.
- Avoid nested objects if not needed.

### Pagination

Limit data returned per request to improve response times and reduce memory.

### Connection Pooling

Configure datasource connection pool for high concurrency:

```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 50
      minimum-idle: 10
      idle-timeout: 30000
      max-lifetime: 1800000
```

### Profiling and Monitoring

Use tools like Spring Boot Actuator, Micrometer, and Prometheus to monitor API performance and tune accordingly.

---

## 10. Frontend Deployment Strategies

### Static Hosting

Deploy frontend builds (e.g., React, Angular) to CDN or static hosts such as:

- AWS S3 + CloudFront
- Netlify
- Vercel
- Firebase Hosting

### Reverse Proxy

Use Nginx or Apache to serve SPA and proxy API requests:

```nginx
server {
    listen 80;
    server_name example.com;

    location / {
        root /var/www/spa;
        try_files $uri /index.html;
    }

    location /api/ {
        proxy_pass http://backend:8080/api/;
        proxy_set_header Host $host;
    }
}
```

### CI/CD Integration

- Automate frontend build in pipeline (e.g., Maven, Gradle with frontend plugins, or separate Node.js pipelines).
- Deploy frontend assets to CDN or server as part of release.
- Use environment variables injected at build or runtime for API URLs.

### Environment Variables Injection Example

- Inject API URL at build time using `.env` or `process.env`.
- Use runtime config files fetched by frontend on startup.

### SPA Deployment Best Practices

| Practice                 | Description                                   |
|--------------------------|-----------------------------------------------|
| Use Hash-Based Routing   | Avoids 404 on page refresh                      |
| Configure Fallback Routes | Backend forwards unmatched routes to index.html |
| Cache Busting             | Use hashed filenames to invalidate caches      |
| HTTPS                    | Secure frontend serving and API calls           |

---

## 11. Testing Frontend-Backend Integration

### Manual API Testing

- Use Postman or Insomnia to verify API endpoints.
- Test authentication flows and error scenarios.

### Automated Integration Tests

Use `MockMvc` or `WebTestClient` for backend API tests:

```java
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/api/v1/users"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isArray());
    }
}
```

### Contract Testing with Spring Cloud Contract

- Define contracts as Groovy DSL or YAML.
- Generate stubs for frontend and backend.
- Ensure frontend and backend adhere to API contracts.

Example contract:

```groovy
Contract.make {
    request {
        method 'GET'
        url '/api/v1/users/1'
    }
    response {
        status 200
        body(
            id: 1,
            name: "John Doe"
        )
        headers {
            contentType(applicationJson())
        }
    }
}
```

### End-to-End (E2E) Testing

- Use Cypress, Selenium, or Playwright.
- Automate UI interactions and verify backend integration.
- Test authentication, error handling, and data flows.

Example Cypress test:

```javascript
describe('User Login', () => {
  it('should login and display dashboard', () => {
    cy.visit('/login');
    cy.get('input[name=email]').type('user@example.com');
    cy.get('input[name=password]').type('password');
    cy.get('button[type=submit]').click();
    cy.url().should('include', '/dashboard');
    cy.contains('Welcome, user@example.com');
  });
});
```

---

## 12. Best Practices & Patterns

| Topic                 | Best Practices                                                                                              |
|-----------------------|------------------------------------------------------------------------------------------------------------|
| Token Storage         | Use HttpOnly cookies for sensitive tokens; avoid localStorage for JWT if possible to prevent XSS attacks.   |
| Rate Limiting         | Implement rate limiting at API gateway or backend level to prevent abuse and DoS attacks.                   |
| SPA Deployment        | Use fallback routing, cache busting, and HTTPS for secure and reliable deployments.                         |
| CI/CD Integration     | Automate frontend builds, environment injection, and deployment alongside backend services.                 |
| API Design            | Maintain consistent naming, use standard HTTP codes, and document APIs thoroughly.                          |
| Security              | Validate and sanitize all inputs, enable HTTPS, and monitor for vulnerabilities regularly.                  |
| Monitoring            | Use centralized logging, metrics, and alerting to track API usage and errors.                               |
| Accessibility         | Ensure frontend UIs comply with accessibility standards (WCAG) and are responsive across devices.           |

---

## 13. References

- [Baeldung: Spring Boot with React](https://www.baeldung.com/spring-boot-react-full-stack)  
- [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway)  
- [Spring WebSocket](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#websocket)  
- [Spring Security Reference](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)  
- [springdoc-openapi](https://springdoc.org/)  
- [Spring GraphQL](https://spring.io/projects/spring-graphql)  
- [Spring Cloud Contract](https://spring.io/projects/spring-cloud-contract)  
- [MDN: CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/CORS)  
- [OWASP: REST Security](https://owasp.org/www-project-api-security/)  
- [Cypress](https://www.cypress.io/)  
- [Playwright](https://playwright.dev/)  
- [Netlify](https://www.netlify.com/)  
- [Vercel](https://vercel.com/)  
- [Spring Boot Actuator](https://spring.io/projects/spring-boot)  
- [Micrometer](https://micrometer.io/)  
- [Prometheus](https://prometheus.io/)  
- [Nginx Reverse Proxy](https://docs.nginx.com/nginx/admin-guide/web-server/reverse-proxy/)  
- [OAuth 2.0 and OpenID Connect](https://oauth.net/2/)  
- [JWT Best Practices](https://auth0.com/blog/critical-vulnerabilities-in-json-web-token-libraries/)  
- [HTTP Caching](https://developer.mozilla.org/en-US/docs/Web/HTTP/Caching)  
- [Spring Security OAuth2](https://spring.io/projects/spring-security-oauth)  
- [Spring WebFlux and WebSocket Security](https://spring.io/guides/gs/messaging-stomp-websocket/)  

---