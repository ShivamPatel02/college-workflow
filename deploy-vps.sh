#!/bin/bash
# VPS Deployment Script for Ubuntu/Debian

echo "🚀 Deploying College Workflow to VPS..."

# Update system
sudo apt update && sudo apt upgrade -y

# Install Java 17
echo "📦 Installing Java 17..."
sudo apt install openjdk-17-jre -y

# Create app directory
sudo mkdir -p /opt/college-workflow
cd /opt/college-workflow

# Download latest JAR (replace with your actual JAR)
echo "📥 Downloading application..."
wget https://github.com/ShivamPatel02/college-workflow/releases/latest/download/workflow-0.0.1.jar

# Create systemd service
echo "⚙️ Creating systemd service..."
sudo tee /etc/systemd/system/college-workflow.service > /dev/null <<EOF
[Unit]
Description=College Workflow Management System
After=network.target

[Service]
Type=simple
User=ubuntu
WorkingDirectory=/opt/college-workflow
ExecStart=/usr/bin/java -jar workflow-0.0.1.jar
Restart=always
RestartSec=10
Environment=SPRING_PROFILES_ACTIVE=prod

[Install]
WantedBy=multi-user.target
EOF

# Enable and start service
sudo systemctl daemon-reload
sudo systemctl enable college-workflow
sudo systemctl start college-workflow

# Install nginx (optional reverse proxy)
echo "🌐 Installing nginx..."
sudo apt install nginx -y

# Configure nginx
sudo tee /etc/nginx/sites-available/college-workflow > /dev/null <<EOF
server {
    listen 80;
    server_name your-domain.com;  # Replace with your domain
    
    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host \$host;
        proxy_set_header X-Real-IP \$remote_addr;
        proxy_set_header X-Forwarded-For \$proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto \$scheme;
    }
}
EOF

# Enable nginx site
sudo ln -s /etc/nginx/sites-available/college-workflow /etc/nginx/sites-enabled/
sudo nginx -t && sudo systemctl restart nginx

# Open firewall
sudo ufw allow 80
sudo ufw allow 443
sudo ufw allow 8080

echo "✅ Deployment complete!"
echo "🌐 Access your app at: http://your-server-ip"
echo "📊 Check status: sudo systemctl status college-workflow"
echo "📝 View logs: sudo journalctl -u college-workflow -f"