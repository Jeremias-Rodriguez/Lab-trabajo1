/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import static java.lang.Math.random;
import java.util.Random;
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
    
    private final ScheduledExecutorService jefeSindicato =
     Executors.newScheduledThreadPool(1);
    
    
    public Sindicato(OrganizadorSindicatos organizador) {
        this.organizador = organizador;
    }
    
    Runnable tarea = new Runnable(){
    @Override
    public void run(){
        Piquete piquete = organizador.crearPiquete();

        boolean exito = (boolean)organizador.mandarPiquete(piquete);
        piquetesExitosos += (exito) ? 1 : 0;

        organizador.finalizarPiquete(piquete.getUbicacion());
    }
    };
    
    public void ejecutar(){
        Random random = new Random();
        final ScheduledFuture automatizador = 
            jefeSindicato.scheduleAtFixedRate(tarea, (random.nextInt(8)+2)*1000, 10000, TimeUnit.MILLISECONDS);
        
        /**
         * Pone un "tope" de creacion de piquetes.
         */
        jefeSindicato.schedule(new Runnable(){
            public void run(){
                automatizador.cancel(true);
            }
        }, 90, TimeUnit.SECONDS);
    }
}
