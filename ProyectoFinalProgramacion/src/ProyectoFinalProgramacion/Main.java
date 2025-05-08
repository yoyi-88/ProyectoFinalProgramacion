package ProyectoFinalProgramacion;

public class Main {
    public static void main(String[] args) {
        GestionUsuarios gestion = new GestionUsuarios();

        Usuario u1 = new Usuario("Alice", "abc123hashed");
        Usuario u2 = new Usuario("Bob", "xyz456hashed");

        // Insertar usuarios
        gestion.insertarUsuario(u1);
        gestion.insertarUsuario(u2);

        System.out.println("Usuarios después de insertar:");
        gestion.leerUsuario();

        // Borrar uno
        gestion.borrarUsuario(u1.getUuid());

        System.out.println("Usuarios después de borrar a Alice:");
        gestion.leerUsuario();
        gestion.checkUser(u1.nombre);
    }
}

