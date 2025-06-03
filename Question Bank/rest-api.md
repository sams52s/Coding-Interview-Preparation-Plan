## 🌐 REST API Design

### 1. REST Principles

**Question:** What are the fundamental principles of REST, and why are they important in API design?

**Answer:**  
REST (Representational State Transfer) is an architectural style for designing networked applications. The key principles include:

- **Statelessness:** Each request from client to server must contain all the information needed to understand and process the request. The server does not store any session state about the client.
- **Client-Server Architecture:** Separation of concerns between client and server improves scalability and portability.
- **Uniform Interface:** Resources are identified via URIs, and standard HTTP methods (GET, POST, PUT, DELETE) are used to perform operations.
- **Cacheability:** Responses must define themselves as cacheable or not to improve performance.
- **Layered System:** The API can be composed of multiple layers, improving scalability and security.
- **Code on Demand (optional):** Servers can extend client functionality by transferring executable code.

These principles ensure APIs are scalable, maintainable, and efficient.

**Real-world scenario:**  
An e-commerce platform uses REST APIs to allow clients (web/mobile apps) to retrieve product information, place orders, and manage user accounts. Statelessness ensures that each API call is independent, enabling easy scaling of backend services.

**Coding example:**  
```http
GET /products/123 HTTP/1.1
Host: api.example.com
Accept: application/json
```

Response:
```json
{
  "id": 123,
  "name": "Wireless Mouse",
  "price": 29.99,
  "stock": 100
}
```

**Follow-up question:** How does statelessness impact the design of authentication mechanisms in REST APIs?

**Answer:**  
Since REST APIs are stateless, authentication information must be included in every request, typically via tokens (e.g., JWT) or API keys. This avoids server-side session storage, allowing horizontal scaling and reducing server complexity.

---

### 2. HTTP Status Codes

**Question:** How should HTTP status codes be used effectively in REST API responses?

**Answer:**  
HTTP status codes communicate the result of an API request. Using them correctly improves client understanding and error handling. Commonly used codes include:

- **200 OK:** Request succeeded (GET, PUT, DELETE).
- **201 Created:** Resource successfully created (POST).
- **204 No Content:** Request succeeded but no content to return.
- **400 Bad Request:** Client sent an invalid request.
- **401 Unauthorized:** Authentication required or failed.
- **403 Forbidden:** Authenticated but not authorized.
- **404 Not Found:** Resource does not exist.
- **500 Internal Server Error:** Server encountered an error.

**Real-world scenario:**  
When a user attempts to retrieve a non-existent product, the API should return 404 Not Found, letting the client know the resource is unavailable.

**Coding example:**  
```java
@GetMapping("/products/{id}")
public ResponseEntity<Product> getProduct(@PathVariable Long id) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
        return ResponseEntity.ok(product.get());
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
```

**Follow-up question:** What are the benefits of using 4xx vs 5xx status codes?

**Answer:**  
4xx codes indicate client errors, helping clients correct their request. 5xx codes indicate server errors, signaling issues that clients cannot fix. Distinguishing these helps in debugging and improving user experience.

---

### 3. Centralized Exception Handling (@ControllerAdvice)

**Question:** What is centralized exception handling in Spring Boot, and how does @ControllerAdvice facilitate it?

**Answer:**  
Centralized exception handling allows handling exceptions globally rather than in each controller method. `@ControllerAdvice` in Spring Boot is a specialization of `@Component` that handles exceptions across the whole application in one place.

This improves code readability, reduces boilerplate, and ensures consistent error responses.

**Real-world scenario:**  
When a user submits invalid data, the API returns a structured error message with a proper HTTP status code, handled uniformly across all controllers.

**Coding example:**  
```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        ErrorResponse error = new ErrorResponse("VALIDATION_FAILED", "Invalid input");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
```

**Follow-up question:** How can you customize error responses to include additional information like timestamps or error codes?

**Answer:**  
You can create a custom error response class with fields for timestamp, error code, message, and details. Populate these fields in the exception handler methods before returning the response.

---

### 4. API Versioning

**Question:** Why is API versioning important, and what are common strategies to implement it?

**Answer:**  
API versioning allows you to evolve your API without breaking existing clients. It enables backward compatibility and smooth transitions between versions.

Common versioning strategies:

- **URI versioning:** e.g., `/api/v1/products`
- **Request parameter versioning:** e.g., `/api/products?version=1`
- **Header versioning:** e.g., `Accept: application/vnd.example.v1+json`
- **Content negotiation**

**Real-world scenario:**  
An API provider releases a new version of the product API with added fields. Existing clients continue using v1, while new clients migrate to v2.

**Coding example:**  
```java
@RestController
@RequestMapping("/api/v1/products")
public class ProductV1Controller {
    // v1 methods
}

@RestController
@RequestMapping("/api/v2/products")
public class ProductV2Controller {
    // v2 methods with additional fields
}
```

**Follow-up question:** What are the pros and cons of URI versioning vs header versioning?

