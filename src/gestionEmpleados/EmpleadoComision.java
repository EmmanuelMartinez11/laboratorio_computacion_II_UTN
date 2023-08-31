package gestionEmpleados;

public class EmpleadoComision extends Empleado implements Impuesto{

    private double ventasRealizadas;

    public EmpleadoComision() {
    }

    public EmpleadoComision(String nombre, int id, double sueldoBase, double ventasRealizadas) {
        super(nombre, id, sueldoBase);
        this.ventasRealizadas = ventasRealizadas;
    }

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public double calcularSueldo() {
        return sueldoBase + (0.3 * ventasRealizadas); //30% de comision
    }

    @Override
    public double calcularIMpuesto() {
        return calcularSueldo()*0.1; //Impuesto del 10%
    }
}
