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
### reqerimientos
- JDK 17
- mvn 3.9.8
- Docker-desktop
- Docker-compose

### Descargar el dodigo base

``` shell
   git clone https://github.com/eduardob1324/Bot_Discord.git
```

### compilar el proyecto

``` shell
   mvn install -DskipTests=true 
```

### crar ek servidor en discord
- link de ayuda https://support.discord.com/hc/es/articles/204849977--C%C3%B3mo-creo-un-servidor
