package ProyectoFinalProgramacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionUsuarios {
    private List<Usuario> usuariosLista;

    public GestionUsuarios() {
        usuariosLista = new ArrayList<>();
    }

    public void insertarUsuario(Usuario usuario) {
        usuariosLista.add(usuario);
    }

    public void borrarUsuario(String uuid) {
        Iterator<Usuario> iterator = usuariosLista.iterator();
        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUuid().equals(uuid)) {
                iterator.remove();
                break;
            }
        }
    }

    public void leerUsuario() {
        for (Usuario u : usuariosLista) {
            System.out.println(u.listarUsuario());
        }
    }


    public void checkUser(String nombre ) {


        for (Usuario a:usuariosLista) {

            if (a.nombre.equalsIgnoreCase(nombre)) {
                System.out.println(a.listarUsuario());
            }else {
                System.out.println("no existe");

            }
        }
    }

    /*
    Una vez creado un objeto String, su contenido ya no puede variar.
    Ahora veremos las características de la clase StringBuilder para
    crear y manipular información de cadenas dinámicas. Cada objeto
    StringBuilder es capaz de almacenar diferentes caracteres especificados
    por su capacidad. Si se excede la capacidad de un objeto StringBuilder,
    esta se expande de forma automática para dar cabida a los caracteres
    adicionales.
     */
}
