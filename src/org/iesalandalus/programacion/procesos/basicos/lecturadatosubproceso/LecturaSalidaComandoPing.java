package org.iesalandalus.programacion.procesos.basicos.lecturadatosubproceso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LecturaSalidaComandoPing {
    public static void main(String[] args) {
        int exitVal = 0;

        try
        {
            // Crear proceso con argumentos
            ProcessBuilder pb = new ProcessBuilder("ping", "google.com");
            //ProcessBuilder pb = new ProcessBuilder("ping", "-c", "3", "google.com");

            // Iniciar el proceso
            Process proceso = pb.start();

            // Leer la salida
            BufferedReader brLector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream())
            );

            String linea;
            while ((linea = brLector.readLine()) != null) {
                System.out.println(linea);
            }

            brLector.close();

            // Esperar fin del proceso
            int exitValue = proceso.waitFor();
            System.out.println("El subproceso finalizó con valor de Salida: " + exitValue);

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
