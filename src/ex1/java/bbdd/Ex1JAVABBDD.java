package ex1.java.bbdd;

import java.sql.SQLException;

/**
 *
 * @author Víctor Alarcón
 */
public class Ex1JAVABBDD {

    public static void main(String[] args) throws SQLException {

        /*
        System.out.println("-- MOSTREM EQUIPS --");
        JavaConnection.viewEquips();
        
        JavaConnection.closeConnection();
    
        String[] params = {Equip.E_ID,Equip.E_ESTADI};
        System.out.println(Equip.createQuery(params));
               */
        
        Equips cosa = new Equips();
        
        System.out.println(cosa.primer().toString());
        System.out.println(cosa.seguent().toString());
        System.out.println(cosa.seguent().toString());
        System.out.println(cosa.anterior().toString());
        System.out.println(cosa.anterior().toString());
        System.out.println(cosa.anterior().toString());
        
    }
    
}
