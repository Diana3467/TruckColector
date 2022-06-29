package com.example.appcollector.ui.Modelos;

public class Horario {
    private String cDias;
    private String cHoraInicioHor;
    private String cHoraFinHor;

    public Horario() {
    }

    public Horario(String cDias, String cHoraInicioHor, String cHoraFinHor) {
        this.cDias = cDias;
        this.cHoraInicioHor = cHoraInicioHor;
        this.cHoraFinHor = cHoraFinHor;
    }

    public String getcDiasHor() {
        return cDias;
    }

    public void setcDiasHor(String cDias) {
        this.cDias = cDias;
    }

    public String getcHoraInicioHor() {
        return cHoraInicioHor;
    }

    public void setcHoraInicioHor(String cHoraInicioHor) {
        this.cHoraInicioHor = cHoraInicioHor;
    }

    public String getcHoraFinHor() {
        return cHoraFinHor;
    }

    public void setcHoraFinHor(String cHoraFinHor) {
        this.cHoraFinHor = cHoraFinHor;
    }

    @Override
    public String toString() {
        return "Horario{" +
                ", cDias='" + cDias + '\'' +
                ", cHoraInicioHor='" + cHoraInicioHor + '\'' +
                ", cHoraFinHor='" + cHoraFinHor + '\'' +
                '}';
    }
}
