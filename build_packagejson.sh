# 1. Go to your terminal and enter your react folder
cd react-project

# 2. Use Vite to generate a standard React template right here
# (Press enter to accept defaults if prompted)
npm create vite@latest . -- --template react

# 3. Point your terminal back to Minikube
eval $(minikube docker-env)

# 4. Try building your Docker image again
docker build -t react-frontend:latest .


# not using npm, Generate a fresh Vite React project directly inside your local folder using Docker
docker run --rm -v "$(pwd)/react-project:/app" -w /app node:20-alpine sh -c "npx create-vite@latest . --template react"

