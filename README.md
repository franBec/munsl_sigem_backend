# munsl_sigem_backend

![escudo municipal](/assets/escudo-municipal-blanco-2x.png)
![sigem](/assets/sigem-blanco.png)

Este proyecto es un **MVP (Producto Mínimo Viable)** de una posible reescritura del backend del sistema [SIGEM](https://sigem.sanluislaciudad.gob.ar/).

## Objetivo del Proyecto

El objetivo principal de este proyecto es crear una representación fiel del funcionamiento actual de SIGEM mientras se sientan las bases para una futura modernización. Este MVP forma parte de la primera fase de una serie de mejoras planificadas, que incluyen:

1. **Migración de la lógica de negocio**:
    - De Java 8 a Java 21.
    - Abandonar el monolito basado en [Grails](https://grails.org/).
    - Adoptar una arquitectura separada de frontend y backend.

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

## Levantar el proyecto en local

### Requisitos previos
- Para ejecutar con Gradle:
- **Java 21**
- **Gradle**
- Para implementación en contenedores:
- **Docker**

### Ejecutar con  Gradle
1. Clonar el repositorio:
    ```bash
    git clone https://github.com/franBec/munsl_sigem_backend
    ```
2. Navegar al directorio del proyecto:
    ```bash
    cd munsl_sigem_backend
    ```
3. Construir y ejecutar la applicación usando Gradle:
    ```bash
    ./gradlew bootRun
    ```

### Ejecutar con Docker
1. Clonar el repositorio:
    ```bash
    git clone https://github.com/franBec/munsl_sigem_backend
    ```
2. Navegar al directorio del proyecto:
    ```bash
    cd munsl_sigem_backend
    ```
3. Construir la imagen Docker:
    ```bash
    docker build -t munsl_sigem_backend .
    ```
4. Ejecuar el contenedor Docker:
     ```bash
     docker run -p 8080:8080 munsl_sigem_backend
     ```
## Author
Franco Exequiel Becvort <🐤/>
- [Linkedin](https://www.linkedin.com/in/franco-becvort/)
- [Website](https://pollito.dev/)