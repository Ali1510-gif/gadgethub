FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=gadgethub
ENV MYSQL_USER=project
ENV MYSQL_PASSWORD=rayeesali1510

# Copy SQL file inside container
COPY gadgethub.sql /docker-entrypoint-initdb.d/