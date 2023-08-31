package gestionEmpleados;

import java.util.ArrayList;

public class GestorEmpleados {
    ArrayList<Empleado> empleados = new ArrayList<Empleado>();

    //Funcion agregarEmpleado que recibe un objeto como parametro
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    //Funcion modificarEmpleado que recibe el nombre y el sueldo a moodificar, manteniendo el id
    public void modificarEmpleado(int id, String nombre, double sueldoBase) {
        for (Empleado empleado:empleados) {
            if(empleado.id == id) {
                empleado.nombre = nombre;
                empleado.sueldoBase = sueldoBase;
            }
        }
    }

    //Funcion eliminarEmpleado que recibe un id como parametro y usa la funcion getID de los demas empleados
    public void eliminarEmpleado(int id) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == id) {
                empleados.remove(i);
                System.out.println("Empleado con ID " + id + " eliminado exitosamente.");
                return;
            }
        }
    }
}
