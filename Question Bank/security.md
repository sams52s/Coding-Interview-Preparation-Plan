## 🔒 Security Interview Q&A

### Application Security

**Q: What is the OWASP Top 10 and why is it important for application security?**  
**A:** The OWASP Top 10 is a list of the most critical security risks to web applications, maintained by the Open Web Application Security Project (OWASP). It provides developers and security professionals with a prioritized awareness of common vulnerabilities such as injection flaws, broken authentication, sensitive data exposure, and more. Understanding and mitigating these risks is essential to building secure applications.

**Use Case:** A development team uses the OWASP Top 10 as a checklist during code reviews and penetration testing to ensure common vulnerabilities are addressed before deployment.

**Follow-up:**  
**Q:** Can you explain how to prevent SQL Injection attacks?  
**A:** SQL Injection occurs when untrusted input is concatenated into SQL queries, allowing attackers to manipulate the database. Prevention includes using parameterized queries or prepared statements, input validation, and ORM frameworks that abstract SQL generation.

**Example (Java with JDBC):**
```java
String query = "SELECT * FROM users WHERE username = ? AND password = ?";
PreparedStatement stmt = connection.prepareStatement(query);
stmt.setString(1, username);
stmt.setString(2, password);
ResultSet rs = stmt.executeQuery();
```

---

### Authentication & Authorization

**Q: What are OAuth 2.0 flows and when would you use each?**  
**A:** OAuth 2.0 defines several flows (grant types) to authorize third-party applications without sharing user credentials. Common flows include:

- **Authorization Code Grant:** Used for server-side apps; exchanges an authorization code for an access token.
- **Implicit Grant:** Used for client-side apps (deprecated due to security concerns).
- **Resource Owner Password Credentials Grant:** Used when the app is trusted by the user (not recommended for third-party apps).
- **Client Credentials Grant:** For machine-to-machine communication.

**Use Case:** A mobile app uses the Authorization Code Grant with PKCE to securely authenticate users via a third-party identity provider.

**Follow-up:**  
**Q:** Can you explain JWT implementation in authentication?  
**A:** JSON Web Tokens (JWT) are compact, URL-safe tokens that carry claims and are signed to verify authenticity. They are commonly used in stateless authentication systems.

