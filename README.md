# OperacionFuegoDeQuasarService
Repositorio privado para servicio Spring OperacionFuegoDeQuasarService.

Como pre requisitos se debe tener instalado Maven, Docker, Heroku CLI, creación de cuenta en Heroku.

•	Crear archivo Dockerfile sobre raíz de proyecto.
 
El archivo debe tener la siguiente configuración:

FROM openjdk:8-alpine
EXPOSE 8081
VOLUME /tmp
COPY ./target/OperacionFuegoDeQuasarService-0.0.1.jar OperacionFuegoDeQuasarService-0.0.1.jar
RUN apk update
# Fire up our Spring Boot app by default
CMD ["sh","-c","java -Dserver.port=$PORT -Xmx300m -Xss512k -XX:CICompilerCount=2 -Dfile.encoding=UTF-8 -XX:+UseContainerSupport -jar /OperacionFuegoDeQuasarService-0.0.1.jar","--spring.profiles.active=h2"]

•	Abrir un CMD y ubicarse sobre la raíz del proyecto.

•	Ejecutar comando mvn package para compilar el proyecto vía maven.

•	Ejecutar heroku login

Se abrirá una ventana en el navegador donde se debe ingresar contraseña y usuario de Heroku.

heroku login
Logging in... done
Logged in as miguel_azf@hotmail.com
G4GAMhkikt1AQ.i3LWXFNGtkyMboqTyTSfGgYaId_09uelPt4EdxFNaA0

•	Ejecutar heroku container:login

heroku container:login
Login Succeeded


•	Ejecutar heroku create web -a meli
(meli es el nombre del proyecto a crear)

•	Ejecutar heroku container:push web –a meli
(Hace push sobre registry en heroku)

•	Ejecutar heroku container:release web –a meli.

(Realiza despliegue de la app sore proyecto meli)

•	Finalmente ejecutar heroku open –a meli.

(Abre navegador con la url inicial de la aplicación, se deberá agregar path según sea el caso)

