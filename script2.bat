docker build -f config-service/Dockerfile -t matiassilvaal/config-service:latest ./config-service
docker build -f eureka-service/Dockerfile -t matiassilvaal/eureka-service:latest ./eureka-service
docker build -f gateway-service/Dockerfile -t matiassilvaal/gateway-service:latest ./gateway-service
docker build -f data-service/Dockerfile -t matiassilvaal/data-service:latest ./data-service
docker build -f empleado-service/Dockerfile -t matiassilvaal/empleado-service:latest ./empleado-service
docker build -f justificativo-service/Dockerfile -t matiassilvaal/justificativo-service:latest ./justificativo-service
docker build -f horasextras-service/Dockerfile -t matiassilvaal/horasextras-service:latest ./horasextras-service
docker build -f calcularplanilla-service/Dockerfile -t matiassilvaal/calcularplanilla-service:latest ./calcularplanilla-service