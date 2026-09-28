package prueba2;

import Otros.DatosNoCorrectosException;
import Otros.Empleado;
import Otros.Nomina;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.Scanner;

public class MetodosBD {
    public void mostrarEmpleados() {
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT e.dni,e.nombre,e.sexo,e.categoria,e.anyos,n.sueldo FROM empleados e JOIN nominas n ON e.DNI=n.empleado");) {

            while (rs.next()) {
                System.out.println("Dni: " + rs.getString(1) + ", Nombre: " + rs.getString(2) + ", Sexo: " + rs.getString(3)
                        + " ,Categoria: " + rs.getInt(4) + " ,Años: " + rs.getInt(5) + " ,Sueldo: " + rs.getDouble(6));
            }

        } catch (Exception e) {
            System.out.println("Error al mostrar los empleados" + e.getMessage());
        }
    }

    public void mostrarSalarioEmpleado(String dni) {
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT sueldo,empleado FROM nominas where empleado = ?");) {

            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("sueldo: " + rs.getDouble(1) + " ,empleado: " + rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar el salario de los empleados" + e.getMessage());
        }
    }

    public void modificardatos(String dni) {
        int opcion;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("SUBMENÚ");
            System.out.println("0. Volver al inicio");
            System.out.println("1. Actualizar nombre");
            System.out.println("2. Actualizar dni");
            System.out.println("3. Actualizar sexo");
            System.out.println("4. Actualizar categoria");
            System.out.println("5. Actualizar años");
            System.out.println("------------------------------------------------------------------");
            System.out.println("Inserta un numero para elegir su opcion");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    System.out.println("Saliendo");
                    break;
                case 1:
                    System.out.println("A que nombre se va a actualizar");
                    String nombre = sc.nextLine();
                    actualizaNombre(dni, nombre);
                    break;
                case 2:
                    System.out.println("Dime el dni nuevo del empleado a actualizar");
                    String dniNuv = sc.nextLine();
                    actualizaDni(dni, dniNuv);
                    break;
                case 3:
                    System.out.println("Dime el sexo nuevo del empleado a actualizar");
                    String sexo = sc.nextLine();
                    actualizaSexo(dni, sexo);
                    break;
                case 4:
                    try {
                        System.out.println("Dime la categoria nueva del empleado a actualizar");
                        int categoria = sc.nextInt();
                        actualizaCategoria(dni, categoria);
                    } catch (DatosNoCorrectosException e) {
                        System.out.println("La categoria es entre 1 y 10");
                    }
                    break;
                case 5:
                    try {
                        System.out.println("Dime los años trabajados nuevos del empleado a actualizar");
                        int anyos = sc.nextInt();
                        actualizaAnyos(dni, anyos);
                    } catch (DatosNoCorrectosException e) {
                        System.out.println("Loa años deben ser positivos");
                    }
                    break;
                default:
                    System.out.println("La opcion debe estar entre 0 y 5");
                    break;
            }
        } while (opcion != 0);
    }

    public void actualizaNombre(String dni, String nombre) {
        String update = "UPDATE empleados SET nombre = ? WHERE dni = ?";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             PreparedStatement ps = conn.prepareStatement(update)) {

            ps.setString(1, nombre);
            ps.setString(2, dni);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al actualizar el nombre del empleado" + e.getMessage());
        }
    }

    public void actualizaDni(String dni, String dniNuv) {
        String update = "UPDATE empleados SET dni = ? WHERE dni = ?";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             PreparedStatement ps = conn.prepareStatement(update)) {

            ps.setString(1, dniNuv);
            ps.setString(2, dni);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al actualizar el dni del empleado" + e.getMessage());
        }
    }

    public void actualizaSexo(String dni, String sexo) {
        String update = "UPDATE empleados SET sexo = ? WHERE dni = ?";
        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             PreparedStatement ps = conn.prepareStatement(update)) {

            ps.setString(1, sexo);
            ps.setString(2, dni);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al actualizar el sexo del empleado" + e.getMessage());
        }
    }

    public void actualizaCategoria(String dni, int categoria) throws DatosNoCorrectosException {
        if (categoria >= 1 && categoria <= 10) {
            String update = "UPDATE empleados SET categoria = ? WHERE dni = ?";
            try (Connection conn = ConexionBD.getConnection();
                 Statement st = conn.createStatement();
                 PreparedStatement ps = conn.prepareStatement(update)) {

                ps.setInt(1, categoria);
                ps.setString(2, dni);

                ps.executeUpdate();
                actualizarSueldoEmpleado(dni);
            } catch (Exception e) {
                System.out.println("Error al actualizar la categoria del empleado" + e.getMessage());
            }
        } else {
            throw new DatosNoCorrectosException("Datos no correctos");
        }
    }

    public void actualizaAnyos(String dni, int anyos) throws DatosNoCorrectosException {
        if (anyos > 0) {
            String update = "UPDATE empleados SET anyos = ? WHERE dni = ?";
            try (Connection conn = ConexionBD.getConnection();
                 Statement st = conn.createStatement();
                 PreparedStatement ps = conn.prepareStatement(update)) {

                ps.setInt(1, anyos);
                ps.setString(2, dni);

                ps.executeUpdate();
                actualizarSueldoEmpleado(dni);
            } catch (Exception e) {
                System.out.println("Error al actualizar los anios del empleado" + e.getMessage());
            }
        } else {
            throw new DatosNoCorrectosException("Datos no correctos");
        }
    }

    public void actualizarSueldoEmpleado(String dni) {
        String select = "SELECT categoria, anyos FROM empleados WHERE dni = ?";
        String update = "UPDATE nominas SET sueldo = ? WHERE empleado = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement psSelect = conn.prepareStatement(select)) {

            psSelect.setString(1, dni);
            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                int categoria = rs.getInt("categoria");
                int anyos = rs.getInt("anyos");

                Empleado emp = new Empleado("", dni, ' ', categoria, anyos);

                try (PreparedStatement psUpdate = conn.prepareStatement(update)) {
                    psUpdate.setDouble(1, Nomina.sueldo(emp));
                    psUpdate.setString(2, dni);
                    psUpdate.executeUpdate();
                }

            } else {
                System.out.println("No existe ningún empleado con dni " + dni);
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar sueldos: " + e.getMessage());
        }
    }

    public void actualizarSueldoEmpleados() {
        String select = "SELECT dni, categoria, anyos FROM empleados";
        String update = "UPDATE nominas SET sueldo = ? WHERE empleado = ?";

        try (Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(select);
             PreparedStatement ps = conn.prepareStatement(update)) {

            while (rs.next()) {
                String dni = rs.getString(1);
                int categoria = rs.getInt(2);
                int anyos = rs.getInt(3);

                Empleado emp = new Empleado("", dni, ' ', categoria, anyos);

                ps.setDouble(1, Nomina.sueldo(emp));
                ps.setString(2, dni);
                ps.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("Error al actualizar sueldos: " + e.getMessage());
        }
    }

    public void backup(String ruta) {
        String select = "SELECT e.Nombre, e.DNI, e.sexo, e.categoria, e.anyos, n.sueldo FROM empleados e JOIN nominas n ON e.dni = n.empleado";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
             Connection conn = ConexionBD.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(select);) {
            while (rs.next()) {
                String nombre = rs.getString(1);
                String dni = rs.getString(2);
                String sexo = rs.getString(3);
                int categoria = rs.getInt(4);
                int anyos = rs.getInt(5);
                double sueldo = rs.getDouble(6);
                bw.write("Nombre: " + nombre + ", Dni: " + dni + ", Sexo: " + sexo
                        + ", Categoria: " + categoria + ", Años: " + anyos + ", Sueldo: " + sueldo);
                bw.newLine();
            }

        } catch (Exception e) {
            System.out.println("Error al hacer copia de seguridad: " + e.getMessage());
        }
    }
}
