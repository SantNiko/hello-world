package v0;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author a15josenms
 */
public class Insertar {
    
    public static void principal(BufferedReader lee, Statement sentencia) {
        switch (Menu.clase(lee, "Insertar")) {
            case 1:
                medico(lee, sentencia);
                break;
            case 2:
                enfermo(lee, sentencia);
                break;
            case 3:
                seguro(lee, sentencia);
                break;
            case 0:
                System.out.println("Salio del menu insertar");
                break;
            default:
                throw new AssertionError();
        }
    }

    // metodos de introduccion de autor en la base de datos
    //proceso general de inserccion
    public static void medico(BufferedReader lee, Statement sentencia) {

        String nombreMedico, especialidad, clinica;
        int idMedico;

        do {
            idMedico = id(lee, "Medico");
            nombreMedico = nombre(lee, "medico");
            especialidad = especialidad(lee);
            clinica = clinica(lee);
            medicoBD(sentencia, idMedico, nombreMedico, especialidad, clinica);
        } while (Menu.siNo(lee, "Desea introducir otro medico?"));
    }

    //proceso de escritura de los datos en la base de datos
    public static boolean medicoBD(Statement sentencia,int idMedico, String nombre, String especialidad, String clinica) {
        boolean r = true;
        try {
            sentencia.executeUpdate("INSERT INTO Medicos"
                    + "(idMedico, nombre, especialidad, clinica)"
                    + "VALUES"
                    + "("+idMedico+",'" + nombre + "','" + especialidad + "'"+clinica+");");
        } catch (SQLException ex) {
            System.out.println("Error: No se pudo insertar en la tabla Medicos la tupla deseada");
            //System.out.println(ex.getMessage());
            if (ex.getMessage().contains("Duplicate")) {
                System.out.println("\t -Porque ya existe un medico con ese nombre");
            }
            r = false;
        }
        return r;
    }

    //metodos de introduccion de libro en la base de datos
    //proceso general de insercción
    public static void enfermo(BufferedReader lee, Statement sentencia) {
        String nuss, nombre, patologia;
        int idEnfermo = -1, seguro;
        float precio = 0f;

        do {
            idEnfermo = id(lee, "Enfermo");
            nuss = nuss(lee);
            nombre = nombre(lee,"enfermo");
            patologia = patologia(lee);
            seguro = seguroEnfermo();
            if (seguro == -1) {
                System.out.println("No se encontró ningun autor con ese nombre en la base de datos");
                System.out.println("No se pudo insertar el libro en la BD");
            } else {
                enfermoBD(sentencia, idEnfermo, nuss, nombre, patologia, seguro);
            }
        } while (Menu.siNo(lee, "Desea introducir otro libro?"));
    }
    
    public static boolean enfermoBD(Statement sentencia,int idEnfermo, String dni, String nombre,String patologia, int seguro) {
        boolean r = true;
        try {
            sentencia.executeUpdate("INSERT INTO Enfermos"
                    + "(idEnfermo, dni, nombre, patologia, seguro)"
                    + "VALUES"
                    + "(" + idEnfermo + ",'" + dni + "'," + nombre + "," + patologia + ","+seguro+");");
        } catch (SQLException ex) {
            System.out.println("Error: No se pudo insertar en la tabla Enfermos la tupla deseada");
            //System.out.println(ex.getMessage());
            if (ex.getMessage().contains("Duplicate")) {
                System.out.println("\t -Porque ya existe un enfermo con ese nombre");
            }
            r = false;
        }
        return r;
    }
    
    
    public static void seguro(BufferedReader lee, Statement sentencia) {
        String nombreSeguro, prestaciones = "";
        int idSeguro = -1;

        do {
            idSeguro = id(lee, "Seguro");
            nombreSeguro = nombre(lee, "seguro");
            prestaciones = prestaciones(lee);
            seguroBD(sentencia, idSeguro, nombreSeguro, prestaciones);
        } while (Menu.siNo(lee, "Desea introducir otro libro?"));
    }

    //proceso de escritura de los datos en la base de datos
    public static boolean seguroBD(Statement sentencia,int idSeguro, String nombre, String prestaciones) {
        boolean r = true;
        try {
            sentencia.executeUpdate("INSERT INTO Seguros"
                    + "(idSeguro, nombre, prestaciones)"
                    + "VALUES"
                    + "(" + idSeguro + ",'" + nombre + "'," + prestaciones + ");");
        } catch (SQLException ex) {
            System.out.println("Error: No se pudo insertar en la tabla Libros la tupla deseada");
            //System.out.println(ex.getMessage());
            if (ex.getMessage().contains("Duplicate")) {
                System.out.println("\t -Porque ya existe un libro de ese autor con ese titulo");
            }
            r = false;
        }
        return r;
    }
    //---------------------------------

