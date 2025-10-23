package com.aprendec.model;

public class CalcNomina {
    private static final int[] SUELDO_BASE = new int[] {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

    public static double sueldo (Empleado empleado){
        double sueldo = SUELDO_BASE[empleado.getCategoria()] + 5000 * empleado.getAnyos();
        return sueldo;
    }
}
