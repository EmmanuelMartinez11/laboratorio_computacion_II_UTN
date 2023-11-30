import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nueva_base_de_datos";
        String usuario = "root";
        String pass = "";


        try {
            Connection conexion = DriverManager.getConnection(url, usuario, pass);
            String consulta = "SELECT * FROM estudiantes";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();
            mostrarTabla(conexion);

            resultSet.close();
            preparedStatement.close();
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void mostrarTabla(Connection conexion) {
        try {
            String consulta = "SELECT * FROM estudiantes";
            PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                int legajo = resultSet.getInt("legajo");
                int dni = resultSet.getInt("dni");

                System.out.println("ID: " + id);
                System.out.println("Nombre: " + nombre);
                System.out.println("Apellido: " + apellido);
                System.out.println("Legajo: " + legajo);
                System.out.println("DNI: " + dni);
                System.out.println("-----------------------");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}