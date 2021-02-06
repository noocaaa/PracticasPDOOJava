/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import civitas.CivitasJuego;
import java.util.ArrayList;

/**
 * @author noelia
 */
public class TestP3 { //para probar todo el juego
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            
        VistaTextual vista = new VistaTextual();
        
        ArrayList<String> nombres = new ArrayList<>();
        
        //añadimos los jugadores
        nombres.add("Noelia");
        nombres.add("Lucia");
        nombres.add("Alberto");
        nombres.add("Aiden");
                        
        CivitasJuego juego = new CivitasJuego(nombres);
  
        Dado.getInstance().setDebug(true); //ponemos el dado en modo debug
        
        //Controlador controlador = new Controlador(juego, vista); //cuando era VistaTextual en vez de CivitasView
                
        //controlador.juega();
        
    }
}
