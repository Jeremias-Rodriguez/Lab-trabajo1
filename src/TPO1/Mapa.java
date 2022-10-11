/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 * Esta clase representa y contiene las funciones del mapa.
 * 
 * Si desea definir su propio mapa, este debe contener los siguientes valores:
 * ARBOL        --> 44
 * RUTA         --> 0
 * FIN_CURVA    --> 4
 * YA_RECORRIDO --> -1
 * COLECTIVO    --> 3
 * DESTINO      --> 5
 * PIQUETE      --> 1
 * 
 * @author jerexio
 * @author repetto.francisco
 */
public class Mapa {
    private final int ARBOL = 44;
    private final int RUTA = 0;
    private final int FIN_CURVA = 4;
    private final int YA_RECORRIDO = -1;
    private final int COLECTIVO = 3;
    private final int DESTINO = 5;
    private final int PIQUETE = 1;
    
    /**
     * Podria ser matriz de AtomicInteger, pero es "mas dificil de implementar".
     */
    private int[][] mapa;

    public Mapa(int[][] mapa) {
        this.mapa = mapa;
    }
    
    /**
     * Recibe, la posicion X e Y, previa al desplazamiento y luego de desplazar
     * @param Xprev
     * @param Yprev
     * @param posX
     * @param posY
     * Verifica si la posicion nueva es ruta o fin de curva, sino significa
     * que esta ocupada
     * 
     * @return Pudo desplazarse
     */
    public synchronized boolean desplazarColectivo(int Xprev, int Yprev,
            int posX, int posY){
        boolean seActualizo = false;
        
        if(mapa[posX][posY] == RUTA | mapa[posX][posY] == FIN_CURVA){
            mapa[Xprev][Yprev] = YA_RECORRIDO;
            mapa[posX][posY] = COLECTIVO;
            seActualizo = true;
        }
        
        return seActualizo;
    }
    
    /**
     * Desplaza el colectivo hacia arriba o abajo, segun sea el caso, si lo
     * que esta al frente es un arbol.
     */
    public synchronized boolean desplazarColectivoSiArbol(int Xactual, int Yactual,
            int Xfuturo, int Yfuturo){
        boolean seActualizo = false;
        
        if(mapa[Xactual][Yfuturo] == ARBOL){
            if(mapa[Xfuturo][Yactual] == RUTA | mapa[Xfuturo][Yactual] == FIN_CURVA){
                mapa[Xactual][Yactual] = YA_RECORRIDO;
                mapa[Xfuturo][Yactual] = COLECTIVO;
                
                seActualizo = true;
            }
        }
        
        return seActualizo;
    }
    
    /**
     * Se usa para establecer piquetes
     * Verifica si la posicion donde se debe establecer no esta ocupada por el
     * colectivo.
     */
    public synchronized boolean establecerPiquete(int posX, int posY){
        boolean seActualizo = false;
        
        if(mapa[posX][posY] != COLECTIVO){
            mapa[posX][posY] = PIQUETE;
            seActualizo = true;
        }
        
        return seActualizo;
    }
    
    /**
     * Elimina el piquete en la posicion XY
     * @param posX
     * @param posY 
     */
    public synchronized void destablecerPiquete(int posX, int posY){
        mapa[posX][posY] = RUTA;
    }
    
    /**
     * Verifica si la posicion XY es destino
     * @param posX
     * @param posY
     * @return 
     */
    public boolean esDestino(int posX, int posY){
        return mapa[posX][posY] == DESTINO;
    }
    
    /**
     * Se usa para verificar si la posicion XY es una esquina 
     * @param posX
     * @param posY
     * @return si es esquina
     */
    public boolean esEsquina(int posX, int posY){
        return (mapa[posX+1][posY] == RUTA || mapa[posX-1][posY] == RUTA);
    }
    
    /**
     * Se usa con fines de debug del mapa
     */
    public synchronized void mostrarMapa(){
        int maxX = mapa.length;
        int maxY = mapa[0].length;
        
        for(int x = RUTA; x < maxX; x++){
            for(int y = RUTA; y < maxY; y++){
                System.out.print(mapa[x][y]+"\t");
            }
            System.out.println("");
        }
    }
}
