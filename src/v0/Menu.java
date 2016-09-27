package v0;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author a15josenms
 */
public class Menu {
    
    public static byte principal(BufferedReader lee) {
        byte opt=0;
        do {
            System.out.printf("\t\tMenu\n"
                            + "\t1. Creación de la Base de Datos y tablas\n"
                            + "\t2. Inserción de nuevas filas\n"
                            + "\t3. Borrado de filas\n"
                            + "\t4. Modificar el precio de un libro\n"
                            + "\t5. Consulta por titulo de libro\n"
                            + "\t6. Consulta por nombre de autor\n"
                            + "\t7. Visualizar libros de la Libreria\n"
                            + "\t8. Visualización de todos los autores con sus libros\n"
                            + "\t0. Salir\n");
            try {
                opt = Byte.parseByte(lee.readLine());
            } catch (IOException e) {
                System.out.println("introducción invalida, solo se aceptan los números en este menu");
                opt = -1;
            }
            
            if (opt<0 | opt>8) {
                System.out.println("valor no valido, escoja una de las opciones (0-8)");
            }
            
        } while (opt<0 | opt>8);
        
        return opt;
    }
    
    public static byte clase(BufferedReader lee, String accion) {
        byte opt=0;
        do {
            System.out.printf("\t\tMenu %1$s\n"
                            + "\t1. %1$s Medico\n"
                            + "\t2. %1$s Enfermo\n"
                            + "\t3. %1$s Seguro\n"
                            + "\t0. Salir\n", accion);
            try {
                opt = Byte.parseByte(lee.readLine());
            } catch (IOException e) {
                System.out.println("introducción invalida, solo se aceptan los números en este menu");
                opt = -1;
            }
            
            if (opt<0 | opt>2) {
                System.out.println("valor no valido, escoja una de las opciones (0-8)");
            }
            
        } while (opt<0 | opt>2);
        
        return opt;
    }
    
    public static byte atrAutor(BufferedReader lee, String accion) {
        byte opt=0;
        do {
            System.out.printf("\t\tMenu %1$s\n"
                            + "\t1. %1$s nombre\n"
                            + "\t2. %1$s nacionalidad\n"
                            + "\t0. Salir\n", accion);
            try {
                opt = Byte.parseByte(lee.readLine());
            } catch (IOException e) {
                System.out.println("introducción invalida, solo se aceptan los números en este menu");
                opt = -1;
            }
            
            if (opt<0 | opt>2) {
                System.out.println("valor no valido, escoja una de las opciones (0-2)");
            }
            
        } while (opt<0 | opt>2);
        
        return opt;
    }
    public static byte atrLibro(BufferedReader lee, String accion) {
        byte opt=0;
        do {
            System.out.printf("\t\tMenu %1$s\n"
                            + "\t1. %1$s titulo\n"
                            + "\t2. %1$s precio\n"
                            + "\t3. %1$s autor\n"
                            + "\t0. Salir\n", accion);
            try {
                opt = Byte.parseByte(lee.readLine());
            } catch (IOException e) {
                System.out.println("introducción invalida, solo se aceptan los números en este menu");
                opt = -1;
            }
            
            if (opt<0 | opt>2) {
                System.out.println("valor no valido, escoja una de las opciones (0-2)");
            }
            
        } while (opt<0 | opt>2);
        
        return opt;
    }
    
    public static boolean siNo(BufferedReader lee, String accion) {
        byte opt;
        do {
            System.out.printf("\t\t%1$s\n"
                            + "\t1. Si\n"
                            + "\t0. No\n", accion);
            try {
                opt = Byte.parseByte(lee.readLine());
            } catch (IOException e) {
                System.out.println("introducción invalida, solo se aceptan los números en este menu");
                opt = -1;
            }
            
            if (opt<0 | opt>1) {
                System.out.println("valor no valido, escoja una de las opciones (0-8)");
            }
            
        } while (opt<0 | opt>1);
        return (opt==1);
    }
    
}
