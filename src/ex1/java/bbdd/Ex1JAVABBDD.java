package ex1.java.bbdd;

import java.sql.SQLException;

/**
 *
 * @author Víctor Alarcón
 */
public class Ex1JAVABBDD {

    /**
     * Area de main, on s'executen totes les funcions.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        
        
        //Equips cosa = new Equips();
        Jugadors cosa2 = new Jugadors();
        
        //System.out.println(cosa2.primer().toString());
        
        
        //System.out.println(cosa2.buscar(2).toString());
        
        //Jugador jus = new Jugador(3,"Cosa",34,34);
        
        //System.out.println(cosa2.primer().toString());
        //System.out.println(cosa2.seguent().toString());
        //System.out.println(cosa2.seguent().toString());
        //System.out.println(cosa2.anterior().toString());
        //System.out.println(cosa2.anterior().toString());
        //System.out.println(cosa2.anterior().toString());
        
        
        //cosa2.afegirJugador(jus);
        
        //System.out.println(cosa2.ultim().toString());
        
        //Equip eq = new Equip("Impressive Team","Impressive Stadium","Impressive City","08500");
        
        //cosa.afegirEquip(eq);
        //cosa2.eliminarJugador(5);
        
        cosa2.obtenirJugadorsByEquip(3);
        System.out.println(cosa2.primer().toString());
        System.out.println(cosa2.seguent().toString());
        System.out.println(cosa2.seguent().toString());
        
        
    }
    
}
