package org.iesalandalus.programacion.procesos.basicos.redirecciones;

import java.io.File;
import java.io.IOException;

public class RedireccionSalidaError {
    public static void main(String[] args) {
        try {

            ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "DIRf");

            File fLog = new File("salidas/salida.log");
            File fError = new File("errores/error.log");
            //pb.redirectOutput(fLog);
            pb.redirectOutput(ProcessBuilder.Redirect.appendTo(fLog));
            pb.redirectError(fError);

            // Iniciamos el proceso
            Process proceso = pb.start();

            // Esperamos que termine
            int codigoSalida = proceso.waitFor();
            System.out.println("=== Proceso hijo terminó con código: " + codigoSalida);


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