**Example (Java Spring Security configuration snippet):**
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .oauth2ResourceServer()
        .jwt();
    return http.build();
}
```

---

### Security Advanced Questions

**Q: What are the key differences between OAuth 2.1 and OpenID Connect?**  
**A:** OAuth 2.1 is an evolution of OAuth 2.0 that consolidates best practices and removes insecure features (like implicit flow). OpenID Connect (OIDC) is an identity layer on top of OAuth 2.0 that provides authentication and user profile information.

**Use Case:** Use OAuth 2.1 for secure authorization and OpenID Connect when you need to authenticate users and obtain their identity information.

**Follow-up:**  
**Q:** How does OpenID Connect enhance OAuth 2.0?  
**A:** OIDC adds an ID token, which is a JWT containing user identity claims, enabling clients to verify the user’s identity and obtain profile info.

---

**Q: How would you implement API key rotation securely?**  
**A:** API key rotation involves periodically replacing keys to reduce the risk of compromise. Implementations should support multiple active keys during rotation, allow revoking old keys, and automate the rotation process.

**Use Case:** An API gateway supports two keys per client; when rotating, the new key is added, and after verification, the old key is revoked.

**Follow-up:**  
**Q:** What tools or services help with secret management?  
**A:** Vault by HashiCorp, AWS KMS, and Azure Key Vault provide secure storage and automated rotation of secrets.

---

**Q: What are common pitfalls in JWT usage?**  
**A:** Common issues include:

- Not verifying the token signature.
- Using weak or none algorithms.
- Storing sensitive data in the payload.
- Long token expiration times.
- Not handling token revocation.

**Use Case:** An app mistakenly trusts unsigned tokens, allowing attackers to forge tokens and gain unauthorized access.

**Follow-up:**  
**Q:** How can you mitigate these pitfalls?  
**A:** Always validate signatures, use strong algorithms (e.g., RS256), keep token lifetimes short, and implement token revocation strategies like blacklists or refresh tokens.

---

**Q: How do you secure sensitive configuration secrets?**  
**A:** Use dedicated secret management systems like Vault or cloud KMS to store secrets encrypted at rest. Access should be controlled via IAM policies and audited.

**Use Case:** A microservices architecture fetches database credentials from Vault at startup rather than hardcoding them.

**Example (Vault policy snippet):**
```hcl
path "secret/data/myapp/*" {
  capabilities = ["read"]
}
```

---

**Q: Can you discuss Cross-Origin Resource Sharing (CORS) and its security implications?**  
**A:** CORS is a browser mechanism that controls which domains can access resources on a server. Misconfigured CORS (e.g., allowing `*` or all origins) can lead to data leakage.

**Use Case:** A web API restricts CORS to trusted domains to prevent unauthorized scripts from accessing sensitive endpoints.

**Follow-up:**  
**Q:** How to configure CORS securely in Spring Boot?  
**A:** Use `@CrossOrigin` with specific origins or configure global CORS mappings.

**Example:**
```java
@Bean
public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/api/**").allowedOrigins("https://trusted.com");
        }
    };
}
```

---

**Q: How would you implement Multi-Factor Authentication (MFA)?**  
**A:** MFA adds an additional verification step beyond username and password, such as OTP via SMS/email, authenticator apps, or hardware tokens.

**Use Case:** A banking app requires users to enter a code from an authenticator app after password login.

**Follow-up:**  
**Q:** How to integrate MFA in a Java Spring Security application?  
**A:** Use a custom authentication provider or integrate with services like Google Authenticator or Authy APIs.

---

**Q: How do you design a secure password reset system?**  
**A:** Key points include:

- Use time-limited, single-use tokens.
- Send reset links via email.
- Validate token integrity and expiration.
- Require strong new passwords.
- Notify users of password changes.

**Use Case:** A web app sends a secure reset link that expires in 15 minutes and invalidates after use.

---

**Q: What is mutual TLS (mTLS) and when would you use it?**  
**A:** mTLS requires both client and server to authenticate via certificates, providing strong two-way authentication.

**Use Case:** Internal microservices communicate over mTLS to ensure only authorized services access APIs.

---

**Q: How do you prevent SSRF (Server-Side Request Forgery) attacks?**  
**A:** Validate and sanitize all user-supplied URLs, restrict outbound requests to allowed domains/IPs, and use network-level controls.

**Use Case:** An application validates URLs before fetching remote content, preventing attackers from accessing internal resources.

---

**Q: Explain the principle of least privilege and how to implement it.**  
**A:** Users and services should have only the permissions necessary to perform their tasks, reducing attack surface.

**Use Case:** A database user only has read access to necessary tables, not full admin rights.

---

**Q: How do you design audit logging for sensitive actions?**  
**A:** Log key events like login attempts, password changes, data access, with timestamps, user IDs, and IP addresses. Logs should be tamper-evident and stored securely.

---

**Q: How do you implement secure session management at scale?**

**A:** Implement a robust session management system using distributed caches and secure cookie handling.

**Implementation Example:**
```java
@Configuration
public class SessionConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;
    }

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setCookieName("SESSIONID");
        serializer.setCookiePath("/");
        serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
        serializer.setCookieMaxAge(3600);
        serializer.setUseSecureCookie(true);
        serializer.setSameSite("Strict");
        return serializer;
    }
}

@Service
public class SessionManager {
    private final RedisTemplate<String, Object> redisTemplate;
    
    public void createSession(String sessionId, UserSession session) {
        redisTemplate.opsForValue().set(
            "session:" + sessionId, 
            session,
            Duration.ofHours(1)
        );
    }
    
    @Scheduled(fixedRate = 300000) // Every 5 minutes
    public void cleanExpiredSessions() {
        // Implement cleanup logic
    }
}
```

**Follow-up Q1:** How do you handle session synchronization in a microservices architecture?

**A1:** Use a consistent session store and implement session affinity:
```java
@Configuration
public class LoadBalancerConfig {
    @Bean
    public SessionAffinity sessionAffinity() {
        return new CookieBasedSessionAffinity();
    }
}
```

**Follow-up Q2:** How do you prevent session fixation attacks?

**A2:** Regenerate session IDs after authentication:
```java
@Component
public class SessionFixationProtectionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                  HttpServletResponse response, 
                                  FilterChain chain) {
        if (isAuthenticationSuccess(request)) {
            HttpSession oldSession = request.getSession(false);
            if (oldSession != null) {
                oldSession.invalidate();
            }
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute("CREATION_TIME", Instant.now());
        }
        chain.doFilter(request, response);
    }
}
```

**Real-world Scenario:**
An e-commerce platform handling millions of concurrent sessions:
```java
@Service
public class ScalableSessionService {
    private final RedissonClient redisson;
    
    public UserSession getUserSession(String sessionId) {
        RLock lock = redisson.getLock("session-lock:" + sessionId);
        try {
            lock.lock(10, TimeUnit.SECONDS);
            return retrieveSession(sessionId);
        } finally {
            lock.unlock();
        }
    }
    
    @CircuitBreaker(name = "sessionService")
    private UserSession retrieveSession(String sessionId) {
        // Implementation with circuit breaker pattern
    }
}
```

---

**Q: What are best practices for securing CI/CD pipelines?**

**A:** Implement comprehensive security controls throughout the pipeline.

**Example Jenkins Pipeline with Security Controls:**
```groovy
pipeline {
    agent any
    
    environment {
        SONAR_TOKEN = credentials('sonar-token')
        DOCKER_REGISTRY = credentials('docker-registry')
    }
    
    stages {
        stage('Security Scan') {
            parallel {
                stage('SAST') {
                    steps {
                        sh 'sonar-scanner'
                    }
                }
                stage('Dependency Check') {
                    steps {
                        sh 'npm audit'
                        sh 'cargo audit'
                    }
                }
            }
        }
        
        stage('Container Scan') {
            steps {
                script {
                    sh 'trivy image ${DOCKER_IMAGE}'
                }
            }
        }
    }
    
    post {
        always {
            junit '**/test-results/*.xml'
            recordIssues enabledForFailure: true,
                        tool: checkStyle()
        }
    }
}
```

**Follow-up Q:** How do you secure secrets in CI/CD pipelines?

**A:** Use dedicated secret management services and rotate credentials:
```yaml
# GitHub Actions example with secure secrets
name: Secure Pipeline
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: aws-actions/configure-aws-credentials@v1
        with:
          role-to-assume: arn:aws:iam::123456789012:role/github-actions
          aws-region: us-east-1
      
      - name: Get secrets from AWS Secrets Manager
        run: |
          SECRET=$(aws secretsmanager get-secret-value \
            --secret-id prod/api/key \
            --query SecretString \
            --output text)
```

---

**Q: Explain the impact and mitigation of supply chain attacks.**  
**A:** Supply chain attacks compromise third-party components or tools. Mitigation includes verifying dependencies, using signed packages, and monitoring for unusual behavior.

---

**Q: How do you design secure file upload/download endpoints?**  
**A:** Validate file types and sizes, scan for malware, store files outside webroot, and use secure URLs with limited access.

---

**Q: How to detect and respond to credential stuffing attacks?**  
**A:** Monitor failed login attempts, use rate limiting, CAPTCHA, and encourage MFA.

---

**Q: What is certificate pinning and when should it be used?**  
**A:** Certificate pinning binds a service to a specific certificate or public key to prevent MITM attacks. Used in mobile apps or sensitive communications.

---

**Q: How to ensure end-to-end encryption in distributed systems?**  
**A:** Encrypt data at rest and in transit, use TLS for communication, and employ application-level encryption where necessary.

---

**Q: How do you securely manage and rotate database credentials?**  
**A:** Use secret managers to store credentials, automate rotation, and update applications dynamically.

---

**Q: Explain the security implications of CORS misconfiguration.**  
**A:** Overly permissive CORS can expose APIs to unauthorized domains, risking data theft. Proper configuration restricts access to trusted origins only.
