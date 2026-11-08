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
            option = Validations.pedirenteropositivo(read, "Elija una opcion: ");

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
        char letra = Validations.pedirLetra(this.read, "Ingresa la letra que deseas buscar: ");
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa en que prioridad buscarla (1 o 2): ");


        System.out.println("Buscando la letra '" + letra + "' en la prioridad " + prioridad + "...");
    }

    private void handleEliminar() {
        System.out.println("--- ELIMINAR LETRA ---");
        //
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa de que prioridad deseas eliminar (1 o 2): ");

        if (prioridad == 1) {
            System.out.println("Eliminando el primer nodo de la prioridad 1 (como Cola)...");

        } else {
            char letra = Validations.pedirLetra(this.read, "Ingresa la letra exacta que deseas eliminar: ");
            System.out.println("Buscando y eliminando la letra '" + letra + "' de la prioridad 2...");

        }
    }

    private void handleModificar() {
        System.out.println("--- MODIFICAR LETRA ---");
        int prioridad = Validations.pedirPrioridad(this.read, "Ingresa en que prioridad esta la letra a modificar (1 o 2): ");

        char letraVieja = Validations.pedirLetra(this.read, "Ingresa la letra ACTUAL que deseas cambiar: ");
        char letraNueva = Validations.pedirLetra(this.read, "Ingresa la NUEVA letra: ");

        System.out.println("Intentando modificar '" + letraVieja + "' por '" + letraNueva + "' en prioridad " + prioridad + "...");

    }

    private static void showCredits() {
        System.out.println("--- CREDITOS ---");
        System.out.println("Materia: Estructuras de Datos");
        System.out.println("Integrantes:");
        System.out.println("- Daniel Martinez Rodriguez [25420122]");
        System.out.println("- Emiliano Lara");
        System.out.println("- Juan Diego Arroyo Bucio [55420050]");
    }

}