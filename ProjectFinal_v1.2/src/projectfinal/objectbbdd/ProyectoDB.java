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
import java.util.List;

public class ProyectoDB {
    private EntityManagerFactory emf;
    private EntityManager em;
    
    public ProyectoDB() {
        // Crear la conexión con la base de datos
        emf = Persistence.createEntityManagerFactory("objectdb:$objectdb/db/proyecto.odb");
        em = emf.createEntityManager();
    }
    
    // Método para cerrar la conexión
    public void close() {
        em.close();
        emf.close();
    }
    
    // Operaciones CRUD para Usuario
    
    /**
     * Crea un nuevo usuario en la base de datos
     * @param nombre Nombre del usuario
     * @param contrasenha Contraseña del usuario
     * @return El usuario creado
     */
    public Usuario crearUsuario(String nombre, String contrasenha) {
        Usuario usuario = new Usuario(nombre, contrasenha);
        
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        
        return usuario;
    }
    
    /**
     * Obtiene un usuario por su ID
     * @param id ID del usuario a buscar
     * @return El usuario encontrado o null si no existe
     */
    public Usuario obtenerUsuario(Long id) {
        return em.find(Usuario.class, id);
    }
    
    /**
     * Obtiene todos los usuarios de la base de datos
     * @return Lista de usuarios
     */
    public List<Usuario> obtenerTodosUsuarios() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza un usuario existente
     * @param usuario El usuario con los datos actualizados
     */
    public void actualizarUsuario(Usuario usuario) {
        em.getTransaction().begin();
        em.merge(usuario);
        em.getTransaction().commit();
    }
    
    /**
     * Elimina un usuario de la base de datos
     * @param id ID del usuario a eliminar
     */
    public void eliminarUsuario(Long id) {
        Usuario usuario = em.find(Usuario.class, id);
        if (usuario != null) {
            em.getTransaction().begin();
            em.remove(usuario);
            em.getTransaction().commit();
        }
    }
    
    // Operaciones CRUD para Tarea
    
    /**
     * Crea una nueva tarea en la base de datos
     * @param descripcion Descripción de la tarea
     * @param nombreCorto Nombre corto de la tarea
     * @param estado Estado de la tarea
     * @param usuario Usuario asignado a la tarea
     * @return La tarea creada
     */
    public Tarea crearTarea(String descripcion, String nombreCorto, Tarea.Estado estado, Usuario usuario) {
        Tarea tarea = new Tarea(descripcion, nombreCorto, estado);
        tarea.setUsuario(usuario);
        
        em.getTransaction().begin();
        em.persist(tarea);
        usuario.addTarea(tarea);
        em.getTransaction().commit();
        
        return tarea;
    }
    
    /**
     * Obtiene una tarea por su ID
     * @param id ID de la tarea a buscar
     * @return La tarea encontrada o null si no existe
     */
    public Tarea obtenerTarea(Long id) {
        return em.find(Tarea.class, id);
    }
    
    /**
     * Obtiene todas las tareas de la base de datos
     * @return Lista de tareas
     */
    public List<Tarea> obtenerTodasTareas() {
        TypedQuery<Tarea> query = em.createQuery("SELECT t FROM Tarea t", Tarea.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza una tarea existente
     * @param tarea La tarea con los datos actualizados
     */
    public void actualizarTarea(Tarea tarea) {
        em.getTransaction().begin();
        em.merge(tarea);
        em.getTransaction().commit();
    }
    
    /**
     * Elimina una tarea de la base de datos
     * @param id ID de la tarea a eliminar
     */
    public void eliminarTarea(Long id) {
        Tarea tarea = em.find(Tarea.class, id);
        if (tarea != null) {
            em.getTransaction().begin();
            em.remove(tarea);
            em.getTransaction().commit();
        }
    }
    
    // Consultas JPQL personalizadas
    
    /**
     * Obtiene todas las tareas de un usuario específico
     * @param usuarioId ID del usuario
     * @return Lista de tareas del usuario
     */
    public List<Tarea> obtenerTareasPorUsuario(Long usuarioId) {
        TypedQuery<Tarea> query = em.createQuery(
            "SELECT t FROM Tarea t WHERE t.usuario.id = :usuarioId", Tarea.class);
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }
    
    /**
     * Obtiene todas las tareas con un estado específico
     * @param estado Estado de las tareas a buscar
     * @return Lista de tareas con el estado especificado
     */
    public List<Tarea> obtenerTareasPorEstado(Tarea.Estado estado) {
        TypedQuery<Tarea> query = em.createQuery(
            "SELECT t FROM Tarea t WHERE t.estado = :estado", Tarea.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
    
    /**
     * Actualiza el estado de una tarea
     * @param tareaId ID de la tarea a actualizar
     * @param nuevoEstado Nuevo estado para la tarea
     * @return Número de tareas actualizadas
     */
    public int actualizarEstadoTarea(Long tareaId, Tarea.Estado nuevoEstado) {
        em.getTransaction().begin();
        Query query = em.createQuery(
            "UPDATE Tarea t SET t.estado = :estado WHERE t.id = :id");
        query.setParameter("estado", nuevoEstado);
        query.setParameter("id", tareaId);
        int updated = query.executeUpdate();
        em.getTransaction().commit();
        return updated;
    }
}
