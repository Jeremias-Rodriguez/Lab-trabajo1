/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

import Interfaz.Interfaz;

/**
 *
 * @author repetto.francisco
 */
public class Main {
    public static void main(String[] args) {
        Piquteros piqueteros = new Piquteros();
        
        int[][] plano = new int[7][26];

        Precargador.precargar(plano);
        
        Mapa mapa = new Mapa(plano);
        
        
        OrganizadorSindicatos org = new OrganizadorSindicatos(piqueteros, mapa);
        
//        int cantSind = 4;
//        
//        for(int i = 0; i < cantSind; i++){
//            Sindicato sin = new Sindicato(org);
//            sin.ejecutar();
//        }
//        
//        
        
        
//        Thread hilo = new Thread(){
//            public void run(){
//                while(true){
//                    mapa.mostrarMapa();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException ex) {
//                        System.err.println("El colectivo pincho para siempre");
//                    }
//                    System.out.println("\n\n\n\n");
//                }
//            }
//        };
//        hilo.start();
        
        Ubicacion posInicial = new Ubicacion("Ruta 3", 3, 1);
        Colectivo colectivo = new Colectivo(posInicial, mapa);
        
        Ruta ruta = new Ruta(mapa, colectivo);
        Interfaz interfaz = new Interfaz(ruta);
        
        colectivo.setInterfaz(interfaz);
        colectivo.start();
    }
}
