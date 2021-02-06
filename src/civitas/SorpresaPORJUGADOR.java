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
public class SorpresaPORJUGADOR extends Sorpresa{

    SorpresaPORJUGADOR(int valor, String texto) {
        super(valor, texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ // DONE! 
        Jugador jugador = todos.get(actual);

        if (super.jugadorCorrecto(actual, todos)) {
            Sorpresa uno, dos;
            
            super.informe(actual,todos);
                        
            uno = new SorpresaPAGARCOBRAR(valor*-1, "Cobra");
            dos = new SorpresaPAGARCOBRAR(valor*(todos.size()-1), "Paga");
            
            for (int i=0; i < todos.size(); i++) {
                if (i != actual){ //si no son iguales
                    uno.aplicarAJugador(i, todos);
                } else {
                    dos.aplicarAJugador(i, todos);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return super.toString() + "\n Tipo Sorpresa: PORJUGADOR";
    }
}
