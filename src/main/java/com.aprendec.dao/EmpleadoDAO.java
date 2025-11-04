package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.CalcNomina;
import com.aprendec.model.Empleado;

public class EmpleadoDAO extends AbstractDAO<Empleado>{
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    @Override
    protected boolean ejecutarGuardar(Empleado empleado) throws SQLException {
        String sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES(?,?,?,?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, empleado.getDni());
        statement.setString(2, empleado.getNombre());
        statement.setString(3, empleado.getSexo());
        statement.setInt(4, empleado.getCategoria());
        statement.setInt(5, empleado.getAnyos());
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean ejecutarEditar(Empleado empleado) throws SQLException {
        String sql = null;
        sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
        statement = connection.prepareStatement(sql);

        statement.setString(1, empleado.getNombre());
        statement.setString(2, empleado.getSexo());
        statement.setInt(3, empleado.getCategoria());
        statement.setInt(4, empleado.getAnyos());
        statement.setString(5, empleado.getDni());

        System.out.println("ESTOY EN EDITAR");
        estadoOperacion = statement.executeUpdate() > 0;

        //editar la nomina
        double nuevaNomina = CalcNomina.sueldo(empleado);

        //sueldo en casa, nomina en el instituto
        sql = "UPDATE nominas SET nomina=? WHERE dni=?";
        statement = connection.prepareStatement(sql);
        statement.setDouble(1, nuevaNomina);
        statement.setString(2, empleado.getDni());
        statement.executeUpdate();

        return estadoOperacion;
    }

    @Override
    protected boolean ejecutarEliminar(Object dni) throws SQLException {
        String sql = "DELETE FROM empleados WHERE dni=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, dni.toString());
        return statement.executeUpdate() > 0;
    }

    // obtener lista de productos
    public List<Empleado> obtenerEmpleados() throws SQLException {
        ResultSet resultSet = null;
        List<Empleado> listaEmpleados = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Empleado p = new Empleado();
                p.setDni(resultSet.getString(1));
                p.setNombre(resultSet.getString(2));
                p.setSexo(resultSet.getString(3));
                p.setCategoria(resultSet.getInt(4));
                p.setAnyos(resultSet.getInt(5));
                listaEmpleados.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEmpleados;
    }

    // obtener producto
    public Empleado obtenerEmpleado(String dni) throws SQLException {
        ResultSet resultSet = null;
        Empleado emp = new Empleado();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados WHERE dni =?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                emp.setDni(resultSet.getString(1));
                emp.setNombre(resultSet.getString(2));
                emp.setSexo(resultSet.getString(3));
                emp.setCategoria(resultSet.getInt(4));
                emp.setAnyos(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emp;
    }

    public List<Empleado> buscarEmpleado(String criterio, String valor) throws SQLException {
        ResultSet resultSet = null;
        List<Empleado> listaEmpleados = new ArrayList<>();

        String sql = null;
        connection = obtenerConexion();

        try {
            switch (criterio) {
                case "dni":
                    sql = "SELECT * FROM empleados WHERE dni = ?";
                    break;
                case "nombre":
                    sql = "SELECT * FROM empleados WHERE nombre LIKE ?";
                    valor = "%" + valor + "%";
                    //esta con % para que no tengas que escribir nombre y apellidos y solo baste con nombre o apellido
                    break;
                case "sexo":
                    sql = "SELECT * FROM empleados WHERE sexo = ?";
                    break;
                case "categoria":
                    sql = "SELECT * FROM empleados WHERE categoria = ?";
                    break;
                case "anyos":
                    sql = "SELECT * FROM empleados WHERE anyos = ?";
                    break;
            }

            PreparedStatement ps = connection.prepareStatement(sql);

            if (criterio.equals("categoria") || criterio.equals("anyos")) {
                ps.setInt(1, Integer.parseInt(valor));
            } else {
                ps.setString(1, valor);
            }

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Empleado e = new Empleado();
                e.setDni(resultSet.getString(1));
                e.setNombre(resultSet.getString(2));
                e.setSexo(resultSet.getString(3));
                e.setCategoria(resultSet.getInt(4));
                e.setAnyos(resultSet.getInt(5));
                listaEmpleados.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaEmpleados;
    }


    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

}