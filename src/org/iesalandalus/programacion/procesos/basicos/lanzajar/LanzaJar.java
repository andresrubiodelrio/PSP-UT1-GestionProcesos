package org.iesalandalus.programacion.procesos.basicos.lanzajar;

import java.io.File;
import java.io.IOException;

public class LanzaJar {

    public static void main(String[] args)
    {
        String ficherojar = "jars/PintaDatos.jar";

        //Establecemos el proceso a ejecutar
        ProcessBuilder pb = new ProcessBuilder("java","-jar",ficherojar,"uno","dos");

        //Redirigimos la salida estándar del subproceso a nuestra salida estándar.
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        //Redirigimos los errores a fichero
        pb.redirectError(new File("errores/JarError.txt"));

        Process p = null;
        try
        {
            p = pb.start();
            System.out.println("El proceso hijo terminó con salida: "+p.waitFor()); //esperar hasta que finalice
        }
        catch (IOException e)
        {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }
        catch (InterruptedException e)
        {
            System.out.println("El proceso hijo fue interrumpido.");
        }


    }

}
