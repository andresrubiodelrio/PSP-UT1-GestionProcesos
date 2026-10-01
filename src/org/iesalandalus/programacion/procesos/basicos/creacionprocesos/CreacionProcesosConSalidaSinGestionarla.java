package org.iesalandalus.programacion.procesos.basicos.creacionprocesos;

import java.io.IOException;

public class CreacionProcesosConSalidaSinGestionarla {
    public static void main(String[] args) {
        Runtime r=Runtime.getRuntime();

        //String comando="NOTEPAD";
        String[] comando1={"CMD", "/C", "DIR"};
        String[] comando2 = {"cmd", "/c", "dir", "/o"};
        // 3ª forma: usando una cadena y dividiéndola para convertirla en una lista
        String comando3 =  "c:/windows/system32/cmd c:\\dir";
        String[] comando4 =  {"c:/windows/system32/cmd", "c:\\dir"};

        try
        {
            Process proceso1=r.exec(comando1);
            System.out.println("PID del proceso 1: " + proceso1.pid());

            ProcessBuilder pb = new ProcessBuilder(comando2);
            Process proceso2=pb.start();
            System.out.println("PID del proceso 2: " + proceso2.pid());

            // La expresión regular \s significa partir por los espacios en blanco
            pb = new ProcessBuilder(comando3.split("\\s"));
            Process proceso3=pb.start();
            System.out.println("PID del proceso 3: " + proceso3.pid());


            pb = new ProcessBuilder(comando4);
            Process proceso4=pb.start();
            System.out.println("PID del proceso 4: " + proceso4.pid());

        }
        catch (IOException | SecurityException e)
        {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }
        catch (NullPointerException | IllegalArgumentException e)
        {
            System.out.println("ERROR: El comando especificado como parámetro es nulo o vacío.");
        }

    }
}
