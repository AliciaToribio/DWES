package nomina2;

import Laboral.Empleado;
import Laboral.Nomina;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class MetodosAltaEmpleado {
    public void altaEmpleado(Empleado e) {
        String insertEmpleados = "INSERT INTO empleados (dni,nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
        String insertNominas = "INSERT INTO nominas (sueldo,empleado) VALUES (?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertEmpleados);) {

            ps.setString(1,e.dni);
            ps.setString(2, e.nombre);
            ps.setString(3, String.valueOf(e.sexo));
            ps.setInt(4, e.getCategoria());
            ps.setInt(5, e.anyos);
            ps.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Error al añadir empleado" + ex.getMessage());
        }
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertNominas);) {

            ps.setDouble(1, Nomina.sueldo(e));
            ps.setString(2, e.dni);
            ps.executeUpdate();
        } catch (Exception ex2) {
            System.out.println("Error al añadir en nominas" + ex2.getMessage());

        }
    }

    public void altaEmpleado(String ruta) {
        String insertEmpleados = "INSERT INTO empleados (dni,nombre, sexo, categoria, anyos) VALUES (?, ?, ?, ?, ?)";
        String insertNominas = "INSERT INTO nominas (sueldo,empleado) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertEmpleados);
             BufferedReader br = new BufferedReader(new FileReader(ruta))) {

            String linea;

            while ((linea = br.readLine()) != null) {
                String[] cadena = linea.split(";");

                String nombre = cadena[0];
                String dni = cadena[1];
                char sexo = cadena[2].charAt(0);
                Integer categoria = Integer.parseInt(cadena[3]);
                Integer anyos = Integer.parseInt(cadena[4]);

                Empleado emp = new Empleado(nombre, dni, sexo, categoria, anyos);

                ps.setString(1, emp.dni);
                ps.setString(2, emp.nombre);
                ps.setString(3, String.valueOf(emp.sexo));
                ps.setInt(4, emp.getCategoria());
                ps.setInt(5, emp.anyos);
                ps.executeUpdate();
                try (PreparedStatement pst = conn.prepareStatement(insertNominas)) {
                    pst.setDouble(1, Nomina.sueldo(emp));
                    pst.setString(2, emp.dni);
                    pst.executeUpdate();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al dar de alta a los empleados " + ex.getMessage());
        }
    }
}

