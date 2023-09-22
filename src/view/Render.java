package view;

import Model.Servidor;

import java.util.ArrayList;
import java.util.Hashtable;

public class Render {
    public static void findServidor(Hashtable<String, Servidor> concessaoList, String matricula) {
        if (concessaoList.containsKey(matricula)) {
            System.out.println("Servidor encontrado na lista com concessão:");
            Servidor servidor = concessaoList.get(matricula);
            System.out.println(servidor.getMatricula() + " " + servidor.getNome());
            System.out.println(servidor.getConcessoes().size());

            for(int i = 0; i<servidor.getConcessoes().size(); i++){
                System.out.println(servidor.getConcessoes().get(i).toString());
            }

        } else {
            System.out.println("Servidor não encontrado");
        }
    }

    public static  void listarAfastamentos(Hashtable<String, Servidor> concessaoList, String matricula){
        if(concessaoList.containsKey(matricula)){
            if (!concessaoList.get(matricula).getAfastamentos().isEmpty()) {
                System.out.println("Servidor encontrado na lista com afastamentos:");
                for(int i = 0; i<concessaoList.get(matricula).getAfastamentos().size(); i++){
                    System.out.println(concessaoList.get(matricula).getAfastamentos().get(i).toString());
                }
            }
        } else {
            System.out.println("Servidor não encontrado na lista com afastamentos:");
        }
    }
}
