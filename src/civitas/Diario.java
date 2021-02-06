/**
 * ALMACENA LOS ELEMENTOS PENDIENTES DE SER CONSULTADOS O LEÍDOS
 * @author profesores PDOO
 */

package civitas;

import java.util.ArrayList;

public class Diario {
  
    //es un singleton. la propia clase almacena la referencia a la única instancia. 
    static final private Diario instance = new Diario();

    private ArrayList<String> eventos;

    //constructor privado para evitar que se puedan crear mas instancias
    public Diario () {
      eventos = new ArrayList<>();
    }

    // método de clase para obtener la instancia
    static public Diario getInstance() {
      return instance;
    }    
    
    void ocurreEvento (String e) {
      eventos.add (e);
    }

    public boolean eventosPendientes () {
      return !eventos.isEmpty();
    }

    public String leerEvento () {
      String salida = "";
      if (!eventos.isEmpty()) {
        salida = eventos.remove(0);
      }
      return salida;
    }
}

//para obtener la unica instancia del diario será Diario.getInstance();