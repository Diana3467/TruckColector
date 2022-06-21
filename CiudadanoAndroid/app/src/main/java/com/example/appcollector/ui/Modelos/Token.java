package com.example.appcollector.ui.Modelos;

public class Token {
    private String cMensajeAut;

    public Token(String cMensajeAut) {
        this.cMensajeAut = cMensajeAut;
    }

    @Override
    public String toString() {
        return cMensajeAut ;
    }

    public String getcMensajeAut() {
        return cMensajeAut;
    }

    public void setcMensajeAut(String cMensajeAut) {
        this.cMensajeAut = cMensajeAut;
    }
}
