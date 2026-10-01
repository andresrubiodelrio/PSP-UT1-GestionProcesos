package org.iesalandalus.programacion.procesos.basicos.enviodatosasubproceso;

import java.io.*;

public class OtroGetOutputStreamConBufferWriter
{
    public static void main(String[] args)
    {
        Process proceso = null;

        try{

            proceso = new ProcessBuilder("CMD", "/C", "DATE").start();

            //enviar datos a DATE
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));
            bw.write("12-03-26");
            bw.flush(); //vacia el buf
            bw.close(); // Cerramos la escritura para indicar que no hay más datos


            // Ahora leemos la salida del proceso
            BufferedReader brLector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));

            String linea;
            System.out.println("=== SALIDA DEL PROCESO ===");
            while ((linea = brLector.readLine()) != null) {
                System.out.println(linea);
            }
            brLector.close();

            // COMPROBACION DE ERROR - 0 bien - 1 mal
            int exitVal = proceso.waitFor();
            System.out.println("Proceso finalizó con código: " + exitVal);


            // OBTENER LOS POSIBLES ERRORES

            BufferedReader brError = new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            while ((linea = brError.readLine()) != null)
                System.out.println("ERROR >" + linea);

            brError.close();

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
