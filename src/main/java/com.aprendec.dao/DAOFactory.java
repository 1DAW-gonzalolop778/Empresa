package com.aprendec.dao;

/**
 * Factory Method
 * Con esto el controlador pide un DAO a esta clase sin saber internamente si usa EmpleadoDAO o NominaDAO
 * Si se cambia el origen de los datos solo se deberia cambiar esta clase y no el resto del codigo
 */
public class DAOFactory {

    public static AbstractDAO<?> getDAO(String tipo) {
        switch (tipo.toLowerCase()) {
            case "empleado":
                return new EmpleadoDAO();
            case "nomina":
                return new NominaDAO();
            default:
                throw new IllegalArgumentException("Tipo de DAO no soportado: " + tipo);
        }
    }
}
