package ex1.java.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Víctor Alarcón
 */
public final class Equips {
    
    private ResultSet rs;

    public Equips() throws SQLException {
        this.rs = obtenirEquips();
    }
    
    public Equip actual() throws SQLException{
        Equip eq = new Equip();
        eq.setEq_id(rs.getInt(Equip.E_ID));
        eq.setEq_nom(rs.getString(Equip.E_NOM));
        eq.setEstadi(rs.getString(Equip.E_ESTADI));
        eq.setPoblacio(rs.getString(Equip.E_POBLACIO));
        eq.setCod_postal(rs.getString(Equip.E_COD_POSTAL));
        
        return eq;
    }
    
    public Equip primer() throws SQLException{
        this.rs.first();
        return actual();
    }
    
    public Equip ultim() throws SQLException{
        this.rs.last();
        return actual();
    }
    
    public Equip anterior() throws SQLException{
        if(!rs.isFirst()) this.rs.previous();
        return actual();
    }
    
    public Equip seguent() throws SQLException{
        if(!rs.isLast()) this.rs.next();
        return actual();
    }
    
    public ResultSet obtenirEquips() throws SQLException{
        String sql = Equip.createQuery();
        //PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql);
        return JavaConnection.selector(sql);
    }
    
    public ResultSet obtenirEquips(int id) throws SQLException{
        String[] params = {Equip.E_ID};
        String sql = Equip.createQuery();
        PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, id);
        
        return JavaConnection.selector(pstmt);
    }
    
}
