package ex1.java.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Víctor Alarcón Serrano
 */
public class JavaConnection {
    
    private static Connection conn;
    
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
    
    public static void closeConnection() throws SQLException{
        JavaConnection.getConnection().close();
    }
    
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
    
    
    public static void viewEquips() throws SQLException{
        
        ResultSet rs = executeQuerySeleccio("Select * from equips");
        
        while(rs.next()){
            Equip eq = new Equip();
            eq.setEq_id(rs.getInt(1));
            eq.setEq_nom(rs.getString(2));
            eq.setEstadi(rs.getString(3));
            eq.setPoblacio(rs.getString(4));
            eq.setCod_postal(rs.getString(5));
            
            System.out.println(eq.toString());   
        }
        rs.close();
    }
    
    //Funció que executa una PreparedSteatment de selecció. I retorna un resultset.
    public static ResultSet selector(PreparedStatement st){
        ResultSet rs = null;
        
        try{
            rs = st.executeQuery();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
        }   
        
        return rs;
    }
    
    //Funció que executa un sql de selecció. I retorna un resultset.
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
