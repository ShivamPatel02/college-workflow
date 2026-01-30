# 🚀 Simple Deployment Guide (No Docker)

## ✅ Your Application is Ready!
- **JAR File:** `target/workflow-0.0.1.jar` (50MB - includes everything!)
- **Database:** H2 (built-in, no setup needed)
- **Requirements:** Just Java 17+

---

## 🎯 Deployment Options

### 1. **Local Server Deployment** ⭐ (Easiest)

**Copy these files to your server:**
```
workflow-0.0.1.jar  (from target folder)
```

**Run on any machine with Java:**
```bash
java -jar workflow-0.0.1.jar
```

**Access:** `http://localhost:8080`

---

### 2. **Cloud Hosting** ☁️ (Recommended)

#### **Heroku (Free tier available)**
1. Create account at heroku.com
2. Install Heroku CLI
3. In your project folder:
```bash
heroku create your-app-name
git add .
git commit -m "Deploy"
git push heroku main
```
Your app will be at: `https://your-app-name.herokuapp.com`

#### **Railway (Very easy)**
1. Go to railway.app
2. Connect your GitHub repo
3. Railway auto-detects Spring Boot
4. Deploy with one click!

#### **Render (Simple)**
1. Go to render.com
2. Connect GitHub repo
3. Choose "Web Service"
4. Build command: `./mvnw clean package -DskipTests`
5. Start command: `java -jar target/workflow-0.0.1.jar`

---

### 3. **VPS/Server Deployment** 🖥️

**Requirements:**
- Ubuntu/CentOS server
- Java 17 installed

**Steps:**
```bash
# 1. Upload JAR file
scp workflow-0.0.1.jar user@your-server:/home/user/

# 2. SSH to server
ssh user@your-server

# 3. Install Java (if not installed)
sudo apt update
sudo apt install openjdk-17-jre

# 4. Run application
java -jar workflow-0.0.1.jar

# 5. Run in background
nohup java -jar workflow-0.0.1.jar > app.log 2>&1 &
```

**Access:** `http://your-server-ip:8080`

---

### 4. **Windows Server** 🪟

**Steps:**
1. Copy `workflow-0.0.1.jar` to Windows server
2. Install Java 17 from adoptium.net
3. Open Command Prompt as Administrator
4. Navigate to JAR location
5. Run: `java -jar workflow-0.0.1.jar`

**Run as Windows Service:**
1. Download NSSM (nssm.cc)
2. Run as admin: `nssm install WorkflowApp`
3. Set path to Java and JAR file
4. Start service: `nssm start WorkflowApp`

---

### 5. **Shared Hosting** 🏠

Many shared hosting providers support Java applications:

**Steps:**
1. Upload JAR file via FTP/cPanel
2. Use hosting control panel to run Java applications
3. Set startup command: `java -jar workflow-0.0.1.jar`

---

## 🔧 Configuration Options

### Custom Port
```bash
java -jar workflow-0.0.1.jar --server.port=8090
```

### Production Mode
```bash
java -jar workflow-0.0.1.jar --spring.profiles.active=prod
```

### Custom Memory
```bash
java -Xmx512m -jar workflow-0.0.1.jar
```

### Background with Logs
```bash
nohup java -jar workflow-0.0.1.jar > workflow.log 2>&1 &
```

---

## 🌐 Free Hosting Options

### **1. Heroku** (Recommended)
- ✅ Free tier available
- ✅ Easy deployment
- ✅ Custom domain support
- ❌ Sleeps after 30 min inactivity

### **2. Railway**
- ✅ $5/month credit free
- ✅ Very simple setup
- ✅ GitHub integration

### **3. Render**
- ✅ Free tier for web services
- ✅ Auto-deploy from GitHub
- ❌ Limited free hours

### **4. Google Cloud Platform**
- ✅ $300 free credit
- ✅ Professional grade
- ❌ More complex setup

---

## 📱 Quick Test

**After deployment, test these URLs:**
- Main app: `http://your-domain:8080`
- Login with: `student1` / `pass`
- Health check: `http://your-domain:8080/actuator/health`

---

## 🔒 Security Tips

1. **Change default passwords** in production
2. **Use HTTPS** (most cloud providers offer free SSL)
3. **Firewall rules** - only allow port 8080
4. **Regular updates** - rebuild JAR with updates

---

## 🆘 Troubleshooting

**Port already in use:**
```bash
java -jar workflow-0.0.1.jar --server.port=8090
```

**Out of memory:**
```bash
java -Xmx1g -jar workflow-0.0.1.jar
```

**Check if running:**
```bash
ps aux | grep workflow
netstat -tlnp | grep 8080
```

**Stop application:**
```bash
pkill -f workflow-0.0.1.jar
```

---

## 🎉 That's It!

Your application is completely self-contained. Just copy the JAR file and run it anywhere with Java 17+!

**Demo Users:**
- Students: `student1`, `student2` 
- Coordinators: `coordinator_cse`, `coordinator_ece`
- HODs: `hod_cse`, `hod_ece`
- Director: `director`
- Password for all: `pass`