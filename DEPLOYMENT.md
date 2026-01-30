# 🚀 Deploy to Railway - Complete Guide

## 🎯 Why Railway?
- ✅ **Perfect for Java Spring Boot** - Auto-detects and configures
- ✅ **$5 free credit monthly** - More than enough for testing
- ✅ **Zero configuration** - Just connect GitHub and deploy
- ✅ **No sleep mode** - Your app stays awake (unlike free Render)
- ✅ **Fast deployment** - Usually 5-10 minutes
- ✅ **Auto-deploy** - Updates when you push to GitHub

## 🚀 Step-by-Step Deployment

### **Step 1: Go to Railway**
1. Visit **[railway.app](https://railway.app)**
2. Click **"Start a New Project"**
3. **Sign up with GitHub** (recommended for easy repo access)

### **Step 2: Create New Project**
1. After login, you'll see the Railway dashboard
2. Click **"Start a New Project"**
3. Select **"Deploy from GitHub repo"**

### **Step 3: Connect Repository**
1. You'll see a list of your GitHub repositories
2. Find and select **"ShivamPatel02/college-workflow"**
3. Click **"Deploy Now"**

### **Step 4: Railway Auto-Magic** ✨
Railway will automatically:
- ✅ **Detect** it's a Java Maven project
- ✅ **Install** Java 17 runtime
- ✅ **Run** `./mvnw clean package -DskipTests`
- ✅ **Start** your Spring Boot application
- ✅ **Provide** a live HTTPS URL

### **Step 5: Monitor Deployment**
Watch the build logs in Railway dashboard:
```
==> Detected Java app
==> Installing Java 17
==> Running Maven build
==> Building JAR file
==> Starting Spring Boot application
==> Deployed to https://college-workflow-production.up.railway.app
```

### **Step 6: Access Your Live App**
Your app will be available at a URL like:
```
https://college-workflow-production-xxxx.up.railway.app
```

## 🧪 **Test Your Live Application**

### **Demo Users (Password: `pass`):**
- **Students:** `student1`, `student2`, `student3`, `student4`, `student5`, `student6`
- **Coordinators:** `coordinator_cse`, `coordinator_ece`, `coordinator_mech`, `coordinator_civil`, `coordinator_eee`, `coordinator_it`
- **HODs:** `hod_cse`, `hod_ece`, `hod_mech`, `hod_civil`, `hod_eee`, `hod_it`
- **Director:** `director`

### **Test Workflow:**
1. **Login** as `student1` / `pass`
2. **Create a new request** from dashboard
3. **Logout** (button only on dashboard)
4. **Login** as `coordinator_cse` / `pass`
5. **Approve the request** from pending approvals
6. **Check request tracking** and history

## 🔧 **Railway Configuration (Automatic)**

Railway automatically detects and configures:
```
Build Command: ./mvnw clean package -DskipTests
Start Command: java -jar target/workflow-0.0.1.jar
Port: $PORT (Railway provides this)
Environment: Production
```

## 💰 **Pricing**
- **Free:** $5 credit monthly (plenty for development/testing)
- **Usage:** ~$0.10-0.50/day for small apps
- **No sleep mode** - your app stays active

## 🚀 **Deployment Timeline**
- **Step 1-3:** 2 minutes (setup)
- **Build process:** 5-8 minutes (Maven download + compile)
- **Deployment:** 1-2 minutes (start application)
- **Total:** ~10 minutes to live app

## 🔄 **Auto-Deploy Setup**
Once deployed, every time you push to GitHub:
```bash
git add .
git commit -m "Update application"
git push origin main
```
Railway automatically rebuilds and redeploys! 🎯

## 🛠️ **Advanced Configuration (Optional)**

If you need custom settings, create `railway.json`:
```json
{
  "$schema": "https://railway.app/railway.schema.json",
  "build": {
    "builder": "NIXPACKS"
  },
  "deploy": {
    "startCommand": "java -jar target/workflow-0.0.1.jar",
    "healthcheckPath": "/",
    "healthcheckTimeout": 100
  }
}
```

## 🆘 **Troubleshooting**

### **Build Fails:**
- Check build logs in Railway dashboard
- Ensure `mvnw` and `.mvn/wrapper/` files are in repository
- Verify Java 17 compatibility

### **App Won't Start:**
- Check application logs in Railway dashboard
- Verify Spring Boot starts on `$PORT` environment variable
- Check for any missing dependencies

### **502 Bad Gateway:**
- App is still starting (wait 2-3 minutes)
- Check if app binds to correct port (`$PORT`)

## 🎉 **After Successful Deployment**

**You'll have:**
- 🌐 **Live URL** - Share with anyone
- 🔒 **HTTPS** - Secure by default  
- 📊 **Dashboard** - Monitor usage and logs
- 🔄 **Auto-deploy** - Updates on GitHub push
- 💾 **Persistent data** - H2 database file persists

## 📱 **Quick Deployment Checklist**
- [ ] Go to railway.app
- [ ] Sign up with GitHub
- [ ] "Start a New Project" → "Deploy from GitHub repo"
- [ ] Select "ShivamPatel02/college-workflow"
- [ ] Click "Deploy Now"
- [ ] Wait 10 minutes
- [ ] Test your live app!

## 🎯 **Expected Result**
Your college workflow management system will be live at:
```
https://your-app-name.up.railway.app
```

**Ready to deploy? Just follow the steps above! 🚀**

---

## 📞 **Need Help?**
- **Railway Docs:** [docs.railway.app](https://docs.railway.app)
- **GitHub Repo:** [github.com/ShivamPatel02/college-workflow](https://github.com/ShivamPatel02/college-workflow)
- **Support:** Railway has excellent Discord community support

**Your Spring Boot app is perfectly configured for Railway deployment! 🎓✨**