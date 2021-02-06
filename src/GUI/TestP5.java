package GUI;

import java.util.ArrayList;
import civitas.CivitasJuego;

/**
 * @author noelia
 */
public class TestP5 {
    public static void main(String[] args) {
        // TODO code application logic here
        CivitasView view = new CivitasView(); //punto 1
        Dado.createInstance(view); //punto 2
        
        CapturaNombres screen = new CapturaNombres(view, true); //punto 3
        ArrayList<String> nombres = screen.getNombres(); //punto 4
        
        CivitasJuego game = new CivitasJuego(nombres); //punto 5
        Controlador controlador = new Controlador(game, view); //punto 6
        
        view.setCivitasJuego(game); //punto 8
        
        //view.actualizarVista();
          
        controlador.juega(); //comprobar que funciona
    }
}
