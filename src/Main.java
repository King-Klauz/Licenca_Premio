import Controller.AfastamentoService;
import Controller.ConcessaoService;
import Controller.RegrasDeNegocio.CalculoConcessao;
import Controller.ServidoresAptos;
import Model.Afastamentos;
import Model.Servidor;
import view.Render;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Servidor> servidoresAptos = new ArrayList<Servidor>(); //Servidores que podem pedir concessao
        Hashtable<String, Servidor> concessaoList = new Hashtable<>();

        ArrayList<Servidor> planilhaExcel = new ArrayList<Servidor>();

        ServidoresAptos.leitorServidores(servidoresAptos);
        ConcessaoService.leitorServidores(concessaoList);
        AfastamentoService.leitorAfastamentos(concessaoList, servidoresAptos);

        //System.out.printf("Matrícula: ");
        //String matricula = scan.nextLine();

        CalculoConcessao.aux(servidoresAptos, concessaoList, planilhaExcel);
        Render.findServidor(concessaoList, "80549");

        Render.listarAfastamentos(concessaoList, "80549");

        /*for(int i = 0; i<servidoresAptos.size(); i++){
            if(servidoresAptos.get(i).getMatricula().equals("162362")){
                for(int j = 0;j<servidoresAptos.get(i).getAfastamentos().size();j++){
                    System.out.println(servidoresAptos.get(i).getAfastamentos().get(j).toString());
                }
                System.out.println(servidoresAptos.get(i).getAfastamentos().size());
            }
        }*/
    }
}