# Frontend Integration for Backend Developers

As a Java/Backend developer, understanding how frontend applications (React, Angular, Vue, mobile apps) interact with your APIs is crucial. This covers the typical integration patterns, common pain points, and best practices.

## 1. REST API Consumption

Frontend apps typically consume backend services using HTTP clients like `fetch` or `axios`.

### Key Backend Responsibilities
- **Consistent Data Formats**: Always return JSON. Ensure consistent naming conventions (e.g., camelCase is standard for JS consumers, while backend Java might use camelCase too, distinguish from snake_case databases).
- **Clear Error Handling**: Do not just return 500 Internal Server Error. Provide structured error responses.
  ```json
  {
    "timestamp": "2023-10-27T10:00:00Z",
    "status": 400,
    "error": "Bad Request",
    "message": "Username already exists",
    "path": "/api/v1/users"
  }
  ```
- **Pagination and Filtering**: Do not return 10,000 records at once. Implement offset/limit or cursor-based pagination.
- **API Documentation**: Use Swagger/OpenAPI. Frontend developers rely on this to generate their types and understand endpoints.

## 2. CORS (Cross-Origin Resource Sharing)

A massive pain point in frontend-backend integration.

### What is CORS?
Browsers enforce the **Same-Origin Policy**, meaning a web app running on `http://localhost:3000` (React) cannot easily make an API call to `http://localhost:8080` (Spring Boot). CORS is a mechanism using HTTP headers that lets a server indicate any origins other than its own from which a browser should permit loading resources.

### The Preflight Request (OPTIONS)
For complex requests (like PUT, DELETE, or requests with custom headers), the browser first sends an HTTP `OPTIONS` request to check if the actual request is safe to send.

### Handling CORS in Spring Boot
You must configure your backend to return the correct headers (`Access-Control-Allow-Origin`, `Access-Control-Allow-Methods`).

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:3000", "https://production-app.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true); // Required if sending cookies
    }
}
```

## 3. Authentication & Authorization Integration

### JWT (JSON Web Tokens)
The most common stateless authentication mechanism.
1. Frontend sends login credentials.
2. Backend validates, generates a JWT, and returns it.
3. Frontend stores the JWT (in `localStorage` or memory, or HTTP-only cookies).
4. Frontend sends the JWT in the `Authorization: Bearer <token>` header for subsequent requests.

*Security Note*: `localStorage` is vulnerable to XSS. HTTP-only cookies are vulnerable to CSRF but are generally preferred for storing tokens securely against XSS.

### Handling Token Expiration
- Short-lived Access Tokens (e.g., 15 mins).
- Long-lived Refresh Tokens (e.g., 7 days).
- When the Access Token expires, the backend returns a `401 Unauthorized`. The frontend intercepts this, silently uses the Refresh Token to hit a `/refresh` endpoint, gets a new Access Token, and retries the original request.

## 4. Real-time Communication

Standard HTTP is client-pulled. When the backend needs to push data to the frontend, you use:

- **WebSockets**: Bi-directional, persistent connection. Ideal for chat apps, live trading dashboards. In Spring, use `spring-websocket`.
- **Server-Sent Events (SSE)**: Unidirectional (server to client). Ideal for live news feeds, progress updates, or real-time notifications where the client only listens.
- **Long Polling**: Legacy fallback. Client sends a request, server holds it open until new data is available.

## 5. Typical Interview Questions

1. **What is CORS and how do you resolve CORS errors?**
   - *Answer*: CORS is a browser security feature preventing unauthorized cross-origin requests. Resolve it by configuring the backend server to return appropriate `Access-Control-Allow-*` headers for the trusted frontend origins.
2. **How does a frontend application maintain state with a stateless REST API?**
   - *Answer*: By passing a token (like a JWT) in the Authorization header of every request. The token contains stateless claims identifying the user.
3. **When would you use WebSockets over HTTP REST?**
   - *Answer*: When low-latency, real-time, bi-directional communication is required, as WebSockets maintain a persistent connection, avoiding the overhead of HTTP headers and connection handshakes on every message.
4. **What is the difference between an Access Token and a Refresh Token?**
   - *Answer*: Access tokens grant access to APIs but are short-lived. Refresh tokens are long-lived and are used solely to obtain new access tokens without requiring the user to re-authenticate, thereby balancing security and user experience.
