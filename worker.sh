# 1. Point terminal context to Minikube
eval $(minikube docker-env)

# 2. Recompile the updated worker image
docker build -t spring-boot-worker:latest ./worker-project

# 3. Restart the deployment to pick up the new container code
kubectl rollout restart deployment spring-worker

