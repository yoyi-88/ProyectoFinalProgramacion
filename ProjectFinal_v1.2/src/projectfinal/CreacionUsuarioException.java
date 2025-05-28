/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectfinal;

/**
 *
 * @author 1DAW2425-09
 */
import java.util.NoSuchElementException;

/**
 * Excepción personalizada II
 */

public class CreacionUsuarioException extends IllegalArgumentException {
    public CreacionUsuarioException(String mensaje) {
        super(mensaje);
    }
}
