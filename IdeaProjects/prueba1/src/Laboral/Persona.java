package Laboral;
/**
 *
 * Representa a una persona con nombre, DNI y sexo
 */
public class Persona {
    public String nombre, dni;
    public char sexo;

    /**
     * Crea una persona indicando todos sus datos
     * @param nombre
     * @param dni
     * @param sexo
     */
    public Persona(String nombre, String dni, char sexo) {
        this.nombre = nombre;
        this.dni = dni;
        this.sexo = sexo;
    }

    /**
     * Crea una persona indicando todos sus datos
     * @param name
     * @param sexo
     */
    public Persona(String name, char sexo) {
        this.nombre = name;
        this.sexo = sexo;
    }

    /**
     * Establece o modifica el DNI de la persona
     * @param dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Devuelve texto de la persona con su nombre y DNI
     * @return
     */
    public String Imprime() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                '}';
    }
}
