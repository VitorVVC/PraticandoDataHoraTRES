package entities;

public class Pessoa {

    private String name;
    private int dia;
    private int mes;
    private int ano;

    public Pessoa(String name, int dia, int mes, int ano) {
        this.name = name;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
