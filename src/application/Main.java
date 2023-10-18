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
        saudacaoInterativa("Vitor");
        saudacao();
        printHora();
        LocalDate nascimento = LocalDate.of(2005, 4, 19);
        infoPessoa("Vitor", 19, 4, 2005);
        printPessoaIdade("Vitor", nascimento);
        horaEm("Mexico", "America/Mexico_City");

        try {
            validarDia(19, 4);
            validarMes(4);
            validarInt(4);
            //validarDia(19,13);
            //validarMes(13);
            //validarInt(-9);
        } catch (IllegalArgumentException exception) {
            System.out.println("ERROR: " + exception.getMessage());
        } finally {
            System.out.println("Try Finalizado com sucesso.");
        }

        // Interface teste INTERATIVA \\
        /*
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
            horaEm(local, timezone);

            System.out.print("Deseja realizar o processo novamente? S para sim e N para não: ");
            yon = sc.next().charAt(0);
        }
        System.out.println("Progama encerrado");
        */
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

    // Método que printa uma saudacão referente a hora do local
    public static void saudacao() {
        // Obtém o instante atual
        Instant instant = Instant.now();
        // Converte o instante para a zona de tempo do sistema do computador
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        // Obtém a hora local a partir do ZonedDateTime
        LocalTime hora = zdt.toLocalTime();
        // Verifica se a hora está entre 6:00 e 12:00
        if (hora.isAfter(LocalTime.of(6, 0)) && hora.isBefore(LocalTime.of(12, 0))) {
            System.out.println("Bom Dia!");
        }
        // Verifica se a hora está entre 12:00 e 18:00
        else if (hora.isAfter(LocalTime.of(12, 0)) && hora.isBefore(LocalTime.of(18, 0))) {
            System.out.println("Boa Tarde!");
        }
        // Se nenhuma das condições acima for verdadeira, imprime "Boa noite"
        else {
            System.out.println("Boa noite ");
        }
    }

    // Saudacão interativa , referente ao nome da pessoa + a funcão acima
    public static void saudacaoInterativa(String nome) {
        // Obtém o instante atual
        Instant instant = Instant.now();
        // Converte o instante para a zona de tempo do sistema do computador
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        // Obtém a hora local a partir do ZonedDateTime
        LocalTime hora = zdt.toLocalTime();
        // Verifica se a hora está entre 6:00 e 12:00
        if (hora.isAfter(LocalTime.of(6, 0)) && hora.isBefore(LocalTime.of(12, 0))) {
            System.out.printf("Bom Dia %s!%n", nome);
        }
        // Verifica se a hora está entre 12:00 e 18:00
        else if (hora.isAfter(LocalTime.of(12, 0)) && hora.isBefore(LocalTime.of(18, 0))) {
            System.out.printf("Boa Tarde %s!%n", nome);
        }
        // Se nenhuma das condições acima for verdadeira, imprime "Boa noite"
        else {
            System.out.printf("Boa noite %s!%n", nome);
        }
    }

    // Print da hora local
    public static void printHora() {
        // Obtém o instante atual
        Instant instant = Instant.now();
        // Converte o instante para zona de tempo do sistema do computador
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        // Obtém a hora local a partir do ZonedDateTime
        LocalDateTime now = zdt.toLocalDateTime();
        // Formatacão para printar ( Exemplo: Data: 18/10/2023 & Hora: 16:53:09 )
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Data: 'dd/MM/yyyy '& Hora:' HH:mm:ss");
        // Print da formatacão passando now como argumento, para formatar a hora e data conforme feito no formatter
        System.out.println(formatter.format(now));
        //System.out.println("Dia: " + now.getDayOfMonth());
        //System.out.println("Mes: " + now.getMonthValue());
        //System.out.println("Ano: " + now.getYear());
        //System.out.println("Hour: " + now.getHour());
        //System.out.println("Minutes: " + now.getMinute());
        //System.out.println("Seconds: " + now.getSecond());
    }

    // Usado como método para proibir uma integracão de numero negativos no sistema
    public static void validarInt(int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Não aceitamos numeros negativos.");
        }
    }

    // Usado como método para validar o mes para limitar uma integracão com meses não existentes
    public static void validarMes(int mes) {
        if (mes < 0 || mes > 12) {
            throw new IllegalArgumentException("Não aceitamos numeros abaixo de zero ou acima de 12.");
        }
    }

    // Método que vai pedir um dia como entrada e mes, referente ao mes ele irá conferir se o dia pode exisitir em tal mes
    public static void validarDia(int dia, int mes) {
        // Valida o mes
        validarMes(mes);
        // Atribui a um int o mes do ano do LocalDate HOJE, get no ano e mes para puxar o tamanho do mes
        int maxDias = YearMonth.of(LocalDate.now().getYear(), mes).lengthOfMonth();
        // Se o dia for menor que 1 ou maior que a quantidade de dias no mes ele retorna um erro
        if (dia < 1 || dia > maxDias) {
            throw new IllegalArgumentException("So aceitamos valores o primeiro e ultimo dia do mes. ");
        }
    }

    // Método para obterDados de uma pessoa , recebendo como parametro um Scanner
    // Para assim receber um dado e o armazena-lo
    public static Pessoa obterDadosPessoa(Scanner sc) {
        // Método para obter o nome de quem comandará o sistema, porém reaproveitado para obter um nome neste método também
        String name = obterNome(sc);
        // Método que receberá o dia do nascimento e salvara em um inteiro
        System.out.print("Qual o dia de seu nascimento?(Ex: 19) ");
        int dia = sc.nextInt();
        // Método que receberá o mes do nascimento e salvara em um inteiro
        System.out.print("Qual o mês de seu nascimento?(Ex: 5) ");
        int mes = sc.nextInt();
        // Método que receberá o ano do nascimento e salvara em um inteiro
        System.out.print("Qual o ano de seu nascimento?(Ex: 2003) ");
        int ano = sc.nextInt();
        // Após isso cria uma pessoa com todos os atributos antes armazenados
        return new Pessoa(name, dia, mes, ano);
    }

    // Obtem um nome, criei-o para simplificar o processo de criar um "Mandante" para o progama
    // Recebe um scanner como parametro
    public static String obterNome(Scanner sc) {
        System.out.print("Olá , por favor forneca-me seu primeiro nome: ");
        return sc.next();
    }

    // Obtem a quantidade de pessoas, passando como parametro um scanner
    // Serve papra armazenar quantas pessoas serão adicionadas ao sistema
    public static int obterQuantidadePessoas(Scanner sc) {
        System.out.print("Forneca quantas pessoas voce gostaria de acrescentar ao sistema: ");
        return sc.nextInt();
    }
}
