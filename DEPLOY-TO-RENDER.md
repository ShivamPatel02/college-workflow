# 🚀 Deploy to Render (Free) - Step by Step

## ✅ **Why Render?**
- 🆓 **Completely FREE** (750 hours/month)
- 🔄 **Auto-deploy** from GitHub
- 🌐 **Custom domain** support
- 🔒 **Free SSL** certificate
- ⚡ **Easy setup** (5 minutes)

---

## 📋 **Step-by-Step Instructions**

### **Step 1: Push to GitHub** 📤
```bash
# Initialize git (if not already done)
git init

# Add all files
git add .

# Commit
git commit -m "College Workflow App - Ready for deployment"

# Create main branch
git branch -M main

# Add your GitHub repo (replace with your username/repo)
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git

# Push to GitHub
git push -u origin main
```

### **Step 2: Deploy on Render** 🌐

1. **Go to [render.com](https://render.com)**
2. **Sign up/Login** (use GitHub account for easy connection)
3. **Click "New +"** → **"Web Service"**
4. **Connect GitHub** and select your repository
5. **Configure the service:**

   **Basic Settings:**
   - **Name:** `college-workflow` (or any name you like)
   - **Environment:** `Java`
   - **Region:** Choose closest to you
   - **Branch:** `main`

   **Build & Deploy:**
   - **Build Command:** `./mvnw clean package -DskipTests`
   - **Start Command:** `java -jar target/workflow-0.0.1.jar`

   **Advanced (Optional):**
   - **Environment Variables:**
     - `SPRING_PROFILES_ACTIVE` = `prod`
   
6. **Click "Create Web Service"**

### **Step 3: Wait for Deployment** ⏳
- Build takes 5-10 minutes
- Watch the logs in Render dashboard
- When complete, you'll get a URL like: `https://college-workflow.onrender.com`

---

## 🎉 **That's It! Your App is Live!**

**Your live application will be at:**
```
https://YOUR_APP_NAME.onrender.com
```

**Demo Users:**
- Students: `student1`, `student2`
- Coordinators: `coordinator_cse`, `coordinator_ece`  
- HODs: `hod_cse`, `hod_ece`
- Director: `director`
- **Password for all:** `pass`

---

## 🔧 **Troubleshooting**

### **Build Fails?**
Check these files exist:
- `pom.xml` ✅
- `src/main/java/app/Main.java` ✅
- `mvnw` and `mvnw.cmd` ✅

### **App Won't Start?**
- Check Render logs in dashboard
- Ensure port configuration: `server.port=${PORT:8080}`

### **Database Issues?**
- H2 database is included, no setup needed
- Data persists between deployments

---

## 🚀 **Auto-Deploy Setup**

Once connected, every time you push to GitHub:
```bash
git add .
git commit -m "Update application"
git push
```
Render will automatically rebuild and deploy! 🎯

---

## 💡 **Pro Tips**

1. **Custom Domain:** Add your own domain in Render dashboard
2. **Environment Variables:** Add secrets in Render dashboard (not in code)
3. **Monitoring:** Check logs and metrics in Render dashboard
4. **Scaling:** Upgrade to paid plan for better performance

---

## 📞 **Need Help?**

- **Render Docs:** [render.com/docs](https://render.com/docs)
- **GitHub Issues:** Create issues in your repo
- **Render Support:** Available in dashboard

**Happy Deploying! 🎉**