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
        String matricula = "1504596";

        CalculoConcessao.aux(servidoresAptos, concessaoList, planilhaExcel);
        //CalculoConcessao.inserirNovasConcessoes( servidoresAptos, concessaoList, planilhaExcel);
        //CalculoConcessao.listarConcessoes(planilhaExcel);
        Render.findServidor(concessaoList, matricula);
        Render.listarAfastamentos(concessaoList, matricula);
    }
}