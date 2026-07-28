# 1. Enable the built-in Nginx Ingress Controller
minikube addons enable ingress

# 2. Generate a self-signed SSL certificate valid for localhost
openssl req -x509 -nodes -days 365 -newkey rsa:2048 \
  -keyout tls.key -out tls.crt \
  -subj "/CN=localhost/O=Development"

# 3. Create a Kubernetes TLS Secret using the generated certificate files
kubectl create secret tls localhost-tls-secret --key=tls.key --cert=tls.crt

# 4. Clean up the local cert files (they are now safely stored inside the cluster)
rm tls.key tls.crt
