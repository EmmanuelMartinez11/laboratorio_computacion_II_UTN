package gestionEmpleados;

public class EmpleadoPorHoras extends Empleado implements Impuesto{
    private int horasTrabajadas;

    public EmpleadoPorHoras(String nombre, int id, double sueldoBase, int horasTrabajadas) {
        super(nombre, id, sueldoBase);
        this.horasTrabajadas = horasTrabajadas;
    }
    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public double calcularSueldo() {
        return sueldoBase * horasTrabajadas;
    }

    @Override
    public double calcularIMpuesto() {
        return calcularSueldo()*0.1; //Impuesto del 10%
    }
}
