package org.example.conexion;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;
import java.util.Scanner;

public class GestorDB implements DataB {
    private Connection connection;

    public GestorDB() {
        getConexion();
    }

    public void getConexion() {
        String NombreUsuario = "MariaPB";
        String Password = "290619";
        String BaseDatos = "almacen";
        String Host = "127.0.0.1:3306";
        String Url = "jdbc:mariadb://" + Host + "/" + BaseDatos;
        try {
            //Cargo en memoria el Driver para poder utilizarlo, y pongo el nombre del Driver de mi libreria
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            //Conecto con la base de datos, lo que utilizamos de la clase Driver es el DriverManager.getConnection
            connection = DriverManager.getConnection(Url, NombreUsuario, Password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void llamarConexion() {
        System.out.println("Conectado");
    }

    public void tablaProductos() {
        try {
            String urlString = "https://dummyjson.com/products";
            URL direc = new URL(urlString);
            HttpURLConnection conex = (HttpURLConnection) direc.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conex.getInputStream()));

            String lectura = br.readLine();
            System.out.println(lectura);

            JSONObject objeto = new JSONObject(lectura);
            JSONArray productos = objeto.getJSONArray("products");
            String query = "INSERT INTO " + DataB.NOM_TABLA + " (" + DataB.ID + ", " + DataB.NOMBRE + ", " + DataB.DESCRIPCION + ", " + DataB.CANTIDAD + ", " + DataB.PRECIO + ") VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = null;
            try {
                pst = connection.prepareStatement(String.format(query));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < productos.length(); i++) {
                JSONObject prod = productos.getJSONObject(i);
                int Id = prod.getInt("id");
                String Nombre = prod.getString("title");
                String descripcion = prod.getString("description");
                int cantidad = prod.getInt("stock");
                int precio = prod.getInt("price");

                try {
                    pst.setInt(1, Id);
                    pst.setString(2, Nombre);
                    pst.setString(3, descripcion);
                    pst.setInt(4, cantidad);
                    pst.setFloat(5, precio);

                    pst.execute();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }


        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarEmpleado() {
        Scanner teclado = new Scanner(System.in);

        String query = "INSERT INTO " + DataB.NOM_TABLA_EMPLEADO + " (  " + DataB.NOMBRE_EMPLEADO + ", " + DataB.APELLIDO_EMPLEADO + ", " + DataB.CORREO_EMPLEADO + ") VALUES ( ?, ?, ?)";
        System.out.println("A continuacion vamos a añadir un empleado");
        System.out.println("Introduce el nombre");
        String nombre = teclado.nextLine();
        System.out.println("Introduce los apellidos");
        String apellido = teclado.nextLine();
        System.out.println("Introduce el correo");
        String correo = teclado.nextLine();
        try {
            PreparedStatement pst = null;
            try {
                pst = connection.prepareStatement(String.format(query));

                pst.setString(1, nombre.toString());
                pst.setString(2, apellido.toString());
                pst.setString(3, correo);
                pst.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void agregarPedido() {
        mostrarProductos();
        Scanner teclado = new Scanner(System.in);
        String query = "INSERT INTO " + DataB.NOM_TABLA_PEDIDO + " (  " + DataB.ID_PRODUCTO + ", " + DataB.DESCRIPCION_PEDIDO + ", " + DataB.PRECIO_TOTAL_PRODUCTO + ") VALUES (  ?,?,?)";

        System.out.println("A continuacion vamos a añadir un pedido");
        System.out.println("Introduce el ID del producto");
        int id = teclado.nextInt();
        System.out.println("¿Cuantas unidades tiene el pedido?");
        int cantidadUnidades = teclado.nextInt();
        float precioTotal = buscarPrecio(id) * cantidadUnidades;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(String.format(query));
            pst.setInt(1, id);
            pst.setString(2, buscarDescripcion(id));
            pst.setFloat(3, precioTotal);
            pst.execute();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void mostrarEmpleados() {
        String query = "SELECT * FROM " + DataB.NOM_TABLA_EMPLEADO;
        System.out.println("Mostrando empleados");
        PreparedStatement pst = null;
        try {
            try {
                pst = connection.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "  | " + rs.getString(2) + " " + rs.getString(3));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public void mostrarProductos() {
        String query = "SELECT * FROM " + DataB.NOM_TABLA;
        System.out.println("Mostrando Productos");
        PreparedStatement pst = null;
        try {
            try {
                pst = connection.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(1) + "  | " + rs.getString(2) + "  | " + rs.getString(5) + "€");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarPedidos() {
        String query = "SELECT * FROM " + DataB.NOM_TABLA_PEDIDO;
        System.out.println("Mostrando Pedidos");
        PreparedStatement pst = null;
        try {
            try {
                pst = connection.prepareStatement(query);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getInt(1) + "  | " + rs.getString(2) + "  | " + rs.getString(3) + "  | " + rs.getFloat(4) + "€");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarProductosBaratos() {
        String query = "SELECT * FROM " + DataB.NOM_TABLA + " WHERE " + DataB.PRECIO + " < " + 600;
        System.out.println("Mostrando Productos");
        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + "  | " + rs.getString(2) + "  | " + rs.getString(5) + "€");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void productosFavoritos() {
        String selectQuery = "SELECT " + DataB.ID + " FROM " + DataB.NOM_TABLA + " WHERE " + DataB.PRECIO + " > " + 1000;

        PreparedStatement pst = null;

        try {
            pst = connection.prepareStatement(selectQuery);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt(DataB.ID);
                insertarProductosFavoritos(idProducto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public String buscarDescripcion(int id) {
        String query = "SELECT " + DataB.DESCRIPCION + " FROM " + DataB.NOM_TABLA + " WHERE " + DataB.ID + " = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(query);
            pst.setString(1, String.valueOf(id));
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getString(DataB.DESCRIPCION);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public float buscarPrecio(int id) {
        String query = "SELECT " + DataB.PRECIO + " FROM " + DataB.NOM_TABLA + " WHERE " + DataB.ID + " = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(query);
            pst.setFloat(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            return rs.getFloat(DataB.PRECIO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public void insertarProductosFavoritos(int idProducto) {
        String insertQuery = "INSERT INTO " + DataB.NOM_TABLA_PRODUCTOS_FAV + " (" + DataB.ID_id_PRODUCTO_FAV + ") VALUES ( ?)";
        try {
            PreparedStatement pst = connection.prepareStatement(insertQuery);
            pst.setInt(1, idProducto);
            pst.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}






