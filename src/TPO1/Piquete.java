/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

import java.util.concurrent.Callable;

/**
 *
 * @author repetto.francisco
 */
public class Piquete{
     //Atributos
    private Ubicacion ubicacion;
    private long duracionMillis;
    
    public Piquete(Ubicacion ubicacion, long duracionMillis){
        this.ubicacion = ubicacion;
        this.duracionMillis = 5000+duracionMillis;
    }
    
    public String mostrarPiquete(){
        return ubicacion.toString() + " que dura "+duracionMillis/1000+" segundos";
    }
}
