package entities;

import java.time.LocalDate;
import java.time.YearMonth;

public class Pessoa {
    // Criando a classe pessoa
    // Variaveis de nome , dia , mes e ano
    private String name;
    private int dia;
    private int mes;
    private int ano;

    // Construtor
    public Pessoa(String name, int dia, int mes, int ano) {
        this.name = name;
        this.dia = validarDia(dia,mes);
        this.mes = validarMes(mes);
        this.ano = validarInt(ano);
    }

    // Getters & Setters
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
        this.dia = validarDia(dia,this.mes);
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = validarMes(mes);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = validarInt(ano);
    }


        // Métodos copiados da classe principal para servir de auxilio para o construtor e set
        // Porém de void foram para int para poderem retornar algo para atribuir um valor

    // Usado como método para validar o mes para limitar uma integracão com meses não existentes
    public int validarMes(int mes) {
        if (mes < 0 || mes > 12) {
            throw new IllegalArgumentException("Não aceitamos numeros abaixo de zero ou acima de 12.");
        }
        return mes;
    }
    // Método que vai pedir um dia como entrada e mes, referente ao mes ele irá conferir se o dia pode exisitir em tal mes
    public int validarDia(int dia, int mes) {
        // Valida o mes
        validarMes(mes);
        // Atribui a um int o mes do ano do LocalDate HOJE, get no ano e mes para puxar o tamanho do mes
        int maxDias = YearMonth.of(LocalDate.now().getYear(), mes).lengthOfMonth();
        // Se o dia for menor que 1 ou maior que a quantidade de dias no mes ele retorna um erro
        if (dia < 1 || dia > maxDias) {
            throw new IllegalArgumentException("So aceitamos valores o primeiro e ultimo dia do mes. ");
        }
        return dia;
    }
    public int validarInt(int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Não aceitamos numeros negativos.");
        }
        return valor;
    }
}
