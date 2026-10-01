package org.iesalandalus.programacion.procesos.basicos.subprocesoserrores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubprocesoErroneoConFlujo {
    public static void main(String[] args) {
        // Ejecutamos el proceso DIR
        Process proceso1;
        int c;
        int exitVal;

        try
        {
            proceso1 = new ProcessBuilder("CMD", "/C", "DIRf").start();

            BufferedReader brLector=new BufferedReader(new InputStreamReader(proceso1.getInputStream()));
            String linea=null;
            while ((linea=brLector.readLine())!=null)
            {
                System.out.println("ERROR: " + linea);
            }

            brLector.close();

            BufferedReader brError=new BufferedReader(new InputStreamReader(proceso1.getErrorStream()));
            while ((linea=brError.readLine())!=null)
            {
                System.out.println("ERROR: " + linea);
            }

            brError.close();

            // COMPROBACIÓN DE ERROR - 0 bien - DISTINTO DE 0 mal
            exitVal = proceso1.waitFor(); // recoge la salida de System.exit()
            System.out.println("El subproceso finalizó con valor de Salida: " + exitVal);

        }
        catch (IOException e)
        {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }
        catch (InterruptedException e)
        {
            System.out.println("Se interrumpió la espera.");
        }





    }
}


