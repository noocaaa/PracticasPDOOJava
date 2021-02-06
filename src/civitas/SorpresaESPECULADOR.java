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
public class SorpresaESPECULADOR extends Sorpresa{
        
    SorpresaESPECULADOR(int valor, String texto){   
        super(valor, texto);
    }

    @Override
    void aplicarAJugador(int actual, ArrayList<Jugador> todos) {
        if(super.jugadorCorrecto(actual, todos)){
            super.informe(actual, todos);
            
            jugadorEspeculador nuevo = new jugadorEspeculador(todos.get(actual),valor);
            todos.remove(todos.get(actual));
            todos.add(actual, nuevo);
        }
    }
    
    @Override
    public String toString() {
        return "\n" + super.toString() + "\nTipo Sorpresa: SorpresaESPECULADOR";
    }
}
