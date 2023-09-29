package Parcial1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Hospital {
    public static void main(String[] args) {
        Doctor cardiologo = new Doctor("Emmanuel", 4208095, "13/08/1999", "Cardiologo");
        Doctor dermatologo = new Doctor("José", 4504095, "13/11/1994", "Dermatologo");
        Doctor oftalmologo = new Doctor("Juan", 1234567, "21/04/1979", "Oftalmologo");
        ArrayList<Doctor> listaDoctores = new ArrayList<>();
        listaDoctores.add(cardiologo);
        listaDoctores.add(dermatologo);
        listaDoctores.add(oftalmologo);




        ArrayList<HistorialMedico> historiales1 = new ArrayList<>();
        historiales1.add(new HistorialMedico("01/01/2023", "Consulta de rutina"));
        ArrayList<HistorialMedico> historiales2 = new ArrayList<>();
        historiales2.add(new HistorialMedico("15/02/2023", "Fiebre alta"));
        ArrayList<HistorialMedico> historiales3 = new ArrayList<>(); // Sin historial

        Paciente paciente1 = new Paciente("Juan Perez", 12345678, "01/05/1980", 555123456, 1, historiales1);
        Paciente paciente2 = new Paciente("Maria Rodriguez", 98765432, "10/12/1992", 555987654, 2, historiales2);
        Paciente paciente3 = new Paciente("Carlos Gomez", 54321678, "03/08/1975", 555543216, 3, historiales3);

        ArrayList<Paciente> listaPacientes = new ArrayList<>();
        listaPacientes.add(paciente1);
        listaPacientes.add(paciente2);
        listaPacientes.add(paciente3);

        Listador.buscarPacientePorDNI(listaPacientes);

        Scanner scanner = new Scanner(System.in);

    }
}

class Persona {
    private String nombre;
    private int dni;
    private String fechaNacimiento;

    public Persona(String nombre, int dni, String fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}

class Doctor extends Persona {
    private String especialidad;

    public Doctor(String nombre, int dni, String fechaNacimiento, String especialidad) {
        super(nombre, dni, fechaNacimiento);
        this.especialidad = especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    public String getEspecialidad() {
        return especialidad;
    }
}

class HistorialMedico {
    private String fecha;
    private String observaciones;

    public HistorialMedico(String fecha, String observaciones) {
        this.fecha = fecha;
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }
}

interface Informacion {
    void verHistorialDeEventos();
}

class Paciente extends Persona implements Informacion {
    private int numTelefono;
    private int tipoSangre;
    private ArrayList<HistorialMedico> historial;

    public Paciente(String nombre, int dni, String fechaNacimiento, int numTelefono, int tipoSangre, ArrayList<HistorialMedico> historial) {
        super(nombre, dni, fechaNacimiento);
        this.numTelefono = numTelefono;
        this.tipoSangre = tipoSangre;
        this.historial = historial;
    }

    public int getNumTelefono() {
        return numTelefono;
    }

    public int getTipoSangre() {
        return tipoSangre;
    }

    public ArrayList<HistorialMedico> getHistorialMedico() {
        return historial;
    }

    @Override
    public void verHistorialDeEventos() {
        if (historial.isEmpty()) {
            System.out.println("El paciente no tiene historial médico.");
        } else {
            System.out.println("HISTORIAL MÉDICO:");
            for (HistorialMedico historiaMedica : historial) {
                System.out.println(historiaMedica.getFecha() + " - " + historiaMedica.getObservaciones());
            }
        }
    }
}
class serializadorDePacientes {
    ArrayList<Paciente> listaPacientes;
    public void guardar() {
        try {
            FileOutputStream objetoFileOS = new FileOutputStream("archivo.txt");
            ObjectOutputStream flujoSalida = new ObjectOutputStream(objetoFileOS);
            flujoSalida.writeObject(listaPacientes);
            flujoSalida.close();
        } catch (Exception exception) {
            System.out.println("Ocurrió un error de tipo: "+exception);
        }
    }

    //Cargar consesionaria

    public void cargar() {
        try {
            FileInputStream objetoFileIS = new FileInputStream("archivo.txt");
            ObjectInputStream flujoEntrada = new ObjectInputStream(objetoFileIS);
            this.listaPacientes = (ArrayList<Paciente>) flujoEntrada.readObject();;
            flujoEntrada.close();

        } catch (Exception exception) {
            System.out.println("Ocurrió un error de tipo: "+exception);
        }
    }
}
class Listador {
    public static void mostrarDoctores(ArrayList<Doctor> doctores){
        Iterator iteradorDoctores = doctores.iterator();
        while (iteradorDoctores.hasNext()) {
            Doctor doctorDevuelto = (Doctor) iteradorDoctores.next();
            System.out.println("Doctor "+doctorDevuelto.getNombre() +" - "+ doctorDevuelto.getEspecialidad());
        }
    }
    public static void buscarPacientePorDNI(ArrayList<Paciente> pacientes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Indique el DNI del paciente a buscar");
        int dniIngresado = scanner.nextInt();
        scanner.nextLine(); // Consumir el carácter de nueva línea

        Iterator iteradorPacientes = pacientes.iterator();

        while (iteradorPacientes.hasNext()) {
            Paciente pacienteDevuelto = (Paciente) iteradorPacientes.next();
            if (pacienteDevuelto.getDni() == dniIngresado) {
                ArrayList<HistorialMedico> historial = pacienteDevuelto.getHistorialMedico();
                if (historial.isEmpty()) {
                    System.out.println("El paciente no tiene historial médico.");
                    System.out.println("Quiere: \n1.Agregar un nuevo historial\n2.No");
                    int eleccion = scanner.nextInt();
                    scanner.nextLine(); // Consumir el carácter de nueva línea

                    if (eleccion == 1) {
                        System.out.println("Ingrese la fecha: ");
                        String nuevaFecha = scanner.nextLine();
                        System.out.println("Ingrese las observaciones: ");
                        String nuevaObservacion = scanner.nextLine();
                        historial.add(new HistorialMedico(nuevaFecha, nuevaObservacion));
                    }
                } else {
                    System.out.println("HISTORIAL MÉDICO:");
                    for (HistorialMedico historiaMedica : historial) {
                        System.out.println(historiaMedica.getFecha() + " - " + historiaMedica.getObservaciones());
                    }
                }
                return; // Salir del método después de encontrar al paciente
            }
        }
        // Si llega aquí, el paciente no se encontró
        System.out.println("Paciente no encontrado");
    }

}