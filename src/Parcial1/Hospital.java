package Parcial1;

import java.io.*;
import java.util.*;

public class Hospital {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcion;
        Gestor gestor = new Gestor();
        Doctor doctorUno = new Doctor("Juan Lopez", 34187952, "03/08/1981", "Cardiologia");
        Doctor doctorDos = new Doctor("Miguel Esperanza", 31256882, "02/01/1979", "Neurologia");
        Doctor doctorTres = new Doctor("Eustaquio Gimenez", 29532455, "25/07/1991", "Pediatria");
        Doctor doctorCuatro = new Doctor("Pedro Martinez", 40101232, "12/11/1992", "Cirugia");
        Doctor doctorCinco = new Doctor("Rodrigo Garnacho", 32682158, "30/03/1961", "Enfermeria");
        gestor.agregarDoctor(doctorUno);
        gestor.agregarDoctor(doctorDos);
        gestor.agregarDoctor(doctorTres);
        gestor.agregarDoctor(doctorCuatro);
        gestor.agregarDoctor(doctorCinco);
        do{
            System.out.println("Hospital Julio C. Perrando - Av. 9 de Julio 1100 · 0362 444-2399");
            System.out.println("Menú:");
            System.out.println("1. Listar Doctores.");
            System.out.println("2. Registrar un nuevo paciente.");
            System.out.println("3. Actualizar información personal de un paciente.");
            System.out.println("4. Consultar el historial médico de un paciente.");
            System.out.println("5. Nuevo historial para un paciente.");
            System.out.println("6. Guardar Historial de pacientes en archivo");
            System.out.println("7. Cargar Historial de pacientes desde archivo");
            System.out.println("8. Salir.");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion){
                case 1:
                    gestor.mostrarDoctores();
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del paciente que desea agregar: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingrese el dni: ");
                    int dni = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese fecha de nacimiento con el siguiente formato: dd/MM/yyyy");
                    String fecha;
                    fecha = scanner.nextLine();
                    System.out.println("Ingrese su numero de telefono: ");
                    int telefono = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese su tipo de sangre: ");
                    int sangre = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese su historial medico con el siguiente formato: dd/MM/yyyy - observaciones");
                    String historial = scanner.nextLine();
                    ArrayList<String> historialAgregado = new ArrayList<>();
                    historialAgregado.add(historial);
                    Paciente paciente = new Paciente(nombre, dni, fecha, telefono, sangre, historialAgregado);
                    gestor.agregarPaciente(paciente);
                    break;
                case 3:
                    gestor.actualizarPaciente();
                    break;
                case 4:
                    System.out.println("Ingrese el dni del paciente que desea ver su historial medico: ");
                    int dniHistorial = scanner.nextInt();
                    gestor.mostrarHistorial(dniHistorial);
                    break;
                case 5:
                    System.out.println("Ingrese el dni del paciente al cual desea agregar un nuevo historial: ");
                    int dniNuevoHistorial = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese el historial que desea agregar con el siguiente formato: dd/MM/yyyy - observaciones");
                    String texto = scanner.nextLine();
                    gestor.agregarHistorial(dniNuevoHistorial, texto);
                    break;
                case 6:
                    gestor.guardarPacientes();
                    break;
                case 7:
                    gestor.cargarPacientes();
                    if (gestor != null) {
                        System.out.println("Pacientes cargados desde archivo exitosamente.");
                    } else {
                        System.out.println("Error al cargar los pacientes desde el archivo.");
                    }
                    break;
                case 8:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, elija una opcion valida");
            }
        } while(opcion != 8);
        scanner.close();
    }
}

class Persona implements Serializable{
    private String nombre;
    private int dni;
    private String nacimiento;

    public Persona(String nombre, int dni, String nacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }
}

class Doctor extends Persona implements Serializable{
    private String especialidad;

    public Doctor(String nombre, int dni, String nacimiento, String especialidad) {
        super(nombre, dni, nacimiento);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}

interface verHistorialDeEventos {
    void verHistorialDeEventos();
}
class Paciente extends Persona implements verHistorialDeEventos, Serializable{
    private int telefono;
    private int tipoSangre;
    private ArrayList<String> historial = new ArrayList <>();

