/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal.objectbbdd;

/**
 *
 * @author usuario
 */
public class ProyectoTest {
    public static void main(String[] args) {
        // Crear instancia de la base de datos
        ProyectoDB proyectoDB = new ProyectoDB();
        
        try {
            // Crear usuarios
            Usuario ana = proyectoDB.crearUsuario("Ana", "clave123");
            Usuario carlos = proyectoDB.crearUsuario("Carlos", "segura456");
            Usuario beatriz = proyectoDB.crearUsuario("Beatriz", "password789");
            
            System.out.println("Usuarios creados:");
            proyectoDB.obtenerTodosUsuarios().forEach(System.out::println);
            
            // Crear tareas para Ana
            proyectoDB.crearTarea("Diseñar nueva campaña de marketing", "Marketing", 
                                 Tarea.Estado.POR_HACER, ana);
            proyectoDB.crearTarea("Coordinar evento corporativo", "Evento", 
                                 Tarea.Estado.POR_HACER, ana);
            proyectoDB.crearTarea("Actualizar documentación interna", "Docs", 
                                 Tarea.Estado.POR_HACER, ana);
            
            // Crear tareas para Carlos
            proyectoDB.crearTarea("Preparar informe mensual de ventas", "Ventas", 
                                 Tarea.Estado.EN_PROCESO, carlos);
            proyectoDB.crearTarea("Desarrollar nuevo módulo del sistema", "Módulo", 
                                 Tarea.Estado.EN_PROCESO, carlos);
            proyectoDB.crearTarea("Configurar entorno de desarrollo", "Setup Dev", 
                                 Tarea.Estado.EN_PROCESO, carlos);
            
            // Crear tareas para Beatriz
            proyectoDB.crearTarea("Optimizar base de datos", "DB Tuning", 
                                 Tarea.Estado.TERMINADO, beatriz);
            proyectoDB.crearTarea("Realizar pruebas de calidad", "QA", 
                                 Tarea.Estado.TERMINADO, beatriz);
            proyectoDB.crearTarea("Entrenar a nuevos empleados", "Capacitación", 
                                 Tarea.Estado.TERMINADO, beatriz);
            
            System.out.println("\nTodas las tareas:");
            proyectoDB.obtenerTodasTareas().forEach(System.out::println);
            
            // Consultas específicas
            System.out.println("\nTareas de Ana:");
            proyectoDB.obtenerTareasPorUsuario(ana.getId()).forEach(System.out::println);
            
            System.out.println("\nTareas en proceso:");
            proyectoDB.obtenerTareasPorEstado(Tarea.Estado.EN_PROCESO).forEach(System.out::println);
            
            // Actualizar una tarea
            Tarea primeraTareaAna = proyectoDB.obtenerTareasPorUsuario(ana.getId()).get(0);
            System.out.println("\nActualizando estado de tarea: " + primeraTareaAna);
            proyectoDB.actualizarEstadoTarea(primeraTareaAna.getId(), Tarea.Estado.EN_PROCESO);
            System.out.println("Tarea actualizada: " + proyectoDB.obtenerTarea(primeraTareaAna.getId()));
            
            // Eliminar una tarea
            Tarea primeraTareaBeatriz = proyectoDB.obtenerTareasPorUsuario(beatriz.getId()).get(0);
            System.out.println("\nEliminando tarea: " + primeraTareaBeatriz);
            proyectoDB.eliminarTarea(primeraTareaBeatriz.getId());
            System.out.println("Tareas restantes de Beatriz:");
            proyectoDB.obtenerTareasPorUsuario(beatriz.getId()).forEach(System.out::println);
            
        } finally {
            // Cerrar la conexión con la base de datos
            proyectoDB.close();
        }
    }
}
