/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebainterfaz;

/**
 *
 * @author KMY
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class MisTareasAWT extends Frame {

    // Conexión DB
    private static final String URL = "jdbc:mariadb://localhost:3306/proyecto";
    private static final String USER = "root";     // Cambia si es necesario
    private static final String PASS = "";     // Cambia si es necesario

    // Componentes pantalla login
    private TextField txtUsuario;
    private TextField txtContrasenha;
    private Label lblMensaje;

    // Pantalla tareas
    private Frame tareasFrame;
    private TextArea tareasArea;
    private Label lblUsuario;

    private String usuarioNombre;

    public MisTareasAWT() {
        setTitle("MisTareas - Login");
        setSize(350, 250);
        setLayout(null);
        setBackground(new Color(230, 230, 230)); // gris claro

        // Logo (Label grande centrado)
        Label logo = new Label("MisTareas", Label.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        logo.setBounds(50, 30, 250, 40);
        logo.setForeground(Color.BLACK);
        add(logo);

        // Usuario
        Label lblUsuarioLogin = new Label("Usuario:");
        lblUsuarioLogin.setBounds(50, 80, 80, 20);
        lblUsuarioLogin.setForeground(Color.BLACK);
        add(lblUsuarioLogin);

        txtUsuario = new TextField();
        txtUsuario.setBounds(140, 80, 150, 20);
        add(txtUsuario);

        // Contraseña
        Label lblContrasenha = new Label("Contraseña:");
        lblContrasenha.setBounds(50, 110, 80, 20);
        lblContrasenha.setForeground(Color.BLACK);
        add(lblContrasenha);

        txtContrasenha = new TextField();
        txtContrasenha.setEchoChar('*');
        txtContrasenha.setBounds(140, 110, 150, 20);
        add(txtContrasenha);

        // Mensaje error
        lblMensaje = new Label("");
        lblMensaje.setBounds(50, 140, 240, 20);
        lblMensaje.setForeground(Color.RED);
        add(lblMensaje);

        // Botones Entrar y Salir
        Button btnEntrar = new Button("Entrar");
        btnEntrar.setBounds(80, 170, 80, 30);
        add(btnEntrar);

        Button btnSalir = new Button("Salir");
        btnSalir.setBounds(180, 170, 80, 30);
        add(btnSalir);

        // Acciones botones
        btnEntrar.addActionListener(e -> {
            String user = txtUsuario.getText().trim();
            String pass = txtContrasenha.getText().trim();
            if (validarUsuario(user, pass)) {
                usuarioNombre = user;
                abrirPantallaTareas();
                this.setVisible(false);
            } else {
                lblMensaje.setText("Usuario o contraseña incorrectos.");
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        // Cerrar ventana
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private boolean validarUsuario(String usuario, String contrasenha) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT * FROM usuarios WHERE Nombre = ? AND Contrasenha = ?")) {

            ps.setString(1, usuario);
            ps.setString(2, contrasenha);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // si hay fila, usuario existe y contraseña coincide
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void abrirPantallaTareas() {
        tareasFrame = new Frame("MisTareas - " + usuarioNombre);
        tareasFrame.setSize(500, 400);
        tareasFrame.setLayout(null);
        tareasFrame.setBackground(new Color(230, 230, 230)); // gris claro

        lblUsuario = new Label("Usuario: " + usuarioNombre);
        lblUsuario.setBounds(20, 40, 460, 30);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsuario.setForeground(Color.BLACK);
        tareasFrame.add(lblUsuario);

        tareasArea = new TextArea();
        tareasArea.setBounds(20, 80, 450, 250);
        tareasArea.setEditable(false);
        tareasFrame.add(tareasArea);

        Button btnMostrar = new Button("Mostrar tareas");
        btnMostrar.setBounds(180, 340, 120, 30);
        tareasFrame.add(btnMostrar);

        btnMostrar.addActionListener(e -> mostrarTareas());

        tareasFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                tareasFrame.dispose();
                System.exit(0);
            }
        });

        tareasFrame.setVisible(true);
    }

    private void mostrarTareas() {
        tareasArea.setText("");
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT Descripcion, NombreCorto, Estado FROM tareas t " +
                 "JOIN usuarios u ON t.Usuario_id = u.id WHERE u.Nombre = ?")) {

            ps.setString(1, usuarioNombre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String desc = rs.getString("Descripcion");
                    String corto = rs.getString("NombreCorto");
                    String estado = rs.getString("Estado");

                    tareasArea.append("[" + estado + "] " + corto + ": " + desc + "\n");
                }
            }

        } catch (SQLException ex) {
            tareasArea.setText("Error al cargar tareas.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Cargar driver MySQL
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver MariaDB no encontrado.");
            return;
        }

        new MisTareasAWT();
    }
}