    //Metodos de lectura de teclado y comprobacion de rangos
    public static String nombre(BufferedReader lee, String dQuien) {
        String nombre;
        do {
            System.out.printf("Introduzca el nombre %s: (max 45 char)",dQuien);
            try {
                nombre = lee.readLine();
                if (nombre.isEmpty() | nombre.length() > 45) {
                    System.out.println("Error, recuerde que el nombre tiene que tener de 1-45 char");
                }
            } catch (IOException ex) {
                System.out.println("Error en la introduccion Insertar.nombre()");
                nombre = "";
            }
        } while (nombre.isEmpty() | nombre.length() > 45);
        return nombre;
    }

    public static String especialidad(BufferedReader lee) {
        String especialidad;
        do {
            System.out.println("Introduzca la especialidad del medico: (max 45 char)");
            try {
                especialidad = lee.readLine();
                if (especialidad.isEmpty() | especialidad.length() > 45) {
                    System.out.println("Error, recuerde que la especialidad del medico tiene que tener de 1-45 char");
                }
            } catch (IOException ex) {
                System.out.println("Error en la introduccion Insertar.especialidad()");
                especialidad = "";
            }
        } while (especialidad.isEmpty() | especialidad.length() > 45);
        return especialidad;
    }

    public static String clinica(BufferedReader lee) {
        String titulo;
        do {
            System.out.println("Introduzca la clinica en que trabaja el medico: (max 45 char)");
            try {
                titulo = lee.readLine();
                if (titulo.isEmpty() | titulo.length() > 45) {
                    System.out.println("Error, recuerde que la clinica tiene que tener de 1-45 char");
                }
            } catch (IOException ex) {
                System.out.println("Error en la introduccion Insertar.clinica()");
                titulo = "";
            }
        } while (titulo.isEmpty() | titulo.length() > 45);
        return titulo;
    }

    public static String nuss(BufferedReader lee) {
        String nuss;
        do {
            System.out.println("Introduzca el precio del libro: (>0)");
            try {
                nuss = lee.readLine();
                if (nuss.isEmpty() | nuss.length() > 45) {
                    System.out.println("Error, recuerde que la clinica tiene que tener de 1-45 char");
                }
            } catch (IOException ex) {
                System.out.println("Error en la introduccion Insertar.precio()");
                nuss = null;
            }
        } while (nuss != null);
        return nuss;
    }
    
    public static String patologia(BufferedReader lee) {
        String patologia;
        do {
            System.out.println("Introduzca la patologia del enfermo: (max 45 char)");
            try {
                patologia = lee.readLine();
                if (patologia.isEmpty() | patologia.length() > 45) {
                    System.out.println("Error, recuerde que la clinica tiene que tener de 1-45 char");
                }
            } catch (IOException ex) {
                System.out.println("Error en la introduccion Insertar.patologia()");
                patologia = "";
            }
        } while (patologia.isEmpty() | patologia.length() > 45);
        return patologia;
    }
    
    public static String prestaciones(BufferedReader lee) {
        String prestaciones;
        do {
            System.out.println("Introduzca las prestaciones del seguro: (max 45 char)");
            try {
                prestaciones = lee.readLine();
                if (prestaciones.isEmpty() | prestaciones.length() > 45) {
                    System.out.println("Error, recuerde que la clinica tiene que tener de 1-45 char");
                }
            } catch (IOException ex) {
                System.out.println("Error en la introduccion Insertar.patologia()");
                prestaciones = "";
            }
        } while (prestaciones.isEmpty() | prestaciones.length() > 45);
        return prestaciones;
    }

    public static int seguroEnfermo(Statement sentencia, String nombreSeguro, BufferedReader lee) {
        int idSeguro = -1;

        idSeguro = Consultar.seguro(sentencia, nombreSeguro);
        if (idSeguro == -1) {
            System.out.println("No se encontró ningun autor en la base de datos con ese nombre");
            System.out.println("procedemos a la introduccion de dicho autor en la base de datos antes de continuar con la introduccion del libro .....");
            seguroBD(sentencia,id(lee, "Seguro"), nombreSeguro, prestaciones(lee));
        }
        idSeguro = Consultar.seguro(sentencia, nombreSeguro);
        if (idSeguro == -1) {
            System.out.println("Otra vez no se encontró al autor .... error grabe revisar");
        }
        return idSeguro;
    }
    
    public static int id(BufferedReader lee,String dQue){
        int id;
        do {
            System.out.printf("Introduzca el id del %s: (>=0 y <=10)",dQue);
            try {
                id = Integer.parseInt(lee.readLine());
                if (id<0|id>10) {
                    System.out.println("Error, recuerde que el id (0-10 digitos)");
                }
            } catch (IOException ex) {
                System.out.printf("Error en la introduccion Insertar.id%s()",dQue);
                id = -1;
            }
        } while (id<0|id>10);
        return id;
    }
}
