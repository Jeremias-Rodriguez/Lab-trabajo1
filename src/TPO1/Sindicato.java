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
    private final ScheduledExecutorService jefeSindicato =
     Executors.newScheduledThreadPool(1);
    
    
    public Sindicato(OrganizadorSindicatos organizador) {
        this.organizador = organizador;
    }
    
    Runnable tarea = new Runnable(){
    @Override
    public void run(){
        Random random = new Random();
        tiempoIni = System.currentTimeMillis();
        tiempoFin = System.currentTimeMillis() + (random.nextInt(200)*1000);
        
        Piquete piquete = organizador.establecerPiqueteEnUbicacion();

        boolean exito = (boolean)organizador.mandarPiquete(piquete);
        piquetesExitosos += (exito) ? 1 : 0;

        System.out.println(Thread.currentThread().getName()+" - "+piquete.getUbicacion().toString());

        organizador.finalizarPiquete(piquete.getUbicacion());

        tiempoIni = System.currentTimeMillis();
    }
    };
    
    public void ejecutar(){
        final ScheduledFuture automatizador = 
            jefeSindicato.scheduleAtFixedRate(tarea, 10, 1000, TimeUnit.MILLISECONDS);
        jefeSindicato.schedule(new Runnable(){
            public void run(){
                automatizador.cancel(true);
                System.out.println("El horario de protesta finalizis");
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }
}
