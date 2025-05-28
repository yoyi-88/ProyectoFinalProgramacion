
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;

/**
 * Excepción personalizada para cuando un usuario no se encuentra.
 */
import java.util.NoSuchElementException;

/**
 * Excepción personalizada para cuando un usuario no se encuentra.
 */

public class UsuarioNoEncontradoException extends NoSuchElementException {
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}