package Otros;

import Laboral.Persona;
/**
 * Representa a un empleado de la empresa que tiene una categoría profesional y unos años de antigüedad
 */
public class Empleado extends Persona {
    private int categoria;
    public int anyos;

    /**
     * Crea un empleado con los datos personales indicados, categoría 1y 0 años de antigüedad
     * @param nombre
     * @param dni
     * @param sexo
     */
    public Empleado(String nombre, String dni, char sexo) {
        super(nombre, dni, sexo);
        this.categoria = 1;
        this.anyos = 0;
    }

    /**
     * Crea un empleado con los datos personales indicados, categoría 1y 0 años de antigüedad
     * @param nombre
     * @param dni
     * @param sexo
     * @param categoria
     * @param anyos
     * @throws DatosNoCorrectosException
     */
    public Empleado(String nombre, String dni, char sexo, int categoria, int anyos) throws DatosNoCorrectosException {
        super(nombre, dni, sexo);
        if(!(categoria >= 1 || categoria <= 10)){
            throw new DatosNoCorrectosException("Datos no correctos");
        }
        this.categoria = categoria;
         if (!(anyos >=0)) {
             throw new DatosNoCorrectosException("Datos no correctos");
         }
        this.anyos = anyos;
    }

    /**
     * Devuelve la categoría profesional del empleado
     * @return
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     *  Modifica la categoría profesional del empleado
     * @param categoria
     * @throws DatosNoCorrectosException
     */
    public void setCategoria(int categoria) throws DatosNoCorrectosException {
        if(!(categoria >= 1 || categoria <= 10)){
            throw new DatosNoCorrectosException("Datos no correctos");
        }
        this.categoria = categoria;
    }

    /**
     * Incrementa en una unidad los años de antigüedad
     */
    public void incrAnyo() {
        this.anyos++;
    }

    /**
     * Devuelve un texto del empleado con su categoría, años de antigüedad, nombre, DNI y sexo
     * @return
     */
    @Override
    public String Imprime() {
        return
                "categoria=" + categoria +
                ", anyos=" + anyos +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
