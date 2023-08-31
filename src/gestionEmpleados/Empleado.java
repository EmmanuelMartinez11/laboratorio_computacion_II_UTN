package gestionEmpleados;

//Creo la superclase empleados

public abstract class Empleado {
    protected String nombre;
    protected int id;
    protected double sueldoBase;

    public Empleado() {
    }

    public abstract int getId();
    public Empleado(String nombre, int id, double sueldoBase) {
        this.nombre = nombre;
        this.id = id;
        this.sueldoBase = sueldoBase;
    }

    //Creo el metodo abstracto calcularSueldo
    public abstract double calcularSueldo();
}
