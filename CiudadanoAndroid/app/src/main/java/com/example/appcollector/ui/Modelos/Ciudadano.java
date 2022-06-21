package com.example.appcollector.ui.Modelos;

public class Ciudadano {
    private String nCodigoCiud;
    private String cNombreCiud;
    private String cApePatCiud;
    private String cApeMatCiud;
    private String cDNICiud;
    private String cCelCiud;
    private String cNumDirecCiud;
    private String lEstadoCiud;
    private String cPassCiud;
    private String nCodigoCalle;
    private String cNombreCalle;
    private String cDescZona;
    private String cInfoCiud;

    public Ciudadano() {
    }

    public Ciudadano(String nCodigoCiud, String cNombreCiud, String cApePatCiud, String cApeMatCiud, String cDNICiud, String cCelCiud, String cNumDirecCiud, String lEstadoCiud, String cPassCiud, String nCodigoCalle, String cNombreCalle, String cDescZona, String cInfoCiud) {
        this.nCodigoCiud = nCodigoCiud;
        this.cNombreCiud = cNombreCiud;
        this.cApePatCiud = cApePatCiud;
        this.cApeMatCiud = cApeMatCiud;
        this.cDNICiud = cDNICiud;
        this.cCelCiud = cCelCiud;
        this.cNumDirecCiud = cNumDirecCiud;
        this.lEstadoCiud = lEstadoCiud;
        this.cPassCiud = cPassCiud;
        this.nCodigoCalle = nCodigoCalle;
        this.cNombreCalle = cNombreCalle;
        this.cDescZona = cDescZona;
        this.cInfoCiud = cInfoCiud;
    }

    public String getnCodigoCiud() {
        return nCodigoCiud;
    }

    public void setnCodigoCiud(String nCodigoCiud) {
        this.nCodigoCiud = nCodigoCiud;
    }

    public String getcNombreCiud() {
        return cNombreCiud;
    }

    public void setcNombreCiud(String cNombreCiud) {
        this.cNombreCiud = cNombreCiud;
    }

    public String getcApePatCiud() {
        return cApePatCiud;
    }

    public void setcApePatCiud(String cApePatCiud) {
        this.cApePatCiud = cApePatCiud;
    }

    public String getcApeMatCiud() {
        return cApeMatCiud;
    }

    public void setcApeMatCiud(String cApeMatCiud) {
        this.cApeMatCiud = cApeMatCiud;
    }

    public String getcDNICiud() {
        return cDNICiud;
    }

    public void setcDNICiud(String cDNICiud) {
        this.cDNICiud = cDNICiud;
    }

    public String getcCelCiud() {
        return cCelCiud;
    }

    public void setcCelCiud(String cCelCiud) {
        this.cCelCiud = cCelCiud;
    }

    public String getcNumDirecCiud() {
        return cNumDirecCiud;
    }

    public void setcNumDirecCiud(String cNumDirecCiud) {
        this.cNumDirecCiud = cNumDirecCiud;
    }

    public String getlEstadoCiud() {
        return lEstadoCiud;
    }

    public void setlEstadoCiud(String lEstadoCiud) {
        this.lEstadoCiud = lEstadoCiud;
    }

    public String getcPassCiud() {
        return cPassCiud;
    }

    public void setcPassCiud(String cPassCiud) {
        this.cPassCiud = cPassCiud;
    }

    public String getnCodigoCalle() {
        return nCodigoCalle;
    }

    public void setnCodigoCalle(String nCodigoCalle) {
        this.nCodigoCalle = nCodigoCalle;
    }

    public String getcNombreCalle() { return cNombreCalle; }

    public void setcNombreCalle(String cNombreCalle) { this.cNombreCalle = cNombreCalle; }

    public String getcDescZona() { return cDescZona; }

    public void setcDescZona(String cDescZona) { this.cDescZona = cDescZona; }

    public String getcInfoCiud() {
        return cInfoCiud;
    }

    public void setcInfoCiud(String cInfoCiud) {
        this.cInfoCiud = cInfoCiud;
    }

    @Override
    public String toString() {
        return "Ciudadano{" +
                "nCodigoCiud='" + nCodigoCiud + '\'' +
                ", cNombreCiud='" + cNombreCiud + '\'' +
                ", cApePatCiud='" + cApePatCiud + '\'' +
                ", cApeMatCiud='" + cApeMatCiud + '\'' +
                ", cDNICiud='" + cDNICiud + '\'' +
                ", cCelCiud='" + cCelCiud + '\'' +
                ", cNumDirecCiud='" + cNumDirecCiud + '\'' +
                ", lEstadoCiud='" + lEstadoCiud + '\'' +
                ", cPassCiud='" + cPassCiud + '\'' +
                ", nCodigoCalle='" + nCodigoCalle + '\'' +
                ", cNombreCalle='" + cNombreCalle + '\'' +
                ", cDescZona='" + cDescZona + '\'' +
                ", cInfoCiud='" + cInfoCiud + '\'' +
                '}';
    }
}
