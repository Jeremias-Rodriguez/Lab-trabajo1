/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jerexio
 */
public class Sindicato extends Thread {

    private OrganizadorSindicatos organizador;
    private int piquetesExitosos = 0;
    
    private long tiempoIni;
    private long tiempoFin;
    private final ScheduledExecutorService sindicatos =
     Executors.newScheduledThreadPool(4);
    
    
    public Sindicato(OrganizadorSindicatos organizador) {
        this.organizador = organizador;
    }
    
    Runnable tarea = new Runnable(){
    @Override
    public void run(){
        Random random = new Random();
        tiempoIni = System.currentTimeMillis();
        tiempoFin = System.currentTimeMillis() + (random.nextInt(200)*1000);
        
        while(tiempoIni <= tiempoFin){
            Piquete piquete = organizador.establecerPiqueteEnUbicacion();
            
            boolean exito = (boolean)organizador.mandarPiquete(piquete);
            piquetesExitosos += (exito) ? 1 : 0;

            System.out.println("PIQUETE ES NULL 2: "+(piquete.getUbicacion() == null));
            System.out.println(Thread.currentThread().getName()+" - "+piquete.getUbicacion().toString());

            organizador.finalizarPiquete(piquete.getUbicacion());

            tiempoIni = System.currentTimeMillis();
           
        }
        System.out.println("\n\n---------------------------------------");
        System.out.println("FIN piquete exitosos: "+piquetesExitosos);
        System.out.println("---------------------------------------\n\n");
    }};
    
    public void ejecutar(){
        int cantSindicatos = 4;
        final ScheduledFuture[] automatizadores = new ScheduledFuture[cantSindicatos];
        for(int i = 0; i < cantSindicatos; i++){
            automatizadores[i] = 
            sindicatos.scheduleAtFixedRate(tarea, 10, 1000, TimeUnit.MILLISECONDS);
        }
    }
}
