package Recursividad;

public class Sumatoria {

    //Sumatoria recursiva, se ingresa un numero, si el numero es menor a 0, retorna 0
    //Sino se llama de vuelta a la funcion pero con n-1, así hasta que n sea 0; retornando 0 y haciendo una cascada de return
    public static int sumar(int n) {
        if (n<=0) {
            return 0;
        } else {
            return n+sumar(n-1);
        }
    }

    //Sumatoria iterativa, va de 0 hasta n, acumulando el valor de i de 0 hasta n
    public static double sumar(double n) {
        double acumulador=0;
        for (int i=0; i<=n; i++) {
            acumulador = acumulador +i;
        }
        return acumulador;
    }
}
