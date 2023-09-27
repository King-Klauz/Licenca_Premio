import Controller.AfastamentoService;
import Controller.ConcessaoService;
import Controller.PlanilhaService;
import Controller.RegrasDeNegocio.CalculoConcessao;
import Controller.ServidoresAptos;
import Model.Afastamentos;
import Model.Servidor;
import view.Render;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<Servidor> servidoresAptos = new ArrayList<Servidor>(); //Servidores que podem pedir concessao
        Hashtable<String, Servidor> concessaoList = new Hashtable<>();

        ArrayList<Servidor> planilhaExcel = new ArrayList<Servidor>();

        ServidoresAptos.leitorServidores(servidoresAptos);
        ConcessaoService.leitorServidores(concessaoList);
        AfastamentoService.leitorAfastamentos(concessaoList, servidoresAptos);

        //System.out.printf("Matrícula: ");
        String matricula = "118497";

        CalculoConcessao.aux(servidoresAptos, concessaoList, planilhaExcel);
        CalculoConcessao.inserirNovasConcessoes(servidoresAptos, concessaoList, planilhaExcel);

        Render.findServidor(concessaoList, matricula);
        Render.listarAfastamentos(concessaoList, matricula);
        CalculoConcessao.listarConcessoes(planilhaExcel, matricula);
        System.out.println(servidoresAptos.size());
        System.out.println(concessaoList.size());
        System.out.println(planilhaExcel.get(3818).getNome()+" "+planilhaExcel.get(3818).getMatricula()+" "+planilhaExcel.get(3818).getFlag());

        PlanilhaService.escreverPlanilha(planilhaExcel);
    }
}