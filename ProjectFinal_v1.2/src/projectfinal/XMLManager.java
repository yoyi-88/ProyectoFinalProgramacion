/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;

/**
 *
 * @author 1DAW2425-09
 */

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class XMLManager {

    // Guarda los usuarios en un archivo XML
    public void guardarUsuariosEnXML(List<Usuario> usuarios, String archivoXML) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element root = doc.createElement("usuarios");
            doc.appendChild(root);

            for (Usuario u : usuarios) {
                Element usuario = doc.createElement("usuario");

                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(u.getNombre()));
                usuario.appendChild(nombre);

                Element uuid = doc.createElement("uuid");
                uuid.appendChild(doc.createTextNode(u.getUuid()));
                usuario.appendChild(uuid);

                Element contrasena = doc.createElement("hashedContrasena");
                contrasena.appendChild(doc.createTextNode(u.getHashedContrasena()));
                usuario.appendChild(contrasena);

                root.appendChild(usuario);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(new File(archivoXML)));

            System.out.println("Usuarios guardados en " + archivoXML);

        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println("Error al guardar XML: " + e.getMessage());
        }
    }

    // Carga los usuarios desde un archivo XML
    public List<Usuario> cargarUsuariosDesdeXML(String archivoXML) {
        List<Usuario> usuarios = new ArrayList<>();

        try {
            File file = new File(archivoXML);
            if (!file.exists()) {
                System.out.println("Archivo XML no encontrado.");
                return usuarios;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("usuario");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodo = nodeList.item(i);

                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;

                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    String uuid = elemento.getElementsByTagName("uuid").item(0).getTextContent();
                    String contrasena = elemento.getElementsByTagName("hashedContrasena").item(0).getTextContent();

                    Usuario u = new Usuario(nombre, contrasena);
                    // Sobrescribir UUID si lo necesitas conservar
                    // Reflejar el UUID correcto usando reflexión o constructor personalizado
                    usuarios.add(u);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar XML: " + e.getMessage());
        }

        return usuarios;
    }
}
