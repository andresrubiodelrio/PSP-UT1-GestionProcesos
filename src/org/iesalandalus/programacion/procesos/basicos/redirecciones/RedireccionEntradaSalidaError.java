package org.iesalandalus.programacion.procesos.basicos.redirecciones;

import java.io.*;

public class RedireccionEntradaSalidaError {

  public static void main(String args[])
  {
    try
    {
      ProcessBuilder pb = new ProcessBuilder("CMD");

      File fBat = new File("datos/fichero.bat");
      File fOut = new File("salidas/salida.txt");
      File fErr = new File("errores/error.txt");

      pb.redirectInput(fBat);
      pb.redirectOutput(ProcessBuilder.Redirect.appendTo(fOut));
      pb.redirectError(ProcessBuilder.Redirect.appendTo(fErr));
      Process proceso= pb.start();

      int exitVal=proceso.waitFor();
      System.out.println("Proceso hijo terminó con código: " + exitVal);
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
