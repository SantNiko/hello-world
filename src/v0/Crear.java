/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package v0;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a15josenms
 */
public class Crear {
    
    public static void bd(Connection conexion){
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.execute("CREATE DATABASE IF NOT EXISTS Hospital;");
            sentencia.execute("USE Hospital;");
        } catch (SQLException ex) {
            System.out.println("---------------Error en Crear.bd() ----------------");
        }
    }
    
    public static void tablas(Connection conexion){
        try {
            Statement sentencia = conexion.createStatement();
            sentencia.execute("CREATE TABLE IF NOT EXISTS Medicos ("
                            + "     idMedico INT(10)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                            + "     nombre VARCHAR(45) NOT NULL,"
                            + "     especialidad VARCHAR(45) NOT NULL,"
                            + "     clinica VARCHAR(45) NOT NULL"
                            + "     PRIMARY KEY (idMedico),"
                            + "     UNIQUE INDEX (nombre)"
                            + ") ENGINE = InnoDB;");
            
            System.out.println("Tabla Medicos creada con suceso");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS Seguros ("
                            + "     idSeguro INT(10)  UNSIGNED ZEROFILL NOT NULL AUTO_INCREMENT,"
                            + "     nombre VARCHAR(45) NOT NULL,"
                            + "     prestaciones VARCHAR(45) NOT NULL"
                            + "     PRIMARY KEY (idSeguro),"
                            + "     UNIQUE INDEX (nombre)"
                            + ") ENGINE = InnoDB;");
            
            System.out.println("Tabla Seguros creada con suceso");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS Enfermos ("
                            + "     idEnfermo INT(10) UNSIGNED ZEROFILL NOT NULL,"
                            + "     nuss VARCHAR(9) NOT NULL,"
                            + "     nombre VARCHAR(45) NOT NULL,"
                            + "     patologia VARCHAR(45) NOT NULL,"
                            + "     seguro INT(10)  UNSIGNED ZEROFILL NOT NULL"
                            + "     PRIMARY KEY (idEnfermo),"
                            + "     CONSTRAINT fk1_seguro "
                            + "         FOREIGN KEY (seguro)"
                            + "             REFERENCES Seguros (idSeguro)"
                            + "                 ON DELETE RESTRICT"
                            + "                 ON UPDATE CASCADE"
                            + ") ENGINE = InnoDB;");
            
            System.out.println("Tabla Enfermos creada con suceso");
            
            sentencia.execute("CREATE TABLE IF NOT EXISTS MedicosEnfermos ("
                            + "     enfermo INT(10) UNSIGNED ZEROFILL NOT NULL,"
                            + "     medico INT(10)  UNSIGNED ZEROFILL NOT NULL"
                            + "     PRIMARY KEY (enfermo,medico),"
                            + "     CONSTRAINT fk2_enfermo "
                            + "         FOREIGN KEY (enfermo)"
                            + "             REFERENCES Enfermos (idEnfermo)"
                            + "                 ON DELETE CASCADE"
                            + "                 ON UPDATE CASCADE"
                            + "     CONSTRAINT fk3_medico "
                            + "         FOREIGN KEY (medico)"
                            + "             REFERENCES Enfermos (idMedico)"
                            + "                 ON DELETE CASCADE"
                            + "                 ON UPDATE CASCADE"
                            + ") ENGINE = InnoDB;");
            
            System.out.println("Tabla MedicosEnfermos creada con suceso");
            
        } catch (SQLException ex) {
            System.out.println("---------------Error en Crear.tablas() ----------------");
        }
    }
    
}
