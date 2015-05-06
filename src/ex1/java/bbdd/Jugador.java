package ex1.java.bbdd;

/**
 * Classe que crea jugadors, conté les dades de la taula Jugadors per poder tractar-les.
 * @author valarcon
 */
public class Jugador {
    
    /*
    Constants que ens donen els noms dels camps i de les taules.
    */
    public static final String J_TAULA = "JUGADORS";
    
    public static final String J_ID = "JUG_ID";
    public static final String J_EQ_ID = "EQ_ID";
    public static final String J_NOM = "JUG_NOM";
    public static final String J_DORSAL = "DORSAL";
    public static final String J_EDAT = "EDAT";
    
    public static String SELECT_ALL = "SELECT "+J_ID +", "+J_EQ_ID+", "+J_NOM+", "+J_DORSAL+", "+J_EDAT+" FROM "+J_TAULA; 
    
    /*
    Atributs de la classe jugador.
    */
    private int jug_id;
    private int eq_id;
    private String jug_nom;
    private int dorsal;
    private int edat;

    /**
     * Classe que rep tots els parametres a exepció del id, aquest s'auto genera
     * a la base de dades.
     * @param eq_id
     * @param jug_nom
     * @param dorsal
     * @param edat
     */
    public Jugador(int eq_id, String jug_nom, int dorsal, int edat) {
        this.jug_id = -1;
        this.eq_id = eq_id;
        this.jug_nom = jug_nom;
        this.dorsal = dorsal;
        this.edat = edat;
    }
    
    /**
     * Constructora que no rep cap parametre, posa per defecte el contingut.
     */
    public Jugador() {
        this.jug_id = -1;
        this.eq_id = -1;
        this.jug_nom = null;
        this.dorsal = 0;
        this.edat = 0;
    }
    
    public int getJug_id() {
        return jug_id;
    }

    public void setJug_id(int jug_id) {
        this.jug_id = jug_id;
    }

    public int getEq_id() {
        return eq_id;
    }

    public void setEq_id(int eq_id) {
        this.eq_id = eq_id;
    }

    public String getJug_nom() {
        return jug_nom;
    }

    public void setJug_nom(String jug_nom) {
        this.jug_nom = jug_nom;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    /**
     * Funcio que crea l'sql que seleciona tot el contingut.
     * @return
     */
    public static String createQuery(){
        return Jugador.SELECT_ALL;
    }

    /**
     * Rep els parametres de where, retorna sql.
     * @param where
     * @return
     */
    public static String createQuery(String[] where){
        String st = Jugador.SELECT_ALL+" WHERE ";
        for(int i = 0; i<where.length;i++){
            st += where[i]+"= ?";
            if(where.length>1) st+=" AND ";
        }
        
        return st;
    }
    
    /**
     * Rep el camp de selecio i el where.
     * @param select
     * @param where
     * @return
     */
    public static String createQuery(String select, String[] where){
        String st = "Select "+select+" FROM "+Jugador.J_TAULA+" WHERE ";
        for(int i = 0; i<where.length;i++){
            st += where[i]+"= ?";
            if(where.length>1&&i!=where.length-1) st+=" AND ";
        }
        
        return st;
    }
    
    @Override
    public String toString() {
        return "Jugador{" + "jug_id=" + jug_id + ", eq_id=" + eq_id + ", jug_num=" + jug_nom + ", dorsal=" + dorsal + ", edat=" + edat + '}';
    }
    
    
}
