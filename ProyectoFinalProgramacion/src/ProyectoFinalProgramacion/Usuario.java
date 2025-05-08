package ProyectoFinalProgramacion;


import java.util.UUID;

public class Usuario extends Persona {
    private String uuid;
    private String hashedContrasena;

    public Usuario(String nombre, String hashedContrasena) {
        super(nombre);
        this.uuid = UUID.randomUUID().toString();
        this.hashedContrasena = hashedContrasena;
    }

    public String getUuid() {
        return uuid;
    }

    public String getHashedContrasena() {
        return hashedContrasena;
    }

    public void setHashedContrasena(String hashedContrasena) {
        this.hashedContrasena = hashedContrasena;
    }

    public String listarUsuario() {
        return listarPersona() + ", UUID: " + uuid + ", Contraseña (hashed): " + hashedContrasena;
    }
}
