# Mi Bot para Discord

## Descripción
El Bot envía mensajes privados a los miembros del servidor los días lunes, miércoles y viernes mediante una tarea programada. Su propósito principal es ayudar a los usuarios a mantener el seguimiento de sus objetivos semanales mediante preguntas programadas y proporcionar ayuda en caso de ser necesario.

## Características Principales
- **Envío de Preguntas Programadas:**  Envía preguntas automáticas a los usuarios los días lunes, miércoles y viernes.
- **Esperar Respuestas:** Recibe y gestiona las respuestas de los usuarios.
- **Notificación a Administradores:** Informa a los administradores sobre las dudas o necesidades de ayuda de los usuarios.
- **Guardado de Datos:** Utiliza una base de datos en memoria para guardar las respuestas y datos de los usuarios.


## Preguntas Realizadas
- **Lunes:** ¿Cuál es el objetivo de la semana?
- **Miércoles:** ¿Cómo vas con el objetivo? ¿Necesitas ayuda? ¿En qué necesitas ayuda?
- **Viernes:** ¿Qué porcentaje de avance lograste en tu objetivo?

## Instalación y Configuración
#### Reqerimientos
- JDK 17
- mvn 3.9.8
- Docker-desktop
- Docker-compose

#### Descargar el código base

``` shell
   git clone https://github.com/eduardob1324/Bot_Discord.git
```

#### Crear el servidor en Discord
- link de ayuda:  [crear un servidor](https://support.discord.com/hc/es/articles/204849977--C%C3%B3mo-creo-un-servidor)

#### Crear el bot en Discord
- Se pueden apoyar en los pasos 1 y 2 del siguiente enlace:
- Enlace de ayuda:  [crear un Bot](https://www.hostinger.mx/tutoriales/como-alojar-un-bot-de-discord)

#### se requiere modificar el archivo 'docker-compose.yml'
- El archivo se encuentra en la carpeta principal del proyecto
- Las lineas que se requiere modificar son:
 ```
 10    - key_bot=
 11    - server=
 ```
- Ahi se deben condigurar la clave del Bot y el ID del servidor.
- link de ayuda: [ver id server](https://support.discord.com/hc/es/articles/206346498--D%C3%B3nde-puedo-encontrar-mi-ID-de-usuario-servidor-mensaje)

#### Configurar las tareas programadas 
- Se requiere modificar el archivo ScheduledTask.java
```
 src/main/java/tasks/ScheduledTask.java
```
- La tarea encargada de enviar los mensajes a los mienbros del servidos los dias lunes, miércoles y viernes es: **sendScheduledMessageOfDay**
- La tarea encargada de validar que los usuarios enviaron una respuesta es **sendScheduledUpdateResponseUser** en dado caso de que no respondan antes del siguiente envío de mensaje, los campos respuesta serán null.
- Las tareas porgramadas estan programadas para ejecutarse cada 2 minutos.
- Para modificar el tiempo de ejecución de las tarear programadas modificar el cron:
```
  @Scheduled(cron = "2 * * * * *")
```
- link de ayuda para configuracion del cron: [cron spring](https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions)
- Para modificar el mensaje que aparece cuando se ejecuta la tarea programada modificar el retorno en el método **getDayOfWeek** solo retorna respuesta los dias (1, 3, 5) lunes, miércoles y viernes respectivamente.
```
    public static int getDayOfWeek(){
        LocalDate currentDate = LocalDate.now();
        //return currentDate.getDayOfWeek().getValue();
        return 1;
    }
```

#### Compilar el proyecto

``` shell
   mvn install -DskipTests=true 
```

#### Para levantar el proyecto
- Abre una terminal desde la carpeta raíz del proyecto y ejecuta;
``` shell
   docker-compose up
```
- Una vez que la aplicación esté desplegada, verás en Discord que el bot está activo.

## Funcionalidades en desarrollo.
- Creación de interfaz para poder crear eventos a los que asistan los miembros del servidor.
- Integración de listener para recepción de mensajes con #
- Creación de reportes para manejo de métricas.





















