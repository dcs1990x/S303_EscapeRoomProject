package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManagerTest {

    public static Connection getConnection() {

        // 1. Leemos las variables de entorno
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        // 2. Validamos (Principio "Fail-Fast")
        if (dbUrl == null || dbUser == null || dbPass == null) {
            // Si CUALQUIERA de las variables es null, detenemos la aplicación.
            throw new RuntimeException(
                    "Error de configuración: Faltan variables de entorno (DB_URL, DB_USER, o DB_PASS). " +
                            "Asegúrate de que tu archivo .env existe y está completo."
            );
        }

        // 3. Si todo existe, intentamos conectar
        try {
            return DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (Exception e) {
            // Este 'catch' ahora solo capturará errores reales de SQL
            // (ej. "Contraseña incorrecta", "DB no encontrada", etc.)
            throw new RuntimeException("Error al conectar a H2", e);
        }
    }
}
