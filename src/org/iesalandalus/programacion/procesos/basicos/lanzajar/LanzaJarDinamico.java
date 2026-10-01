package org.iesalandalus.programacion.procesos.basicos.lanzajar;

import org.iesalandalus.programacion.utilidades.Entrada;

import java.io.File;
import java.io.IOException;

public class LanzaJarDinamico {

    public static void main(String[] args)
    {
        //String ficherojar = "c:" + File.separator + "tmp" + File.separator + "PintaDatos.jar";
        String ficherojar = "jars/PintaDatos.jar";
        String parametro;

        //Establecemos el proceso a ejecutar
        ProcessBuilder pb = new ProcessBuilder("java","-jar",ficherojar);

        do {
            System.out.print("Introduce el parámetro a pasar al subproceso. Finaliza con un *. ");
            parametro=Entrada.cadena();
            if (!parametro.equals("*"))
                pb.command().add(parametro);
        }while (!parametro.equals("*"));


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
