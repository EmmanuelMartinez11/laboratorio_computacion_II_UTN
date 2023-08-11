import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int num1, num2, num3;
        Scanner escaner = new Scanner(System.in);

        System.out.print("Ingrese el primer número: ");
        num1 = escaner.nextInt();
        System.out.print("Ingrese el segundo número: ");
        num2 = escaner.nextInt();
        System.out.print("Ingrese el tercer número: ");
        num3 = escaner.nextInt();

        if ((num1 < num2) && (num1 < num3)) {
            if (num2 < num3) System.out.print(num1 + " " + num2 + " " + num3);
            else System.out.print(num1 + " " + num3 + " " + num2);
        } else if ((num2 < num1) && (num2 < num3)) {
            if (num1 < num3) System.out.print(num2 + " " + num1 + " " + num3);
            else System.out.print(num2 + " " + num3 + " " + num1);
        } else if ((num3 < num1) && (num3 < num2)) {
            if (num1 < num2) System.out.print(num3 + " " + num1 + " " + num2);
            else System.out.print(num3 + " " + num2 + " " + num1);
        } else System.out.print(num1 + " " + num2 + " " + num3);
    }
}