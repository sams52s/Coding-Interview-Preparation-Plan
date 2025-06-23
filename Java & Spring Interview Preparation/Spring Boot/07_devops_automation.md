# 7. DevOps & Automation

#### Overview
DevOps practices for automating builds, deployments, infrastructure, and secrets management.

#### Subtopics
- **CI/CD Pipelines**
  - Integrate security scanning (Snyk, OWASP Dependency Check)
  - Automated deployment
- **GitOps**
  - Manage deployments declaratively (ArgoCD, Flux)
- **Infrastructure Provisioning**
  - Terraform, CloudFormation, Helm, Kustomize
  - Example:
    ```hcl
    resource "aws_instance" "spring_boot_app" {
      ami           = "ami-0abcdef1234567890"
      instance_type = "t2.micro"
      tags = {
        Name = "SpringBootAppServer"
      }
    }
    ```
- **Secrets Management**
  - Vault, AWS Secrets Manager

#### Example: Integrating Dependency Vulnerability Scan in GitHub Actions
```yaml
name: Security Scan
on: [push]
jobs:
  snyk:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Run Snyk to check vulnerabilities
        uses: snyk/actions/maven@master
        env:
          SNYK_TOKEN: ${{ secrets.SNYK_TOKEN }}
```

#### External Links
- [Spring Boot DevOps Guide](https://spring.io/guides/topicals/spring-boot-docker/)
- [HashiCorp Terraform](https://www.terraform.io/docs/)
- [Vault Docs](https://www.vaultproject.io/docs/)
- [GitHub Actions](https://docs.github.com/en/actions)

---