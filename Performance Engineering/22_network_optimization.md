# Network Optimization

Network latency and bandwidth are often hidden constraints in distributed systems. Optimizing how data travels over the wire is crucial for performance.

## 1. Network Latency vs Bandwidth

- **Latency**: The time it takes for a packet of data to get from point A to point B. It is bound by the speed of light. (e.g., ~150ms round trip from NY to Tokyo).
- **Bandwidth**: The maximum rate of data transfer across a given path (e.g., 1 Gbps).

*Crucial Concept*: You cannot buy lower latency. You can buy more bandwidth, but a 10Gbps fiber line doesn't make a single packet reach its destination faster than a 1Gbps line; it just allows *more* packets simultaneously. 

## 2. HTTP Optimization Strategies

HTTP/1.1 is inefficient because of **Head-of-Line (HOL) Blocking** and the requirement to open multiple TCP connections for concurrent requests.

### Upgrading the Protocol
- **HTTP/2**: Introduces multiplexing (sending multiple requests over a single TCP connection concurrently), server push, and header compression (HPACK). Solves application-layer HOL blocking.
- **HTTP/3 (QUIC)**: Replaces TCP with UDP. Solves TCP-layer HOL blocking where packet loss halts the entire stream. Faster connection handshakes (0-RTT).

### Payload Minimization
- **Compression**: Use GZIP or Brotli to compress text assets (HTML, CSS, JSON) before sending them over the wire.
- **Minification**: Remove whitespace and comments from JS/CSS.
- **Image Optimization**: Use modern formats (WebP, AVIF) and responsive images (`srcset`) to serve the smallest acceptable image based on screen size.

### Connection Optimization
- **Keep-Alive**: Reuse TCP connections instead of doing a 3-way handshake for every HTTP request.
- **TLS Optimization**: Ensure TLS sessions can be resumed to avoid the costly cryptographic handshakes on subsequent visits.

## 3. Content Delivery Networks (CDNs)

The ultimate solution to latency is bringing the data closer to the user.

- **How it works**: A CDN caches static assets (images, videos, JS) on Edge Servers distributed globally. When a user in Tokyo requests an image, it is served from the Tokyo edge node, not from the Origin server in Virginia.
- **Dynamic Content**: CDNs can also accelerate dynamic API requests by terminating the TLS connection at the edge node and maintaining persistent, optimized long-haul connections back to the origin.

## 4. API Design for Network Efficiency

- **GraphQL**: Allows the client to specify exactly what fields it needs in a single request, preventing over-fetching (getting too much data) and under-fetching (needing to make multiple sequential REST calls).
- **Pagination**: Never return large datasets. Use offset-limit or cursor-based pagination.
- **Batch APIs**: Instead of the client making 10 requests for 10 user profiles, provide an endpoint that accepts an array of IDs and returns the 10 profiles in one bulk response.

## Interview Questions on Network Optimization

1. **Why is making a database connection slow, and how do we solve it?**
   - *Answer*: Opening a connection requires a TCP 3-way handshake, a TLS handshake (often), and database-specific authentication protocols. It can take tens/hundreds of milliseconds. We solve this using a Connection Pool (like HikariCP), which maintains a pre-opened pool of connections that application threads can borrow and return.
2. **What is Head-of-Line (HOL) blocking in HTTP/1.1?**
   - *Answer*: In HTTP/1.1, a single TCP connection can process only one request/response at a time. If the first request takes a long time to generate a response, subsequent requests queued on that connection are blocked, waiting. HTTP/2 solves this by multiplexing streams over a single connection.
3. **How does a CDN improve performance?**
   - *Answer*: By caching static content on servers geographically closer to the end user. This physically reduces the distance data must travel, significantly lowering network latency and offloading traffic from the origin servers.
