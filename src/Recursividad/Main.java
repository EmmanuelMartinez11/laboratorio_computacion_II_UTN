package Recursividad;

import Recursividad.Sumatoria;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Division act1 = new Division();
        Sumatoria act2 = new Sumatoria();
        Factorial act3 = new Factorial();

        //Actividad 1
        System.out.println("Actividad 1 - division");
        int numeradorInt = 11;
        int denominadorInt = 2;
        int act1Recur = act1.dividir(numeradorInt, denominadorInt);
        System.out.println(act1Recur);

        double numeradorDouble = 11.0;
        double denominadorDouble = 2.0;
        double act1Iter = act1.dividir(numeradorDouble, denominadorDouble);
        System.out.println(act1Iter);


        //Actividad 2
        System.out.println("\nActividad 2 - sumatoria");
        int numInt = 4;
        int act2Recur = act2.sumar(numInt);
        System.out.println(act2Recur);

        double numDouble = 4;
        double act2Iter = act2.sumar(numDouble);
        System.out.println(act2Iter);


        //Actividad 3
        System.out.println("\nActividad 3 - factorial");
        int num_Int = 5;
        int act3Recur = act3.hallarFactorial(num_Int);
        System.out.println(act3Recur);

        double num_Double = 5;
        double act3Iter = act3.hallarFactorial(num_Double);
        System.out.println(act3Iter);
    }
}