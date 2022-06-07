package com.example.appcollectorcond.ui.Modelos;

public class Conductor {
    private String nCodigoCond;
    private String cNombreCond;
    private String cApePatCond;
    private String cApeMatCond;
    private String cDNICond;
    private String cEdadCond;
    private String cCelCond;
    private String cDireccCond;
    private String cCorEleCond;
    private String lEstadoCond;
    private String cPassCond;
    private String cInfoCond;

    public Conductor(String nCodigoCond, String cNombreCond, String cApePatCond, String cApeMatCond, String cDNICond, String cEdadCond, String cCelCond, String cDireccCond, String cCorEleCond, String lEstadoCond, String cPassCond, String cInfoCond) {
        this.nCodigoCond = nCodigoCond;
        this.cNombreCond = cNombreCond;
        this.cApePatCond = cApePatCond;
        this.cApeMatCond = cApeMatCond;
        this.cDNICond = cDNICond;
        this.cEdadCond = cEdadCond;
        this.cCelCond = cCelCond;
        this.cDireccCond = cDireccCond;
        this.cCorEleCond = cCorEleCond;
        this.lEstadoCond = lEstadoCond;
        this.cPassCond = cPassCond;
        this.cInfoCond = cInfoCond;
    }

    public String getnCodigoCond() {
        return nCodigoCond;
    }

    public void setnCodigoCond(String nCodigoCond) {
        this.nCodigoCond = nCodigoCond;
    }

    public String getcNombreCond() {
        return cNombreCond;
    }

    public void setcNombreCond(String cNombreCond) {
        this.cNombreCond = cNombreCond;
    }

    public String getcApePatCond() {
        return cApePatCond;
    }

    public void setcApePatCond(String cApePatCond) {
        this.cApePatCond = cApePatCond;
    }

    public String getcApeMatCond() {
        return cApeMatCond;
    }

    public void setcApeMatCond(String cApeMatCond) {
        this.cApeMatCond = cApeMatCond;
    }

    public String getcDNICond() {
        return cDNICond;
    }

    public void setcDNICond(String cDNICond) {
        this.cDNICond = cDNICond;
    }

    public String getcEdadCond() {
        return cEdadCond;
    }

    public void setcEdadCond(String cEdadCond) {
        this.cEdadCond = cEdadCond;
    }

    public String getcCelCond() {
        return cCelCond;
    }

    public void setcCelCond(String cCelCond) {
        this.cCelCond = cCelCond;
    }

    public String getcDireccCond() {
        return cDireccCond;
    }

    public void setcDireccCond(String cDireccCond) {
        this.cDireccCond = cDireccCond;
    }

    public String getcCorEleCond() {
        return cCorEleCond;
    }

    public void setcCorEleCond(String cCorEleCond) {
        this.cCorEleCond = cCorEleCond;
    }

    public String getlEstadoCond() {
        return lEstadoCond;
    }

    public void setlEstadoCond(String lEstadoCond) {
        this.lEstadoCond = lEstadoCond;
    }

    public String getcPassCond() {
        return cPassCond;
    }

    public void setcPassCond(String cPassCond) {
        this.cPassCond = cPassCond;
    }

    public String getcInfoCond() {
        return cInfoCond;
    }

    public void setcInfoCond(String cInfoCond) {
        this.cInfoCond = cInfoCond;
    }

    @Override
    public String toString() {
        return "Conductor{" +
                "nCodigoCond='" + nCodigoCond + '\'' +
                ", cNombreCond='" + cNombreCond + '\'' +
                ", cApePatCond='" + cApePatCond + '\'' +
                ", cApeMatCond='" + cApeMatCond + '\'' +
                ", cDNICond='" + cDNICond + '\'' +
                ", cEdadCond='" + cEdadCond + '\'' +
                ", cCelCond='" + cCelCond + '\'' +
                ", cDireccCond='" + cDireccCond + '\'' +
                ", cCorEleCond='" + cCorEleCond + '\'' +
                ", lEstadoCond='" + lEstadoCond + '\'' +
                ", cPassCond='" + cPassCond + '\'' +
                ", cInfoCond='" + cInfoCond + '\'' +
                '}';
    }
}
