package org.iesalandalus.programacion.procesos.basicos.creacionprocesos;

import java.io.IOException;

public class CreacionProcesoNotepad {
    public static void main(String[] args) {

        creaProcesoNotePadProcessBuilder();
        creaProcesoNotePadRuntime();
    }

    private static void creaProcesoNotePadProcessBuilder()
    {
        ProcessBuilder pb=new ProcessBuilder("NOTEPAD");
        try
        {
            Process proceso1=pb.start();
            System.out.println("PID del nuevo proceso: " + proceso1.pid());
            if(proceso1.isAlive())
                System.out.println("El proceso está activo");
        }
        catch (IOException e)
        {
            System.out.println("No se pudo iniciar el proceso: " + e.getMessage());
        }

    }

    private static void creaProcesoNotePadRuntime()
    {
        try
        {
            Process proceso1=Runtime.getRuntime().exec("NOTEPAD");
            System.out.println("PID del nuevo proceso: " + proceso1.pid());
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
