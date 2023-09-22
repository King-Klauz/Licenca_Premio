package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Servidor {
    private String matricula;
    private String nome;

    private LocalDate dataIngresso;

    private ArrayList<Concessao> concessoes = new ArrayList<>();
    private ArrayList<Afastamentos> afastamentos = new ArrayList<>();

    public Servidor(String matricula, String nome, LocalDate dataIngresso){
        this.matricula = matricula;
        this.nome = nome;
        this.dataIngresso = dataIngresso;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Concessao> getConcessoes() {
        return concessoes;
    }

    public void setConcessoes(ArrayList<Concessao> concessoes) {
        this.concessoes = concessoes;
    }

    public ArrayList<Afastamentos> getAfastamentos() {
        return afastamentos;
    }

    public void setAfastamentos(ArrayList<Afastamentos> afastamentos) {
        this.afastamentos = afastamentos;
    }
}
