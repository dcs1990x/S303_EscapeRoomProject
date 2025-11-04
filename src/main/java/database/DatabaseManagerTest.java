package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManagerTest {

    // ✅ Única instancia de conexión (Singleton)
    private static Connection connection = null;

    private static final String DB_URL = "jdbc:h2:tcp://localhost:1521/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "123456";

    // Constructor privado para evitar instanciación externa
    private DatabaseManagerTest() {}

    /**
     * Obtiene la conexión única a la base de datos.
     * Si no existe o está cerrada, crea una nueva.
     */
    public static Connection getConnection() {
        try {
            // Verificar si la conexión existe y está abierta
            if (connection == null || connection.isClosed()) {
                System.out.println("DB_URL: " + DB_URL);
                System.out.println("DB_USER: " + DB_USER);
                System.out.println("DB_PASS: ***");

                // Cargar el driver H2
                Class.forName("org.h2.Driver");

                // Crear UNA SOLA conexión
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
                System.out.println("✅ Conexión establecida: " + connection);
            } //else {
              //  System.out.println("♻️ Reutilizando conexión existente: " + connection);
            //}

            return connection;

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Error: Driver H2 no encontrado: " + e.getMessage());
            throw new RuntimeException("Driver H2 no encontrado", e);
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a H2: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al conectar a H2", e);
        }
    }

    /**
     * Cierra la conexión cuando ya no se necesite
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("✅ Conexión cerrada correctamente");
                }
                connection = null;
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar conexión: " + e.getMessage());
            }
        }
    }
}