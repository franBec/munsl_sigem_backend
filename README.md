# munsl_sigem_backend

![escudo municipal](/assets/escudo-municipal-blanco-2x.png)

Este proyecto es un **MVP (Producto Mínimo Viable)** de una posible reescritura del backend del sistema [SIGEM](https://sigem.sanluislaciudad.gob.ar/).

## Objetivo del Proyecto

El objetivo principal de este proyecto es crear una representación fiel del funcionamiento actual de SIGEM mientras se sientan las bases para una futura modernización. Este MVP forma parte de la primera fase de una serie de mejoras planificadas, que incluyen:

1. **Migración de la lógica de negocio**:
    - De Java 8 a Java 21.
    - Abandonar el monolito basado en [Grails](https://grails.org/).
    - Adoptar una arquitectura separada de frontend (Next.js 14) y backend (Groovy Spring Boot 3).

2. **Prioridad en la representación actual**:
    - Dado el alcance y la complejidad de esta transición, el objetivo inicial es replicar el comportamiento actual de manera fiel.
    - Algunas "mejores prácticas" no se implementarán en esta etapa debido a limitaciones de tiempo y compatibilidad. Por ejemplo:
        - Correcto nombrado y estructura de las bases de datos.
        - Uso de buckets para el almacenamiento de archivos en lugar de discos locales.

## Limitaciones

- Este proyecto es un MVP y no refleja el estado final de la modernización planificada.
- Algunas prácticas y estructuras heredadas han sido mantenidas para garantizar una transición gradual.

## Licencia

Aún no se ha definido una licencia pública para este proyecto. Si el código permanece abierto, la licencia será especificada en futuras actualizaciones del repositorio.

## Documentación API

La documentación de OpenAPI se puede encontrar en el archivo [munsl_sigem_backend.yaml](https://github.com/franBec/munsl_sigem_backend/blob/main/src/main/resources/openapi/munsl_sigem_backend.yaml).

## Respecto a SPRING_PROFILES_ACTIVE

SPRING_PROFILES_ACTIVE es una propiedad clave en aplicaciones basadas en Spring Boot que define qué perfil o perfiles (profiles) activos utilizará la aplicación al momento de ejecutarse.

- Por ejemplo, si se establece SPRING_PROFILES_ACTIVE=dev, Spring Boot cargará las propiedades del archivo `application-dev.yml`

La siguiente tabla detalla las diferencias clave entre los tres perfiles de este proyecto:

| Propiedad     | application-dev.yml (Desarrollo local) | application-test.yml (Live demo) | application.yml (Producción) |
|---------------|----------------------------------------|----------------------------------|------------------------------|
| CORS          | http://localhost:3000                  | https://munsl-sigem.vercel.app/  | No especificado              |
| Base de datos | Conexión en memoria con H2             | Conexión en memoria con H2       | SQL Server                   |

- application-dev.yml: Configuración enfocada en el desarrollo local. Incluye CORS para localhost y base de datos en memoria para pruebas rápidas.
- application-test.yml: Configuración similar a desarrollo pero ajustada para pruebas con un cliente en la nube (vercel.app).
- application.yml (Base/Producción): Configuración principal para producción, diseñada para SQL Server.

## Base de datos en memoria con H2

La configuración de los perfiles application-dev.yml y application-test.yml utiliza una base de datos H2 en memoria para almacenar los datos. Esto significa que toda la información y las tablas que se crean en la base de datos existen únicamente mientras la aplicación está en ejecución. Cuando la aplicación se detiene o se reinicia, todos los datos almacenados en la base de datos se pierden.

**Beneficios de usar H2 en memoria**
- Rápido y ligero: Ideal para pruebas y desarrollo local sin necesidad de configurar una base de datos externa.
- Automatización: Permite cargar datos iniciales de forma rápida y reproducible.

## Live demo

[https://munsl-sigem-backend.onrender.com](https://munsl-sigem-backend.onrender.com)

- Usa `SPRING_PROFILES_ACTIVE = test` 
- Esta hosteado en una instancia gratuita de [render](https://dashboard.render.com/).
  - Las instancias gratuitas dejan de funcionar por inactividad, lo que puede retrasar la primera solicitud después de mucho tiempo por algunos minutos.

Actualmente, solo existen dos usuarios con los cuales se puede hacer login

| USUARIO     | CONTRASEÑA | DESCRIPCION |
|-------------|------------|-------------|
| 23215487369 | XXX        | Ciudadano   |
| mvillegas   | XXX        | Admin       |

## Autor

Franco Exequiel Becvort <🐤/>
- [Linkedin](https://www.linkedin.com/in/franco-becvort/)
- [Website](https://pollito.dev/)
