CREATE DATABASE proyecto;
USE proyecto;
CREATE TABLE usuarios (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Nombre VARCHAR(20),
	Contrasenha VARCHAR(45)
);

CREATE TABLE tareas (
	id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	Descripcion VARCHAR(100),
	NombreCorto VARCHAR(20),
    Estado ENUM('Por hacer', 'En proceso', 'Terminado'),
    Usuario_id INT UNSIGNED,
    FOREIGN KEY (Usuario_id)REFERENCES Usuarios (id)
ON DELETE RESTRICT ON UPDATE CASCADE
);

INSERT INTO usuarios (Nombre, Contrasenha) VALUES
('Ana', 'clave123'),
('Carlos', 'segura456'),
('Beatriz', 'password789');

INSERT INTO tareas (Descripcion, NombreCorto, Estado, Usuario_id) VALUES
('Diseñar nueva campaña de marketing', 'Marketing', 'Por hacer', 1),
('Preparar informe mensual de ventas', 'Ventas', 'En proceso', 2),
('Optimizar base de datos', 'DB Tuning', 'Terminado', 3),
('Coordinar evento corporativo', 'Evento', 'Por hacer', 1),
('Desarrollar nuevo módulo del sistema', 'Módulo', 'En proceso', 2),
('Realizar pruebas de calidad', 'QA', 'Terminado', 3),
('Actualizar documentación interna', 'Docs', 'Por hacer', 1),
('Configurar entorno de desarrollo', 'Setup Dev', 'En proceso', 2),
('Entrenar a nuevos empleados', 'Capacitación', 'Terminado', 3);