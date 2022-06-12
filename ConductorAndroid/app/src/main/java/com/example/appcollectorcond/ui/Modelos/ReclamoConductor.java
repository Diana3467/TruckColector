package com.example.appcollectorcond.ui.Modelos;

public class ReclamoConductor {
    private String nCodigoRecCo;
    private String cFechaRecCo;
    private String cDescripcionRecCo;
    private String lEstadoRecCo;
    private String nCodigoCond;
    private String cNombreCompleto;
    private String cPlacaCar;

    public ReclamoConductor(String nCodigoRecCo, String cFechaRecCo, String cDescripcionRecCo, String lEstadoRecCo, String nCodigoCond, String cNombreCompleto, String cPlacaCar) {
        this.nCodigoRecCo = nCodigoRecCo;
        this.cFechaRecCo = cFechaRecCo;
        this.cDescripcionRecCo = cDescripcionRecCo;
        this.lEstadoRecCo = lEstadoRecCo;
        this.nCodigoCond = nCodigoCond;
        this.cNombreCompleto = cNombreCompleto;
        this.cPlacaCar = cPlacaCar;
    }

    public String getnCodigoRecCo() {
        return nCodigoRecCo;
    }

    public void setnCodigoRecCo(String nCodigoRecCo) {
        this.nCodigoRecCo = nCodigoRecCo;
    }

    public String getcFechaRecCo() {
        return cFechaRecCo;
    }

    public void setcFechaRecCo(String cFechaRecCo) {
        this.cFechaRecCo = cFechaRecCo;
    }

    public String getcDescripcionRecCo() {
        return cDescripcionRecCo;
    }

    public void setcDescripcionRecCo(String cDescripcionRecCo) {
        this.cDescripcionRecCo = cDescripcionRecCo;
    }

    public String getlEstadoRecCo() {
        return lEstadoRecCo;
    }

    public void setlEstadoRecCo(String lEstadoRecCo) {
        this.lEstadoRecCo = lEstadoRecCo;
    }

    public String getnCodigoCond() {
        return nCodigoCond;
    }

    public void setnCodigoCond(String nCodigoCond) {
        this.nCodigoCond = nCodigoCond;
    }

    public String getcNombreCompleto() {
        return cNombreCompleto;
    }

    public void setcNombreCompleto(String cNombreCompleto) {
        this.cNombreCompleto = cNombreCompleto;
    }

    public String getcPlacaCar() {
        return cPlacaCar;
    }

    public void setcPlacaCar(String cPlacaCar) {
        this.cPlacaCar = cPlacaCar;
    }

    @Override
    public String toString() {
        return "ReclamoConductor{" +
                "nCodigoRecCo='" + nCodigoRecCo + '\'' +
                ", cFechaRecCo='" + cFechaRecCo + '\'' +
                ", cDescripcionRecCo='" + cDescripcionRecCo + '\'' +
                ", lEstadoRecCo='" + lEstadoRecCo + '\'' +
                ", nCodigoCond='" + nCodigoCond + '\'' +
                ", cNombreCompleto='" + cNombreCompleto + '\'' +
                ", cPlacaCar='" + cPlacaCar + '\'' +
                '}';
    }
}
