package com.aprendec.model;

public class Nomina {

    private String dni;
    private int nominas;

    public Nomina(String dni, int nominas) {
        super();
        this.dni = dni;
        this.nominas = nominas;
    }

    public Nomina() {
        // TODO Auto-generated constructor stub
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getNominas() {
        return nominas;
    }

    public void setNominas(int nominas) {
        this.nominas = nominas;
    }

    @Override
    public String toString() {
        return "Empleado [dni=" + dni + ", nominas=" + nominas + "]";
    }

}