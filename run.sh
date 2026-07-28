# 1. Direct terminal session environment variable mappings into Minikube's Docker Engine
eval $(minikube docker-env)

# 2. Build application containers inside the Minikube registry scope
docker build -t spring-boot-api:latest ./api-project
docker build -t spring-boot-worker:latest ./worker-project
docker build -t react-frontend:latest ./react-project

# 3. Provision the full application topology setup configurations
kubectl apply -f infrastructure.yaml

# 4. Open a system bridge interface mapping cluster ports to your computer network stack
# Note: This command asks for your root user password to claim administrative control over port 443/80. Keep this running.
sudo minikube tunnel
