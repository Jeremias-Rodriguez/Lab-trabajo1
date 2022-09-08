/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 *
 * @author jerexio
 */
public class Mapa {
    /**
     * Podria ser matriz de AtomicInteger, pero es "mas dificil de implementar".
     */
    private int[][] mapa;

    public Mapa(int[][] mapa) {
        this.mapa = mapa;
    }
    
    public synchronized boolean desplazarColectivo(int Xprev, int Yprev,
            int posX, int posY){
        boolean seActualizo = false;
        
        if(mapa[posX][posY] == 0 | mapa[posX][posY] == 4){
            mapa[Xprev][Yprev] = -1;
            mapa[posX][posY] = 3;
            seActualizo = true;
        }
        
        return seActualizo;
    }
    
    public synchronized boolean desplazarColectivoSiArbol(int Xactual, int Yactual,
            int Xfuturo, int Yfuturo){
        boolean seActualizo = false;
        
        if(mapa[Xactual][Yfuturo] == 44){
            if(mapa[Xfuturo][Yactual] == 0 | mapa[Xfuturo][Yactual] == 4){
                mapa[Xactual][Yactual] = -1;
                mapa[Xfuturo][Yactual] = 3;
                
                seActualizo = true;
            }
        }
        
        return seActualizo;
    }
    
    public synchronized boolean establecerPiquete(int posX, int posY){
        boolean seActualizo = false;
        
        if(mapa[posX][posY] != 3){
            mapa[posX][posY] = 1;
            seActualizo = true;
        }
        
        return seActualizo;
    }
    
    public synchronized void destablecerPiquete(int posX, int posY){
        mapa[posX][posY] = 0;
    }
    
    public boolean esDestino(int posX, int posY){
        return mapa[posX][posY] == 5;
    }
    
    public synchronized void mostrarMapa(){
        int maxX = mapa.length;
        int maxY = mapa[0].length;
        
        for(int x = 0; x < maxX; x++){
            for(int y = 0; y < maxY; y++){
                System.out.print(mapa[x][y]+"\t");
            }
            System.out.println("");
        }
    }
    
    public boolean esEsquina(int posX, int posY){
        return (mapa[posX+1][posY] == 0 || mapa[posX-1][posY] == 0);
    }
}
