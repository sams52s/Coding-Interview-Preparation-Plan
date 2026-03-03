# DevSecOps Practices

DevSecOps integrates security into the software development lifecycle from the beginning, rather than treating it as an afterthought. "Shift Left" is the core mantra—bringing security testing closer to the developer.

## Key Concepts and Terminology

1. **Shift Left Security**
   - Implementing security measures early in the development lifecycle rather than at the end.
   - **Goal**: Catch vulnerabilities when they are cheapest to fix (during coding/build phases).

2. **Continuous Integration/Continuous Deployment (CI/CD)**
   - Integrating security scanning directly into CI pipelines (GitLab CI, GitHub Actions, Jenkins).
   - If a high-severity vulnerability is found, the build fails automatically.

3. **Infrastructure as Code (IaC) Security**
   - Scanning Terraform, CloudFormation, or Kubernetes manifests for misconfigurations before deployment.
   - Tools: Checkov, tfsec.

## Core Security Practices in the Pipeline

### 1. SAST (Static Application Security Testing)
- **What it is**: White-box testing that analyzes source code or compiled code for vulnerabilities without executing the program.
- **When it runs**: During code commit or the build phase (CI pipeline).
- **What it catches**: SQL Injection, Cross-Site Scripting (XSS), buffer overflows, hardcoded secrets.
- **Tools**: SonarQube, Fortify, Checkmarx, Snyk Code.
- **Pros/Cons**: Finds issues early; high false-positive rate.

### 2. DAST (Dynamic Application Security Testing)
- **What it is**: Black-box testing that analyzes a running application from the outside, mimicking an attacker.
- **When it runs**: After deployment to a staging/QA environment.
- **What it catches**: Authentication issues, server configuration errors, runtime vulnerabilities.
- **Tools**: OWASP ZAP, Burp Suite Enterprise.
- **Pros/Cons**: Low false positives, tests true runtime behavior; runs late in the cycle, harder to pinpoint exactly *where* in the code the issue lies.

### 3. SCA (Software Composition Analysis)
- **What it is**: Scanning third-party open-source libraries and dependencies for known vulnerabilities (CVEs) and license compliance.
- **When it runs**: Build phase.
- **Why it matters**: Modern apps are up to 80% open-source code. E.g., Log4Shell was an SCA issue.
- **Tools**: Snyk Open Source, OWASP Dependency-Check, Black Duck.

### 4. Secret Scanning
- **What it is**: Identifying hardcoded secrets, API keys, and passwords in the codebase before they are pushed to version control.
- **Tools**: GitHub Secret Scanning, GitLeaks, TruffleHog.

### 5. Container Security
- **What it is**: Scanning Docker images for vulnerabilities in base OS layers and libraries.
- **When it runs**: During CI (after image build) and continuously in the registry.
- **Tools**: Trivy, Clair, Docker Scout.

## Implementing DevSecOps: A Typical Workflow

1. **Pre-commit**: Developer runs a local scan using an IDE plugin (e.g., SonarLint) to catch basic flaws as they type.
2. **Commit**: A pre-commit hook runs GitLeaks to ensure no secrets are being pushed.
3. **Pull Request CI**:
   - SAST scans the updated code.
   - SCA scans for new vulnerable dependencies.
   - If failure thresholds are met, the PR is blocked.
4. **Build**: Code is compiled, tests run, Docker image is built.
5. **Container Scan**: The Docker image is scanned by Trivy. If clean, pushed to the registry.
6. **Deployment & DAST**: Deployed to staging. DAST (OWASP ZAP) runs automated penetration testing against the live app.
7. **Production & RASP/WAF**: Application is protected by a Web Application Firewall (WAF) or Runtime Application Self-Protection (RASP).
8. **Continuous Monitoring**: Continually scanning the registry and production environment for newly discovered CVEs.

## Interview Questions on DevSecOps

1. **What is the difference between SAST and DAST?**
   - *Answer*: SAST analyzes source code early without running the application (white-box). DAST interacts with the running application to find vulnerabilities dynamically (black-box).
2. **Explain "Shift Left" in security.**
   - *Answer*: Integrating security testing early in the SDLC rather than just before release, reducing the cost and effort to fix vulnerabilities.
3. **How do you manage secrets in an application?**
   - *Answer*: Never hardcode. Use secret managers like AWS Secrets Manager, HashiCorp Vault, or environment variables injected at runtime.
4. **What is Software Composition Analysis (SCA)?**
   - *Answer*: The process of automating visibility into open source software (OSS) use for the purpose of risk management, security, and license compliance.
