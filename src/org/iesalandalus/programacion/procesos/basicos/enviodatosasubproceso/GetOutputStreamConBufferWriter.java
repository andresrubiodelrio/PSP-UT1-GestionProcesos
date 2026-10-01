package org.iesalandalus.programacion.procesos.basicos.enviodatosasubproceso;

import java.io.*;

public class GetOutputStreamConBufferWriter {
        public static void main(String[] args) {
            try {
                // Palabra a buscar
                String palabraABuscar = "ejemplo";

                // Comando a ejecutar en Windows. 'findstr' busca la palabra en la entrada.
                String comandoWindows = "cmd.exe /c findstr /i \"" + palabraABuscar + "\"";

                ProcessBuilder pb = new ProcessBuilder(comandoWindows.split(" "));
                Process proceso = pb.start();

                // Abrimos el OutputStream para enviarle datos al proceso
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(proceso.getOutputStream()));

                // Escribimos algunas líneas
                writer.write("Hola Windows!");
                writer.newLine();
                writer.write("Esta es una línea de texto de ejemplo.\n");
                writer.newLine();
                writer.write("Aquí hay otra línea.\n");
                writer.newLine();
                writer.write("Buscamos la palabra 'ejemplo' en esta entrada.\n");
                writer.newLine();
                writer.write("Probando getOutputStream() con BufferedWriter");
                writer.newLine();
                writer.flush(); // aseguramos que los datos viajen al proceso

                writer.close(); // cerramos para indicar que no hay más entrada

                //Gestionamos los posibles errores
                BufferedReader brError=new BufferedReader(new InputStreamReader(proceso.getErrorStream()));
                String linea=null;
                while ((linea=brError.readLine())!=null)
                {
                    System.out.println("ERROR: " + linea);
                }

                // Ahora leemos la salida del proceso
                BufferedReader brLector = new BufferedReader(
                        new InputStreamReader(proceso.getInputStream()));

                System.out.println("=== SALIDA DEL PROCESO ===");
                while ((linea = brLector.readLine()) != null) {
                    System.out.println(linea);
                }

                brLector.close();

                int exitValue=proceso.waitFor();
                System.out.println("Proceso finalizó con código: " + exitValue);

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

