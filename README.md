<img width="650" height="358" alt="screenshot_java" src="https://github.com/user-attachments/assets/43fe2e3d-63b4-410b-afe6-2ddca1c2f206" />

This is a webapp that has react frontend, Java Springbok, postgresql, JPA, Kafka as backend,
simulated the kubernetes, Ingress load balancer, docker deployment using Minikube.

🗺️ The Port Mapping BreakdownHere is a visual map of how a request moves through the system, 
  explaining why the numbers change:
  
         Browser ---> https://localhost (Port 443 / 80)
                             |
                             v
                  [ Kubernetes Ingress ] (API Gateway)
                 /                      \
      Matches path: /                    Matches path: /api
               /                            \
              v                              v
   React Frontend Service            Spring API Service 
    - Service Port: 80                 - Service Port: 8080
    - Target Port: 80                  - Target Port: 8080

              |                              |
              v                              v
      React Pod                       Spring API Pod 
     (Nginx Server on port 80)       (Tomcat Server on port 8080)
