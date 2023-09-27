package Controller;

import Model.Servidor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlanilhaService {
    public static void escreverPlanilha(ArrayList<Servidor> planilhaExcel) throws IOException {

        BufferedWriter escritor = new BufferedWriter(new FileWriter("Relatórios.csv"));

        escritor.write("Matricula;Nome;NumConcessao;Inicio;Fim;Saldo;Codigo;Descricao;DiasAfastado;Situacao;\n");
        for(int i = 0; i<planilhaExcel.size();i++){

            escritor.write(planilhaExcel.get(i).getMatricula()+";"+planilhaExcel.get(i).getNome()+";");
            if(!planilhaExcel.get(i).getConcessoes().isEmpty()){
                escritor.write(planilhaExcel.get(i).getConcessoes().get(0).getNumConcessao()+";"+planilhaExcel.get(i).getConcessoes().get(0).getDataInicio()+
                        ";"+planilhaExcel.get(i).getConcessoes().get(0).getDataFim()+";"+planilhaExcel.get(i).getConcessoes().get(0).getSaldoConcessao()+
                        ";"+planilhaExcel.get(i).getConcessoes().get(0).getCodigoLicenca()+";"+planilhaExcel.get(i).getConcessoes().get(0).getLicenca()+
                        ";"+planilhaExcel.get(i).getDiasAfastado()+";"+planilhaExcel.get(i).getFlag()+";");
            }else{
                escritor.write(";"+";"+";"+";"+";"+";"+";"+planilhaExcel.get(i).getFlag());
            }

            escritor.write("\n");
            escritor.flush();
            System.out.println(planilhaExcel.get(i).getMatricula()+' '+planilhaExcel.get(i).getFlag());
        }
    }
}
