eval $(minikube docker-env)

# 3. Compile the local image using the directory payload
docker build -t react-frontend:latest ./react-project
