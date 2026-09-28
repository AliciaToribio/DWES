package nomina2;

import Laboral.Empleado;
import Laboral.Nomina;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class FicheroEmpleado {
    public static ArrayList<Empleado> leerEmpleados(String ruta) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                Empleado emp;

                String nombre = partes[0];
                String dni = partes[1];
                char sexo = partes[2].charAt(0);
                int categoria = Integer.parseInt(partes[3]);
                int anyos = Integer.parseInt(partes[4]);
                emp = new Empleado(nombre, dni, sexo, categoria, anyos);
                empleados.add(emp);
            }
        } catch (Exception e) {
            System.out.println("Error al leer empleado"+ e.getMessage());
        }
        return empleados;
    }

    public static void escribirSueldo(String rutaFichero, ArrayList<Empleado> empleados) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaFichero))) {
            for (Empleado emp : empleados) {
                String linea = emp.dni + ";" + Nomina.sueldo(emp);
                bw.write(linea);
                bw.newLine();
            }
        } catch (Exception e) {
            System.out.println("Error al escribir el sueldo empleado"+ e.getMessage());

        }
    }
}
