package Controller;

import Model.Servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;

public class ServidoresAptos {
    public static void leitorServidores(ArrayList<Servidor> servidoresAptos) {

        String matricula;
        String nome;
        LocalDate dataIngresso;

        BufferedReader leitor;
        int codigo = 0;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato da data

        try {
            leitor = new BufferedReader(new FileReader("src/data/servidores_efetivos.csv"));
            String linha = null;
            linha = leitor.readLine();
            while ((linha = leitor.readLine()) != null) {
                String[] dividir;
                dividir = linha.split(";");

                //System.out.println(linha);

                matricula = dividir[0];
                nome = dividir[1];
                dataIngresso = LocalDate.parse(dividir[4], dateFormatter);

                Servidor servidor = new Servidor(matricula, nome, dataIngresso);
                servidoresAptos.add(servidor);
            }
            leitor.close();
        } catch (FileNotFoundException file) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo");
        }
    }

}
