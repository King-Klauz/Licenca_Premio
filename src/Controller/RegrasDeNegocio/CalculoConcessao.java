package Controller.RegrasDeNegocio;

import Model.Concessao;
import Model.Servidor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class CalculoConcessao {
    public static void aux(ArrayList<Servidor> servidoresAptos, Hashtable<String, Servidor> concessaoList, ArrayList<Servidor> planilhaExcel) {
        String auxMatricula;
        int i = 0;

        while (i < servidoresAptos.size()) {
            auxMatricula = servidoresAptos.get(i).getMatricula();

            if (concessaoList.containsKey(auxMatricula)) {

                int j = 0;
                int flag = 0;
                int flagNovaConcessao = 0;
                Servidor servidor = concessaoList.get(auxMatricula);

                while (j < servidor.getConcessoes().size()) {
                    LocalDate dataLimite = LocalDate.of(2023, 9, 29);
                    if (servidor.getConcessoes().get(j).getCodigoLicenca() == 1 && servidor.getConcessoes().get(j).getSaldoConcessao() >= 45) {
                        servidor.setFlag("CONCESSAO JÁ DISPONIVEL");
                        LocalDate novaConcessaoDataInicio = servidor.getConcessoes().get(j).getDataInicio();
                        LocalDate novaConcessaoDataFim = servidor.getConcessoes().get(j).getDataFim();
                        int somarDiasAfastamento = 0;

                        flag = 1;
                        int r = 0;
                        while (r < servidor.getAfastamentos().size()) {
                            if (servidor.getAfastamentos().get(r).getAcao().equals("INTERROMPE")) {
                                if (servidor.getAfastamentos().get(r).getDataInicio().isAfter(servidor.getConcessoes().get(j).getDataInicio())
                                        && servidor.getAfastamentos().get(r).getDataInicio().isBefore(servidor.getConcessoes().get(j).getDataFim())) {
                                    novaConcessaoDataInicio = servidor.getAfastamentos().get(r).getDataFim();
                                    novaConcessaoDataFim = servidor.getAfastamentos().get(r).getDataFim().plusDays(1825);
                                    flagNovaConcessao = 1;
                                }
                            }
                            r++;
                        }
                        r = 0;
                        //if(flagNovaConcessao==1){
                            while (r < servidor.getAfastamentos().size()) {
                                if (servidor.getAfastamentos().get(r).getAcao().equals("SUSPENDE")) {
                                    if (servidor.getAfastamentos().get(r).getDataInicio().isAfter(novaConcessaoDataInicio)
                                            && servidor.getAfastamentos().get(r).getDataInicio().isBefore(novaConcessaoDataFim)) {
                                        //somarDiasAfastamento = somarDiasAfastamento + servidor.getAfastamentos().get(r).getDias();
                                        servidor.setDiasAfastado(servidor.getDiasAfastado() + servidor.getAfastamentos().get(r).getDias());
                                    }
                                }
                                r++;
                            }
                        //}

                        if (novaConcessaoDataFim.plusDays(somarDiasAfastamento).isBefore(dataLimite)) {
                            if (flagNovaConcessao == 1) {
                                Concessao concessao = new Concessao(servidor.getConcessoes().size() + 1, novaConcessaoDataInicio, novaConcessaoDataFim.plusDays(somarDiasAfastamento),
                                        90, servidor.getDataIngresso(), 1, "LICENCA PREMIO POR ASSIDUIDADE");
                                servidor.getConcessoes().add(concessao);
                            } else {
                                servidor.getConcessoes().get(j).setDataFim(novaConcessaoDataFim.plusDays(somarDiasAfastamento));
                            }
                        }
                    }
                    j++;
                }
                if (flag == 0) {
                    //System.out.println("\n"+auxMatricula);
                    int k = 0;
                    int numeroConcessao = 0;
                    LocalDate dataLimite = LocalDate.of(2023, 9, 29);

                    LocalDate novaConcessaoDataInicio = null;
                    LocalDate novaConcessaoDataFim = null;

                    LocalDate maiorDataInterrupcao = servidor.getDataIngresso();
                    while(k<servidor.getConcessoes().size()){
                        if(servidor.getConcessoes().get(k).getNumConcessao()>numeroConcessao){
                            numeroConcessao = servidor.getConcessoes().get(k).getNumConcessao();
                            novaConcessaoDataInicio = servidor.getConcessoes().get(k).getDataFim().plusDays(1);
                            novaConcessaoDataFim = servidor.getConcessoes().get(k).getDataFim().plusDays(1825).plusDays(1);
                        }
                        k++;
                    }
                    k=0;

                    int somarDiasAfastamento = 0;

                    flag = 1;
                    int r = 0;
                    while (r < servidor.getAfastamentos().size()) {
                        if (servidor.getAfastamentos().get(r).getAcao().equals("INTERROMPE")) {
                            if (servidor.getAfastamentos().get(r).getDataInicio().isAfter(novaConcessaoDataInicio)
                                    && servidor.getAfastamentos().get(r).getDataInicio().isBefore(novaConcessaoDataFim)) {
                                novaConcessaoDataInicio = servidor.getAfastamentos().get(r).getDataFim().minusDays(1);
                                novaConcessaoDataFim = servidor.getAfastamentos().get(r).getDataFim().plusDays(1825).minusDays(1);
                            }
                        }
                        r++;
                    }
                    r = 0;
                    while (r < servidor.getAfastamentos().size()) {
                        if (servidor.getAfastamentos().get(r).getAcao().equals("SUSPENDE")) {
                            if (servidor.getAfastamentos().get(r).getDataInicio().isAfter(novaConcessaoDataInicio)
                                    && servidor.getAfastamentos().get(r).getDataInicio().isBefore(novaConcessaoDataFim)) {
                                somarDiasAfastamento = somarDiasAfastamento + servidor.getAfastamentos().get(r).getDias();
                            }
                        }
                        r++;
                    }

                    if (novaConcessaoDataFim.plusDays(somarDiasAfastamento).isBefore(dataLimite)) {

                            Concessao concessao = new Concessao(numeroConcessao + 1, novaConcessaoDataInicio, novaConcessaoDataFim.plusDays(somarDiasAfastamento),
                                    90, servidor.getDataIngresso(), 1, "LICENCA PREMIO POR ASSIDUIDADE");
                            servidor.getConcessoes().add(concessao);
                        servidor.setFlag("NOVA CONCESSAO GERADA");
                        servidor.setDiasAfastado(somarDiasAfastamento);
                    }else{
                        servidor.setFlag("NAO TEM CONCESSAO DISPONIVEL");
                        servidor.setDiasAfastado(somarDiasAfastamento);
                    }

                    /*while (k < servidor.getAfastamentos().size()) {
                        if (servidor.getAfastamentos().get(k).getAcao().equals("INTERROMPE")) {
                            int a = 0;
                            while (a < servidor.getConcessoes().size()) {

                                if (servidor.getConcessoes().get(a).getDataInicio().isBefore(servidor.getAfastamentos().get(k).getDataInicio())
                                        && servidor.getConcessoes().get(a).getDataFim().isAfter(servidor.getAfastamentos().get(k).getDataInicio())) {
                                    maiorDataInterrupcao = servidor.getAfastamentos().get(k).getDataFim();
                                }
                                if ((servidor.getConcessoes().size() - a) == 1) {
                                    if (servidor.getConcessoes().get(a).getDataInicio().plusYears(5).
                                            isBefore(servidor.getAfastamentos().get(k).getDataInicio())
                                            && servidor.getConcessoes().get(a).getDataFim().plusYears(5).
                                            isAfter(servidor.getAfastamentos().get(k).getDataInicio())) {
                                        maiorDataInterrupcao = servidor.getAfastamentos().get(k).getDataFim();
                                    }
                                }
                                a++;
                            }
                        }
                        k++;
                    }
                    System.out.println(auxMatricula);
                    System.out.println(maiorDataInterrupcao);

                    k = 0;

                    LocalDate somarAfastamentos = null;
                    LocalDate somarAfastamentosAux = null;

                    if (maiorDataInterrupcao.equals(servidor.getDataIngresso())) {
                        int b = 0;
                        int numeroDaConcessao = 0;
                        while (b < servidor.getConcessoes().size()) {
                            if (servidor.getConcessoes().get(b).getNumConcessao() > numeroDaConcessao) {
                                if (auxMatricula.equals("3392")) {
                                    System.out.println("3392");
                                    System.out.println(servidor.getConcessoes().get(b).getDataFim());
                                    System.out.println(servidor.getConcessoes().get(b).getDataInicio());
                                }
                                numeroDaConcessao = servidor.getConcessoes().get(b).getNumConcessao();
                                somarAfastamentos = servidor.getConcessoes().get(b).getDataFim();
                                somarAfastamentosAux = servidor.getConcessoes().get(b).getDataFim();
                            }
                            b++;
                        }

                    } else {
                        somarAfastamentos = maiorDataInterrupcao;
                        somarAfastamentosAux = maiorDataInterrupcao;
                    }


                    LocalDate dataLimite = LocalDate.of(2023, 9, 29);

                    if (somarAfastamentos.plusYears(5).isBefore(dataLimite)) {
                        int flag1 = 0;
                        somarAfastamentos = somarAfastamentos.plusYears(5);


                        int somaDosDiasDeAfastamento = 0;

                        while (k < servidor.getAfastamentos().size()) {

                            if (servidor.getAfastamentos().get(k).getDataInicio().isAfter(
                                    somarAfastamentosAux)) {
                                //System.out.println("entrou");
                                flag1 = 1;
                                if (servidor.getAfastamentos().get(k).getAcao().equals("SUSPENDE")) {
                                    somaDosDiasDeAfastamento = somaDosDiasDeAfastamento + servidor.getAfastamentos().get(k).getDias();
                                }
                            }
                            k++;
                        }

                        if (somarAfastamentos.plusDays(somaDosDiasDeAfastamento).isBefore(dataLimite)) {
                            //System.out.println(auxMatricula);
                            //System.out.println(somaDosDiasDeAfastamento);
                            somarAfastamentos = somarAfastamentos.plusDays(somaDosDiasDeAfastamento);
                            //System.out.println("somado dias de afastamento-> "+somarAfastamentos);

                            Concessao concessao = new Concessao(servidor.getConcessoes().size() + 1,
                                    somarAfastamentosAux,
                                    somarAfastamentos, 90, servidor.getDataIngresso(), 1,
                                    "LICENCA PREMIO POR ASSIDUIDADE");

                            servidor.getConcessoes().add(concessao);
                            //System.out.println(servidor.getNome());
                            concessaoList.put(servidor.getMatricula(), servidor);
                        }

                    }*/
                }
            }

            if (!concessaoList.containsKey(auxMatricula)) {
                int j = 0;

                LocalDate dataIngresso = servidoresAptos.get(i).getDataIngresso();

                for (int b = 0; b < servidoresAptos.get(i).getAfastamentos().size(); b++) {
                    if (servidoresAptos.get(i).getAfastamentos().get(b).getAcao().equals("INTERROMPE")) {
                        if (dataIngresso.isBefore(servidoresAptos.get(i).getAfastamentos().get(b).getDataInicio())
                                && dataIngresso.plusDays(1825).isAfter(servidoresAptos.get(i).getAfastamentos().get(b).getDataInicio())) {
                            dataIngresso = servidoresAptos.get(i).getAfastamentos().get(b).getDataFim();
                        }
                    }
                }

                LocalDate dataLimite = LocalDate.of(2023, 9, 29);

                if (dataIngresso.plusDays(1825).isAfter(dataLimite)) {
                    servidoresAptos.get(i).setFlag("NAO TEM CONCESSAO DISPONIVEL");
                } else {

                    int somarDiasAfastamento = 0;

                    if (!servidoresAptos.get(i).getAfastamentos().isEmpty()) {
                        for (int x = 0; x < servidoresAptos.get(i).getAfastamentos().size(); x++) {
                            if (servidoresAptos.get(i).getAfastamentos().get(x).getAcao().equals("SUSPENDE")) {
                                if (dataIngresso.isBefore(servidoresAptos.get(i).getAfastamentos().get(x).getDataInicio())
                                        && dataIngresso.plusDays(1825).isAfter(servidoresAptos.get(i).getAfastamentos().get(x).getDataInicio())) {
                                    somarDiasAfastamento = somarDiasAfastamento + servidoresAptos.get(i).getAfastamentos().get(x).getDias();
                                }
                            }
                        }
                    }

                    if (dataIngresso.plusDays(1825).plusDays(somarDiasAfastamento).isBefore(dataLimite)) {

                        String matricula = servidoresAptos.get(i).getMatricula();
                        String nome = servidoresAptos.get(i).getNome();

                        Servidor servidor = new Servidor(matricula, nome, servidoresAptos.get(i).getDataIngresso());
                        Concessao concessao = new Concessao(1, dataIngresso, dataIngresso.plusDays(1825).plusDays(somarDiasAfastamento),
                                90, servidoresAptos.get(i).getDataIngresso(), 1, "LICENCA PREMIO POR ASSIDUIDADE");

                        servidor.getConcessoes().add(concessao);
                        servidor.setAfastamentos(servidoresAptos.get(i).getAfastamentos());
                        servidor.setFlag("PRIMEIRA CONCESSAO GERADA");
                        servidor.setDiasAfastado(somarDiasAfastamento);

                        concessaoList.put(matricula, servidor);
                    }else{
                        servidoresAptos.get(i).setFlag("NAO TEM CONCESSAO DISPONIVEL");
                    }
                }
            }
            i++;
        }
    }

    public static void inserirNovasConcessoes(ArrayList<Servidor> servidoresAptos, Hashtable<String, Servidor> concessaoList, ArrayList<Servidor> planilhaExcel){
        int i = 0;

        while(i<servidoresAptos.size()){

            String matricula = servidoresAptos.get(i).getMatricula();
            if(concessaoList.containsKey(matricula)){
                Servidor servidor = concessaoList.get(matricula);
                Servidor servidorAux = new Servidor(servidor.getMatricula(), servidor.getNome(), servidor.getDataIngresso());

                int j = servidor.getConcessoes().size();
                int numeroConcessao = 999999999;

                while(j>0){
                    int index = j-1;

                    if(servidor.getConcessoes().get(index).getCodigoLicenca() == 1
                    && servidor.getConcessoes().get(index).getSaldoConcessao()>=45){
                        Concessao concessao;

                        if(servidor.getConcessoes().get(index).getNumConcessao()<numeroConcessao){
                            numeroConcessao=servidor.getConcessoes().get(index).getNumConcessao();
                            concessao =  servidor.getConcessoes().get(index);
                            if(servidorAux.getConcessoes().isEmpty()){
                                servidorAux.getConcessoes().add(concessao);
                                servidorAux.setFlag(servidor.getFlag());
                            }else{
                                servidorAux.getConcessoes().clear();
                                servidorAux.getConcessoes().add(concessao);
                                servidorAux.setFlag(servidor.getFlag());
                            }
                        }
                    }
                    j--;
                }
                planilhaExcel.add(servidorAux);
            }
            i++;
        }

    }

    public static void listarConcessoes(ArrayList<Servidor> planilhaExcel){

        for(int i = 0; i< planilhaExcel.size(); i++){
            for(int j = 0;j<planilhaExcel.get(i).getConcessoes().size();j++){
                System.out.println(planilhaExcel.get(i).getMatricula() + " " + planilhaExcel.get(i).getNome() + "\n" + planilhaExcel.get(i).getFlag());
                System.out.println(planilhaExcel.get(i).getConcessoes().get(j)+"\n");
            }
        }
    }

}