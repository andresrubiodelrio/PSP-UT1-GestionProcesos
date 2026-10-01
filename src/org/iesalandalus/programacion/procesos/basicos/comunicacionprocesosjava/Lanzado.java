package org.iesalandalus.programacion.procesos.basicos.comunicacionprocesosjava;

public class Lanzado {
    public static void main(String[] args)
    {

        if (args.length == 0)
            System.err.println("ERROR: El proceso Lanzado necesita al menos un parámetro para que se ejecute correctamente.");
        else {
            System.out.println("Hola, soy el proceso hijo.");
            System.out.println("Recibí " + args.length + " argumentos:");

            for (String arg : args) {
                System.out.println(" - " + arg);
            }

            System.out.println("Proceso hijo finalizado.");
        }
    }
}
