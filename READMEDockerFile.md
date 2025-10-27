# 🚀 Guía de Pruebas: Entorno Docker + Java

Esta guía te permitirá levantar el proyecto completo (Aplicación Java + Base de Datos H2) en tu máquina usando Docker.

**¡Importante!** Esta rama **incluye el archivo de la base de datos** (`data/h2/test.mv.db`). No se necesita crear una base de datos desde el proyecto Java, solo nos conectamos a la ya existente.

---

## Paso 0: Prerrequisitos (Instalación Única)

Si es la primera vez que configuras este entorno, necesitarás instalar:

1.  **Docker Desktop:** Para ejecutar los contenedores.
    * [docker.com/products/docker-desktop](https://www.docker.com/products/docker-desktop/)
    * **Importante:** Después de instalar, ¡asegúrate de que Docker Desktop esté en marcha! (Verás el icono de la ballena 🐳 en tu barra de tareas).
2.  **Maven:** Necesario para "empaquetar" el código Java.
    * [maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
    * (Asegúrate de que esté en tu `PATH` del sistema para poder usar el comando `mvn`).

---

## Paso 1: Descargar la Rama del Proyecto

1.  Descarga todas las ramas del servidor:
    ```bash
    git fetch origin
    ```

2.  Cámbiate a la rama de pruebas:
    ```bash
    git switch lois_feature/Dockerfile+H2connection
    ```

---

## Paso 2: Configurar tu Entorno (Archivo `.env`)

Nunca subimos contraseñas al repositorio. Para eso usamos un archivo local.

1.  En la raíz del proyecto, busca el archivo plantilla `env.example`.
2.  Crea una **copia** de ese archivo y llámala `.env` (con el punto al principio).
3.  Abre el nuevo archivo `.env` y rellena las credenciales que usará la base de datos:

    ```ini
    # .env
    DB_USER= xxxxxx (Pon el usuario que quieras)
    DB_PASS= xxxxxx (Pon la contraseña que quieras, o déjala vacía)
    ```

---

## Paso 3: El Ciclo de Pruebas (Construir y Ejecutar)

Este es el ciclo que repetirás cada vez que quieras probar cambios.

### 3a. Compilar el Código Java (Maven)

Nuestro `Dockerfile` no compila el código, solo lo copia. Por tanto, **debes compilarlo tú primero**.

```bash
mvn clean package
```

(Esto crea el archivo .jar en la carpeta target/ que Docker necesita)

### 3b. Levantar el Entorno (Docker)

```bash
docker-compose up --build
```

--build: Fuerza a Docker a crear una nueva imagen de la app, copiando el nuevo .jar que acabas de compilar.

up: Arranca los dos contenedores: h2db (la base de datos) y app (tu aplicación Java).

El contenedor h2db detectará automáticamente el archivo test.mv.db de la carpeta data/h2 y cargará la base de datos con las tablas y datos existentes.


## Paso 4: Probar la Aplicación

Vuelve a la terminal donde tienes `docker-compose up` corriendo. Verás el menú interactivo que se ha creado para pruebas:

Si quieres verificar la base de datos manualmente:

1.  Abre tu navegador web y ve a la consola de H2: **`http://localhost:81`**
2.  Usa los siguientes datos para conectar:
    * **JDBC URL:** `jdbc:h2:tcp://localhost:1521//opt/h2-data/test`
    * **User Name:** El `DB_USER` que pusiste en tu `.env`.
    * **Password:** El `DB_PASS` que pusiste en tu `.env`.
3.  Haz clic en **Connect**. Podrás ver la tabla `USUARIOS` y sus datos.

---

## Paso 5: Cómo Parar Todo

1.  Ve a la terminal donde se está ejecutando `docker-compose`.
2.  Pulsa `Ctrl + C`.
3.  (Opcional) Si quieres borrar los contenedores (no los datos), ejecuta:
    ```bash
    docker-compose down
    ```
