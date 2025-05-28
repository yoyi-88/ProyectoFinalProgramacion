/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal.objectbbdd;

/**
 *
 * @author usuario
 */
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Tarea implements Serializable {
    public enum Estado {
        POR_HACER("Por hacer"),
        EN_PROCESO("En proceso"),
        TERMINADO("Terminado");
        
        private final String descripcion;
        
        Estado(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tareaSeq")
    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "tareaSeq")
    private Long id;
    
    private String descripcion;
    private String nombreCorto;
    
    @Enumerated(EnumType.STRING)
    private Estado estado;
    
    @ManyToOne
    private Usuario usuario;
    
    // Constructor sin parámetros requerido por JPA
    public Tarea() {
    }
    
    // Constructor con parámetros
    public Tarea(String descripcion, String nombreCorto, Estado estado) {
        this.descripcion = descripcion;
        this.nombreCorto = nombreCorto;
        this.estado = estado;
    }
    
    // Getters y setters
    public Long getId() {
        return id;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getNombreCorto() {
        return nombreCorto;
    }
    
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }
    
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public String toString() {
        return "Tarea [id=" + id + ", nombreCorto=" + nombreCorto + ", estado=" + estado.getDescripcion() + 
               ", usuario=" + (usuario != null ? usuario.getNombre() : "Ninguno") + "]";
    }
}
