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

public class NominaDAO extends AbstractDAO<Nomina>{
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    @Override
    protected boolean ejecutarGuardar(Nomina nomina) throws SQLException {
        String sql = "INSERT INTO nominas (dni, nomina) VALUES(?,?)";
        statement = connection.prepareStatement(sql);
        statement.setString(1, nomina.getDni());
        statement.setInt(2, nomina.getNominas());
        return statement.executeUpdate() > 0;
    }

    @Override
    protected boolean ejecutarEditar(Nomina nomina) throws SQLException {
        String sql = "UPDATE nominas SET nomina=? WHERE dni=?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, nomina.getNominas());
        statement.setString(2, nomina.getDni());
        return statement.executeUpdate() > 0;
    }

    @Override
    protected boolean ejecutarEliminar(Object dni) throws SQLException {
        String sql = "DELETE FROM nominas WHERE dni=?";
        statement = connection.prepareStatement(sql);
        statement.setString(1, dni.toString());
        return statement.executeUpdate() > 0;
    }

    // obtener lista de nominas
    public List<Nomina> obtenerNominas(String dni) throws SQLException {
        ResultSet resultSet = null;
        List<Nomina> listaNominas = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();
        resultSet = null;
        try {
            sql = "SELECT * FROM nominas WHERE dni= ?";
            System.out.println("Me meto aqui y el dni es " + dni);
            statement = connection.prepareStatement(sql);
            statement.setString(1, dni);
            //me peta la linea de justo abajo
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Nomina p = new Nomina();
                p.setDni(resultSet.getString(1));
                p.setNominas(resultSet.getInt(2));
                listaNominas.add(p);
                System.out.println(listaNominas);
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