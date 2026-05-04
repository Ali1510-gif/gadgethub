FROM tomcat:8.5

RUN apt-get update && apt-get install -y mysql-server && rm -rf /var/lib/apt/lists/*

COPY gadgethub.sql /gadgethub.sql
COPY ./app/GadgetHub-Website.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD service mysql start && \
    echo "Waiting for MySQL..." && \
    sleep 15 && \
    mysql -u root -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root'; FLUSH PRIVILEGES;" && \
    mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS gadgethub;" && \
    mysql -u root -proot gadgethub < /gadgethub.sql && \
    catalina.sh run