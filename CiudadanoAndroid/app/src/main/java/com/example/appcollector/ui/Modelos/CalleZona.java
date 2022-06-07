package com.example.appcollector.ui.Modelos;

public class CalleZona {
    private String nCodigoCalle;
    private String cNombreCalle;
    private String cDescZona;
    private String nCodigoZona;

    public CalleZona(String nCodigoCalle, String cNombreCalle, String cDescZona, String nCodigoZona) {
        this.nCodigoCalle = nCodigoCalle;
        this.cNombreCalle = cNombreCalle;
        this.cDescZona = cDescZona;
        this.nCodigoZona = nCodigoZona;
    }

    public String getnCodigoCalle() {
        return nCodigoCalle;
    }

    public void setnCodigoCalle(String nCodigoCalle) {
        this.nCodigoCalle = nCodigoCalle;
    }

    public String getcNombreCalle() {
        return cNombreCalle;
    }

    public void setcNombreCalle(String cNombreCalle) {
        this.cNombreCalle = cNombreCalle;
    }

    public String getcDescZona() {
        return cDescZona;
    }

    public void setcDescZona(String cDescZona) {
        this.cDescZona = cDescZona;
    }

    public String getnCodigoZona() {
        return nCodigoZona;
    }

    public void setnCodigoZona(String nCodigoZona) {
        this.nCodigoZona = nCodigoZona;
    }

    @Override
    public String toString() {
        return cNombreCalle;
    }
}
