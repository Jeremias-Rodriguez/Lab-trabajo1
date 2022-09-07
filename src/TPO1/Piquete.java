/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

/**
 *
 * @author repetto.francisco
 */
public class Piquete {
     //Atributos
    private Ubicacion ubicacion;
    private long duracionMillis;
    
    public Piquete(Ubicacion ubicacion, long duracionMillis){
        this.ubicacion = ubicacion;
        this.duracionMillis = 5000+duracionMillis;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public long getDuracionMillis() {
        return duracionMillis;
    }

    public void setDuracionMillis(long duracionMillis) {
        this.duracionMillis = duracionMillis;
    }
    
}
