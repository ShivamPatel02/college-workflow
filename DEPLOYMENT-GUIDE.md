# 🚀 Workflow Application Deployment Guide

## 📦 Built Artifacts
- **JAR File:** `target/workflow-0.0.1.jar` (Ready to deploy!)
- **Size:** ~50MB (includes all dependencies)

---

## 🎯 Deployment Options

### 1. **Simple JAR Deployment** ⭐ (Recommended for testing)

**Requirements:**
- Java 17+ installed on target server
- Port 8080 available

**Steps:**
```bash
# Copy the JAR file to your server
scp target/workflow-0.0.1.jar user@server:/path/to/app/

# Run on server
java -jar workflow-0.0.1.jar

# Run with custom port
java -jar workflow-0.0.1.jar --server.port=8090

# Run in background
nohup java -jar workflow-0.0.1.jar > app.log 2>&1 &
```

**Access:** `http://your-server:8080`

---

### 2. **Docker Deployment** 🐳 (Recommended for production)

**Create Dockerfile:**
```dockerfile
FROM openjdk:17-jre-slim

WORKDIR /app
COPY target/workflow-0.0.1.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
```

**Build and run:**
```bash
# Build Docker image
docker build -t workflow-app .

# Run container
docker run -d -p 8080:8080 --name workflow workflow-app

# Run with environment variables
docker run -d -p 8080:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  --name workflow workflow-app
```

---

### 3. **Cloud Deployment** ☁️

#### **Heroku**
```bash
# Install Heroku CLI, then:
heroku create your-workflow-app
git add .
git commit -m "Deploy to Heroku"
git push heroku main
```

#### **Railway**
```bash
# Connect GitHub repo to Railway
# Railway will auto-detect Spring Boot and deploy
```

#### **AWS EC2**
```bash
# Upload JAR to EC2 instance
scp -i your-key.pem target/workflow-0.0.1.jar ec2-user@your-ec2-ip:/home/ec2-user/

# SSH to EC2 and run
ssh -i your-key.pem ec2-user@your-ec2-ip
sudo yum install java-17-openjdk
java -jar workflow-0.0.1.jar
```

---

### 4. **Production with External Database** 🗄️

**MySQL Setup:**
```bash
# Run with MySQL (instead of H2)
java -jar workflow-0.0.1.jar \
  --spring.datasource.url=jdbc:mysql://localhost:3306/workflow_db \
  --spring.datasource.username=workflow_user \
  --spring.datasource.password=your_password \
  --spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

**PostgreSQL Setup:**
```bash
java -jar workflow-0.0.1.jar \
  --spring.datasource.url=jdbc:postgresql://localhost:5432/workflow_db \
  --spring.datasource.username=workflow_user \
  --spring.datasource.password=your_password \
  --spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

---

### 5. **Windows Service** 🪟

**Using NSSM (Non-Sucking Service Manager):**
```cmd
# Download NSSM, then:
nssm install WorkflowApp "C:\Program Files\Java\jdk-17\bin\java.exe"
nssm set WorkflowApp Arguments "-jar C:\path\to\workflow-0.0.1.jar"
nssm set WorkflowApp DisplayName "College Workflow Application"
nssm start WorkflowApp
```

---

### 6. **Linux Service** 🐧

**Create systemd service:**
```bash
# Create service file
sudo nano /etc/systemd/system/workflow.service
```

**Service content:**
```ini
[Unit]
Description=College Workflow Application
After=network.target

[Service]
Type=simple
User=workflow
ExecStart=/usr/bin/java -jar /opt/workflow/workflow-0.0.1.jar
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
```

**Enable and start:**
```bash
sudo systemctl daemon-reload
sudo systemctl enable workflow
sudo systemctl start workflow
sudo systemctl status workflow
```

---

## 🔧 Configuration Options

### Environment Variables
```bash
# Database
SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/workflow_db
SPRING_DATASOURCE_USERNAME=workflow_user
SPRING_DATASOURCE_PASSWORD=your_password

# Server
SERVER_PORT=8080
SPRING_PROFILES_ACTIVE=prod

# Security
SPRING_SECURITY_USER_NAME=admin
SPRING_SECURITY_USER_PASSWORD=secure_password
```

### Application Properties
Create `application-prod.properties`:
```properties
# Production settings
server.port=8080
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
logging.level.org.hibernate.SQL=WARN

# External database
spring.datasource.url=jdbc:mysql://localhost:3306/workflow_db
spring.datasource.username=workflow_user
spring.datasource.password=${DB_PASSWORD}
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

---

## 🔒 Security Considerations

1. **Change default passwords**
2. **Use HTTPS in production**
3. **Configure firewall rules**
4. **Use external database for production**
5. **Enable logging and monitoring**
6. **Regular backups**

---

## 📊 Monitoring

**Health Check Endpoint:**
```
GET http://your-server:8080/actuator/health
```

**Application Logs:**
```bash
# View logs
tail -f app.log

# Docker logs
docker logs -f workflow
```

---

## 🚀 Quick Start Commands

**Local Testing:**
```bash
java -jar target/workflow-0.0.1.jar
```

**Production (with external DB):**
```bash
java -jar workflow-0.0.1.jar \
  --spring.profiles.active=prod \
  --server.port=80 \
  --spring.datasource.url=jdbc:mysql://db-server:3306/workflow_db
```

**Docker Production:**
```bash
docker run -d -p 80:8080 \
  -e SPRING_PROFILES_ACTIVE=prod \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://db-server:3306/workflow_db \
  --name workflow-prod workflow-app
```

---

## 📞 Support

- **Demo Users:** student1, coordinator_cse, hod_cse, director (password: pass)
- **H2 Console:** `/h2-console` (development only)
- **Application:** Runs on port 8080 by default

**Happy Deploying! 🎉**