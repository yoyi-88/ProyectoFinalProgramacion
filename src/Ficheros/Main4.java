package Ficheros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Main4 {
    public static void main(String[] args) {
        try {
            String url = "jdbc:mariadb://localhost:3306/proyecto";
            Connection con = DriverManager.getConnection(url,"root","");
            Statement stmt = con.createStatement();
            System.out.println("Conexión Exitosa");

            // Execute the query
            String query = "SELECT * FROM tareas";
            ResultSet rs = stmt.executeQuery(query);

            // Process the result set
            while (rs.next()) {
                // Replace with actual column names from your table
                int id = rs.getInt("id");
                String Descripcion = rs.getString("Descripcion");
                String NombreCorto = rs.getString("NombreCorto");
                System.out.println("ID: " + id + ", Tarea: " + Descripcion + ", Nombre Corto: " + NombreCorto);
            }
            rs.close(); //Nuevo
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.err.println("Error Conexión");
            e.printStackTrace();
        }
    }
}