package ex1.java.bbdd;

public class Jugador {
    
    public static final String J_TAULA = "JUGADORS";
    
    public static final String J_ID = "JUG_ID";
    public static final String J_EQ_ID = "EQ_ID";
    public static final String J_NOM = "JUG_NOM";
    public static final String J_DORSAL = "DORSAL";
    public static final String J_EDAT = "EDAT";
    
    public static String SELECT_ALL = "SELECT "+J_ID +", "+J_EQ_ID+", "+J_NOM+", "+J_DORSAL+", "+J_EDAT+" FROM "+J_TAULA; 
    
    private int jug_id;
    private int eq_id;
    private String jug_nom;
    private int dorsal;
    private int edat;

    public Jugador(int jug_id, int eq_id, String jug_nom, int dorsal, int edat) {
        this.jug_id = jug_id;
        this.eq_id = eq_id;
        this.jug_nom = jug_nom;
        this.dorsal = dorsal;
        this.edat = edat;
    }
    
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

    @Override
    public String toString() {
        return "Jugador{" + "jug_id=" + jug_id + ", eq_id=" + eq_id + ", jug_num=" + jug_nom + ", dorsal=" + dorsal + ", edat=" + edat + '}';
    }
    
    
}
