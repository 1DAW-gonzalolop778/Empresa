package com.aprendec.conexion;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class Conexion {
    private static BasicDataSource dataSource = null;

    //Esta clase implementa un patron Singleton sin que yo me hubiera dado cuenta
    //Singleton hace que solo exista una unica instancia de clase durante la ejecución del programa
    //Al usar el static en DataSource significa que solo habrá una copia compartida por toda la app

    private static DataSource getDataSource() {
        //Con este if se asegura que la primera vez se cree una instancia y las siguientes devuelva la misma instancia
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("usuario");
            //cambia la base de datos a gestiondeempleados para trabajar en clase
            //cambia la base de datos a gestion_nominas para tabajar en casa
            dataSource.setUrl("jdbc:mysql://localhost:3306/gestiondeempleados");
            dataSource.setInitialSize(20);
            dataSource.setMaxIdle(15);
            dataSource.setMaxTotal(20);
            dataSource.setMaxWaitMillis(5000);
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }
}