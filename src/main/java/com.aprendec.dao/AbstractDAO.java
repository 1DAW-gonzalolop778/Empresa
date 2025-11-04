package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aprendec.conexion.Conexion;


//he usado el patron de Template Method para tener una superclase con subclases que cambian
//se repetia en empleadoDAO y nominaDAO eliminar, guardar y editar por lo que los he puesto en esta superclase abstracta
public abstract class AbstractDAO<T> {

    protected Connection connection;
    protected PreparedStatement statement;

    public final boolean guardar(T obj) throws SQLException {
        boolean estado = false;
        try {
            iniciarTransaccion();
            estado = ejecutarGuardar(obj);
            commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return estado;
    }

    public final boolean editar(T obj) throws SQLException {
        boolean estado = false;
        try {
            iniciarTransaccion();
            estado = ejecutarEditar(obj);
            commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return estado;
    }

    public final boolean eliminar(Object id) throws SQLException {
        boolean estado = false;
        try {
            iniciarTransaccion();
            estado = ejecutarEliminar(id); // Implementado en subclase
            commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        } finally {
            cerrarRecursos();
        }
        return estado;
    }

    // Métodos que cada subclase debe implementar
    protected abstract boolean ejecutarGuardar(T obj) throws SQLException;
    protected abstract boolean ejecutarEditar(T obj) throws SQLException;
    protected abstract boolean ejecutarEliminar(Object id) throws SQLException;

    // Gestión de transacciones y recursos
    private void iniciarTransaccion() throws SQLException {
        connection = Conexion.getConnection();
        connection.setAutoCommit(false);
    }

    private void commit() throws SQLException {
        if (connection != null) connection.commit();
    }

    private void rollback() {
        try {
            if (connection != null) connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cerrarRecursos() {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
