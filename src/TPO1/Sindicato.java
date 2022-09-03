/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author repetto.francisco
 */
public class Sindicato {
      //Atributos
    private int numPiqueterosTotal;
    private int numPiqueterosDisp;
    private List<Ubicacion> lugaresParaPiquete;
    
    
    public Sindicato(){
        //Esto lo estoy elegiendo pero se puede cambiar*/
        this.numPiqueterosTotal = 5;
        this.numPiqueterosDisp = 5;
        ArrayList<Ubicacion> lugares = new ArrayList();
        lugares.add(new Ubicacion("Ruta1",3));
        lugares.add(new Ubicacion("Ruta1",10));
        lugares.add(new Ubicacion("Ruta2",5));
        lugares.add(new Ubicacion("Ruta3",2));
        lugaresParaPiquete = Collections.synchronizedList(lugares);
    }
    
    private ScheduledExecutorService sindicato = Executors.newScheduledThreadPool(10);
    
    
    private ScheduledThreadPoolExecutor piqueteros = new ScheduledThreadPoolExecutor(numPiqueterosTotal);
    

   public void mandarPiquetes() {
       //Armo los piquetes
        Random random = new Random();
        
        //hacer randomizer piqute
       Ubicacion ubic = lugaresParaPiquete.remove(random.nextInt(4));
       System.out.println(lugaresParaPiquete.toString());
       long duracion = random.nextInt(5) * 1000;
       Piquete nuevoPiquete = new Piquete(ubic, duracion);
       Callable piquete = new Callable() {
           @Override
           public Object call() {
               System.out.println(Thread.currentThread().getName() + " haciendo piquete en "
                       + nuevoPiquete.mostrarPiquete());
               return ubic;
           }
       };

       Runnable organizadorDePiquetes = new Runnable() {
           public void run() {
               System.out.println(Thread.currentThread().getName());
                 //Se crea piquete
               
                //Se crea la tarea piquete que lo va a hacer uno de los hilos del grupo
               

               //Se pone el piquete en la cola de tareas
               Future<Ubicacion> ubicacion = piqueteros.submit(piquete);
//               try {
//                   //System.out.println("asdasdsa"+ubicacion.get());
//               } catch (InterruptedException ex) {
//                  
//               } catch (ExecutionException ex) {
//                  
//               }
           }
       };
       //Lo de abajo crea un automatizador de piquetes
     final ScheduledFuture<?> automatizadorDeOrganizador =
       sindicato.scheduleAtFixedRate(organizadorDePiquetes, 1, 1000, TimeUnit.MILLISECONDS);
   }
}
