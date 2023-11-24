package com.example.demo;

public class Tarefa {
    public int id;
    public String descricao;
    public boolean feita;

    public Tarefa(){
        
    }

    public Tarefa(String descricao, boolean feita){
        this.descricao = descricao;
        this.feita = feita;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setFeita(boolean feita) {
        this.feita = feita;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Descrição" + descricao + ", Feita: " + feita;
    }
} 
    
