package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DaoTest {

    public static void uploadDatatoDataBaseFromRooms() {


        try (//try-catch-with_resources lo de dentro del paréntesis se elimina automáticamente para liberar espacio.
             //En este caso se cierran los canales a la base de datos.

             //1. Creamos la conexión llamando a nuestro DataBaseManager entiendo que esto puede que no vaya
             // en el dao, pero lo pongo aquí solo para hacer pruebas
             Connection connection = DatabaseManagerTest.getConnection();

             //2. El Statement es el que se encarga de llevar los comandos al SQL
             Statement statement = connection.createStatement()) {


            //3. El .execute sirve para hacer la query a la base de datos
            statement.execute("INSERT INTO USUARIOS (NOMBRE, CORREO) VALUES('test', 'pantalois@test.jpg')");

            //4. El .executeQuery nos sirve para sacar datos de la base de datos,
            //por ejemplo, sacar la cantidad de tickets que tiene un jugador
            ResultSet result = statement.executeQuery("SELECT * FROM USUARIOS");

            //5. Enseñamos los datos por consola
            while (result.next()) {
                System.out.println(result.getInt("id") + " - " + result.getString("nombre") +
                        " - " + result.getString("correo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
