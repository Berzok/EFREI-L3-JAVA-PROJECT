package utils;

public class Constantes {
    //R�cup�rer tous les programmeurs
    public final static String ALLPROGS = "SELECT * from PROGRAMMEUR"; 
    //R�cup�rer un programmeurs par ID (ID �tant UNIQUE, pas besoin de LIMIT)
    public final static String PROGBYID = "SELECT * from PROGRAMMEUR where ID = ?";
    //R�cup�rer les programmeurs par nom
    public final static String PROGBYNAME = "SELECT * from PROGRAMMEUR where NOM like ?";
    //R�cup�rer les programmeurs par pr�nom
    public final static String PROGBYFIRSTNAME = "SELECT * from PROGRAMMEUR where PRENOM like ?";
    //R�cup�rer les programmeurs par ann�e de naissance
    public final static String PROGBYYEAR = "SELECT * from PROGRAMMEUR where ANNAISSANCE like ?";
    //Supprimer un programeur � partir de son ID
    public final static String DELPROG = "DELETE FROM PROGRAMMEUR WHERE ID=?";
    //Changer le salaire d'un programmeur � partir de son ID
    public final static String CHANGESALARY = "UPDATE PROGRAMMEUR SET SALAIRE=? WHERE ID=?";
    //Cr�er un programmeur
    public final static String CREATEPROG = "INSERT INTO PROGRAMMEUR(NOM,PRENOM,ADRESSE,PSEUDO,RESPONSABLE,HOBBY,ANNAISSANCE,SALAIRE,PRIME) VALUES(?,?,?,?,?,?,?,?,?)";
}
