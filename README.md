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

## Live demo

`https://munsl-sigem-backend.onrender.com`

Esta hosteado en una instancia gratuita de [render](https://dashboard.render.com/).
- Las instancias gratuitas dejan de funcionar por inactividad, lo que puede retrasar la primera solicitud después de mucho tiempo por algunos minutos.

## Autor
Franco Exequiel Becvort <🐤/>
- [Linkedin](https://www.linkedin.com/in/franco-becvort/)
- [Website](https://pollito.dev/)
