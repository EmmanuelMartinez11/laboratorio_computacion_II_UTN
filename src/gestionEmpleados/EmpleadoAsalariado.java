package gestionEmpleados;

public class EmpleadoAsalariado extends Empleado implements Impuesto{
    public EmpleadoAsalariado() {
    }

    public EmpleadoAsalariado(String nombre, int id, double sueldoBase) {
        super(nombre, id, sueldoBase);
    }

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public double calcularSueldo() {
        return sueldoBase;
    }

    @Override
    public double calcularIMpuesto() {
        return calcularSueldo()*0.1; //Impuesto del 10%
    }
}
