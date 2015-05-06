package ex1.java.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * Classe que conté les funcións generiques de conexió i execució de sql's i 
 * statements a la base de dades. Al essèr generica la utilitzen les dos classes
 * jugadors i equips.
 * @author Víctor Alarcón Serrano
 */
public class JavaConnection {
    
    //L'unic atribut de la classe, conté la conexió a la base de dades, aixó es
    //així per que puguem accedir (des dels seus metodes) sense obrir noves conexions.
    private static Connection conn;
    
    /**
     * Funció que es conecta a la base de dades si no hi ha fet abans i si ho ha
     * fet retorna conn.
     * @return
     */
    public static Connection getConnection(){
        
        if(conn==null){
            try{
                Class.forName("oracle.jdbc.OracleDriver");
                //Obre la connexió.
                conn=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:3521:XE","cirv","1234");
            } catch (ClassNotFoundException | SQLException ex){
                JOptionPane.showMessageDialog(null, ex);
            }    
        }
        return conn;
        
    }
    
    /**
     * Tenca la conexio conn.
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException{
        JavaConnection.getConnection().close();
    }
    
    /**
     * Funció que rep un sql i l'executa. Retorna un resultset.
     * @param query
     * @return
     * @throws SQLException 
     */
    private static ResultSet executeQuerySeleccio(String query) throws SQLException{
        Statement select = null;
        ResultSet rs = null;
       
        try{
            select = JavaConnection.getConnection().createStatement();
            rs = select.executeQuery(query);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }
        return rs;
    }
    
    /**
     * Funció que executa una PreparedSteatment de selecció. I retorna un resultset.
     * @param st
     * @return
     */
    public static ResultSet selector(PreparedStatement st){
        ResultSet rs = null;
        
        try{
            rs = st.executeQuery();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }   
        
        return rs;
    }
    
    /**
     * Funció que executa un sql de selecció. I retorna un resultset.
     * @param sql
     * @return
     */
    public static ResultSet selector(String sql){
        Statement st = null;
        ResultSet rs = null;
        
        try{
            st = (Statement) getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs = st.executeQuery(sql);
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }   
        
        return rs;
    }
    
}
