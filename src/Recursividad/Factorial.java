package Recursividad;

public class Factorial {

    //Factorial recursivo, si el numero es 1 o 0, se retorna 1
    //Sino, se llama de nuevo a la funcion pero con n-1, hasta que n sea 1 y hace como un efecto cascada de returns
    //El n* actua como acumulador
    public static int hallarFactorial(int n) {
        if(n==0 || n==1) {
            return 1;
        } else {
            return n*hallarFactorial(n-1);
        }
    }

    //Factorial iterativa, si el numero es 1 o 0, se retorna 1
    //Sino se multiplican los numeros de 1 hasta n y se retorna ese resultado
    public static double hallarFactorial(double n) {
        double acumulador =1;
        if(n==0 || n==1) {
            return 1;
        } else{
            for (int i=1; i<=n; i++) {
                acumulador = acumulador *i;
            }
            return acumulador;
        }
    }
}
