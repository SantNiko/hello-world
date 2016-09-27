package v0;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author a15josenms
 */
public class Consultar {
    
    public static int seguro (Statement sentencia, String nombreSeguro) {
        ResultSet r = null;
        int idSeguro = -1;
        try {
            r = sentencia.executeQuery("select idSeguro, nombre, prestaciones "
                                        + " from seguros "
                                        + " where nombre = '"+nombreSeguro+"';");
            r.next();
            idSeguro = r.getInt("idSeguro");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error - Consultar.seguro()");
        }
        
        return idSeguro;
    }
    public static ResultSet medico (Statement sentencia, String nombreMedico) {
        ResultSet r = null;
        try {
            r = sentencia.executeQuery("select idMedico, nombre, especialidad, clinica "
                                        + " from medicos "
                                        + " where nombre = '"+nombreMedico+"';");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error - Consultar.medico()");
        }
        return r;
    }
    
    public static ResultSet enfermo (Statement sentencia, String nombreEnfermo) {
        ResultSet r = null;
        try {
            r = sentencia.executeQuery("select idEnfermo, nuss, nombre, patologia, seguro "
                                        + " from enfermos "
                                        + " where nombre = '"+nombreEnfermo+"';");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error - Consultar.enfermos()");
        }
        return r;
    }
    
}
