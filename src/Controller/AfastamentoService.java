package Controller;

import Model.Afastamentos;
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

public class AfastamentoService {
    public static void leitorAfastamentos(Hashtable<String, Servidor> concessaoList, ArrayList<Servidor> servidoresAptos){
        String matricula;
        LocalDate dataInicio;
        LocalDate dataFim;
        Integer dias;
        String acao;

        Afastamentos afastamentos;

        BufferedReader leitor;
        int codigo = 0;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato da data

        try{
            leitor = new BufferedReader(new FileReader("src/data/AFASTAMENTOS.csv"));
            String linha = null;
            linha = leitor.readLine();
            while ((linha = leitor.readLine())!=null){
                String dividir[];
                dividir = linha.split(";");

                matricula =  dividir[0];

                if(concessaoList.containsKey(matricula)){
                    //System.out.println("entrou");
                    dataInicio = LocalDate.parse(dividir[4], dateFormatter);
                    dataFim = LocalDate.parse(dividir[6], dateFormatter);
                    dias = Integer.parseInt(dividir[5]);
                    acao = dividir[7];
                    afastamentos = new Afastamentos(dataInicio, dataFim,dias,acao);
                    concessaoList.get(matricula).getAfastamentos().add(afastamentos);
                    //System.out.println(matricula+"\n"+concessaoList.get(matricula).getAfastamentos().size());
                }

                if(!concessaoList.containsKey(matricula)){
                    for(int i = 0; i<servidoresAptos.size(); i++){
                        if(servidoresAptos.get(i).getMatricula().equals(matricula)){
                            dataInicio = LocalDate.parse(dividir[4], dateFormatter);
                            dataFim = LocalDate.parse(dividir[6], dateFormatter);
                            dias = Integer.parseInt(dividir[5]);
                            acao = dividir[7];
                            afastamentos = new Afastamentos(dataInicio, dataFim,dias,acao);
                            servidoresAptos.get(i).getAfastamentos().add(afastamentos);
                        }
                    }
                }
            }

            leitor.close();
        }catch(FileNotFoundException file){
            System.out.println("Arquivo não encontrado");

        }catch(IOException e){
            System.out.println("Erro ao ler o arquivo");
        }
    }
}
