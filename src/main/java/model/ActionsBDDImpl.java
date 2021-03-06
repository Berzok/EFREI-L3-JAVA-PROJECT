package model;

import utils.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionsBDDImpl {

    private TreeMap<Integer, ProgrammeurBean> listeProg = new TreeMap<>();
    private ProgrammeurBean prog;
    private ActionsBDD action = new ActionsBDD();
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public ActionsBDDImpl() {
        this.conn = this.action.getConnection();
    }

    /**
     * Initialisation d'un programmeur
     * @param rs
     * @return
     */
    public ProgrammeurBean initProgrameur(ResultSet rs) {
        try {
            prog = new ProgrammeurBean();
            prog.setId(rs.getInt("ID"));
            prog.setPrenom(rs.getString("PRENOM"));
            prog.setAnNaissance(Integer.parseInt(rs.getString("ANNAISSANCE")));
            prog.setNom(rs.getString("NOM"));
            prog.setPrime(Float.parseFloat(rs.getString("PRIME")));
            prog.setPseudo(rs.getString("PSEUDO"));
            prog.setSalaire(Float.parseFloat(rs.getString("SALAIRE")));
            prog.setAdresse(rs.getString("ADRESSE"));
            prog.setResponsable(rs.getString("RESPONSABLE"));
            prog.setHobby(rs.getString("HOBBY"));
        } catch (SQLException ex) {
            Logger.getLogger(ActionsBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prog;
    }

    /**
     * M�thode d'ex�cution d'une requ�te pr�par�e
     * @param statement
     * @return
     */
    public TreeMap<Integer, ProgrammeurBean> doRequete(PreparedStatement statement) {
        this.listeProg.clear();
        try {
            this.stmt = statement;
            this.rs = this.action.getResultSet(this.stmt);

            while (this.rs.next()) {
                this.listeProg.put(this.rs.getInt("ID"), initProgrameur(this.rs));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ActionsBDD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return this.listeProg;
    }

    /**
     * M�thode d'ex�cution d'une requ�te pr�par�e avec modification dans la base de donn�es
     * @param statement
     * @return
     */
    public int doRequeteUpdate(PreparedStatement statement) {
        this.listeProg.clear();
        this.stmt = statement;

        return this.action.getResultSetModify(this.stmt);
    }

    /**
     * Ex�cution de la requ�te retournant tous les programmeurs
     * @return
     */
    public TreeMap<Integer, ProgrammeurBean> getProgrammeurs() {
        return doRequete(this.action.getPreparedStatement(this.conn, Constantes.ALLPROGS));
    }

    /**
     * Ex�cution de la requ�te retournant un programmeur grace � son id
     * @param id
     * @return
     */
    public TreeMap<Integer, ProgrammeurBean> getProgrammeurById(int id) {
        return doRequete(this.action.getPreparedStatementInt(this.conn, Constantes.PROGBYID, id));
    }

    /**
     * Ex�cution de la requ�te retournant un ou des programmeurs par leur nom
     * @param name
     * @return
     */
    public TreeMap<Integer, ProgrammeurBean> getProgrammeurByName(String name) {
        return doRequete(this.action.getPreparedStatementString(this.conn, Constantes.PROGBYNAME, name));
    }

    /**
     * Ex�cution de la requ�te retournant un ou des programmeurs par leur pr�nom
     * @param firstName
     * @return
     */
    public TreeMap<Integer, ProgrammeurBean> getProgrammeurByFirstName(String firstName) {
        return doRequete(this.action.getPreparedStatementString(this.conn, Constantes.PROGBYFIRSTNAME, firstName));
    }

    /**
     * Ex�cution de la requ�te retournant un ou des programmeurs par leur ann�e de naissance
     * @param year
     * @return
     */
    public TreeMap<Integer, ProgrammeurBean> getProgrammeurByYear(Integer year) {
        return doRequete(this.action.getPreparedStatementString(this.conn, Constantes.PROGBYYEAR, Integer.toString(year)));
    }

    public TreeMap<Integer, ProgrammeurBean> getListeProg() {
        return this.listeProg;
    }

    /**
     * Ex�cution de la requ�te permettant de supprimer un user par id
     * @param id
     */
    public void deleteProg(int id) {
        doRequeteUpdate(this.action.getPreparedStatementInt(this.conn, Constantes.DELPROG, id));
    }

    /**
     * Ex�cution de la requ�te permettant de changer le salaire via l'id
     * @param prog
     */
    public void editProg(ProgrammeurBean prog) {
        doRequeteUpdate(this.action.getPreparedStatementModifyProg(this.conn, Constantes.EDITPROG, prog));
    }

    /**
     * Ex�cution de la requ�te permettant de cr�er un nouveau programmeur
     * @param prog
     * @return
     */
    public int createProg(ProgrammeurBean prog) {
        return doRequeteUpdate(this.action.getPreparedStatementInsert(this.conn, Constantes.CREATEPROG, prog));
    }
}