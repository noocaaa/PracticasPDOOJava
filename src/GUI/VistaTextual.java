/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import civitas.Casilla;
import civitas.CivitasJuego;
import civitas.Diario;
import civitas.Jugador;
import civitas.OperacionesJuego;
import civitas.SalidasCarcel;
import civitas.TituloPropiedad;

class VistaTextual {
  
  CivitasJuego juegoModel; 
  int iGestion=-1;
  int iPropiedad=-1;
  private static String separador = "=====================";
  
  private Scanner in;
  
  VistaTextual () { //HECHO POR PROFESORES
    in = new Scanner (System.in);
  }
  
  void mostrarEstado(String estado) { //HECHO POR PROFESORES
    System.out.println (estado);
  }
              
  void pausa() { //HECHO POR PROFESORES
    System.out.print ("Pulsa una tecla");
    in.nextLine();
  }

  int leeEntero (int max, String msg1, String msg2) { //HECHO POR PROFESORES
    Boolean ok;
    String cadena;
    int numero = -1;
    do {
      System.out.print (msg1);
      cadena = in.nextLine();
      try {  
        numero = Integer.parseInt(cadena);
        ok = true;
      } catch (NumberFormatException e) { // No se ha introducido un entero
        System.out.println (msg2);
        ok = false;  
      }
      if (ok && (numero < 0 || numero >= max)) {
        System.out.println (msg2);
        ok = false;
      }
    } while (!ok);

    return numero;
  }

  int menu (String titulo, ArrayList<String> lista) { //HECHO POR PROFESORES
    String tab = "  ";
    int opcion;
    System.out.println (titulo);
    for (int i = 0; i < lista.size(); i++) {
      System.out.println (tab+i+"-"+lista.get(i));
    }

    opcion = leeEntero(lista.size(),
                          "\n"+tab+"Elige una opción: ",
                          tab+"Valor erróneo");
    return opcion;
  }

  SalidasCarcel salirCarcel() { //HECHO POR PROFESORES
    int opcion = menu ("Elige la forma para intentar salir de la carcel",
      new ArrayList<> (Arrays.asList("Pagando","Tirando el dado")));
    return (SalidasCarcel.values()[opcion]);
  }

  Respuestas comprar() { //DONE!
    int opcionescogida = menu("¿Desea comprar la calle?", new ArrayList<>(Arrays.asList("Si", "No")));
    return Respuestas.values()[opcionescogida];  
  }

  void gestionar () { //DONE!
    ArrayList<String> propiedades = new ArrayList<String>();
    
   for(TituloPropiedad titulos : juegoModel.getJugadorActual().getPropiedades()){
       propiedades.add(titulos.getNombre());
   }

   iGestion = menu("¿Qué gestión inmobiliaria desea hacer?", new ArrayList<> (Arrays.asList("Vender","Hipotecar", "Cancelar Hipoteca", "Construir Casa", "Construir hotel", "Terminar")));

    if(iGestion != 5 && !propiedades.isEmpty()){
        iPropiedad = menu("¿Qué propiedad desea gestionar?", propiedades);
    }     
  }
  
  public int getGestion(){return iGestion;} //DONE!
  
  public int getPropiedad(){return iPropiedad;} //DONE!
    

  void mostrarSiguienteOperacion(OperacionesJuego operacion) { //DONE!
      if (operacion == OperacionesJuego.AVANZAR) {
          System.out.println("La siguiente operación es avanzar");
      } else if (operacion == OperacionesJuego.COMPRAR) {
          System.out.println("La siguiente operación es comprar");
      } else if (operacion == OperacionesJuego.GESTIONAR) {
          System.out.println("La siguiente operación es gestionar");
      } else if (operacion == OperacionesJuego.PASAR_TURNO) {
          System.out.println("La siguiente operación es pasar turno");
      } else {
          System.out.println("La siguiente operación es salir de la cárcel");          
      }
      
  }


  void mostrarEventos() { //DONE!
      while (Diario.getInstance().eventosPendientes()) {
        System.out.println(Diario.getInstance().leerEvento());
      }
  }
  
  public void setCivitasJuego(CivitasJuego civitas){ //HECHO POR PROFESORES
        juegoModel=civitas;
        this.actualizarVista();

    }
  
  void actualizarVista(){ //DONE!
    Casilla casilla = juegoModel.getCasillaActual();
    Jugador jugadorActual = juegoModel.getJugadorActual();
    System.out.println("\n" + casilla.toString() + "\n " + jugadorActual.toString() + "\n\n");
  } 
}