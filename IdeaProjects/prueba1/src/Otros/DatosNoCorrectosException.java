package Otros;

/**
 * Excepción que se lanza cuando se intentan asignar datos no válidos a un empleado
 */
public class DatosNoCorrectosException extends Exception {
    /**
     * Crea la excepción con un mensaje que describe el error
     * @param message mensaje descriptivo del motivo por el que los datos no son correctos
     */
    public DatosNoCorrectosException(String message) {
        super(message);
    }
}
