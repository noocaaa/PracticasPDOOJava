/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 * @author noelia
 */
public class TestP4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        ArrayList<Jugador> nombres = new ArrayList<Jugador>();
        
        Jugador alberto = new Jugador("Alberto");
        
        nombres.add(alberto);
        
        TituloPropiedad uno = new TituloPropiedad("Calle Granada", 50, 20, 100, 100, 50);
        
        System.out.println(uno);
        
        System.out.println();
        
        alberto.comprar(uno);
        
        System.out.println(uno);
        
        System.out.println();
        
        SorpresaESPECULADOR surprise = new SorpresaESPECULADOR(200, "Hola");
        
        surprise.aplicarAJugador(0, nombres);
        
        System.out.println(nombres.get(0).toString());
        
    }
    
}
