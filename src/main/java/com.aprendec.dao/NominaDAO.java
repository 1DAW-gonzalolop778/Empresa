package com.aprendec.dao;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.Empleado;
import com.aprendec.model.Nomina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NominaDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // guardar producto
    public boolean guardar(Nomina nomina) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "INSERT INTO nominas (dni, nomina) VALUES(?,?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, null);
            statement.setInt(2, nomina.getNominas());

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
    public boolean editar(Nomina nomina) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        try {
            connection.setAutoCommit(false);
            sql = "UPDATE nominas SET nomina=? WHERE dni=?";
            statement = connection.prepareStatement(sql);

            statement.setInt(1, nomina.getNominas());
            statement.setString(2, nomina.getDni());

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
    public boolean eliminar(int dni) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        try {
            connection.setAutoCommit(false);
            sql = "DELETE FROM empleados WHERE dni=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dni);

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
    public List<Nomina> obtenerNominas() throws SQLException {
        ResultSet resultSet = null;
        List<Nomina> listaNominas = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM nominas";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Nomina p = new Nomina();
                p.setDni(resultSet.getString(1));
                p.setNominas(resultSet.getInt(2));
                listaNominas.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaNominas;
    }

    // obtener producto
    public Nomina obtenerNominas(int dni) throws SQLException {
        ResultSet resultSet = null;
        Nomina p = new Nomina();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM empleados WHERE dni =?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, dni);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                p.setDni(resultSet.getString(1));
                p.setNominas(resultSet.getInt(2));
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