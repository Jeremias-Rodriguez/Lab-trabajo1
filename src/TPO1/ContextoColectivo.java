/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 *
 * @author jerexio
 */
public class ContextoColectivo {
    private EstrategiaCoduccion estrategia;

    public ContextoColectivo(EstrategiaCoduccion estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaCoduccion estrategia) {
        this.estrategia = estrategia;
    }
    
    public void ejecutarEstrategia(Colectivo colectivo){
        this.estrategia.conducir(colectivo);
    }
}
