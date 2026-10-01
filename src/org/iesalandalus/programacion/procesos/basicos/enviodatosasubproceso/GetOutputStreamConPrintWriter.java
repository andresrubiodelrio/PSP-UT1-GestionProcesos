package org.iesalandalus.programacion.procesos.basicos.enviodatosasubproceso;

import java.io.*;

public class GetOutputStreamConPrintWriter {
    public static void main(String[] args) {
        try {
            String palabraABuscar = "ejemplo";

            String comandoWindows = "cmd.exe /c findstrs /i \"" + palabraABuscar + "\"";

            ProcessBuilder pb = new ProcessBuilder(comandoWindows.split(" "));
            Process proceso = pb.start();

            // Escribimos en la entrada del proceso
            PrintWriter writer = new PrintWriter(proceso.getOutputStream(), true);

            writer.println("Hola desde Java!");
            writer.println("Esto es un mensaje enviado al proceso.");
            writer.println("Hola Windows!");
            writer.println("Esta es una línea de texto de ejemplo.\n");
            writer.println("Aquí hay otra línea.");
            writer.println("Buscamos la palabra 'ejemplo' en esta entrada.\n");
            writer.println("Probando getOutputStream() con PrintWriter");

            writer.close(); // cerramos para que proceso hijo sepa que no hay más entrada

            //Gestionamos los posibles errores
            BufferedReader brError=new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
            String linea=null;
            while ((linea=brError.readLine())!=null)
            {
                System.out.println("ERROR: " + linea);
            }

            brError.close();

            // Ahora leemos la salida (stdout) del proceso
            BufferedReader brLector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()));

            System.out.println("=== SALIDA DEL PROCESO ===");
            while ((linea = brLector.readLine()) != null) {
                System.out.println(linea);
            }

            int exitValue=proceso.waitFor();
            System.out.println("Proceso finalizó con código: " + exitValue);

        } catch (IOException e)
        {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }
        catch (InterruptedException e)
        {
            System.out.println("Se interrumpió la espera.");
        }
    }
}
