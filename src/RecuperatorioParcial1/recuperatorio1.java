package RecuperatorioParcial1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class recuperatorio1 {
    public static void main(String[] args) {

        ArrayList<Profesor> profesores = new ArrayList<>();
        Profesor profesor1 = new Profesor("Juan", "Perez", 1, "01/01/1980", "especialidad1");
        Profesor profesor2 = new Profesor("Maria", "Gomez", 2, "05/05/1985", "especialidad1");
        Profesor profesor3 = new Profesor("Luis", "Martinez", 3, "10/10/1975", "especialidad1");
        profesores.add(profesor1);
        profesores.add(profesor2);
        profesores.add(profesor3);


        ArrayList<Materia> materias = new ArrayList<>();
        Materia materia1 = new Materia("materia1", 1);
        Materia materia2 = new Materia("materia2", 2);
        Materia materia3 = new Materia("materia3", 3);
        Materia materia4 = new Materia("materia4", 1);
        materias.add(materia1);
        materias.add(materia2);
        materias.add(materia3);
        materias.add(materia4);


        ArrayList<Materia> materiasEstudiante1 = new ArrayList<>();
        materiasEstudiante1.add(materia1);
        materiasEstudiante1.add(materia2);
        ArrayList<Materia> materiasEstudiante2 = new ArrayList<>();
        materiasEstudiante2.add(materia1);
        materiasEstudiante2.add(materia3);
        ArrayList<Materia> materiasEstudiante3 = new ArrayList<>();
        materiasEstudiante3.add(materia3);
        materiasEstudiante3.add(materia2);

        Estudiante estudiante1 = new Estudiante("Carlos", "Rodriguez", 1, "10/10/1995", materiasEstudiante1);
        Estudiante estudiante2 = new Estudiante("Ana", "Lopez", 2, "15/08/1998", materiasEstudiante2);
        Estudiante estudiante3 = new Estudiante("Pedro", "Garcia", 3, "20/05/2000", materiasEstudiante3);

        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(estudiante1);
        estudiantes.add(estudiante2);
        estudiantes.add(estudiante3);

        Gestor gestor = new Gestor(profesores, estudiantes, materias);


        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú: \n1. Listar Profesores. \n2. Listar Estudiantes. \n3. Listar Materias. \n4. Registrar un nuevo profesor. \n5. Registrar un nuevo estudiante. \n6. Asignar profesor a materia. \n7. Inscribir estudiante a materia. \n8. Guardar Información en Archivo. \n9. Cargar Información desde Archivo. \n10. Salir.");
            opcion = scanner.nextInt();

            switch (opcion){
                case 1: gestor.listarProfesores();
                    break;
                case 2: gestor.listarEstudiantes();
                    break;
                case 3: gestor.listarMaterias();
                    break;
                case 4:
                    System.out.println("Ingrese el nombre del nuevo profesor");
                    String nombreNuevoProfesor = scanner.next();
                    System.out.println("Ingrese el apellido del nuevo profesor");
                    String apellidoNuevoProfesor = scanner.next();
                    System.out.println("Ingrese el dni del nuevo profesor");
                    int dniNuevoProfesor = scanner.nextInt();
                    System.out.println("Ingrese la fecha de nacimiento del nuevo profesor, en formato dd/mm/aaaa");
                    String nacimientoNuevoProfesor = scanner.next();
                    System.out.println("Ingrese la especialidad del nuevo profesor");
                    String especialidadNuevoProfesor = scanner.next();
                    Profesor profesorNuevo = new Profesor(nombreNuevoProfesor, apellidoNuevoProfesor, dniNuevoProfesor, nacimientoNuevoProfesor, especialidadNuevoProfesor);
                    gestor.agregarProfesor(profesorNuevo);
                    break;
                case 5:
                    System.out.println("Ingrese el nombre del nuevo estudiante");
                    String nombreNuevoEstudiante = scanner.next();
                    System.out.println("Ingrese el apellido del nuevo estudiante");
                    String apellidoNuevoEstudiante = scanner.next();
                    System.out.println("Ingrese el dni del nuevo estudiante");
                    int dniNuevoEstudiante = scanner.nextInt();
                    System.out.println("Ingrese la fecha de nacimiento del nuevo estudiante, en formato dd/mm/aaaa");
                    String nacimientoNuevoEstudiante = scanner.next();
                    ArrayList<Materia> materiasDelNuevoEstudiante = new ArrayList<>();
                    Estudiante estudiante = new Estudiante(nombreNuevoEstudiante, apellidoNuevoEstudiante, dniNuevoEstudiante, nacimientoNuevoEstudiante, materiasDelNuevoEstudiante);
                    gestor.agregarEstudiante(estudiante);
                    break;

                case 6:
                    System.out.println("Ingrese el dni del profesor");
                    int dniProfesor = scanner.nextInt();
                    System.out.println("Ingrese el nombre de la materia a la cual asignarle el profesor");
                    String materiaAAsignarProfesor = scanner.next();
                    gestor.asignarProfesorAMateria(dniProfesor, materiaAAsignarProfesor);
                    break;
                case 7:
                    System.out.println("Ingrese el dni del estudiante");
                    int dniEstudiante = scanner.nextInt();
                    System.out.println("Ingrese el nombre de la materia a la cual quiere inscribirse el estudiante");
                    String materiaAAsignarEstudiante = scanner.next();
                    gestor.asignarMateriaAAlumno(dniEstudiante, materiaAAsignarEstudiante);
                    break;
                case 8:
                    gestor.guardar();
                    break;
                case 9:
                    gestor.cargar();
                    break;
                case 10: System.out.println("Chau");
                    break;
                default:
                    System.out.println("Ingrese un numero valido");
                    break;
            }
        } while(opcion != 10);

    }
}


