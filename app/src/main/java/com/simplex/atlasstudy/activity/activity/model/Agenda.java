package com.simplex.atlasstudy.activity.activity.model;

import java.util.ArrayList;

/**
 * Created by Men on 08/11/2017.
 */

public class Agenda {
    private String nomeAgenda;
    private String conteudoAgenda;
    private String semanasAgenda;
    private String importanciaCompromisso;


    public Agenda() {
    }

    public String getNomeAgenda() {
        return nomeAgenda;
    }

    public void setNomeAgenda(String nomeAgenda) {
        this.nomeAgenda = nomeAgenda;
    }

    public String getConteudoAgenda() {
        return conteudoAgenda;
    }

    public void setConteudoAgenda(String conteudoAgenda) {
        this.conteudoAgenda = conteudoAgenda;
    }

    public String getSemanasAgenda() {
        return semanasAgenda;
    }

    public void setSemanasAgenda(String semanasAgenda) {
        this.semanasAgenda = semanasAgenda;
    }

    public String getImportanciaCompromisso() {
        return importanciaCompromisso;
    }

    public void setImportanciaCompromisso(String importanciaCompromisso) {
        this.importanciaCompromisso = importanciaCompromisso;
    }
}
