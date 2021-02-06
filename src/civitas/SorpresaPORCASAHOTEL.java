/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package civitas;

import java.util.ArrayList;

/**
 *
 * @author noelia
 */
public class SorpresaPORCASAHOTEL extends Sorpresa{
    
    SorpresaPORCASAHOTEL(int valor, String texto) {
        super(valor, texto);
    }
    
    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos){ // DONE!        
        if (jugadorCorrecto(actual, todos)) {
            Jugador jugador = todos.get(actual);
            informe(actual,todos);
            jugador.modificarSaldo(valor*jugador.getCasasPorHotel());
        }
    }
    
    @Override
    public String toString(){
        return super.toString() + "\n Tipo Sorpresa: PORCASAHOTEL";
    }
}
