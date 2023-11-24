package org.example.conexion;

public interface DataB {

    // --------------------------------------------------------------------------
    String NOM_DB = "almacen";
    String USER = "MariaBD";
    String PASSWORD = "290619";
    String URL = "jdbc:mariadb://";
    String HOST = "127.0.0.1:3306";


    // -----------------------------------------------------------------------------
    String NOM_TABLA = "productos";
    String ID = "ID";
    String NOMBRE = "nombre";
    String DESCRIPCION = "descripcion";
    String CANTIDAD = "cantidad";
    String PRECIO = "precio";

    // ------------------------------------------------------------------------------
    String NOM_TABLA_EMPLEADO = "empleados";
    String ID_EMPLEADO = "ID";
    String NOMBRE_EMPLEADO = "Nombre";
    String APELLIDO_EMPLEADO = "Apellidos";
    String CORREO_EMPLEADO = "Correo";

    // ------------------------------------------------------------------------------
    String NOM_TABLA_PEDIDO = "pedidos";
    String ID_PEDIDO = "ID";
    String ID_PRODUCTO = "id_producto";
    String DESCRIPCION_PEDIDO = "descripcion";
    String PRECIO_TOTAL_PRODUCTO = "precio_total";

    // ------------------------------------------------------------------------------

    String NOM_TABLA_PRODUCTOS_FAV = "productos_fav";

    String ID_PRODUCTO_FAV = "ID";

    String ID_id_PRODUCTO_FAV = "id_producto";

}
