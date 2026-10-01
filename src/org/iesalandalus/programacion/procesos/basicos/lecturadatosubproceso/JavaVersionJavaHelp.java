package org.iesalandalus.programacion.procesos.basicos.lecturadatosubproceso;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class JavaVersionJavaHelp {

    public static void main(String[] args)
    {
        try
        {
            //Aquí hemos creado dos procesos diferentes utilizando el mismo objeto de configuración.
            // ejecutamos java -version
            ProcessBuilder pb = new ProcessBuilder("java", "-version");
            pb.inheritIO();
            Process p= pb.start();

            boolean finalizado=p.waitFor(5, TimeUnit.SECONDS);

            if (!finalizado)
            {
                System.out.println("Proceso está tardando demasiado, se fuerza su fin.");
                p.destroyForcibly();
            }

            System.out.println("\n\n=========================");
            System.out.println("Salida de java --help");
            System.out.println("=========================\n\n");

            pb.command("java", "--help");

            Process segundo = pb.start();
            int exitValue=segundo.waitFor();
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
