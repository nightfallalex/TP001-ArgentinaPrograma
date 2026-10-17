# TP001 - Sistema de Gestión de Parque de Atracciones
### Programa: Argentina Programa - Primer Trabajo Práctico Grupal

Este proyecto consiste en una aplicación backend desarrollada en **Java** utilizando el framework **Spring Boot**, diseñada para la gestión, control y administración de un parque de atracciones temático. Permite modelar las interacciones fundamentales del parque, tales como el control de visitantes, la administración de atracciones, turnos y accesos.

---

## 🚀 Estado del Proyecto e Instalación

El proyecto se encuentra en una fase funcional apta para pruebas de desarrollo local. Al utilizar una base de datos en memoria, **no requiere una preinstalación o configuración externa de bases de datos relacionales tradicionales**, lo que facilita un despliegue inmediato.

### Requisitos Previos

Para ejecutar y compilar este proyecto de forma local, asegúrese de contar con las siguientes herramientas instaladas en su entorno de desarrollo:

*   **Java Development Kit (JDK):** Versión 17 o superior.
*   **Apache Maven:** Sistema de gestión de dependencias (generalmente integrado en los IDEs modernos).
*   **IDE Recomendado:** [IntelliJ IDEA](https://jetbrains.com) (Versión Ultimate o Community con soporte Spring), [Eclipse](https://eclipse.org) o [Visual Studio Code](https://visualstudio.com) con el "Spring Boot Extension Pack".

### Instrucciones de Despliegue Local

1.  **Clonar el repositorio:**
    Abra su terminal (o Git Bash) y ejecute el siguiente comando:
    ```bash
    git clone https://github.com/nightfallalex/TP001-ArgentinaPrograma.git
    cd TP001-ArgentinaPrograma
    ```

2.  **Compilar el proyecto:**
    Descargue las dependencias necesarias y compile el archivo ejecutable mediante Maven:
    ```bash
    mvn clean compile
    ```

3.  **Ejecutar la aplicación:**
    Inicie el servidor embebido Tomcat provisto por Spring Boot ejecutando:
    ```bash
    mvn spring-boot:run
    ```
    La aplicación se iniciará por defecto en el puerto `8080` (`http://localhost:8080`).

---

## 🛠️ Arquitectura y Tecnologías Utilizadas

El sistema adopta una arquitectura en capas estándar de la industria para asegurar la escalabilidad y el desacoplamiento de componentes:

*   **Framework Principal:** [Spring Boot](https://spring.io), facilitando la auto-configuración y la inyección de dependencias.
*   **Capa de Persistencia:** [Spring Data JPA / Hibernate](https://spring.io) para el mapeo objeto-relacional (ORM).
*   **Base de Datos:** [H2 Database Engine](https://www.h2database.com/), un motor de base de datos relacional SQL en memoria, ideal para fases de desarrollo, testing e integración continua sin persistencia volátil externa.
*   **Gestor de Dependencias:** Maven (`pom.xml`).

---

## 🗄️ Acceso a la Base de Datos (H2 Console)

Para inspeccionar el estado de las tablas, realizar consultas SQL en tiempo real y validar el comportamiento de las entidades del parque de atracciones, puede acceder a la interfaz web provista por H2:

1.  Asegúrese de que la aplicación esté ejecutándose localmente.
2.  Acceda desde su navegador web a: **`http://localhost:8080/h2-console`**
3.  En el campo de configuración **JDBC URL**, verifique que coincida con la ruta en memoria establecida en el archivo `application.properties` (comúnmente `jdbc:h2:mem:testdb` o similar).
4.  Haga clic en **Connect** (por defecto, el usuario es `sa` y la contraseña permanece en blanco).

---

---

## 📊 Estructura de Entidades (Modelo de Datos)

El sistema implementa el paradigma de Programación Orientada a Objetos mapeado de forma relacional mediante **JPA/Hibernate**. A continuación, se detallan las entidades principales que representan el núcleo del Parque de Atracciones y sus relaciones conceptuales:

1.  **Atraccion (Atracción):**
    *   *Atributos:* `id` (Long), `nombre` (String), `descripcion` (String), `capacidadMax` (Integer), `edadMinima` (Integer), `estado` (Boolean/Enum para mantenimiento o activa).
    *   *Relaciones:* `OneToMany` con *Turno* o *RegistroAcceso*.

2.  **Visitante (o Cliente):**
    *   *Atributos:* `id` (Long), `dni` (String), `nombre` (String), `apellido` (String), `edad` (Integer), `saldoPase` (Double).
    *   *Relaciones:* `OneToMany` con *RegistroAcceso*.

3.  **RegistroAcceso / Turno (Control de Acceso):**
    *   *Atributos:* `id` (Long), `fechaHora` (LocalDateTime), `costoToken` (Double), `estadoAcceso` (Enum: Concedido, Rechazado por edad/saldo).
    *   *Relaciones:* `ManyToOne` hacia *Visitante* y `ManyToOne` hacia *Atraccion*.

---

## 🔌 API Endpoints (Controladores REST)

La API expone servicios estructurados bajo las convenciones REST, retornando respuestas en formato **JSON**. El puerto base asignado localmente es `http://localhost:8080`.

### 🎡 Gestión de Atracciones (`/api/atracciones`)

*   `GET /api/atracciones` — Recupera el listado completo de atracciones operativas en el parque.
*   `GET /api/atracciones/{id}` — Obtiene los detalles específicos de una atracción por su ID.
*   `POST /api/atracciones` — Registra una nueva atracción en el sistema (Parámetros: JSON con datos de capacidad y restricciones).
*   `PUT /api/atracciones/{id}` — Actualiza la información o el estado de mantenimiento de una atracción existente.
*   `DELETE /api/atracciones/{id}` — Da de baja una atracción del catálogo del parque.

### 👥 Gestión de Visitantes (`/api/visitantes`)

*   `GET /api/visitantes` — Lista todos los visitantes registrados en el sistema.
*   `POST /api/visitantes` — Da de alta a un nuevo visitante y asigna los datos de su pase.
*   `PUT /api/visitantes/{id}/cargar-saldo` — Endpoint de negocio específico para recargar el saldo del pase/tarjeta del visitante.

### 🎟️ Control de Accesos y Turnos (`/api/accesos`)

*   `POST /api/accesos/validar` — Procesa e intenta registrar la entrada de un *Visitante* a una *Atracción*. Ejecuta las validaciones lógicas del negocio (verificación de edad mínima permitida y saldo suficiente en el pase). Devuelve un JSON con el resultado del acceso y el código de estado correspondiente (`200 OK` si es válido, `400 Bad Request` si infringe una norma del parque).


---

## 👥 Colaboradores y Autoría

*   **Desarrollado por:** [nightfallalex](https://github.com/nightfallalex) y equipo de trabajo grupal.
*   Proyecto académico desarrollado en el marco del curso de formación **Argentina Programa**.
