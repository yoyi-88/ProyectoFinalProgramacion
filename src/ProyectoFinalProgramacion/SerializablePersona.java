package ProyectoFinalProgramacion;

import java.io.Serializable;

public class SerializablePersona implements Serializable {
    private static final long serialVersionUID = 1L;
    String nombre;

    public SerializablePersona(String nombre) {
        this.nombre = nombre;
    }
}
