# Infrastructure as Code (IaC)

Infrastructure as Code (IaC) is the process of managing and provisioning computing infrastructure (networks, virtual machines, load balancers, databases, etc.) through machine-readable definition files, rather than physical hardware configuration or interactive configuration tools (like clicking through the AWS Web Console).

## 1. Why use IaC?

- **Version Control**: Infrastructure definitions are stored in Git. You can code review infrastructure changes (PRs), track history, and rollback to a previous known state easily.
- **Idempotency & Consistency**: Running the same script multiple times will always result in the same consistent environment, eliminating "drift" and the "it works on my machine" problem.
- **Speed & Automation**: You can spin up an exact replica of a complex production environment for QA testing in minutes rather than days.
- **Disaster Recovery**: If an entire data center region goes down, you can execute your IaC scripts to rebuild the infrastructure in a new region very quickly.

## 2. Declarative vs. Imperative IaC

- **Imperative**: You specify *how* to achieve the desired state using step-by-step commands (e.g., Ansible, shell scripts). "Create a VM, then install Java, then open port 80."
- **Declarative**: You specify *what* the desired end state should look like, and the tool figures out how to achieve it (e.g., Terraform, CloudFormation, Kubernetes YAML). "I need 3 VMs running Java with port 80 open." The tool computes the difference between the current state and the desired state and applies only the necessary changes. *Declarative is the modern industry standard.*

## 3. Terraform Fundamentals

Terraform (by HashiCorp) is the most popular, cloud-agnostic IaC tool. It allows you to build infrastructure on AWS, Azure, GCP, and even on-premise providers using the same HashiCorp Configuration Language (HCL).

### Core Concepts:
- **Providers**: Plugins that allow Terraform to interact with cloud platforms (e.g., `aws`, `google`, `azurerm` providers).
- **Resources**: The most important element. Represents a piece of infrastructure (a compute instance, an S3 bucket, a VPC).
  ```hcl
  resource "aws_instance" "web_server" {
    ami           = "ami-0c55b159cbfafe1f0"
    instance_type = "t2.micro"
  }
  ```
- **State File (`terraform.tfstate`)**: Terraform uses this JSON file to map the real-world infrastructure objects it manages to your configuration files. **CRITICAL:** This file contains sensitive data (passwords, IP addresses) and must be stored securely (e.g., in an AWS S3 bucket with encryption and locking via DynamoDB), NOT committed to Git.
- **Modules**: Reusable, parameterized packages of Terraform configurations. (Like functions in programming).

### The Terraform Workflow:
1. `terraform init`: Initializes the working directory and downloads necessary provider plugins.
2. `terraform plan`: Reads the code and the state file, and prints out an execution plan showing exactly what resources will be created, modified, or destroyed. *Always review the plan carefully.*
3. `terraform apply`: Executes the plan against the cloud provider APIs to reach the desired state.

## Interview Questions on IaC

1. **What is Infrastructure as Code and why is it important in a DevOps culture?**
   - *Answer*: IaC is the practice of managing infrastructure using code files stored in version control instead of manual processes. It is crucial because it enables consistent, repeatable, and automated deployments, allows for code reviews of infrastructure changes, and significantly speeds up disaster recovery.
2. **What is the Terraform State file? Why is it dangerous to commit it to version control like GitHub?**
   - *Answer*: The state file is how Terraform keeps track of the real-world infrastructure it manages and maps it to your code. It is dangerous to commit to Git because it often contains sensitive data (like database passwords, private IPs) in plain text. It should be stored in a remote, encrypted backend (like Terraform Cloud or an S3 bucket).
3. **What is the difference between Terraform (declarative) and Ansible (imperative)?**
   - *Answer*: Terraform is primarily an infrastructure provisioning tool that uses a declarative model (you state the end goal, Terraform figures out the API calls). Ansible is primarily a configuration management tool (often used to install software onto VMs that Terraform created) and often uses an imperative model (running specific scripts in order), though it has declarative modules too.
