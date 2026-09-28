package Otros;

/**
 * Clase para calcular la nómina de los empleados
 */
public class Nomina {
    private static final int SUELDO_BASE[] = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};
    /**
     * Calcula el sueldo de un empleado
     * El sueldo es el sueldo base de su categoría más 5000 por cada año de antigüedad
     * @param e
     * @return
     */
    public static double sueldo(Empleado e){
        double sueldo =SUELDO_BASE[e.getCategoria()-1] + 5000 * e.anyos;
        return  sueldo;
    }

}
