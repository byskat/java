package ex1.java.bbdd;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Equip {
    
    public static final String E_TAULA = "EQUIPS";
    
    public static final String E_ID = "EQ_ID";
    public static final String E_NOM = "EQ_NOM";
    public static final String E_ESTADI = "ESTADI";
    public static final String E_POBLACIO = "POBLACIO";
    public static final String E_COD_POSTAL = "COD_POSTAL";
    
    public static String SELECT_ALL = "SELECT "+E_ID +", "+E_NOM+", "+E_ESTADI+", "+E_POBLACIO+", "+E_COD_POSTAL+" FROM "+E_TAULA;
        
    private int eq_id;
    private String eq_nom;
    private String estadi;
    private String poblacio;
    private String cod_postal;

    public Equip(int eq_id, String eq_nom, String estadi, String poblacio, String cod_postal) {
        this.eq_id = eq_id;
        this.eq_nom = eq_nom;
        this.estadi = estadi;
        this.poblacio = poblacio;
        this.cod_postal = cod_postal;
    }
    
    public Equip() {
        this.eq_id = -1;
        this.eq_nom = null;
        this.estadi = null;
        this.poblacio = null;
        this.cod_postal = null;
    }
    
    
    public int getEq_id() {
        return eq_id;
    }

    public void setEq_id(int eq_id) {
        this.eq_id = eq_id;
    }

    public String getEq_nom() {
        return eq_nom;
    }

    public void setEq_nom(String eq_nom) {
        this.eq_nom = eq_nom;
    }

    public String getEstadi() {
        return estadi;
    }

    public void setEstadi(String estadi) {
        this.estadi = estadi;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(String cod_postal) {
        this.cod_postal = cod_postal;
    }
    
    public static String createQuery(){
        return Equip.SELECT_ALL;
    }

    public static String createQuery(String[] where){
        String st = Equip.SELECT_ALL+" WHERE ";
        for(int i = 0; i<where.length;i++){
            st += where[i]+"= ?";
            if(where.length>1) st+=" AND ";
        }
        
        return st;
    }

    public static String createQuery(String select, String[] where){
        String st = "Select "+select+" FROM "+Equip.E_TAULA+" WHERE ";
        for(int i = 0; i<where.length;i++){
            st += where[i]+"= ?";
            if(where.length>1&&i!=where.length-1) st+=" AND ";
        }
        
        return st;
    }

    @Override
    public String toString() {
        return "Equip{" + "eq_id=" + eq_id + ", eq_nom=" + eq_nom + ", estadi=" + estadi + ", poblacio=" + poblacio + ", cod_postal=" + cod_postal + '}';
    }
}