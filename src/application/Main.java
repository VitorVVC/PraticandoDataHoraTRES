package application;

import entities.Pessoa;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Interface teste do progama automatica: \\
        /*saudacao();
        printHora();
        LocalDate nascimento = LocalDate.of(2005, 4, 19);
        infoPessoa("Vitor", 19, 4, 2005);
        printPessoaIdade("Vitor", nascimento);
        Instant instant = Instant.now();
        horaEm("Mexico", "America/Mexico_City", instant);

        try {
            validarDia(19, 4);
            validarMes(4);
            validarInt(4);
            //validarDia(19,13);
            //validarMes(13);
            //validarInt(-9);
        } catch (IllegalArgumentException exception) {
            System.out.println("ERROR: " + exception.getMessage());
        }finally {
            System.out.println("Try Finalizado com sucesso.");
        }*/

        // Interface teste INTERATIVA \\
        printHora();
        String name = obterNome(sc);
        saudacaoInterativa(name);
        int quantidade = obterQuantidadePessoas(sc);

        ArrayList<Pessoa> vetorPessoas = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            System.out.printf("Dados da %da pessoa: %n", (i + 1));
            //String nome = obterNome(sc);
            Pessoa pessoa = (obterDadosPessoa(sc));
            vetorPessoas.add(pessoa);
            int dia = pessoa.getDia();
            int mes = pessoa.getMes() + 1;
            int ano = pessoa.getAno();
            try {
                validarDia(dia, mes);
                validarInt(ano);
                LocalDate infop = LocalDate.of(ano, mes, dia);
                infoPessoa(pessoa.getName(), dia, mes, ano);
                printPessoaIdade(pessoa.getName(), infop);
            } catch (IllegalArgumentException exception) {
                System.out.println("ERROR: " + exception.getMessage());
            }
        }

        System.out.println(name + " voce deseja verificar as horas em outro local do mundo? ");
        char yon = sc.next().charAt(0);
        while (yon == 's' || yon == 'S') {
            Instant instant = Instant.now();
            System.out.print("Escreva agora o LOCAL onde deseja conferir o horario: (Ex: Mexico) ");
            String local = sc.next();
            System.out.print("Escreva o timezone desta região: (Ex: America/Mexico_City) ");
            String timezone = sc.next();
            System.out.println("Processo realizado com sucesso !");
            horaEm(local, timezone, instant);

            System.out.print("Deseja realizar o processo novamente? S para sim e N para não: ");
            yon = sc.next().charAt(0);
        }
        System.out.println("Progama encerrado");
    }

    // Funcão que printa a hora em algum local com algum timeZone no momento de agora
    public static void horaEm(String local, String timezone) {
        Instant instant1 = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'& Date: 'dd/MM/yyyy '& Hour:' HH:mm:ss");
        ZonedDateTime zonedDateTime = instant1.atZone(ZoneId.of(timezone));
        System.out.printf("Location: %s %s%n", local, formatter.format(zonedDateTime));
    }
    // Processo para saber qual signo de uma pessoa
    private static void signo(int dia, int mes) {
        try {
            if ((dia >= 22 && mes == 12) || (dia <= 19 && mes == 1)) {
                System.out.println("Capricórnio");
            } else if ((dia >= 21 && mes == 1) || (dia <= 18 && mes == 2)) {
                System.out.println("Aquário");
            } else if ((dia >= 19 & mes == 2) || (dia <= 20 && mes == 3)) {
                System.out.println("Peixes");
            } else if ((dia >= 21 && mes == 3) || (dia <= 20 && mes == 4)) {
                System.out.println("Áries");
            } else if ((dia >= 21 && mes == 4) || (dia <= 20 && mes == 5)) {
                System.out.println("Touro");
            } else if ((dia >= 21 && mes == 5) || (dia <= 20 && mes == 6)) {
                System.out.println("Gemeos");
            } else if ((dia >= 21 && mes == 6) || (dia <= 22 && mes == 7)) {
                System.out.println("Cancer");
            } else if ((dia >= 23 && mes == 7) || (dia <= 22 && mes == 8)) {
                System.out.println("Leão");
            } else if ((dia >= 23 && mes == 8) || (dia <= 22 && mes == 9)) {
                System.out.println("Virgem");
            } else if ((dia >= 23 && mes == 9) || (dia <= 22 && mes == 10)) {
                System.out.println("Libras");
            } else if ((dia >= 23 && mes == 10) || (dia <= 21 && mes == 11)) {
                System.out.println("Escorpião");
            } else {
                System.out.println("Sagitário");
            }
        } catch (Exception exception) {
            System.out.println("Ocorreu um erro: " + exception.getMessage());
        }
    }

    // Pede informacões da pessoa para que possa printa-las
    public static void infoPessoa(String nome, int dia, int mes, int ano) {
        // Valida as entradas e se houver um erro ele retorna øque houve
            validarDia(dia, mes);
            validarMes(mes);
            validarInt(ano);
        // Caso sejam aceitas o programa segue printando
        System.out.println("Informacão do(a): " + nome);
        System.out.printf("Dia de nascimento: %02d%n", dia);
        System.out.printf("Mes de nascimento: %02d%n", mes);
        System.out.printf("Ano de nascimento: %04d%n", ano);
        System.out.printf("Data final: %02d/%02d/%04d%n", dia, mes, ano);
    }

    // Método que calcula a idade de uma pessoa , recebendo como parametro seu nome e aniversario para fazer um calculo com o dia de hoje
    public static void printPessoaIdade(String nome, LocalDate aniversario) {
        // Declara data de hoje
        LocalDate agora = LocalDate.now();
        // Period que faz o calculo entre datas
        Period idade = Period.between(aniversario, agora);
        // Print
        System.out.println(nome + " tem " + idade.getYears() + " anos ");
    }

    public static void saudacao() {
        int hora = LocalDateTime.now().getHour();
        if (hora >= 6 && hora < 12) {
            System.out.println("Bom Dia!");
        } else if (hora >= 12 && hora < 18) {
            System.out.println("Boa Tarde!");
        } else {
            System.out.println("Boa noite ");
        }
    }

    public static void saudacaoInterativa(String nome) {
        int hora = LocalDateTime.now().getHour();
        if (hora >= 6 && hora < 12) {
            System.out.printf("Bom Dia %s !%n", nome);
        } else if (hora >= 12 && hora < 18) {
            System.out.printf("Boa Tarde %s !%n", nome);
        } else {
            System.out.printf("Boa Noite %s ! %n", nome);
        }
    }

    public static void printHora() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Data: 'dd/MM/yyyy '& Hora:' HH:mm:ss");
        System.out.println(formatter.format(now));
        //System.out.println("Dia: " + now.getDayOfMonth());
        //System.out.println("Mes: " + now.getMonthValue());
        //System.out.println("Ano: " + now.getYear());
        //System.out.println("Hour: " + now.getHour());
        //System.out.println("Minutes: " + now.getMinute());
        //System.out.println("Seconds: " + now.getSecond());
    }

    public static void validarInt(int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Não aceitamos numeros negativos.");
        }
    }


    public static void validarMes(int mes) {
        if (mes < 0 || mes > 12) {
            throw new IllegalArgumentException("Não aceitamos numeros abaixo de zero ou acima de 12.");
        }
    }

    public static void validarDia(int dia, int mes) {
        validarMes(mes);
        int maxDias = YearMonth.of(LocalDate.now().getYear(), mes).lengthOfMonth();
        if (dia < 1 || dia > maxDias) {
            throw new IllegalArgumentException("So aceitamos valores o primeiro e ultimo dia do mes. ");
        }
    }

    public static Pessoa obterDadosPessoa(Scanner sc) {
        String name = obterNome(sc);
        System.out.print("Qual o dia de seu nascimento?(Ex: 19) ");
        int dia = sc.nextInt();
        System.out.print("Qual o mês de seu nascimento?(Ex: 5) ");
        int mes = sc.nextInt();
        System.out.print("Qual o ano de seu nascimento?(Ex: 2003) ");
        int ano = sc.nextInt();
        return new Pessoa(name, dia, mes, ano);
    }

    public static String obterNome(Scanner sc) {
        System.out.print("Olá , por favor forneca-me seu primeiro nome: ");
        return sc.next();
    }

    public static int obterQuantidadePessoas(Scanner sc) {
        System.out.print("Forneca quantas pessoas voce gostaria de acrescentar ao sistema: ");
        return sc.nextInt();
    }
}
