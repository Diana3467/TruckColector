package com.example.appcollector.ui.Modelos;

public class ReclamoCiudadano {

    private String nCodigoRecC;
    private String cFechaRecC;
    private String cDescripcionRecC;
    private String lEstadoRecC;
    private String nCodigoCiud;
    private String cNombreCompleto;

    public ReclamoCiudadano() {
    }

    public ReclamoCiudadano(String nCodigoRecC, String cFechaRecC, String cDescripcionRecC, String lEstadoRecC, String nCodigoCiud, String cNombreCompleto) {
        this.nCodigoRecC = nCodigoRecC;
        this.cFechaRecC = cFechaRecC;
        this.cDescripcionRecC = cDescripcionRecC;
        this.lEstadoRecC = lEstadoRecC;
        this.nCodigoCiud = nCodigoCiud;
        this.cNombreCompleto = cNombreCompleto;
    }

    public String getnCodigoRecC() {
        return nCodigoRecC;
    }

    public void setnCodigoRecC(String nCodigoRecC) {
        this.nCodigoRecC = nCodigoRecC;
    }

    public String getcFechaRecC() {
        return cFechaRecC;
    }

    public void setcFechaRecC(String cFechaRecC) {
        this.cFechaRecC = cFechaRecC;
    }

    public String getcDescripcionRecC() {
        return cDescripcionRecC;
    }

    public void setcDescripcionRecC(String cDescripcionRecC) {
        this.cDescripcionRecC = cDescripcionRecC;
    }

    public String getlEstadoRecC() {
        return lEstadoRecC;
    }

    public void setlEstadoRecC(String lEstadoRecC) {
        this.lEstadoRecC = lEstadoRecC;
    }

    public String getnCodigoCiud() {
        return nCodigoCiud;
    }

    public void setnCodigoCiud(String nCodigoCiud) {
        this.nCodigoCiud = nCodigoCiud;
    }

    public String getcNombreCompleto() {
        return cNombreCompleto;
    }

    public void setcNombreCompleto(String cNombreCompleto) {
        this.cNombreCompleto = cNombreCompleto;
    }

    @Override
    public String toString() {
        return "ReclamoCiudadano{" +
                "nCodigoRecC='" + nCodigoRecC + '\'' +
                ", cFechaRecC='" + cFechaRecC + '\'' +
                ", cDescripcionRecC='" + cDescripcionRecC + '\'' +
                ", lEstadoRecC='" + lEstadoRecC + '\'' +
                ", nCodigoCiud='" + nCodigoCiud + '\'' +
                ", cNombreCompleto='" + cNombreCompleto + '\'' +
                '}';
    }
}
