package org.iesalandalus.programacion.procesos.basicos.lanzajar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LanzaJarDinamicoParametrosEnFichero {

    public static void main(String[] args) {
        String ficherojar = "jars/PintaDatos.jar";

        // Lee argumentos desde un fichero y los lleva a una lista
        List<String> parametros = new ArrayList<>();

        try
        {
            BufferedReader br = new BufferedReader(new FileReader(new File("datos/FdatosJar.txt")));
            String linea;
            while ((linea = br.readLine()) != null)
                parametros.add(linea);

            br.close();

            //Establecemos el proceso a ejecutar
            ProcessBuilder pb = new ProcessBuilder("java","-jar",ficherojar);
            pb.command().addAll(parametros); // Agregar los argumentos leídos

            //Redirigimos la salida estándar del subproceso a nuestra salida estándar.
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            //Redirigimos los errores a fichero
            pb.redirectError(new File("errores/JarErrorDinamico.txt"));

            Process p = pb.start();
            System.out.println("Valor de salida: "+p.waitFor()); //esperar hasta que finalice

        }
        catch (FileNotFoundException e)
        {
            System.out.println("Error: Fichero no encontrado");
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
