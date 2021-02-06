package civitas;

import java.util.ArrayList;
import java.util.Collections; //para mezclar las cartas

/**
 * Representa el mazo de las cartas sopresas.
 * @author noelia
 */
public class MazoSorpresas {
    
    //atributos de instancia privados
    private ArrayList<Sorpresa> sorpresas; //almacenar las cartas Sorpresas.
    private boolean barajada; //para saber si ha sido barajado o no.
    private int usadas; //para contar numero de cartas del mazo que ya han sido usadas
    private boolean debug; //activar/desactivar el modo depuración. Activo mazo no baraja, permetiendo obtener sorpresas siempre en el mismo orden en que se añaden.
    private ArrayList<Sorpresa> cartasEspeciales; //almacenará la carta sorpresa del tipo SALIRCARCEL mientras se considere retirada del mazo (cuando este en posición de un jugador)
    private Sorpresa ultimaSorpresa; //para guardar la ultima sorpresa que ha salido
    
    private void init() {
        sorpresas = new ArrayList<>();
        cartasEspeciales = new ArrayList<>(); //contenedor vacío
        barajada = false;
        usadas = 0;
    }
    
    MazoSorpresas(boolean debug) {
        init();
        this.debug = debug;
        if (debug) {
            Diario.getInstance().ocurreEvento("Modo debug activado");
        }
    }
    
    MazoSorpresas() {
        init();
        debug = false;
    }
    
    void alMazo (Sorpresa s) {
        if (!barajada) {
            sorpresas.add(s);
        }
    }
    
    Sorpresa Siguiente () {
        
        if ((!barajada || usadas == sorpresas.size()) && !debug)  {
            Collections.shuffle(sorpresas);
            usadas=0;
            barajada=true;
        }
        
        usadas++;
        ultimaSorpresa =  sorpresas.get(0);
        sorpresas.remove(0);
        sorpresas.add(sorpresas.size()-1, ultimaSorpresa);
        return ultimaSorpresa;
    }
    
    void inhabilitarCartaEspecial(Sorpresa sorpresa) {
        for (int i=0; i < sorpresas.size(); i++){
            if (sorpresas.get(i) == sorpresa) {
                sorpresas.remove(i); 
                cartasEspeciales.add(sorpresa);
                Diario.getInstance().ocurreEvento("Se ha inhabilitado la Carta Especial");
            }
        }
    } 
    
    void habilitarCartaEspecial (Sorpresa sorpresa) {
        for (int i=0; i < cartasEspeciales.size(); i++){
            if (cartasEspeciales.get(i) == sorpresa) {
                cartasEspeciales.remove(i); 
                sorpresas.add(sorpresa);    
                Diario.getInstance().ocurreEvento("Se ha habilitado la Carta Especial");
            }
        }       
        
    }
    
    Sorpresa getUltimaSorpresa(){
        return ultimaSorpresa;
    }
}
