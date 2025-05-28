/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;

/**
 *
 * @author 1DAW2425-09
 */
import java.util.List;

public class MainXML {

    public static void main(String[] args) throws Exception {
        GestionUsuarios gestion = new GestionUsuarios();
        XMLManager xmlManager = new XMLManager();

        // Insertar usuarios manualmente
        gestion.insertarUsuario(new Usuario("Ana", "abc123hashed"));
        gestion.insertarUsuario(new Usuario("Luis", "pass456hashed"));

        // Guardar a XML
        String path = "C:\\Users\\1DAW2425-09\\Downloads\\usuarios.xml";
        xmlManager.guardarUsuariosEnXML(gestion.usuariosLista, path);
        //xmlManager.guardarUsuariosEnXML(gestion.usuariosLista, "usuarios.xml");

        // Cargar desde XML
        List<Usuario> cargados = xmlManager.
                cargarUsuariosDesdeXML("C:\\Users\\1DAW2425-02\\IdeaProjects\\ProjectFinal_v1.2\\src\\projectfinal\\usuarios.xml");
        //List<Usuario> cargados = xmlManager.cargarUsuariosDesdeXML("usuarios.xml");

        // Mostrar usuarios cargados
        System.out.println("Usuarios cargados desde XML:");
        for (Usuario u : cargados) {
            System.out.println(u.listarUsuario());
        }
    }
}
