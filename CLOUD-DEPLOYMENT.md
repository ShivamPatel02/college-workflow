# ☁️ Cloud Deployment Guide (Updated 2025)

## ⚠️ Important Update
**Heroku discontinued their free tier in November 2022.** Here are the best current options:

---

## 🆓 **Free Cloud Options**

### 1. **Render** ⭐ (Best Free Option)
- ✅ **Free tier:** 750 hours/month
- ✅ **Auto-deploy** from GitHub
- ✅ **Custom domains**
- ✅ **SSL certificates**
- ❌ Sleeps after 15 min inactivity

**Steps:**
1. Push your code to GitHub
2. Go to [render.com](https://render.com)
3. Connect GitHub repo
4. Choose "Web Service"
5. Settings:
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/workflow-0.0.1.jar`
   - **Environment:** `SPRING_PROFILES_ACTIVE=prod`

### 2. **Railway** 🚂 (Very Easy)
- ✅ **$5 free credit** monthly
- ✅ **Zero config** deployment
- ✅ **GitHub integration**
- ✅ **No sleep mode**

**Steps:**
1. Push code to GitHub
2. Go to [railway.app](https://railway.app)
3. "Deploy from GitHub"
4. Select your repo
5. Railway auto-detects Spring Boot!

### 3. **Fly.io** 🪰 (Good Performance)
- ✅ **Free allowance:** 3 shared-cpu VMs
- ✅ **No sleep mode**
- ✅ **Global deployment**
- ❌ Requires CLI setup

**Steps:**
1. Install Fly CLI
2. `fly launch` in your project
3. `fly deploy`

---

## 💰 **Paid Options (Cheap)**

### **Heroku** ($7/month)
- ✅ **Most reliable**
- ✅ **Easy deployment**
- ✅ **Great documentation**

### **DigitalOcean App Platform** ($5/month)
- ✅ **Good performance**
- ✅ **GitHub integration**

---

## 🎯 **Recommended: Render (Free)**

Let me create the exact files you need for Render:

### Step 1: Create Render Build Script
```bash
# This will be detected automatically by Render
./mvnw clean package -DskipTests
```

### Step 2: Render Start Command
```bash
java -jar target/workflow-0.0.1.jar
```

### Step 3: Environment Variables (in Render dashboard)
```
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
```

---

## 📋 **Complete Render Deployment Steps**

1. **Push to GitHub:**
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git branch -M main
   git remote add origin https://github.com/yourusername/workflow-app.git
   git push -u origin main
   ```

2. **Deploy on Render:**
   - Go to [render.com](https://render.com)
   - Click "New +" → "Web Service"
   - Connect your GitHub repo
   - Configure:
     - **Name:** `college-workflow`
     - **Environment:** `Java`
     - **Build Command:** `./mvnw clean package -DskipTests`
     - **Start Command:** `java -jar target/workflow-0.0.1.jar`
     - **Instance Type:** `Free`

3. **Set Environment Variables:**
   - `SPRING_PROFILES_ACTIVE` = `prod`
   - `SERVER_PORT` = `10000` (Render uses port 10000)

4. **Deploy:**
   - Click "Create Web Service"
   - Wait 5-10 minutes for build
   - Your app will be at: `https://college-workflow.onrender.com`

---

## 🔧 **Render Configuration Files**

I'll create the files Render needs: