package Parcial2;
import java.sql.*;
import java.util.ArrayList;
public class SistemaDeVentas {
    public static void main(String[] args) {
        // Ejemplo de creación de objetos y llamada a métodos

        // Crear un producto
        Producto producto1 = new Producto(1, "Milanesa", 10.5f, 50);

        // Imprimir informe de productos
        Productos.generarInforme();

        // Obtener un producto por ID
        Producto productoEncontrado = Productos.obtenerProducto(1);
        if (productoEncontrado != null) {
            System.out.println("\nProducto encontrado: " + productoEncontrado.getNombre());
        } else {
            System.out.println("\nProducto no encontrado.");
        }

        // Obtener el producto más vendido
        Producto productoMasVendido = Productos.obtenerProductoMasVendido();
        if (productoMasVendido != null) {
            System.out.println("\nProducto más vendido: " + productoMasVendido.getNombre());
        } else {
            System.out.println("\nNo hay información sobre el producto más vendido.");
        }

        // Crear un vendedor
        Vendedor vendedor1 = new Vendedor(1, "Emmanuel", "Martinez", 123456789, Date.valueOf("1990-01-01"), Date.valueOf("2020-01-01"));

        // Obtener un vendedor por ID
        Vendedor vendedorEncontrado = Comerciales.obtenerVendedorPorID(1);
        if (vendedorEncontrado != null) {
            System.out.println("\nVendedor encontrado: " + vendedorEncontrado.getNombre());
        } else {
            System.out.println("\nVendedor no encontrado.");
        }

        // Obtener listado de vendedores
        ArrayList<Vendedor> listaVendedores = Comerciales.listadoDeVendedores();
        if (listaVendedores != null) {
            System.out.println("\nListado de vendedores:");
            for (Vendedor vendedor : listaVendedores) {
                System.out.println("ID: " + vendedor.getVendedor_id() + ", Nombre: " + vendedor.getNombre());
            }
        } else {
            System.out.println("\nNo se pudo obtener el listado de vendedores.");
        }
    }
}

class Producto{
    private int producto_id;
    private String nombre;
    private float precio_por_unidad;
    private int stock;

    public Producto(int producto_id, String nombre, float precio_por_unidad, int stock) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio_por_unidad = precio_por_unidad;
        this.stock = stock;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio_por_unidad() {
        return precio_por_unidad;
    }

    public void setPrecio_por_unidad(float precio_por_unidad) {
        this.precio_por_unidad = precio_por_unidad;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

class Productos {
    public static void generarInforme() {
        String consulta = "SELECT * FROM productos";
        float valorTotalDeLosProductos = 0;
        System.out.printf("%-25s \t %-10s %-10s %-10s\n", "Nombre", "Stock", "Precio", "Total");
        try {
            ResultSet resultSet = DBHelper.ejecutarConsultaConResultado(consulta);
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                float precio_por_unidad = resultSet.getFloat("precio_por_unidad");
                int stock = resultSet.getInt("stock");
                float valorTotalDelProducto = stock * precio_por_unidad;
                valorTotalDeLosProductos += valorTotalDelProducto;
                System.out.printf("%-25s \t %-10d %-10f %-10f\n", nombre, stock, precio_por_unidad, valorTotalDelProducto);
            }
            System.out.printf("%-25s \t %-10s %-10s %-10f\n", " ", "Total", " ", valorTotalDeLosProductos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Producto obtenerProducto(int producto_id) {
        String consulta = "SELECT * FROM productos WHERE producto_id = '" + producto_id + "'";
        try {
            ResultSet resultSet = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultSet.next()) {
                int id = resultSet.getInt("producto_id");
                String nombre = resultSet.getString("nombre");
                float precio_por_unidad = resultSet.getFloat("precio_por_unidad");
                int stock = resultSet.getInt("stock");

                return new Producto(id, nombre, precio_por_unidad, stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Producto obtenerProductoMasVendido() {
        String consulta = "SELECT producto_id, SUM(cantidad_vendida) as total_vendido " +
                "FROM ventas " +
                "GROUP BY producto_id " +
                "ORDER BY total_vendido DESC " +
                "LIMIT 1";
        try {
            ResultSet resultSet = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultSet.next()) {
                int idProductoMasVendido = resultSet.getInt("producto_id");
                return obtenerProducto(idProductoMasVendido);
            } else {
                System.out.println("No hay información sobre el producto más vendido.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
class Vendedor {
    private int vendedor_id;
    private String nombre;
    private String apellido;
    private int dni;
    private Date fecha_nacimiento;
    private Date fecha_contratacion;

    public Vendedor(int vendedor_id, String nombre, String apellido, int dni, Date fecha_nacimiento, Date fecha_contratacion) {
        this.vendedor_id = vendedor_id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.fecha_contratacion = fecha_contratacion;
    }

    public Vendedor(String consultaBusqueda) {
        try {
            ResultSet resultSet = DBHelper.ejecutarConsultaConResultado(consultaBusqueda);
            this.vendedor_id = resultSet.getInt("vendedor_id");
            this.nombre = resultSet.getString("nombre");
            this.apellido = resultSet.getString("apellido");
            this.dni = resultSet.getInt("dni");
            this.fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
            this.fecha_contratacion = resultSet.getDate("fecha_contratacion");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(int vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_contratacion() {
        return fecha_contratacion;
    }

    public void setFecha_contratacion(Date fecha_contratacion) {
        this.fecha_contratacion = fecha_contratacion;
    }
}
class Comerciales {
    public static Vendedor obtenerVendedorPorID(int vendedor_id) {
        String consulta = "SELECT * FROM vendedores WHERE vendedor_id = '" + vendedor_id + "'";
        try {
            ResultSet resultSet = DBHelper.ejecutarConsultaConResultado(consulta);

            if (resultSet.next()) {
                int id = resultSet.getInt("vendedor_id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                int dni = resultSet.getInt("dni");
                Date fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
                Date fecha_contratacion = resultSet.getDate("fecha_contratacion");
                return new Vendedor(id, nombre, apellido, dni, fecha_nacimiento, fecha_contratacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<Vendedor> listadoDeVendedores () {
        ArrayList vendedores = new ArrayList<Vendedor>();
        String consulta = "SELECT * FROM vendedores";
        try {
            ResultSet resultSet = DBHelper.ejecutarConsultaConResultado(consulta);
            while (resultSet.next()) {
                int vendedor_id = resultSet.getInt("vendedor_id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                int dni = resultSet.getInt("dni");
                Date fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
                Date fecha_contratacion = resultSet.getDate("fecha_contratacion");
                Vendedor vendedor = new Vendedor(vendedor_id, nombre, apellido, dni, fecha_nacimiento, fecha_contratacion);
                vendedores.add(vendedor);
            }
            return vendedores;
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
class DBHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/ventas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void ejecutarConsulta(String consulta) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                statement.executeUpdate();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet ejecutarConsultaConResultado(String consulta) {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(consulta);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}