 Mi Bot para Discord

## Descripción
El Bot envía mensajes privados a los usuarios los días lunes, miércoles y viernes mediante una tarea programada. Su propósito principal es ayudar a los usuarios a mantener el seguimiento de sus objetivos semanales mediante preguntas programadas y proporcionar ayuda en caso de ser necesario.

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

#### Descargar el dodigo base

``` shell
   git clone https://github.com/eduardob1324/Bot_Discord.git
```

#### Crear el servidor en discord
- link de ayuda:  [crear un servidor](https://support.discord.com/hc/es/articles/204849977--C%C3%B3mo-creo-un-servidor)

#### Crear el bot en discord
- se pueden apoyar en los pasos 1 y 2 del siguiente link
- link de ayuda:  [crear un Bot](https://www.hostinger.mx/tutoriales/como-alojar-un-bot-de-discord)

#### se requiere modificar el archivo docker-compose.yml
- el archivo se encuentra en la carpeta principal del proyecto
- Las lineas que se requiere modificar son:
 ```
 10    - key_bot=
 11    - server=
 ```
- ahi se deben condigurar la clave del bot y el id del servidor.
- link de ayuda: [ver id server](https://support.discord.com/hc/es/articles/206346498--D%C3%B3nde-puedo-encontrar-mi-ID-de-usuario-servidor-mensaje)

#### Configurar las tareas programadas 
- se requiere modificar el archivo ScheduledTask.java
```
 src/main/java/tasks/ScheduledTask.java
```
- la tarea encargada de enviar los mensajes a los mienbros del servidos los dias lunes miercoles y viernes es: **sendScheduledMessageOfDay**
- la tarea encargada de validar que los usuarios enviaron una respuesta es **sendScheduledUpdateResponseUser** en dado caso de que no respondan antes del siguiente envio de mensaje, los campos respuesta seran null.
- Las tareas porgramadas estan programadas para ejecutarse cada 2 minutos.
- para modificar el tiempo de ejecucion de las tarear programadas modificar el cron:
```
  @Scheduled(cron = "2 * * * * *")
```
- link de ayuda para configuracion del cron: [cron spring](https://spring.io/blog/2020/11/10/new-in-spring-5-3-improved-cron-expressions)
- Para modificar el mensaje que aparece cuando se ejecuta la tarea programada modificar el retorno en el metodo **getDayOfWeek** solo retorna respuesta los dias (1,3,5) lunes miercoles y viernes respectivamente.
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





















