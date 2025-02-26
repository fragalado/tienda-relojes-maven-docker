# Usa una imagen de Tomcat compatible con Jakarta EE
FROM tomcat:10.1-jdk21

# Copia el archivo WAR generado en la carpeta de despliegue de Tomcat
COPY target/TiendaRelojes-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/TiendaRelojes.war

# Expone el puerto 8080 para acceso web
EXPOSE 8080

# Comando para ejecutar Tomcat
CMD ["catalina.sh", "run"]