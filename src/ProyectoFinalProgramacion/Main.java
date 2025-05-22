
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ProyectoFinalProgramacion;

import ProyectoFinalProgramacion.utils.GestionFicheros;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main  {

    // El método main ahora propaga la excepción personalizada
    public static void main(String[] args) throws UsuarioNoEncontradoException {
        GestionUsuarios gestion = new GestionUsuarios();
        GestionFicheros gestion1=new GestionFicheros();
        String ruta="C:\\Users\\1DAW2425-02\\IdeaProjects\\ProyectoFinalProgramacion\\src\\Ficheros\\Logs.txt";
        Scanner scanner = new Scanner(System.in);
        Usuario u1 = new Usuario("Alice", "abc123hashed");
        Usuario u2 = new Usuario("Bob", "xyz456hashed");
        String mensaje="Usuarios registrados: "+u1.nombre+" / ID: "+u1.getUuid();

        // Insertar usuarios iniciales
        //gestion.insertarUsuario(u1);
        //gestion.insertarUsuario(u2);

        // Menú de opciones
        int opcion = 0;
        while (opcion != 5) {
            System.out.println("\n***** Menú de Gestión de Usuarios *****");
            System.out.println("1. Ver todos los usuarios");
            System.out.println("2. Insertar nuevo usuario");
            System.out.println("3. Borrar usuario");
            System.out.println("4. Buscar usuario");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        // Ver todos los usuarios
                        System.out.println("\nUsuarios registrados:");
                        gestion.leerUsuario();
                        gestion1.anadirFichero(mensaje,ruta);

                        break;

                    case 2:
                        // Insertar nuevo usuario
                        System.out.println("\nRegistro de nuevo usuario:");

                        try {
                            System.out.print("Ingrese nombre: ");
                            String nombre = scanner.nextLine();
                            assert nombre.length() < 20 : "Debe contener menos de 20 caracteres";
                            System.out.println("No puede contener mas de 20 caracteres "+ nombre);
                            if (nombre.isEmpty()) {
                                throw new CreacionUsuarioException("El nombre no puede estar vacío.");
                            }

                            System.out.print("Ingrese contraseña (mínimo 6 caracteres): ");
                            String contrasena = scanner.nextLine();
                            if (contrasena.length() < 6) {
                                throw new CreacionUsuarioException("La contraseña debe tener al menos 6 caracteres.");
                            }

                            // En un caso real, aquí se aplicaría hash a la contraseña
                            Usuario nuevoUsuario = new Usuario(nombre, contrasena);
                            gestion.insertarUsuario(nuevoUsuario);
                            System.out.println("Usuario registrado con éxito.");

                        } catch (IllegalArgumentException | AssertionError e) {
                            System.out.println("Error: " + e.getMessage());
                        } catch (Exception e) { // ¿?
                            throw new RuntimeException(e);
                        }
                        break;

                    case 3:
                        // Borrar usuario
                        System.out.print("\nIngrese el UUID del usuario a borrar: ");
                        String uuidABorrar = scanner.nextLine();
                        try {
                            gestion.borrarUsuario(uuidABorrar);
                            System.out.println("Usuario borrado con éxito.");
                        } catch (Exception e) {
                            System.out.println("Error al borrar el usuario: " + e.getMessage());
                        }
                        break;

                    case 4:
                        // Buscar usuario
                        System.out.print("\nIngrese el nombre del usuario a buscar: ");
                        String nombreBusqueda = scanner.nextLine();
                        try {
                            gestion.checkUser(nombreBusqueda);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;

                    default:
                        try {
                            System.out.println("Opción no válida. Por favor, elija una opción entre 1 y 5.");
                        }catch (NumberFormatException e){
                            System.err.println("vuelva a intentarlo");
                        }

                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número.");
            }
        }

        scanner.close();




    }

}
