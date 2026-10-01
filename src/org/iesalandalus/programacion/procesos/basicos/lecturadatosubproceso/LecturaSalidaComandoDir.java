package org.iesalandalus.programacion.procesos.basicos.lecturadatosubproceso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LecturaSalidaComandoDir {
    public static void main(String[] args) {
        // Ejecutamos el proceso DIR
        Process proceso1;
        int c;
        int exitVal;

        try
        {
            proceso1 = new ProcessBuilder("CMD", "/C", "DIR").start();

            // Mostramos carácter a carácter la salida generada por DIR
            InputStream is = proceso1.getInputStream();

//            while ((c = is.read()) != -1)
//                System.out.print((char) c);
//
//            is.close();

            BufferedReader brLector = new BufferedReader(new InputStreamReader(is));

            String lector;
            while ((lector = brLector.readLine()) != null)
                System.out.println(lector);

            brLector.close();


            // COMPROBACIÓN DE ERROR - 0 bien - DISTINTO DE 0 mal
            exitVal = proceso1.exitValue(); // recoge la salida de System.exit()
            System.out.println("El subproceso finalizó con valor de Salida: " + exitVal);

        }
        catch (IOException e)
        {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }






    }
}


