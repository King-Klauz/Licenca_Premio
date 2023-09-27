package Controller;

import Model.Concessao;
import Model.Servidor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Hashtable;

public class ConcessaoService {
    public static void leitorServidores(Hashtable<String, Servidor> concessaoList){

        String matricula;
        String nome;
        String cargo;
        String tipoServidor;
        int numConcessao;
        LocalDate dataInicio;
        LocalDate dataFim;
        int saldoConcessao;
        LocalDate dataIngOrgaoFormatada;
        int codigoLicenca;
        String licenca;
        Servidor servidor;
        Concessao concessao;

        BufferedReader leitor;
        int codigo = 0;
        String matriculaAnterior = "";

        ArrayList<Concessao> concessoes = new ArrayList<>();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato da data

        try{
            leitor = new BufferedReader(new FileReader("src/data/concessões_18092023.csv"));
            String linha = null;
            linha = leitor.readLine();
            while ((linha = leitor.readLine())!=null){
                String dividir[];
                dividir = linha.split(";");



                matricula = dividir[0];
                nome = dividir[1];
                numConcessao = Integer.parseInt(dividir[4]);
                dataInicio = LocalDate.parse(dividir[5], dateFormatter);
                dataFim = LocalDate.parse(dividir[6], dateFormatter);
                saldoConcessao = Integer.parseInt(dividir[7]);
                dataIngOrgaoFormatada = LocalDate.parse(dividir[8], dateFormatter);
                codigoLicenca = Integer.parseInt(dividir[9]);
                licenca = dividir[10];

                servidor = new Servidor(matricula, nome, dataIngOrgaoFormatada);

                concessao = new Concessao (numConcessao, dataInicio, dataFim, saldoConcessao,
                                                        dataIngOrgaoFormatada, codigoLicenca, licenca);

                //System.out.println(matricula);

                if(matricula.equals(matriculaAnterior)){
                    concessaoList.get(matricula).getConcessoes().add(concessao);
                }else{
                    servidor.getConcessoes().add(concessao);
                    concessaoList.put(matricula, servidor);
                }
                matriculaAnterior = matricula;

            }
            leitor.close();
        }catch(FileNotFoundException file){
            System.out.println("Arquivo não encontrado");

        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
        }
    }
}
