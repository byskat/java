package ex1.java.bbdd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Equips que fa d'intermediari entre la conexió de bases de dades i l'interficie
 * @author Víctor Alarcón
 */
public final class Equips {
    
    //On es guarden tots els continguts.
    private ResultSet rs;

    /**
     * Constructora que executa una funcio que omple el resut set amb totes les
     * dades de la base de dades que troba.
     * @throws SQLException
     */
    public Equips() throws SQLException {
        this.rs = obtenirEquips();
    }
    
    /**
     * Funció que retorna les dades en forma d'objecte equip les dades del cursor
     * del resultset.
     * @return
     * @throws SQLException
     */
    public Equip actual() throws SQLException{
        Equip eq=null;
        if (!rs.isBeforeFirst() && !rs.isAfterLast()){
            eq= new Equip();
            eq.setEq_id(rs.getInt(Equip.E_ID));
            eq.setEq_nom(rs.getString(Equip.E_NOM));
            eq.setEstadi(rs.getString(Equip.E_ESTADI));
            eq.setPoblacio(rs.getString(Equip.E_POBLACIO));
            eq.setCod_postal(rs.getString(Equip.E_COD_POSTAL));
        }
        return eq;
    }
    
    /**
     * Avança al primer valor del recordset.
     * @return
     * @throws SQLException
     */
    public Equip primer() throws SQLException{
        this.rs.first();
        return actual();
    }
    
    /**
     * Avança a l'ultim valor del recordset.
     * @return
     * @throws SQLException
     */
    public Equip ultim() throws SQLException{
        this.rs.last();
        return actual();
    }
    
    /**
     * Va a l'anterior, si hi es, si queda.
     * @return
     * @throws SQLException
     */
    public Equip anterior() throws SQLException{
        if(!rs.isFirst()&&!rs.isBeforeFirst()) this.rs.previous();
        return actual();
    }
    
    /**
     * Va al seguent, si hi es, si queda.
     * @return
     * @throws SQLException
     */
    public Equip seguent() throws SQLException{
        if(!rs.isLast()&&!rs.isAfterLast()) this.rs.next();
        return actual();
    }
    
    /**
     * Rep un id i busca en el recordset la id que coincideix.
     * @param id
     * @return
     * @throws SQLException
     */
    public Equip buscar(int id) throws SQLException{
        boolean bTrobat=false;
        rs.first();
        Equip res = null;
        while(!bTrobat && rs.next()){
            if(rs.getInt(Equip.E_ID)==id){
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
     * Retorna un recordset amb totes les dades de la equips de la bbdd.
     * @return
     * @throws SQLException
     */
    public ResultSet obtenirEquips() throws SQLException{
        String sql = Equip.createQuery();
        return JavaConnection.selector(sql);
    }
    
    /**
     * Retorna l'equip que coincideix amb aquesta id a la base de dades.
     * @param id
     * @return
     * @throws SQLException
     */
    public ResultSet obtenirEquips(int id) throws SQLException{
        String[] params = {Equip.E_ID};
        String sql = Equip.createQuery(params);
        PreparedStatement pstmt = JavaConnection.getConnection().prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        pstmt.setInt(1, id);
        
        return JavaConnection.selector(pstmt);
    }
    
    /**
     * Afegeix al record set/base de dades l'objecte equip passat.
     * @param eq
     * @throws SQLException
     */
    public void afegirEquip(Equip eq) throws SQLException{
    
        rs.moveToInsertRow();

        rs.updateString(Equip.E_NOM, eq.getEq_nom());
        rs.updateString(Equip.E_ESTADI, eq.getEstadi());
        rs.updateString(Equip.E_POBLACIO, eq.getPoblacio());
        rs.updateString(Equip.E_COD_POSTAL, eq.getCod_postal());
        rs.insertRow();
    }
    
    /**
     * Elimina l'equip que es troba al cursor del result set.
     * @throws SQLException
     */
    public void eliminarEquip() throws SQLException{
        if (this.actual()!=null){
            rs.moveToCurrentRow();
            rs.deleteRow();
        }
    }
    
    /**
     * Elimina de la base de dades/recordset l'objecte que tingui la id passada.
     * @param id
     * @throws SQLException
     */
    public void eliminarEquip(int id) throws SQLException{
        this.buscar(id);
        this.eliminarEquip();
    }
}