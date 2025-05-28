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
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarioSeq")
    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "usuarioSeq")
    private Long id;
    
    private String nombre;
    private String contrasenha;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarea> tareas = new ArrayList<>();
    
    // Constructor sin parámetros requerido por JPA
    public Usuario() {
    }
    
    // Constructor con parámetros
    public Usuario(String nombre, String contrasenha) {
        this.nombre = nombre;
        this.contrasenha = contrasenha;
    }
    
    // Getters y setters
    public Long getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getContrasenha() {
        return contrasenha;
    }
    
    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
    
    public List<Tarea> getTareas() {
        return tareas;
    }
    
    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
    
    // Método para añadir tarea
    public void addTarea(Tarea tarea) {
        tareas.add(tarea);
        tarea.setUsuario(this);
    }
    
    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nombre=" + nombre + "]";
    }
}
