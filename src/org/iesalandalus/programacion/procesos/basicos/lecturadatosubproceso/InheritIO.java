package org.iesalandalus.programacion.procesos.basicos.lecturadatosubproceso;

import java.io.IOException;

public class InheritIO {
    public static void main(String[] args) throws Exception {
        // Ejecutamos el proceso DIR
        // Hereda entrada/salida/error del proceso padre
        try
        {
            ProcessBuilder pb =  new ProcessBuilder("CMD", "/C", "DIRe");
            pb.inheritIO();
            Process p = pb.start();

            pb =  new ProcessBuilder("ping", "google.com");
            p = pb.inheritIO().start();

            int exitValue=p.waitFor();
            System.out.println("El subproceso finalizó con valor de salida: " + exitValue);
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
