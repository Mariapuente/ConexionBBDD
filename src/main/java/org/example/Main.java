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
import java.util.Scanner;


import org.example.conexion.GestorDB;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {

        GestorDB gestorDB = new GestorDB();
        gestorDB.llamarConexion();
        Scanner teclado = new Scanner(System.in);

            System.out.println("Bienvenido al gestor de datos");
            System.out.println("Por favor, selecciona una de las siguientes opciones");
            System.out.println("1. Agregar un empleado");
            System.out.println("2. Agregar un pedido");
            System.out.println("3. Mostrar empleados");
            System.out.println("4. Mostrar productos");
            System.out.println("5. Mostrar pedidos");
            System.out.println("6. Mostrar productos baratos");
            System.out.println("7. Salir");

            int opcion = teclado.nextInt();


        while (  opcion < 7 ){
            teclado.nextLine();
            switch (opcion){
                case 1:
                    gestorDB.agregarEmpleado();
                    System.out.println("¿Desea realizar otra operación?, si es así, seleccione el numero correspondiente. De lo contrario, pulse 7");
                    opcion = teclado.nextInt();
                    break;
                case 2:
                    gestorDB.agregarPedido();
                    System.out.println("¿Desea realizar otra operación?, si es así, seleccione el numero correspondiente. De lo contrario, pulse 7");
                    opcion = teclado.nextInt();
                    break;
                case 3:
                    gestorDB.mostrarEmpleados();
                    System.out.println("¿Desea realizar otra operación?, si es así, seleccione el numero correspondiente. De lo contrario, pulse 7");
                    opcion = teclado.nextInt();
                    break;
                case 4:
                    gestorDB.mostrarProductos();
                    System.out.println("¿Desea realizar otra operación?, si es así, seleccione el numero correspondiente. De lo contrario, pulse 7");
                    opcion = teclado.nextInt();
                    break;
                case 5:
                    gestorDB.mostrarPedidos();
                    System.out.println("¿Desea realizar otra operación?, si es así, seleccione el numero correspondiente. De lo contrario, pulse 7");
                    opcion = teclado.nextInt();
                    break;
                case 6:
                    gestorDB.mostrarProductosBaratos();
                    System.out.println("¿Desea realizar otra operación?, si es así, seleccione el numero correspondiente. De lo contrario, pulse 7");
                    opcion = teclado.nextInt();
                    break;
                case 7:
                    break;

                default: System.out.println("Opción incorrecta, selecciona una opción valida");
            }
        }


        //gestorDB.tablaProductos();
        //gestorDB.productosFavoritos();

    }
}

