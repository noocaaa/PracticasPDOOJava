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
public class SorpresaPAGARCOBRAR extends Sorpresa{
      
    SorpresaPAGARCOBRAR(int valor, String texto){
        super(valor, texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ // DONE!        
        Jugador jugador = todos.get(actual);

        if (super.jugadorCorrecto(actual, todos)) {
            super.informe(actual, todos);
            jugador.modificarSaldo(valor);
        }
    }
    
    @Override
    public String toString(){ // DONE!
        return super.toString() + "\n Tipo Sorpresa: PAGARCOBRAR";
    }
    
}
