/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Esta clase representa a los sindicatos que envian piquetes
 * Usa un grupo de 1 solo hilo que representa este sindicato.
 * 
 * ¿Por que deben existir varios objetos sindicatos?
 * ¿Por que no, simplemente se aumenta la cantidad de hilos del pool (o grupo)?
 * 
 * En ScheduleExecutorService, cuando se planifica una tarea (Run)
 * que ocurre cada cierto tiempo (scheduleAtFixedRate). Si dicha tarea
 * incluye un Callable, tanto el hilo del pool como el creador de tareas se
 * quedan esperando una respuesta, esto implica una incorrecta simulacion
 * de varios sindicatos en paralelo.
 * 
 * Por ello se evito. Y en su lugar, se crean varios objetos sindicatos,
 * que tambien es mas representativo
 * 
 * @author jerexio
 * @author repetto.francisco
 */
public class Sindicato {

    private OrganizadorSindicatos organizador;
    private int piquetesExitosos = 0;
    
    //Representa quien crea los piquetes, de este sindicato
    private final ScheduledExecutorService jefeSindicato =
     Executors.newScheduledThreadPool(1);
    
    
    public Sindicato(OrganizadorSindicatos organizador) {
        this.organizador = organizador;
    }
    
    /**
     * Se define una tarea runnable, que es la que crea y ejecuta el
     * pool de un solo hilo, o sea el jefeSindicato.
     */
    private Runnable tarea = new Runnable(){
        /**
         * Crea el piquete
         * Lo manda para que lo realicen
         * Verifica si fue exitoso
         * Finaliza el piquete.
         */
        @Override
        public void run(){
            Piquete piquete = organizador.crearPiquete();

            boolean exito = (boolean)organizador.mandarPiquete(piquete);
            piquetesExitosos += (exito) ? 1 : 0;

            organizador.finalizarPiquete(piquete.getUbicacion());
        }
    };
    
    /**
     * Este metodo tiene 2 funciones
     *  - Cada cierto tiempo crear una nueva tarea
     *  - Poner en la cola de tareas, una tarea que finalice al automatizador
     */
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
