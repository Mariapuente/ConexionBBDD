package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import org.example.conexion.GestorDB;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {

        GestorDB gestorDB = new GestorDB();
        gestorDB.llamarConexion();
        //gestorDB.tablaProductos();

        //gestorDB.agregarEmpleado();
        //gestorDB.agregarPedido();

        //gestorDB.mostrarEmpleados();
        //gestorDB.mostrarProductos();
        //gestorDB.mostrarPedidos();

        //gestorDB.mostrarProductosBaratos();

        //gestorDB.productosFavoritos();

    }
}