class Persona implements Serializable {
    private String nombre;
    private String apellido;
    private int dni;
    private String nacimiento;

    public Persona(String nombre, String apellido, int dni, String nacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nacimiento = nacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

class Profesor extends Persona implements Serializable{
    private String especialidad;

    public Profesor(String nombre, String apellido, int dni, String nacimiento, String especialidad) {
        super(nombre, apellido, dni, nacimiento);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}

class Estudiante extends Persona implements Serializable{
    private ArrayList<Materia> materiasiInscriptas = new ArrayList <>();

    public Estudiante(String nombre, String apellido, int dni, String nacimiento, ArrayList<Materia> materiasiInscriptas) {
        super(nombre, apellido, dni, nacimiento);
        this.materiasiInscriptas = materiasiInscriptas;
    }

    public ArrayList<Materia> getMateriasiInscriptas() {
        return materiasiInscriptas;
    }

    public void setMateriasiInscriptas(ArrayList<Materia> materiasiInscriptas) {
        this.materiasiInscriptas = materiasiInscriptas;
    }

    public void listarMateriasInscriptas() {
        System.out.println("\nMaterias inscriptas por " + getNombre());
        if (materiasiInscriptas.isEmpty()) {
            System.out.println("No tienes materias inscriptas.");
        } else {
            for (Materia materia : materiasiInscriptas) {
                System.out.println(materia.getNombreMateria());
            }
        }
    }
}

class Materia implements Serializable{
    private String nombreMateria;
    private int profesorACargo;

    public Materia(String nombreMateria, int profesorACargo) {
        this.nombreMateria = nombreMateria;
        this.profesorACargo = profesorACargo;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getProfesorACargo() {
        return profesorACargo;
    }

    public void setProfesorACargo(int profesorACargo) {
        this.profesorACargo = profesorACargo;
    }
}
class Gestor implements Serializable{
    private ArrayList<Profesor> profesores;
    private ArrayList<Estudiante> estudiantes;
    private ArrayList<Materia> materias;

    public Gestor(ArrayList<Profesor> profesores, ArrayList<Estudiante> estudiantes, ArrayList<Materia> materias) {
        this.profesores = profesores;
        this.estudiantes = estudiantes;
        this.materias = materias;
    }

    public void listarProfesores(){
        System.out.println("\nprofesores: ");
        for(Profesor profesor: profesores){
            System.out.println(profesor.getNombre());
        }
    }
    public void listarEstudiantes(){
        System.out.println("\nEstudiantes: ");
        for(Estudiante estudiante: estudiantes){
            System.out.println(estudiante.getNombre());
        }
    }
    public void listarMaterias(){
        System.out.println("\nMaterias: ");
        for(Materia materia: materias){
            System.out.println(materia.getNombreMateria());
        }
    }
    public void agregarProfesor(Profesor profesor) {
        profesores.add(profesor);
    }

    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public void asignarProfesorAMateria(int dniProfesor, String nombreMateria) {
        Profesor profesorAAsignar = null;
        for (Profesor profesor : profesores) {
            if (profesor.getDni() == dniProfesor) {
                profesorAAsignar = profesor;
                break;
            }
        }

        Materia materiaAAsignar = null;
        for (Materia materia : materias) {
            if (materia.getNombreMateria().equals(nombreMateria)) {
                materiaAAsignar = materia;
                break;
            }
        }

        if (profesorAAsignar != null && materiaAAsignar != null) {
            materiaAAsignar.setProfesorACargo(dniProfesor);
            System.out.println("El profesor "+profesorAAsignar.getNombre()+" está a cargo de la materia "+materiaAAsignar.getNombreMateria());
        } else {
            System.out.println("No existe el profesor o la materia");
        }
    }


    public void asignarMateriaAAlumno(int dniEstudiante, String nombreMateria) {

        Estudiante estudianteAInscribir = null;
        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getDni() == dniEstudiante) {
                estudianteAInscribir = estudiante;
                break;
            }
        }

        Materia materiaAInscribir = null;
        for (Materia materia : materias) {
            if (materia.getNombreMateria().equals(nombreMateria)) {
                materiaAInscribir = materia;
                break;
            }
        }

        if (estudianteAInscribir != null && materiaAInscribir != null) {
            if (!estudianteAInscribir.getMateriasiInscriptas().contains(nombreMateria)) {
                estudianteAInscribir.getMateriasiInscriptas().add(materiaAInscribir);
                System.out.println("Estudiante "+estudianteAInscribir.getNombre()+" inscrito en la "+materiaAInscribir);
            } else {
                System.out.println("Estudiante ya inscrito en la materia.");
            }
        } else {
            System.out.println("Estudiante o materia no encontrados.");
        }
    }
    public void guardar() {
        try (ObjectOutputStream flujoSalida = new ObjectOutputStream(new FileOutputStream("informacion.txt"))) {
            flujoSalida.writeObject(profesores);
            flujoSalida.writeObject(estudiantes);
            flujoSalida.writeObject(materias);
            System.out.println("Información guardada correctamente.");
        } catch (Exception exception) {
            System.out.println("Ocurrió un error de tipo: "+exception);
        }
    }

    public void cargar() {
        try (ObjectInputStream flujoEntrada = new ObjectInputStream(new FileInputStream("informacion.txt"))) {
            profesores = (ArrayList<Profesor>) flujoEntrada.readObject();
            estudiantes = (ArrayList<Estudiante>) flujoEntrada.readObject();
            materias = (ArrayList<Materia>) flujoEntrada.readObject();
            System.out.println("Información cargada correctamente.");
        } catch (Exception exception) {
            System.out.println("Ocurrió un error de tipo: "+exception);
        }
    }
}