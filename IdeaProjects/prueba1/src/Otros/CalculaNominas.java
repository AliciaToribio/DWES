package Otros;

import prueba2.MetodosAltaEmpleado;
import prueba2.ConexionBD;
import prueba2.FicheroEmpleado;
import prueba2.MetodosBD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalculaNominas {
    /**
     * Crea a los empleados James y Ada, se imprimen sus datos y sueldos, incrementa un año a Ada,
     * sube a James a la categoría 9 e imprime de nuevo los resultados
     * @param args argumentos de línea de comandos (no se utilizan)
     */
    public static void main(String[] args) {
        
//        try {
//            Empleado james = new Empleado("James Cosling", "32000032G", 'M', 4, 7);
//            Empleado ada = new Empleado("Ada Lovelace", "32000031R", 'F');
//
//            escribe(james, ada);
//
//            ada.incrAnyo();
//            james.setCategoria(9);
//
//            escribe(james, ada);
//
//        } catch (DatosNoCorrectosException e) {
//            System.out.println(e);
//        }

        MetodosBD mbd = new MetodosBD();
        ArrayList<Empleado> listaEmpleado = FicheroEmpleado.leerEmpleados("IdeaProjects/prueba1/Recursos/empleados.txt");
        Empleado james = listaEmpleado.get(0);
        Empleado ada = listaEmpleado.get(1);
        escribe(james, ada);
        FicheroEmpleado.escribirSueldo("IdeaProjects/prueba1/Recursos/salario.txt",listaEmpleado);
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {

            System.out.println("---MENÚ---");
            System.out.println("0. Salir");
            System.out.println("1. Mostrar informacion de la bd");
            System.out.println("2. Mostrar salario por DNI de empleado");
            System.out.println("3. Modificacion datos empleados(submenu)");
            System.out.println("4. Recalcular y actualizar el sueldo de un empleado");
            System.out.println("5. Recalcular y actualizar los sueldos de todos los empleados");
            System.out.println("6. Crear copia de seguridad");
            System.out.println("--------------");
            System.out.println("Seleccione una opcion");
            opcion = sc.nextInt();
            String dni;
            switch (opcion) {
                case 0:
                    System.out.println("Saliendo");
                    break;
                case 1:
                    mbd.mostrarEmpleados();
                    break;
                case 2:
                    System.out.println("Escriba el DNI del empleado a mostrar");
                    sc.nextLine();
                    dni = sc.nextLine();
                    mbd.mostrarSalarioEmpleado(dni);
                    break;
                case 3:
                    System.out.println("Escriba el DNI del empleado a actualizar");
                    sc.nextLine();
                    dni = sc.nextLine();
                    mbd.modificardatos(dni);
                    break;
                case 4:
                    System.out.println("Escriba el DNI del empleado a mostrar");
                    sc.nextLine();
                    dni = sc.nextLine();
                    mbd.actualizarSueldoEmpleado(dni);
                    break;
                case 5:
                    mbd.actualizarSueldoEmpleados();
                    break;
                case 6:
                    mbd.backup("IdeaProjects/prueba1/Recursos/backup.txt");
                    break  ;
                default:
                    System.out.println("La opcion debe ser entre 0 y 6");
                    break;
            }

        } while (opcion != 0);
        sc.close();
    }

    /**
     * Imprime por consola los datos y el sueldo de los dos empleados
     * @param james
     * @param ada
     */
    private static void escribe(Empleado james, Empleado ada) {
        System.out.println(james.Imprime() + " cuyo sueldo es " + Nomina.sueldo(james) + "€");

        System.out.println(ada.Imprime() + "cuyo sueldo es " + Nomina.sueldo(ada) + "€");
    }
}