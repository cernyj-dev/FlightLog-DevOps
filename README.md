# DevOps Automation with GitLab CI/CD, Docker, Ansible, and OpenStack

> ⚙️ **A hands-on DevOps project demonstrating CI/CD pipelines, containerization, and infrastructure automation in a cloud environment.**

---

## Project Overview

This repository serves as a training project focused on mastering essential **DevOps practices**. The goal was to automate the **build**, **test**, **containerization**, and **deployment** of a simple Java application using modern tools such as **GitLab CI/CD**, **Docker**, **Ansible**, and **OpenStack**.

---

## Workflow Summary

### 1. Java Application & Dockerization

- The project contains a **Java application**, built using **Maven**.
- A `Dockerfile` was created to build a **Docker image** of the application.
- This image serves as the deployment unit for production and testing.

### 2. GitLab CI/CD Pipeline

- The `.gitlab-ci.yml` file defines a pipeline that:
  - Builds the project using Maven.
  - Identifies and stores build artifacts (binaries).
  - Constructs a Docker image from the application.
  - Pushes the final image to the **GitLab Container Registry**.

### 3. OpenStack & Ansible Automation

- The project was deployed in a **school-managed cloud environment** running on **OpenStack**.
- A **controller virtual machine (VM)** was set up, along with two **managed target VMs**.
- The controller VM included a configuration file listing the IP addresses of the target VMs.
- An **Ansible Playbook** was written to automate deployment on the target VMs.
  - This included installing Docker, logging into the GitLab Container Registry, pulling the application image, and running it as a container.
  - SSH keys were exchanged to allow seamless Ansible access from the controller to the managed hosts.

### 4. Result

Once the playbook was executed with the command:

```bash
ansible-playbook -i hosts webserver.yml
