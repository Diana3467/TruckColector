package com.example.appcollectorcond.ui.Modelos;

public class AsigRutaCond {
    private String cInfoRuta;
    private String cDescriRuta;
    private String cDias;
    private String dFechaInicio;
    private String dFechaFin;
    private String cHoraInicioHor;
    private String cHoraFinHor;

    public AsigRutaCond(String cInfoRuta, String cDescriRuta, String cDias, String dFechaInicio, String dFechaFin, String cHoraInicioHor, String cHoraFinHor) {
        this.cInfoRuta = cInfoRuta;
        this.cDescriRuta = cDescriRuta;
        this.cDias = cDias;
        this.dFechaInicio = dFechaInicio;
        this.dFechaFin = dFechaFin;
        this.cHoraInicioHor = cHoraInicioHor;
        this.cHoraFinHor = cHoraFinHor;
    }

    public String getcInfoRuta() {
        return cInfoRuta;
    }

    public void setcInfoRuta(String cInfoRuta) {
        this.cInfoRuta = cInfoRuta;
    }

    public String getcDescriRuta() {
        return cDescriRuta;
    }

    public void setcDescriRuta(String cDescriRuta) {
        this.cDescriRuta = cDescriRuta;
    }

    public String getdFechaInicio() {
        return dFechaInicio;
    }

    public void setdFechaInicio(String dFechaInicio) {
        this.dFechaInicio = dFechaInicio;
    }

    public String getdFechaFin() {
        return dFechaFin;
    }

    public void setdFechaFin(String dFechaFin) {
        this.dFechaFin = dFechaFin;
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

    public String getcDias() {
        return cDias;
    }

    public void setcDias(String cDias) {
        this.cDias = cDias;
    }

    @Override
    public String toString() {
        return "AsigRutaCond{" +
                "cInfoRuta='" + cInfoRuta + '\'' +
                ", cDescriRuta='" + cDescriRuta + '\'' +
                ", cDias='" + cDias + '\'' +
                ", dFechaInicio='" + dFechaInicio + '\'' +
                ", dFechaFin='" + dFechaFin + '\'' +
                ", cHoraInicioHor='" + cHoraInicioHor + '\'' +
                ", cHoraFinHor='" + cHoraFinHor + '\'' +
                '}';
    }
}