**Answer:**  
- **URI versioning** is simple and visible but can clutter URLs and violates RESTful principles of resource identification.  
- **Header versioning** keeps URLs clean and uses HTTP standards but requires clients to set headers correctly and can be less discoverable.

---

### 5. Security (OAuth2, JWT, API keys)

**Question:** What are common security mechanisms used in REST APIs, and how do OAuth2, JWT, and API keys differ?

**Answer:**  
Security is essential to protect APIs from unauthorized access. Common mechanisms include:

- **API Keys:** Simple tokens passed in headers or query parameters. Easy to implement but less secure as they do not identify users.
- **OAuth2:** An authorization framework that allows third-party applications to access user data without exposing credentials. Supports various flows like Authorization Code, Client Credentials.
- **JWT (JSON Web Token):** A compact, self-contained token that includes claims and is digitally signed. Often used with OAuth2 for stateless authentication.

**Real-world scenario:**  
A social media platform uses OAuth2 to allow third-party apps to post on behalf of users securely. The platform issues JWTs for session management.

**Coding example:**  
```java
// Example of extracting JWT token from Authorization header
@GetMapping("/profile")
public ResponseEntity<UserProfile> getProfile(@RequestHeader("Authorization") String authHeader) {
    String token = authHeader.substring(7); // Remove "Bearer "
    if (jwtService.validateToken(token)) {
        UserProfile profile = userService.getProfileFromToken(token);
        return ResponseEntity.ok(profile);
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
```

**Follow-up question:** How do you secure REST APIs against common threats like CSRF and replay attacks?

**Answer:**  
- Use HTTPS to encrypt data in transit.  
- Implement token expiration and refresh mechanisms.  
- Use CSRF tokens or same-origin policies for browser clients.  
- Validate tokens and implement nonce or timestamp checks to prevent replay attacks.  
- Apply rate limiting and IP filtering.

---

### 6. Advanced API Security Implementation

**Question:** How would you implement a comprehensive security solution for a REST API with OAuth2 and JWT?

**Answer:** Implement multiple layers of security with OAuth2 for authorization flow and JWT for token management.

**Implementation Example:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter())
            .and()
            .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        return NimbusJwtDecoder.withJwkSetUri(properties.getJwt().getJwkSetUri())
            .jwtProcessorCustomizer(processor -> {
                processor.setJWSTypeVerifier(new DefaultJOSEObjectTypeVerifier<>(JOSEObjectType.JWT));
            })
            .build();
    }
}

@Service
@Slf4j
public class TokenService {
    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(authentication.getName())
            .claim("scope", getScopes(authentication))
            .build();
        
        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    @Cacheable(value = "tokenValidation", key = "#token")
    public boolean validateToken(String token) {
        try {
            Jwt jwt = decoder.decode(token);
            return !jwt.getExpiresAt().isBefore(Instant.now());
        } catch (JwtException e) {
            log.error("Token validation failed: {}", e.getMessage());
            return false;
        }
    }
}
```

**Follow-up Q1:** How would you implement token refresh mechanism?

**Answer with Implementation:**
```java
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final TokenService tokenService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return refreshTokenService.findByToken(request.getRefreshToken())
            .map(refreshToken -> {
                String token = tokenService.generateToken(refreshToken.getUser());
                return ResponseEntity.ok(new TokenResponse(token, refreshToken.getToken()));
            })
            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String token) {
        refreshTokenService.revokeByAccessToken(token.substring(7));
        return ResponseEntity.ok().build();
    }
}

@Service
@Transactional
public class RefreshTokenService {
    private final RefreshTokenRepository repository;
    
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = RefreshToken.builder()
            .user(username)
            .token(UUID.randomUUID().toString())
            .expiryDate(Instant.now().plus(30, ChronoUnit.DAYS))
            .build();
        
        return repository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token)
            .filter(refreshToken -> !refreshToken.getExpiryDate().isBefore(Instant.now()));
    }
}
```

**Follow-up Q2:** How would you implement rate limiting for the API?

**Answer with Implementation:**
```java
@Component
public class RateLimitingFilter extends OncePerRequestFilter {
    private final RedisTemplate<String, Integer> redisTemplate;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain chain) throws IOException, ServletException {
        String key = extractKey(request);
        String bucket = "rate:" + key;
        
        Integer requests = redisTemplate.opsForValue().get(bucket);
        if (requests == null) {
            redisTemplate.opsForValue().set(bucket, 1, 1, TimeUnit.MINUTES);
        } else if (requests >= 100) { // 100 requests per minute
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            return;
        } else {
            redisTemplate.opsForValue().increment(bucket);
        }
        
        chain.doFilter(request, response);
    }
    
    private String extractKey(HttpServletRequest request) {
        // Use IP or token-based identification
        return Optional.ofNullable(request.getHeader("Authorization"))
            .orElse(request.getRemoteAddr());
    }
}
```

This Q&A format covers the essential REST API design topics with comprehensive explanations, practical scenarios, code examples, and deeper insights through follow-up questions.