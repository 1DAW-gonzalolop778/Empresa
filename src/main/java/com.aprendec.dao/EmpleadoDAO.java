package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.Empleado;

public class EmpleadoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // guardar producto
    public boolean guardar(Empleado empleado) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "INSERT INTO empleados (dni, nombre, sexo, categoria, anyos) VALUES(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, null);
            statement.setString(2, empleado.getNombre());
            statement.setString(3, empleado.getSexo());
            statement.setInt(4, empleado.getCategoria());
            statement.setInt(5, empleado.getAnyos());

            estadoOperacion = statement.executeUpdate() > 0;

            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // editar producto
    public boolean editar(Empleado empleado) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        try {
            connection.setAutoCommit(false);
            sql = "UPDATE empleados SET nombre=?, sexo=?, categoria=?, anyos=? WHERE dni=?";
            statement = connection.prepareStatement(sql);

            statement.setString(1, empleado.getNombre());
            statement.setString(2, empleado.getSexo());
            statement.setInt(3, empleado.getCategoria());
            statement.setInt(4, empleado.getAnyos());
            statement.setString(5, empleado.getDni());

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // eliminar producto
    public boolean eliminar(int idProducto) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        try {
            connection.setAutoCommit(false);
            sql = "DELETE FROM empleados WHERE dni=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProducto);

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    // obtener lista de productos
    public List<Empleado> obtenerProductos() throws SQLException {
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
    public Empleado obtenerProducto(int idProducto) throws SQLException {
        ResultSet resultSet = null;
        Empleado p = new Empleado();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados WHERE dni =?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProducto);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                p.setDni(resultSet.getString(1));
                p.setNombre(resultSet.getString(2));
                p.setSexo(resultSet.getString(3));
                p.setCategoria(resultSet.getInt(4));
                p.setAnyos(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return p;
    }

    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }

}