    public Paciente(String nombre, int dni, String nacimiento, int telefono, int tipoSangre, ArrayList<String> historial) {
        super(nombre, dni, nacimiento);
        this.telefono = telefono;
        this.tipoSangre = tipoSangre;
        this.historial = historial;
    }

    @Override
    public void verHistorialDeEventos() {
        Iterator<String> iteradorDeHistorial = historial.iterator();
        while (iteradorDeHistorial.hasNext()) {
            String evento = iteradorDeHistorial.next();
            System.out.println(evento);
        }
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(int tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public ArrayList<String> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<String> historial) {
        this.historial = historial;
    }
}
class Gestor implements Serializable{
    private List<Doctor> doctores;
    private List<Paciente> pacientes;

    public Gestor() {
        this.doctores = new ArrayList <>();
        this.pacientes = new ArrayList <>();
    }

    public void agregarDoctor(Doctor doctor){
        doctores.add(doctor);
    }
    public void mostrarDoctores(){
        System.out.println("Doctores: ");
        for(Doctor doctor: doctores){
            System.out.println(doctor.getNombre());
        }
    }

    public void agregarPaciente(Paciente paciente){

        pacientes.add(paciente);
    }
    public void actualizarPaciente(){
        System.out.println("Ingrese el DNI del paciente al cual desea actualizar su información: ");
        Scanner scanner = new Scanner(System.in);
        int dniDelPacienteAActualizar = scanner.nextInt();
        scanner.nextLine(); // Consumir el carácter de nueva línea

        Iterator iteradorDePacientes = pacientes.iterator();

        while (iteradorDePacientes.hasNext()) {
            Paciente pacienteObtenido = (Paciente) iteradorDePacientes.next();
            if (pacienteObtenido.getDni() == dniDelPacienteAActualizar) {
                System.out.println("Ingrese el nuevo nombre del paciente");
                String nuevoNombre = scanner.nextLine();

                System.out.println("Ingrese el nuevo tipo de sangre del paciente");
                int nuevoTipoSangre = scanner.nextInt();
                scanner.nextLine(); // Consumir el carácter de nueva línea

                System.out.println("Ingrese el nuevo número de teléfono del paciente");
                int nuevoTelefono = scanner.nextInt();
                scanner.nextLine(); // Consumir el carácter de nueva línea

                pacienteObtenido.setNombre(nuevoNombre);
                pacienteObtenido.setTipoSangre(nuevoTipoSangre);
                pacienteObtenido.setTelefono(nuevoTelefono);
            }
        }
    }

    public void mostrarHistorial(int dni){
        System.out.println("HISTORIAL MÉDICO: ");

        for(Paciente paciente: pacientes){
            if (paciente.getDni() == dni){
                paciente.verHistorialDeEventos();
            } else {
                System.out.println("No se encontro un paciente con ese DNI");
            }
        }
    }
    public void agregarHistorial(int dni, String texto){
        for (Paciente paciente: pacientes){
            if (paciente.getDni() == dni){
                paciente.getHistorial().add(texto);
            } else {
                System.out.println("No se encontro un paciente con ese DNI");
            }
        }
    }

    public void guardarPacientes() {
        try {
            FileOutputStream objetoFileOS = new FileOutputStream("archivo.txt");
            ObjectOutputStream flujoSalida = new ObjectOutputStream(objetoFileOS);
            flujoSalida.writeObject(pacientes);
            flujoSalida.close();
        } catch (Exception exception) {
            System.out.println("Ocurrió un error de tipo: "+exception);
        }
    }
    public void cargarPacientes() {
        try {
            FileInputStream objetoFileIS = new FileInputStream("archivo.txt");
            ObjectInputStream flujoEntrada = new ObjectInputStream(objetoFileIS);
            pacientes = (ArrayList<Paciente>) flujoEntrada.readObject();
            flujoEntrada.close();

        } catch (Exception exception) {
            System.out.println("Ocurrió un error de tipo: "+exception);
        }
    }
}
