# 1. Map terminal context to Minikube
eval $(minikube docker-env)

# 2. Recompile your API Backend image
docker build -t spring-boot-api:latest ./api-project

# 3. Restart the API deployment pods
kubectl rollout restart deployment spring-api-backend

