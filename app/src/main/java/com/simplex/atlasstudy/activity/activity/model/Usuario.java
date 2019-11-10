package com.simplex.atlasstudy.activity.activity.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.simplex.atlasstudy.activity.activity.config.ConfigFirebase;

/**
 * Created by Men on 10/10/2017.
 */

public class Usuario {

    private String id;
    private String name;
    private String email;
    private String senha;

    public Usuario(){

    }

    public void salvar(){
        DatabaseReference referenceFirebase = ConfigFirebase.getFirebase();
        referenceFirebase.child("usuarios").child(getId()).setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
