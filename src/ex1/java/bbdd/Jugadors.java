package ex1.java.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

/**
 *
 * @author Víctor Alarcón
 */
public final class Jugadors {
    
    private ResultSet rs;

    public Jugadors() throws SQLException {
        this.rs = obtenirJugadors();
    }
    
    public Jugador actual() throws SQLException{
        Jugador ju=null;
        if (!rs.isBeforeFirst() && !rs.isAfterLast()){
            ju = new Jugador();
            ju.setJug_id(rs.getInt(Jugador.J_ID));
            ju.setEq_id(rs.getInt(Jugador.J_EQ_ID));
            ju.setJug_nom(rs.getString(Jugador.J_NOM));
            ju.setDorsal(rs.getInt(Jugador.J_DORSAL));
            ju.setEdat(rs.getInt(Jugador.J_EDAT));
        }
        return ju;
    }
    
    public Jugador primer() throws SQLException{
        this.rs.first();
        return actual();
    }
    
    public Jugador ultim() throws SQLException{
        this.rs.last();
        return actual();
    }
    
    public Jugador anterior() throws SQLException{
        if(!rs.isFirst()&&!rs.isBeforeFirst()) this.rs.previous();
        return actual();
    }
    
    public Jugador seguent() throws SQLException{
        if(!rs.isLast()&&!rs.isAfterLast()) this.rs.next();
        return actual();
    }
    
    public Jugador buscar(int id) throws SQLException{
        boolean bTrobat=false;

        rs.first();
        Jugador res = null;
        
        while(rs.next() && !bTrobat){
            if(rs.getInt(Jugador.J_ID)==id){
                res = this.actual();
                bTrobat=true;
            }
        }
        
        if(bTrobat){
            rs.previous();
        }
        
        return res;
    }
  
    public ResultSet obtenirJugadors() throws SQLException{
        String sql = Jugador.createQuery();
        return JavaConnection.selector(sql);
    }
    
    public ResultSet obtenirJugadors(int id) throws SQLException{
        String[] params = {Jugador.J_ID};
        String sql = Jugador.createQuery(params);
        PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, id);
        
        return JavaConnection.selector(pstmt);
    }
    
    public void afegirJugador(Jugador ju) throws SQLException{
    
        rs.moveToInsertRow();

        rs.updateInt(Jugador.J_EQ_ID, ju.getEq_id());
        rs.updateString(Jugador.J_NOM, ju.getJug_nom());
        rs.updateInt(Jugador.J_DORSAL, ju.getDorsal());
        rs.updateInt(Jugador.J_EDAT, ju.getEdat());
        rs.insertRow();
    }
    
    public void eliminarJugador() throws SQLException{
        if (this.actual()!=null){
            rs.moveToCurrentRow();
            rs.deleteRow();
        }
    }
    
    public void eliminarJugador(int id) throws SQLException{
        this.buscar(id);
        this.eliminarJugador();
    }
}
