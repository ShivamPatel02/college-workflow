#!/bin/bash
set -e

echo "Setting executable permissions for Maven wrapper..."
chmod +x ./mvnw

echo "Building application..."
./mvnw clean package -DskipTests -Dmaven.javadoc.skip=true

echo "Build completed successfully!"
ls -la target/