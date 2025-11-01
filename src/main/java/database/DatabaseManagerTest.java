package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManagerTest {

    public static Connection getConnection() {
        // Leer variables de entorno
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASS");

        // Debug: Imprimir variables
        System.out.println("DB_URL: " + dbUrl);
        System.out.println("DB_USER: " + dbUser);
        System.out.println("DB_PASS: " + (dbPass == null ? "null" : "***"));

        // Validar
        if (dbUrl == null || dbUser == null) {
            throw new RuntimeException(
                    "Error de configuración: Faltan variables de entorno (DB_URL, DB_USER, o DB_PASS). " +
                            "Asegúrate de que tu archivo .env existe y está completo."
            );
        }

        // Conectar
        try {
            // IMPORTANTE: Cargar el driver H2
            Class.forName("org.h2.Driver");

            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass == null ? "" : dbPass);
            System.out.println("✅ Conexión establecida: " + conn);
            return conn;
        } catch (Exception e) {
            System.err.println("❌ Error al conectar a H2: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a H2", e);
        }
    }
}