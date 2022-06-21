package com.example.appcollector.ui.Modelos;

public class DenunciaCiudadano {

    private String nCodigoDenC;
    private String cModoDenC;
    private String cFechaDenC;
    private String nCodigoCalle;
    private String cUbicacionDenC;
    private String cDescripcionDenC;
    private String lEstadoDenCo;
    private String nCodigoCiud;

    public DenunciaCiudadano() {
    }

    public DenunciaCiudadano(String nCodigoDenC, String cModoDenC, String cFechaDenC, String nCodigoCalle, String cUbicacionDenC, String cDescripcionDenC, String lEstadoDenCo, String nCodigoCiud) {
        this.nCodigoDenC = nCodigoDenC;
        this.cModoDenC = cModoDenC;
        this.cFechaDenC = cFechaDenC;
        this.nCodigoCalle = nCodigoCalle;
        this.cUbicacionDenC = cUbicacionDenC;
        this.cDescripcionDenC = cDescripcionDenC;
        this.lEstadoDenCo = lEstadoDenCo;
        this.nCodigoCiud = nCodigoCiud;
    }

    public String getnCodigoDenC() {
        return nCodigoDenC;
    }

    public void setnCodigoDenC(String nCodigoDenC) {
        this.nCodigoDenC = nCodigoDenC;
    }

    public String getcModoDenC() {
        return cModoDenC;
    }

    public void setcModoDenC(String cModoDenC) {
        this.cModoDenC = cModoDenC;
    }

    public String getcFechaDenC() {
        return cFechaDenC;
    }

    public void setcFechaDenC(String cFechaDenC) {
        this.cFechaDenC = cFechaDenC;
    }

    public String getnCodigoCalle() {
        return nCodigoCalle;
    }

    public void setnCodigoCalle(String nCodigoCalle) {
        this.nCodigoCalle = nCodigoCalle;
    }

    public String getcUbicacionDenC() {
        return cUbicacionDenC;
    }

    public void setcUbicacionDenC(String cUbicacionDenC) {
        this.cUbicacionDenC = cUbicacionDenC;
    }

    public String getcDescripcionDenC() {
        return cDescripcionDenC;
    }

    public void setcDescripcionDenC(String cDescripcionDenC) {
        this.cDescripcionDenC = cDescripcionDenC;
    }

    public String getlEstadoDenCo() {
        return lEstadoDenCo;
    }

    public void setlEstadoDenCo(String lEstadoDenCo) {
        this.lEstadoDenCo = lEstadoDenCo;
    }

    public String getnCodigoCiud() {
        return nCodigoCiud;
    }

    public void setnCodigoCiud(String nCodigoCiud) {
        this.nCodigoCiud = nCodigoCiud;
    }

    @Override
    public String toString() {
        return "DenunciaCiudadano{" +
                "nCodigoDenC='" + nCodigoDenC + '\'' +
                ", cModoDenC='" + cModoDenC + '\'' +
                ", cFechaDenC='" + cFechaDenC + '\'' +
                ", nCodigoCalle='" + nCodigoCalle + '\'' +
                ", cUbicacionDenC='" + cUbicacionDenC + '\'' +
                ", cDescripcionDenC='" + cDescripcionDenC + '\'' +
                ", lEstadoDenCo='" + lEstadoDenCo + '\'' +
                ", nCodigoCiud='" + nCodigoCiud + '\'' +
                '}';
    }
}
