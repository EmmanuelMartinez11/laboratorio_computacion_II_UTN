package Recursividad;

public class Division {

    //Uso recursivo de division, si el numerador es menor denominador, se devuelve 0 ya que no se puede dividir
    //sino, se llama nuevamente a la funcion dividir, pero restando el denominador al numerador
    //Así hasta que el denominador sea mayor al numerador y se retorne 0, y hace como un efecto cascada de returns
    //El 1+ funciona como acumulador
    public static int dividir(int numerador, int denominador) {
        if (numerador < denominador) {
            return 0;
        } else {
            return 1 + dividir(numerador - denominador, denominador);
        }
    }

    //Division con iteratividad, mientras el numerador sea menor al denominador, se resta el denominador al numerador
    //Y se agrega 1 al acumulador (cociente)
    public static int dividir(double numerador, double denominador) {
        int cociente = 0;
        while (numerador >= denominador) {
            numerador -= denominador;
            cociente++;
        }
        return cociente;
    }
}