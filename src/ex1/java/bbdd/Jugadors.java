package ex1.java.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Equips que fa d'intermediari entre la conexió de bases de dades i l'interficie
 * @author Víctor Alarcón
 */
public final class Jugadors {
    
    //On es guarden tots els continguts.
    private ResultSet rs;

    /**
     * Constructora que executa una funcio que omple el result set amb totes les
     * dades de la base de dades que troba.
     * @throws SQLException
     */
    public Jugadors() throws SQLException {
        this.rs = obtenirJugadors();
    }
    
    /**
     * Funció que retorna les dades en forma d'objecte equip les dades del cursor
     * del resultset.
     * @return
     * @throws SQLException
     */
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
    
    /**
     * Avança al primer valor del recordset.
     * @return
     * @throws SQLException
     */
    public Jugador primer() throws SQLException{
        this.rs.first();
        return actual();
    }
    
    /**
     * Avança a l'ultim valor del recordset.
     * @return
     * @throws SQLException
     */
    public Jugador ultim() throws SQLException{
        this.rs.last();
        return actual();
    }
    
    /**
     * Va a l'anterior, si hi es, si queda.
     * @return
     * @throws SQLException
     */
    public Jugador anterior() throws SQLException{
        if(!rs.isFirst()&&!rs.isBeforeFirst()) this.rs.previous();
        return actual();
    }
    
    /**
     * Va al seguent, si hi es, si queda.
     * @return
     * @throws SQLException
     */
    public Jugador seguent() throws SQLException{
        if(!rs.isLast()&&!rs.isAfterLast()) this.rs.next();
        return actual();
    }
    
    /**
     * Rep un id i busca en el recordset la id que coincideix.
     * @param id
     * @return
     * @throws SQLException
     */
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

    /**
     * Metode que fa la consulta de seleció a la base de dades i que un cop ho rep
     * ho volca a un resutset.
     * @return
     * @throws SQLException
     */
    public ResultSet obtenirJugadors() throws SQLException{
        String sql = Jugador.createQuery();
        return JavaConnection.selector(sql);
    }
    
    /**
     * Metode que fa la consulta de selecció i filtra amb una id, un cop ho rep 
     * ho volca en un result set.
     * @param id
     * @return
     * @throws SQLException
     */
    public ResultSet obtenirJugadors(int id) throws SQLException{
        String[] params = {Jugador.J_ID};
        String sql = Jugador.createQuery(params);
        PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, id);
        
        return JavaConnection.selector(pstmt);
    }
    
    /**
     * Metode que busca a la base dades els jugadors de l'equip que es vol mostrar.
     * Omple el result set.
     * @param id
     * @throws SQLException
     */
    public void obtenirJugadorsByEquip(int id) throws SQLException{
        String[] params = {Jugador.J_EQ_ID};
        String sql = Jugador.createQuery(params);
        PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, id);
        
        this.rs = JavaConnection.selector(pstmt);
    }
    
    /**
     * Metode que rep un jugador i l'omple al resultset/bbdd.
     * @param ju
     * @throws SQLException
     */
    public void afegirJugador(Jugador ju) throws SQLException{
    
        rs.moveToInsertRow();

        rs.updateInt(Jugador.J_EQ_ID, ju.getEq_id());
        rs.updateString(Jugador.J_NOM, ju.getJug_nom());
        rs.updateInt(Jugador.J_DORSAL, ju.getDorsal());
        rs.updateInt(Jugador.J_EDAT, ju.getEdat());
        rs.insertRow();
    }
    
    /**
     * Metode que elimina del resultset/bbdd el jugador al qual esta apuntant el
     * cursor.
     * @throws SQLException
     */
    public void eliminarJugador() throws SQLException{
        if (this.actual()!=null){
            rs.moveToCurrentRow();
            rs.deleteRow();
        }
    }
    
    /**
     * Metode que rep una id de jugador, el busca en el resultset i l'elimina.
     * @param id
     * @throws SQLException
     */
    public void eliminarJugador(int id) throws SQLException{
        this.buscar(id);
        this.eliminarJugador();
    }
}
