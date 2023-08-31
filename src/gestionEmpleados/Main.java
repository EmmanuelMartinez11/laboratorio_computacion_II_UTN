package gestionEmpleados;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner escaner = new Scanner(System.in);
        GestorEmpleados gestor = new GestorEmpleados();


        int opcion;

        do {
            System.out.println("-------------Menú-------------");
            System.out.println("1. Crear empleado");
            System.out.println("2. Modificar empleado");
            System.out.println("3. Eliminar empleado");
            System.out.println("4. Salir");
            opcion = escaner.nextInt();
        } while ((opcion<=0) || (opcion>=5));



        try {
            switch (opcion) {
                case 1:
                    int empleadoACrear;
                    String nombre;
                    int id;
                    double sueldoBase;
                    int horasTrabajadas;
                    int ventasRealizadas;
                    try {
                        System.out.println("Elija el tipo de empleado a crear:");
                        System.out.println("1. Empleado por Horas");
                        System.out.println("2. Empleado Asalariado");
                        System.out.println("3. Empleado por Comisión");
                        empleadoACrear = escaner.nextInt();

                        if (empleadoACrear==1) {
                            System.out.println("Ingrese el nombre del empleado: ");
                            nombre = escaner.next();
                            System.out.println("Ingrese el id del empleado: ");
                            id = escaner.nextInt();
                            System.out.println("Ingrese el sueldo base del empleado: ");
                            sueldoBase = escaner.nextDouble();
                            System.out.println("Ingrese las horas trabajadas del empleado: ");
                            horasTrabajadas = escaner.nextInt();
                            EmpleadoPorHoras empleadoPorHoras = new EmpleadoPorHoras(nombre, id, sueldoBase, horasTrabajadas);
                            gestor.agregarEmpleado(empleadoPorHoras);
                        } else if (empleadoACrear == 2) {
                            System.out.println("Ingrese el nombre del empleado: ");
                            nombre = escaner.next();
                            System.out.println("Ingrese el id del empleado: ");
                            id = escaner.nextInt();
                            System.out.println("Ingrese el sueldo base del empleado: ");
                            sueldoBase = escaner.nextDouble();
                            EmpleadoAsalariado empleadoAsalariado = new EmpleadoAsalariado(nombre, id, sueldoBase);
                            gestor.agregarEmpleado(empleadoAsalariado);
                        } else {
                            System.out.println("Ingrese el nombre del empleado: ");
                            nombre = escaner.next();
                            System.out.println("Ingrese el id del empleado: ");
                            id = escaner.nextInt();
                            System.out.println("Ingrese el sueldo base del empleado: ");
                            sueldoBase = escaner.nextDouble();
                            System.out.println("Ingrese las ventas realizadas del empleado: ");
                            ventasRealizadas = escaner.nextInt();
                            EmpleadoComision empleadoComision = new EmpleadoComision(nombre, id, sueldoBase, ventasRealizadas);
                            gestor.agregarEmpleado(empleadoComision);
                        }
                        break;
                    } catch (Exception e) {
                        System.out.println("Ingrese un numero valido");
                    }
                case 2:
                    try {
                        System.out.println("Ingrese el id del empleado a modificar");
                        id = escaner.nextInt();
                        System.out.println("Ingrese el nombre del nuevo empleado");
                        nombre = escaner.next();
                        System.out.println("Ingrese el sueldo base del nuevo empleado");
                        sueldoBase = escaner.nextDouble();
                        gestor.modificarEmpleado(id, nombre,sueldoBase);
                        break;
                    } catch (Exception e) {
                        System.out.println("Error al encontrar empleado");
                    }

                case 3:
                    try {
                        System.out.println("Ingrese el id del empleado a eliminar");
                        id = escaner.nextInt();
                        gestor.eliminarEmpleado(id);
                        break;
                    }catch (Exception e) {
                        System.out.println("Eeror al eliminar el empleado");
                    }

                default:
                    System.out.println("Adios");
                    break;
            }

        } catch (Exception e) {
            System.out.println("Error");
        }
        for (Empleado empleado:gestor.empleados) {
            System.out.println("Nombre: "+ empleado.nombre);
        }
    }

}