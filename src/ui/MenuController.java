package ui;

import utils.Validations;
import java.util.Scanner;

public class MenuController {
    // Atributo global
    private Scanner read;
    public MenuController() {
        this.read = new Scanner(System.in);
    }

    public void start() {
        int option = 0;

        while (option != 8) {
            System.out.println("=== GESTION DE LISTA DOBLEMENTE ENLAZADA ===");
            System.out.println("1. Inicializar / Borrar Lista");
            System.out.println("2. Mostrar Lista");
            System.out.println("3. Buscar");
            System.out.println("4. Insertar");
            System.out.println("5. Eliminar");
            System.out.println("6. Modificar");
            System.out.println("7. Creditos");
            System.out.println("8. Salir");

            //  validación genérica de números positivos
            option = Validations.pedirEnteroPositivo(read, "Elija una opcion: ");

            switch (option) {
                case 1:
                    System.out.println("--- INICIALIZAR LISTA ---");
                    // logica Dani
                    break;
                case 2:
                    System.out.println("--- MOSTRAR LISTA ---");
                    // logica  Emi
                    break;
                case 3:
                    handleBuscar(read);
                    break;
                case 4:
                    handleInsertar(read);
                    break;
                case 5:
                    handleEliminar(read);
                    break;
                case 6:
                    handleModificar(read);
                    break;
                case 7:
                    showCredits();
                    break;
                case 8:
                    System.out.println(" Hasta luego...");
                    break;
                default:
                    System.out.println(" Opcion no valida. Escoja del 1 al 8.");
            }
        }
        this.read.close();
    }


    // MÉTODOS DELEGADOS

    private void handleInsertar() {
        System.out.println("--- INSERTAR LETRA ---");
        char letra = Validations.pedirLetra(this.read, "Ingresa la letra a insertar: ");
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa la prioridad (1 o 2): ");
        System.out.println("Lógica de inserción pendiente para " + letra + "...");
    }

    private void handleBuscar() {
        System.out.println("--- BUSCAR LETRA ---");
        // lógica  Validations
    }

    private static void handleEliminar(Scanner read) {
        System.out.println("--- ELIMINAR LETRA ---");

    }

    private static void handleModificar(Scanner read) {
        System.out.println("--- MODIFICAR LETRA ---");

    }

    private static void showCredits() {
        System.out.println("--- CREDITOS ---");
        System.out.println("Materia: Estructuras de Datos");
        System.out.println("Integrantes:");
        System.out.println("- Daniel [ Matricula]");
        System.out.println("- Emiliano Lara");
        System.out.println("- Juan Diego Arroyo Bucio [55420050]");
    }

}