# 4. Security Practices & Implementation

#### Overview
This document provides a comprehensive guide to security practices and implementation in Spring Boot applications. It covers authentication, authorization, CORS, OAuth2, multi-factor authentication, secure coding, audit logging, error handling, monitoring, and best practices for building secure, compliant, and robust systems.

---

## Table of Contents

1. [Spring Security Implementation](#spring-security-implementation)  
2. [CORS Configuration](#cors-configuration)  
3. [Authentication & Authorization](#authentication--authorization)  
4. [OAuth2 & OpenID Connect](#oauth2--openid-connect)  
5. [Multi-factor Authentication (MFA)](#multi-factor-authentication-mfa)  
6. [Session Management](#session-management)  
7. [Audit Logging & Monitoring](#audit-logging--monitoring)  
8. [Secure Coding Practices](#secure-coding-practices)  
9. [API Security Best Practices](#api-security-best-practices)  
10. [Error Handling & Security Events](#error-handling--security-events)  
11. [Security Testing & Hardening](#security-testing--hardening)  
12. [References](#references)  

---

## 1. Spring Security Implementation

### Why This is Important
Spring Security is the de-facto standard for securing Spring Boot applications. It provides authentication, authorization, and protection against common attacks such as CSRF. Proper configuration ensures that your application endpoints are protected and only accessible by authorized users.

### Common Pitfalls
- Disabling CSRF protection without understanding the implications.
- Over-permissive endpoint access due to misconfigured antMatchers.
- Storing passwords without encoding.
- Not customizing `UserDetailsService` when using custom user models.
- Mixing stateful and stateless authentication improperly.

### Real-World Best Practices
- Use `PasswordEncoder` (e.g., BCrypt) to store passwords securely.
- Prefer stateless JWT authentication for REST APIs.
- Use method-level security annotations for fine-grained access control.
- Always enable CSRF protection for stateful web applications.
- Customize `UserDetailsService` for integration with your user database.

### Complete Example: Secure Configuration with JWT

```java
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable()
            .csrf().disable() // Disabled for stateless APIs; enable for web apps
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService())
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public UserDetailsService customUserDetailsService() {
        return new CustomUserDetailsService();
    }
}
```

### JWT Token Provider Example

```java
@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}") // 1 hour
    private long validityInMilliseconds;

    private final UserDetailsService userDetailsService;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
```

### Table: Spring Security Annotations and Their Usage

| Annotation         | Usage                                                      | Description                                                      |
|--------------------|------------------------------------------------------------|------------------------------------------------------------------|
| `@PreAuthorize`    | `@PreAuthorize("hasRole('ADMIN')")`                        | Checks authorization before method execution using SpEL          |
| `@PostAuthorize`   | `@PostAuthorize("returnObject.owner == authentication.name")` | Checks authorization after method execution                      |
| `@Secured`         | `@Secured("ROLE_USER")`                                    | Secures method based on roles (less flexible than `@PreAuthorize`)|
| `@RolesAllowed`    | `@RolesAllowed("ROLE_ADMIN")`                              | JSR-250 annotation for role-based access control                 |
| `@EnableGlobalMethodSecurity` | `@EnableGlobalMethodSecurity(prePostEnabled = true)` | Enables method-level security annotations                        |

---

## 2. CORS Configuration

### Why This is Important
Cross-Origin Resource Sharing (CORS) controls how your backend APIs can be accessed by frontend applications hosted on different origins. Proper CORS configuration is essential to prevent unauthorized domains from accessing your APIs while allowing legitimate clients.

### Common Pitfalls
- Allowing all origins (`*`) in production, which can expose your API to abuse.
- Forgetting to allow credentials when cookies or HTTP authentication are used.
- Misconfiguring HTTP methods or headers, causing frontend errors.
- Not configuring CORS on Spring Security filters, leading to blocked requests.

### Real-World Best Practices
- Restrict allowed origins to known frontend URLs.
- Explicitly specify allowed HTTP methods and headers.
- Allow credentials only if necessary.
- Use Spring Security CORS support in conjunction with WebMvcConfigurer.
- Test CORS behavior thoroughly with frontend clients.

### Complete Example: Fine-Grained CORS Configuration

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("https://frontend.example.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
            .exposedHeaders("Authorization")
            .allowCredentials(true)
            .maxAge(3600);
    }
}
```

### Enabling CORS in Spring Security

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http.cors()
        .and()
        .csrf().disable() // For stateless APIs; enable CSRF for web apps
        .authorizeRequests()
        // other configs
        ;
}

@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Arrays.asList("https://frontend.example.com"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    configuration.setAllowCredentials(true);
    configuration.setMaxAge(3600L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/api/**", configuration);
    return source;
}
```

---

## 3. Authentication & Authorization

### Why This is Important
Authentication verifies user identity, while authorization determines their access rights. Proper implementation is critical to ensure only legitimate users can access protected resources and perform allowed actions.

### Common Pitfalls
- Using default login forms without customization in production.
- Not validating JWT tokens properly.
- Over-permissive role assignments.
- Ignoring method-level security.
- Mixing role-based and attribute-based access control without clear policies.

### Real-World Best Practices
- Use JWT for stateless REST APIs.
- Secure login forms with HTTPS and CSRF protection.
- Implement role-based access control (RBAC) complemented by attribute-based access control (ABAC) for fine-grained policies.
- Use Spring Security annotations to enforce authorization at method level.
- Validate and parse JWT tokens in a filter before request processing.

### Complete JWT Filter Example

```java
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
```

### Table: Role vs Permission Comparison

| Aspect        | Role                                    | Permission (Authority)                    |
|---------------|-----------------------------------------|------------------------------------------|
| Definition    | A group of permissions assigned to users | Specific rights or actions granted       |
| Granularity   | Coarse-grained                         | Fine-grained                            |
| Usage         | Used to group multiple permissions    | Used to control access to specific actions |
| Example       | ROLE_ADMIN, ROLE_USER                  | READ_PRIVILEGE, WRITE_PRIVILEGE          |
| Flexibility   | Less flexible                         | More flexible and precise                |

### Table: Spring Security Annotations Summary

| Annotation       | Description                                   | Example Usage                          |
|------------------|-----------------------------------------------|--------------------------------------|
| `@PreAuthorize`  | Pre-invocation authorization using SpEL       | `@PreAuthorize("hasRole('ADMIN')")` |
| `@Secured`       | Role-based method security                      | `@Secured("ROLE_USER")`               |
| `@RolesAllowed`  | JSR-250 role-based access control               | `@RolesAllowed("ROLE_ADMIN")`         |
| `@PostAuthorize` | Post-invocation authorization                    | `@PostAuthorize("returnObject.owner == authentication.name")` |

---

## 4. OAuth2 & OpenID Connect

### Why This is Important
OAuth2 and OpenID Connect provide a standardized way to delegate authentication and authorization to trusted identity providers (IdPs) such as Google, GitHub, or Keycloak. This enables Single Sign-On (SSO), reduces password management overhead, and enhances security.

### Common Pitfalls
- Misconfiguring redirect URIs.
- Not validating tokens from providers correctly.
- Ignoring scopes and claims in tokens.
- Using outdated or deprecated Spring Security OAuth libraries.
- Not securing client secrets and credentials.

### Real-World Best Practices
- Use Spring Security's OAuth2 Client support for client-side integration.
- Validate and parse ID tokens and access tokens securely.
- Configure scopes and claims according to application needs.
- Store client secrets securely (e.g., environment variables, Vault).
- Use OpenID Connect for federated identity and user profile access.

### Complete Example: OAuth2 Client Configuration with Keycloak

#### `application.yml`

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          keycloak:
            client-id: my-client
            client-secret: ${KEYCLOAK_CLIENT_SECRET}
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            scope:
              - openid
              - profile
              - email
        provider:
          keycloak:
            issuer-uri: https://keycloak.example.com/auth/realms/myrealm
```

#### Security Configuration

```java
@Configuration
@EnableWebSecurity
public class OAuth2LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/login**", "/error").permitAll()
                .anyRequest().authenticated()
            .and()
            .oauth2Login()
                .defaultSuccessUrl("/home", true)
                .userInfoEndpoint()
                    .oidcUserService(this.oidcUserService());
    }

    private OidcUserService oidcUserService() {
        return new OidcUserService();
    }
}
```

### Resource Server Configuration Example

```java
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/api/public/**").permitAll()
                .anyRequest().authenticated();
    }
}
```

---

## 5. Multi-factor Authentication (MFA)

### Why This is Important
MFA adds an additional layer of security by requiring users to provide multiple forms of verification, reducing the risk of account compromise due to stolen credentials.

### Common Pitfalls
- Poor user experience due to complex MFA flows.
- Storing MFA secrets insecurely.
- Not handling fallback or recovery scenarios.
- Ignoring MFA in API or non-web clients.

### Real-World Best Practices
- Use Time-based One-Time Passwords (TOTP) like Google Authenticator or Authy.
- Integrate SMS or email-based codes as alternatives.
- Use third-party MFA providers or built-in MFA in identity providers (e.g., Keycloak).
- Provide clear UI/UX for MFA enrollment and verification.
- Ensure MFA is enforced on sensitive operations or high-risk users.

### Custom MFA Filter Example (Pseudocode)

```java
public class MfaAuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if user is authenticated but MFA not completed
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !mfaCompleted(auth)) {
            String mfaCode = httpRequest.getHeader("X-MFA-Code");
            if (mfaCode == null || !validateMfaCode(auth.getName(), mfaCode)) {
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "MFA required");
                return;
            }
            markMfaCompleted(auth);
        }
        chain.doFilter(request, response);
    }

    private boolean mfaCompleted(Authentication auth) {
        // Implement logic to check if MFA was completed for session/user
        return false;
    }

    private boolean validateMfaCode(String username, String code) {
        // Implement TOTP or code validation logic
        return true;
    }

    private void markMfaCompleted(Authentication auth) {
        // Mark MFA as completed in session or security context
    }
}
```

---

## 6. Session Management

### Why This is Important
Proper session management prevents attacks such as session fixation, session hijacking, and ensures users' sessions expire appropriately to reduce risk.

### Common Pitfalls
- Not enabling session fixation protection.
- Allowing unlimited concurrent sessions per user.
- Long session timeouts leading to stale sessions.
- Mixing stateful and stateless session management.

### Real-World Best Practices
- Enable session fixation protection (default in Spring Security).
- Limit concurrent sessions per user.
- Set reasonable session timeouts.
- Use stateless sessions with JWT for REST APIs.
- Invalidate sessions on logout and password changes.

### Complete Example: Session Management Configuration

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .sessionManagement()
            .sessionFixation().migrateSession()
            .maximumSessions(1)
            .maxSessionsPreventsLogin(true)
            .expiredUrl("/login?expired");
}
```

---

## 7. Audit Logging & Monitoring

### Why This is Important
Audit logging tracks security-relevant events to detect suspicious activities and support forensic analysis. Monitoring helps maintain system health and security posture.

### Common Pitfalls
- Logging sensitive data such as passwords or tokens.
- Not storing logs in a secure and immutable store.
- Ignoring failed login attempts and lockouts.
- Not integrating with centralized logging or SIEM tools.

### Real-World Best Practices
- Log authentication successes and failures.
- Use Spring Boot Actuator for monitoring endpoints.
- Store logs securely and forward to SIEM tools.
- Monitor access to sensitive endpoints.
- Use event listeners to capture security events.

### Example: Authentication Success Event Listener

```java
@Component
public class AuthenticationEventListener {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationEventListener.class);

    @EventListener
    public void handleAuthenticationSuccess(AuthenticationSuccessEvent event) {
        String username = event.getAuthentication().getName();
        logger.info("User '{}' logged in successfully at {}", username, Instant.now());
    }

    @EventListener
    public void handleAuthenticationFailure(AbstractAuthenticationFailureEvent event) {
        String username = (String) event.getAuthentication().getPrincipal();
        logger.warn("Failed login attempt for user '{}' at {}", username, Instant.now());
    }
}
```

---

## 8. Secure Coding Practices

### Why This is Important
Secure coding reduces vulnerabilities introduced during development and protects applications from common attacks such as XSS, SQL Injection, and CSRF.

### Common Pitfalls
- Not validating or sanitizing user inputs.
- Exposing sensitive data in logs or error messages.
- Disabling CSRF protection without justification.
- Using outdated or vulnerable dependencies.
- Granting excessive permissions.

### Real-World Best Practices
- Validate and sanitize all inputs.
- Use output encoding to prevent XSS.
- Enable CSRF protection for web apps.
- Never log sensitive data like passwords or tokens.
- Keep dependencies up-to-date.
- Apply least privilege principle.

---

## 9. API Security Best Practices

### Why This is Important
APIs are often the primary attack surface. Securing APIs prevents data breaches, denial of service, and unauthorized access.

### Common Pitfalls
- Not enforcing HTTPS.
- No rate limiting, allowing brute force or abuse.
- Missing security headers.
- Poor session management.
- Exposing detailed error messages.

### Real-World Best Practices
- Enforce HTTPS everywhere.
- Implement rate limiting using tools like Bucket4j.
- Set security headers to protect against common vulnerabilities.
- Invalidate sessions on logout and sensitive changes.
- Avoid exposing stack traces in error responses.

### Security Headers Table

| Header                     | Purpose                                               | Example Value                                   |
|----------------------------|-------------------------------------------------------|------------------------------------------------|
| `X-Content-Type-Options`   | Prevent MIME-sniffing                                  | `nosniff`                                       |
| `X-Frame-Options`          | Prevent clickjacking                                   | `DENY` or `SAMEORIGIN`                          |
| `Content-Security-Policy`  | Control resources the browser is allowed to load      | `default-src 'self'; script-src 'self'`        |
| `Strict-Transport-Security`| Enforce HTTPS                                         | `max-age=31536000; includeSubDomains`          |
| `Referrer-Policy`          | Control referrer information sent                      | `no-referrer`                                   |

### Example: Configuring Security Headers in Spring Security

```java
http.headers()
    .contentTypeOptions()
    .and()
    .frameOptions().deny()
    .and()
    .contentSecurityPolicy("default-src 'self'; script-src 'self'; object-src 'none';")
    .and()
    .httpStrictTransportSecurity()
        .maxAgeInSeconds(31536000)
        .includeSubDomains(true);
```

---

## 10. Error Handling & Security Events

### Why This is Important
Proper error handling prevents leakage of sensitive information and improves user experience. Security event handling enables proactive response to security incidents.

### Common Pitfalls
- Returning stack traces or sensitive info in error responses.
- Not customizing access denied or authentication entry points.
- Ignoring security events such as lockouts or password changes.

### Real-World Best Practices
- Customize access denied and authentication entry point responses.
- Log security events with relevant details.
- Provide user-friendly error messages without sensitive details.
- Use event listeners to react to security events.

### Example: Custom Access Denied Handler

```java
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
            throws IOException {
        logger.warn("Access denied: {} attempted to access {}", request.getUserPrincipal(), request.getRequestURI());
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied!");
    }
}
```

---

## 11. Security Testing & Hardening

### Why This is Important
Continuous security testing and hardening ensure that vulnerabilities are identified and remediated before exploitation.

### Common Pitfalls
- Relying solely on manual testing.
- Ignoring automated security scans.
- Leaving unused endpoints enabled.
- Exposing actuator endpoints in production.

### Real-World Best Practices
- Use static analysis tools like SonarQube and Checkmarx.
- Perform regular penetration testing with OWASP ZAP or Burp Suite.
- Harden application by disabling unused endpoints.
- Restrict actuator access to authorized users.
- Integrate security tests into CI/CD pipelines.

---

## References

- [Spring Security Docs](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Baeldung: Spring Security](https://www.baeldung.com/security-spring)
- [Spring Security OAuth2](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2)
- [Keycloak Docs](https://www.keycloak.org/docs/latest/server_admin/)
- [OWASP Top Ten](https://owasp.org/www-project-top-ten/)
- [OWASP REST Security](https://owasp.org/www-project-api-security/)
- [Spring Security GitHub](https://github.com/spring-projects/spring-security)
- [Bucket4j Rate Limiting](https://bucket4j.com/)
- [SonarQube](https://www.sonarqube.org/)
- [OWASP ZAP](https://www.zaproxy.org/)
- [Burp Suite](https://portswigger.net/burp)
- [Spring Security Reference: Session Management](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-session)
- [Spring Security Events](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-events)
